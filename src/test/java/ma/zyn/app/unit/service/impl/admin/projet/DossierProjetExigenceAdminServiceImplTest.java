package ma.zyn.app.unit.service.impl.admin.projet;

import ma.zyn.app.bean.core.projet.DossierProjetExigence;
import ma.zyn.app.dao.facade.core.projet.DossierProjetExigenceDao;
import ma.zyn.app.service.impl.admin.projet.DossierProjetExigenceAdminServiceImpl;

import ma.zyn.app.bean.core.projet.DossierProjet ;
import ma.zyn.app.bean.core.exigence.Exigence ;
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
class DossierProjetExigenceAdminServiceImplTest {

    @Mock
    private DossierProjetExigenceDao repository;
    private AutoCloseable autoCloseable;
    private DossierProjetExigenceAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DossierProjetExigenceAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllDossierProjetExigence() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveDossierProjetExigence() {
        // Given
        DossierProjetExigence toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteDossierProjetExigence() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetDossierProjetExigenceById() {
        // Given
        Long idToRetrieve = 1L; // Example DossierProjetExigence ID to retrieve
        DossierProjetExigence expected = new DossierProjetExigence(); // You need to replace DossierProjetExigence with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(Optional.of(expected));

        // When
        DossierProjetExigence result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private DossierProjetExigence constructSample(int i) {
		DossierProjetExigence given = new DossierProjetExigence();
        given.setExigence(new Exigence(1L));
        given.setDossierProjet(new DossierProjet(1L));
        given.setCommentaire("commentaire-"+i);
        given.setEnabled(false);
        return given;
    }

}
