package ma.zyn.app.ws.dto.projet;/*
package ma.zyn.app.ws.dto.projet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.zyn.app.bean.core.exigence.Exigence;
import ma.zyn.app.bean.core.projet.FileInfo;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFileDto {



    private String documentContent;

    private String exigence;

    public String getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }

    public String getExigence() {
        return exigence;
    }

    public void setExigence(String exigence) {
        this.exigence = exigence;
    }

public ProjectFileDto toDto (FileInfo fileInfo, Exigence exigence) {
        return ProjectFileDto.builder()
                .documentContent(fileInfo.getContent())
                .exigence(exigence.getDescription())
                .build();
    }


}
*/
