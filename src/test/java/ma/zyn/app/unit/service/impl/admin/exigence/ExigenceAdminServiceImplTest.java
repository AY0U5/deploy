package ma.zyn.app.unit.service.impl.admin.exigence;

import ma.zyn.app.bean.core.exigence.Exigence;
import ma.zyn.app.dao.facade.core.exigence.ExigenceDao;
import ma.zyn.app.service.impl.admin.exigence.ExigenceAdminServiceImpl;

import ma.zyn.app.bean.core.exigence.FamilleExigence ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class ExigenceAdminServiceImplTest {

    @Mock
    private ExigenceDao repository;
    private AutoCloseable autoCloseable;
    private ExigenceAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ExigenceAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllExigence() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveExigence() {
        // Given
        Exigence toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteExigence() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetExigenceById() {
        // Given
        Long idToRetrieve = 1L; // Example Exigence ID to retrieve
        Exigence expected = new Exigence(); // You need to replace Exigence with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(Optional.of(expected));

        // When
        Exigence result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Exigence constructSample(int i) {
		Exigence given = new Exigence();
        given.setReference("reference-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setFamilleExigence(new FamilleExigence(1L));
        return given;
    }

}
