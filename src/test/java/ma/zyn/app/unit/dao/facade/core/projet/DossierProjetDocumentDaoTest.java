package ma.zyn.app.unit.dao.facade.core.projet;

import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import ma.zyn.app.dao.facade.core.projet.DossierProjetDocumentDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.zyn.app.bean.core.projet.DossierProjet ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DossierProjetDocumentDaoTest {

@Autowired
    private DossierProjetDocumentDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        DossierProjetDocument entity = new DossierProjetDocument();
        entity.setCode(code);
        underTest.save(entity);
        DossierProjetDocument loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        DossierProjetDocument loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        DossierProjetDocument entity = new DossierProjetDocument();
        entity.setId(id);
        underTest.save(entity);
        DossierProjetDocument loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        DossierProjetDocument entity = new DossierProjetDocument();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        DossierProjetDocument loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<DossierProjetDocument> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<DossierProjetDocument> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        DossierProjetDocument given = constructSample(1);
        DossierProjetDocument saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private DossierProjetDocument constructSample(int i) {
		DossierProjetDocument given = new DossierProjetDocument();
        given.setDossierProjet(new DossierProjet(1L));
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setPath("path-"+i);
        given.setContent("content-"+i);
        return given;
    }

}
