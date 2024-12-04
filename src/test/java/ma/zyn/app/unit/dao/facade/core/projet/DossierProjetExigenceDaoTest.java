package ma.zyn.app.unit.dao.facade.core.projet;

import ma.zyn.app.bean.core.projet.DossierProjetExigence;
import ma.zyn.app.dao.facade.core.projet.DossierProjetExigenceDao;

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
import ma.zyn.app.bean.core.exigence.Exigence ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DossierProjetExigenceDaoTest {

@Autowired
    private DossierProjetExigenceDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        DossierProjetExigence entity = new DossierProjetExigence();
        entity.setId(id);
        underTest.save(entity);
        DossierProjetExigence loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        DossierProjetExigence entity = new DossierProjetExigence();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        DossierProjetExigence loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<DossierProjetExigence> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<DossierProjetExigence> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        DossierProjetExigence given = constructSample(1);
        DossierProjetExigence saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
