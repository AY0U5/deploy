package ma.zyn.app.unit.ws.facade.admin.projet;

import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import ma.zyn.app.service.impl.admin.projet.DossierProjetDocumentAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.projet.DossierProjetDocumentRestAdmin;
import ma.zyn.app.ws.converter.projet.DossierProjetDocumentConverter;
import ma.zyn.app.ws.dto.projet.DossierProjetDocumentDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DossierProjetDocumentRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private DossierProjetDocumentAdminServiceImpl service;
    @Mock
    private DossierProjetDocumentConverter converter;

    @InjectMocks
    private DossierProjetDocumentRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllDossierProjetDocumentTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<DossierProjetDocumentDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<DossierProjetDocumentDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveDossierProjetDocumentTest() throws Exception {
        // Mock data
        DossierProjetDocumentDto requestDto = new DossierProjetDocumentDto();
        DossierProjetDocument entity = new DossierProjetDocument();
        DossierProjetDocument saved = new DossierProjetDocument();
        DossierProjetDocumentDto savedDto = new DossierProjetDocumentDto();

        // Mock the converter to return the dossierProjetDocument object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved dossierProjetDocument DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<DossierProjetDocumentDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        DossierProjetDocumentDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved dossierProjetDocument DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getPath(), responseBody.getPath());
        assertEquals(savedDto.getContent(), responseBody.getContent());
    }

}
