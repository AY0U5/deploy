package ma.zyn.app.unit.ws.facade.admin.projet;

import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique;
import ma.zyn.app.service.impl.admin.projet.DossierProjetExigenceAppliqueAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.projet.DossierProjetExigenceAppliqueRestAdmin;
import ma.zyn.app.ws.converter.projet.DossierProjetExigenceAppliqueConverter;
import ma.zyn.app.ws.dto.projet.DossierProjetExigenceAppliqueDto;
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
public class DossierProjetExigenceAppliqueRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private DossierProjetExigenceAppliqueAdminServiceImpl service;
    @Mock
    private DossierProjetExigenceAppliqueConverter converter;

    @InjectMocks
    private DossierProjetExigenceAppliqueRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllDossierProjetExigenceAppliqueTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<DossierProjetExigenceAppliqueDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<DossierProjetExigenceAppliqueDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveDossierProjetExigenceAppliqueTest() throws Exception {
        // Mock data
        DossierProjetExigenceAppliqueDto requestDto = new DossierProjetExigenceAppliqueDto();
        DossierProjetExigenceApplique entity = new DossierProjetExigenceApplique();
        DossierProjetExigenceApplique saved = new DossierProjetExigenceApplique();
        DossierProjetExigenceAppliqueDto savedDto = new DossierProjetExigenceAppliqueDto();

        // Mock the converter to return the dossierProjetExigenceApplique object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved dossierProjetExigenceApplique DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<DossierProjetExigenceAppliqueDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        DossierProjetExigenceAppliqueDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved dossierProjetExigenceApplique DTO
        assertEquals(savedDto.getCommentaire(), responseBody.getCommentaire());
        assertEquals(savedDto.getTauxPrecision(), responseBody.getTauxPrecision());
        assertEquals(savedDto.getPages(), responseBody.getPages());
    }

}
