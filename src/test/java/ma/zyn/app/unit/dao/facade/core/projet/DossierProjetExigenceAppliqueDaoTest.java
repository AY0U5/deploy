package ma.zyn.app.unit.dao.facade.core.projet;

import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique;
import ma.zyn.app.dao.facade.core.projet.DossierProjetExigenceAppliqueDao;

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

import ma.zyn.app.bean.core.projet.DossierProjetDocument ;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat ;
import ma.zyn.app.bean.core.exigence.Exigence ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DossierProjetExigenceAppliqueDaoTest {

@Autowired
    private DossierProjetExigenceAppliqueDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        DossierProjetExigenceApplique entity = new DossierProjetExigenceApplique();
        entity.setId(id);
        underTest.save(entity);
        DossierProjetExigenceApplique loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        DossierProjetExigenceApplique entity = new DossierProjetExigenceApplique();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        DossierProjetExigenceApplique loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<DossierProjetExigenceApplique> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<DossierProjetExigenceApplique> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        DossierProjetExigenceApplique given = constructSample(1);
        DossierProjetExigenceApplique saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
