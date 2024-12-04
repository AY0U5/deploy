package ma.zyn.app.unit.service.impl.admin.projet;

import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import ma.zyn.app.dao.facade.core.projet.DossierProjetExigenceEtatDao;
import ma.zyn.app.service.impl.admin.projet.DossierProjetExigenceEtatAdminServiceImpl;

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
class DossierProjetExigenceEtatAdminServiceImplTest {

    @Mock
    private DossierProjetExigenceEtatDao repository;
    private AutoCloseable autoCloseable;
    private DossierProjetExigenceEtatAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DossierProjetExigenceEtatAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllDossierProjetExigenceEtat() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveDossierProjetExigenceEtat() {
        // Given
        DossierProjetExigenceEtat toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteDossierProjetExigenceEtat() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetDossierProjetExigenceEtatById() {
        // Given
        Long idToRetrieve = 1L; // Example DossierProjetExigenceEtat ID to retrieve
        DossierProjetExigenceEtat expected = new DossierProjetExigenceEtat(); // You need to replace DossierProjetExigenceEtat with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(Optional.of(expected));

        // When
        DossierProjetExigenceEtat result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private DossierProjetExigenceEtat constructSample(int i) {
		DossierProjetExigenceEtat given = new DossierProjetExigenceEtat();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
