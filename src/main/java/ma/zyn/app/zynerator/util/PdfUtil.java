package ma.zyn.app.zynerator.util;

import io.jsonwebtoken.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class PdfUtil {


    public ResponseEntity<List<String>> uploadPdf(MultipartFile file) {
        List<String> pages = new ArrayList<>();
        try {
            if (file.getContentType().equals("application/pdf")) {
                // Process PDF
                try (PDDocument document = PDDocument.load(file.getInputStream())) {
                    PDFTextStripper pdfStripper = new PDFTextStripper();
                    for (int i = 1; i <= document.getNumberOfPages(); i++) {
                        pdfStripper.setStartPage(i);
                        pdfStripper.setEndPage(i);
                        pages.add(pdfStripper.getText(document).trim());
                    }
                } catch (java.io.IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
                // Process Word (DOCX)
                try (XWPFDocument docx = new XWPFDocument(file.getInputStream())) {
                    StringBuilder pageContent = new StringBuilder();

                    // Go through each body element in the DOCX
                    for (IBodyElement element : docx.getBodyElements()) {
                        if (element instanceof XWPFParagraph) {
                            XWPFParagraph paragraph = (XWPFParagraph) element;
                            String paragraphText = paragraph.getText().trim();

                            // Detect manual page breaks
                            CTPPr paragraphProperties = paragraph.getCTP().getPPr();
                            if (paragraphProperties != null && paragraphProperties.isSetPageBreakBefore()) {
                                // Save the current page and start a new one if there's a page break
                                if (pageContent.length() > 0) {
                                    pages.add(pageContent.toString().trim());
                                    pageContent.setLength(0); // Reset the buffer
                                }
                            }

                            // Append the paragraph text to the current page content
                            if (!paragraphText.isEmpty()) {
                                pageContent.append(paragraphText).append("\n");
                            }
                        }
                        // Handle tables in DOCX files
                        else if (element instanceof XWPFTable) {
                            XWPFTable table = (XWPFTable) element;
                            for (XWPFTableRow row : table.getRows()) {
                                for (XWPFTableCell cell : row.getTableCells()) {
                                    pageContent.append(cell.getText()).append("\t");
                                }
                                pageContent.append("\n");  // Newline after each table row
                            }
                        }
                    }

                    // Add any remaining content as the last page
                    if (pageContent.length() > 0) {
                        pages.add(pageContent.toString().trim());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (java.io.IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(pages);
    }
}
