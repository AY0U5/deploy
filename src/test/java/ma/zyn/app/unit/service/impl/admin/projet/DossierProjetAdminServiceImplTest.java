package ma.zyn.app.unit.service.impl.admin.projet;

import ma.zyn.app.bean.core.projet.DossierProjet;
import ma.zyn.app.dao.facade.core.projet.DossierProjetDao;
import ma.zyn.app.service.impl.admin.projet.DossierProjetAdminServiceImpl;

import ma.zyn.app.bean.core.projet.DossierProjetDocument ;
import ma.zyn.app.bean.core.projet.DossierProjetExigence ;
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
class DossierProjetAdminServiceImplTest {

    @Mock
    private DossierProjetDao repository;
    private AutoCloseable autoCloseable;
    private DossierProjetAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DossierProjetAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllDossierProjet() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveDossierProjet() {
        // Given
        DossierProjet toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteDossierProjet() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetDossierProjetById() {
        // Given
        Long idToRetrieve = 1L; // Example DossierProjet ID to retrieve
        DossierProjet expected = new DossierProjet(); // You need to replace DossierProjet with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(Optional.of(expected));

        // When
        DossierProjet result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private DossierProjet constructSample(int i) {
		DossierProjet given = new DossierProjet();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        List<DossierProjetExigence> dossierProjetExigences = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                DossierProjetExigence element = new DossierProjetExigence();
                                                element.setId((long)id);
                                                element.setExigence(new Exigence(Long.valueOf(1)));
                                                element.setDossierProjet(new DossierProjet(Long.valueOf(2)));
                                                element.setCommentaire("commentaire"+id);
                                                element.setEnabled(true);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setDossierProjetExigences(dossierProjetExigences);
        List<DossierProjetDocument> dossierProjetDocuments = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                DossierProjetDocument element = new DossierProjetDocument();
                                                element.setId((long)id);
                                                element.setDossierProjet(new DossierProjet(Long.valueOf(1)));
                                                element.setCode("code"+id);
                                                element.setLibelle("libelle"+id);
                                                element.setPath("path"+id);
                                                element.setContent("content"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setDossierProjetDocuments(dossierProjetDocuments);
        return given;
    }

}
