package ma.zyn.app.unit.service.impl.admin.projet;

import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique;
import ma.zyn.app.dao.facade.core.projet.DossierProjetExigenceAppliqueDao;
import ma.zyn.app.service.impl.admin.projet.DossierProjetExigenceAppliqueAdminServiceImpl;

import ma.zyn.app.bean.core.projet.DossierProjetDocument ;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat ;
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
class DossierProjetExigenceAppliqueAdminServiceImplTest {

    @Mock
    private DossierProjetExigenceAppliqueDao repository;
    private AutoCloseable autoCloseable;
    private DossierProjetExigenceAppliqueAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DossierProjetExigenceAppliqueAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllDossierProjetExigenceApplique() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveDossierProjetExigenceApplique() {
        // Given
        DossierProjetExigenceApplique toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteDossierProjetExigenceApplique() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetDossierProjetExigenceAppliqueById() {
        // Given
        Long idToRetrieve = 1L; // Example DossierProjetExigenceApplique ID to retrieve
        DossierProjetExigenceApplique expected = new DossierProjetExigenceApplique(); // You need to replace DossierProjetExigenceApplique with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(Optional.of(expected));

        // When
        DossierProjetExigenceApplique result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private DossierProjetExigenceApplique constructSample(int i) {
		DossierProjetExigenceApplique given = new DossierProjetExigenceApplique();
        given.setDossierProjetDocument(new DossierProjetDocument(1L));
        given.setExigence(new Exigence(1L));
        given.setCommentaire("commentaire-"+i);
        given.setDossierProjetExigenceEtat(new DossierProjetExigenceEtat(1L));
        given.setTauxPrecision(BigDecimal.TEN);
        given.setPages("pages-"+i);
        return given;
    }

}
