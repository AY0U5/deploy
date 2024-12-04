package ma.zyn.app.unit.dao.facade.core.projet;

import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import ma.zyn.app.dao.facade.core.projet.DossierProjetExigenceEtatDao;

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


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DossierProjetExigenceEtatDaoTest {

@Autowired
    private DossierProjetExigenceEtatDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        DossierProjetExigenceEtat entity = new DossierProjetExigenceEtat();
        entity.setCode(code);
        underTest.save(entity);
        DossierProjetExigenceEtat loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        DossierProjetExigenceEtat loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        DossierProjetExigenceEtat entity = new DossierProjetExigenceEtat();
        entity.setId(id);
        underTest.save(entity);
        DossierProjetExigenceEtat loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        DossierProjetExigenceEtat entity = new DossierProjetExigenceEtat();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        DossierProjetExigenceEtat loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<DossierProjetExigenceEtat> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<DossierProjetExigenceEtat> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        DossierProjetExigenceEtat given = constructSample(1);
        DossierProjetExigenceEtat saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private DossierProjetExigenceEtat constructSample(int i) {
		DossierProjetExigenceEtat given = new DossierProjetExigenceEtat();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setStyle("style-"+i);
        return given;
    }

}
