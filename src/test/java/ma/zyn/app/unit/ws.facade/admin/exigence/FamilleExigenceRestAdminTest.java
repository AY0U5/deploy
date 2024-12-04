package ma.zyn.app.unit.ws.facade.admin.exigence;

import ma.zyn.app.bean.core.exigence.FamilleExigence;
import ma.zyn.app.service.impl.admin.exigence.FamilleExigenceAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.exigence.FamilleExigenceRestAdmin;
import ma.zyn.app.ws.converter.exigence.FamilleExigenceConverter;
import ma.zyn.app.ws.dto.exigence.FamilleExigenceDto;
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
public class FamilleExigenceRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private FamilleExigenceAdminServiceImpl service;
    @Mock
    private FamilleExigenceConverter converter;

    @InjectMocks
    private FamilleExigenceRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllFamilleExigenceTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<FamilleExigenceDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<FamilleExigenceDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveFamilleExigenceTest() throws Exception {
        // Mock data
        FamilleExigenceDto requestDto = new FamilleExigenceDto();
        FamilleExigence entity = new FamilleExigence();
        FamilleExigence saved = new FamilleExigence();
        FamilleExigenceDto savedDto = new FamilleExigenceDto();

        // Mock the converter to return the familleExigence object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved familleExigence DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<FamilleExigenceDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        FamilleExigenceDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved familleExigence DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getStyle(), responseBody.getStyle());
    }

}
