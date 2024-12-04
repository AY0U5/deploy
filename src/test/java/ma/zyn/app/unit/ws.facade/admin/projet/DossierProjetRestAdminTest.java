package ma.zyn.app.unit.ws.facade.admin.projet;

import ma.zyn.app.bean.core.projet.DossierProjet;
import ma.zyn.app.service.impl.admin.projet.DossierProjetAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.projet.DossierProjetRestAdmin;
import ma.zyn.app.ws.converter.projet.DossierProjetConverter;
import ma.zyn.app.ws.dto.projet.DossierProjetDto;
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
public class DossierProjetRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private DossierProjetAdminServiceImpl service;
    @Mock
    private DossierProjetConverter converter;

    @InjectMocks
    private DossierProjetRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllDossierProjetTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<DossierProjetDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<DossierProjetDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveDossierProjetTest() throws Exception {
        // Mock data
        DossierProjetDto requestDto = new DossierProjetDto();
        DossierProjet entity = new DossierProjet();
        DossierProjet saved = new DossierProjet();
        DossierProjetDto savedDto = new DossierProjetDto();

        // Mock the converter to return the dossierProjet object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved dossierProjet DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<DossierProjetDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        DossierProjetDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved dossierProjet DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
