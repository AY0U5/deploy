package ma.zyn.app.unit.ws.facade.admin.projet;

import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import ma.zyn.app.service.impl.admin.projet.DossierProjetExigenceEtatAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.projet.DossierProjetExigenceEtatRestAdmin;
import ma.zyn.app.ws.converter.projet.DossierProjetExigenceEtatConverter;
import ma.zyn.app.ws.dto.projet.DossierProjetExigenceEtatDto;
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
public class DossierProjetExigenceEtatRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private DossierProjetExigenceEtatAdminServiceImpl service;
    @Mock
    private DossierProjetExigenceEtatConverter converter;

    @InjectMocks
    private DossierProjetExigenceEtatRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllDossierProjetExigenceEtatTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<DossierProjetExigenceEtatDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<DossierProjetExigenceEtatDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveDossierProjetExigenceEtatTest() throws Exception {
        // Mock data
        DossierProjetExigenceEtatDto requestDto = new DossierProjetExigenceEtatDto();
        DossierProjetExigenceEtat entity = new DossierProjetExigenceEtat();
        DossierProjetExigenceEtat saved = new DossierProjetExigenceEtat();
        DossierProjetExigenceEtatDto savedDto = new DossierProjetExigenceEtatDto();

        // Mock the converter to return the dossierProjetExigenceEtat object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved dossierProjetExigenceEtat DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<DossierProjetExigenceEtatDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        DossierProjetExigenceEtatDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved dossierProjetExigenceEtat DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
    }

}
