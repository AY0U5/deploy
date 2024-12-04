package ma.zyn.app.unit.service.impl.admin.projet;

import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import ma.zyn.app.dao.facade.core.projet.DossierProjetDocumentDao;
import ma.zyn.app.service.impl.admin.projet.DossierProjetDocumentAdminServiceImpl;

import ma.zyn.app.bean.core.projet.DossierProjetDocument ;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat ;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique ;
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
class DossierProjetDocumentAdminServiceImplTest {

    @Mock
    private DossierProjetDocumentDao repository;
    private AutoCloseable autoCloseable;
    private DossierProjetDocumentAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DossierProjetDocumentAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllDossierProjetDocument() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveDossierProjetDocument() {
        // Given
        DossierProjetDocument toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteDossierProjetDocument() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetDossierProjetDocumentById() {
        // Given
        Long idToRetrieve = 1L; // Example DossierProjetDocument ID to retrieve
        DossierProjetDocument expected = new DossierProjetDocument(); // You need to replace DossierProjetDocument with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(Optional.of(expected));

        // When
        DossierProjetDocument result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private DossierProjetDocument constructSample(int i) {
		DossierProjetDocument given = new DossierProjetDocument();
        given.setDossierProjet(new DossierProjet(1L));
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setPath("path-"+i);
        given.setContent("content-"+i);
        List<DossierProjetExigenceApplique> dossierProjetExigenceAppliques = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                DossierProjetExigenceApplique element = new DossierProjetExigenceApplique();
                                                element.setId((long)id);
                                                element.setDossierProjetDocument(new DossierProjetDocument(Long.valueOf(1)));
                                                element.setExigence(new Exigence(Long.valueOf(2)));
                                                element.setCommentaire("commentaire"+id);
                                                element.setDossierProjetExigenceEtat(new DossierProjetExigenceEtat(Long.valueOf(4)));
                                                element.setTauxPrecision(new BigDecimal(5*10));
                                                element.setPages("pages"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setDossierProjetExigenceAppliques(dossierProjetExigenceAppliques);
        return given;
    }

}
