package ma.zyn.app.unit.dao.facade.core.projet;

import ma.zyn.app.bean.core.projet.DossierProjet;
import ma.zyn.app.dao.facade.core.projet.DossierProjetDao;

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
public class DossierProjetDaoTest {

@Autowired
    private DossierProjetDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        DossierProjet entity = new DossierProjet();
        entity.setCode(code);
        underTest.save(entity);
        DossierProjet loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        DossierProjet loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        DossierProjet entity = new DossierProjet();
        entity.setId(id);
        underTest.save(entity);
        DossierProjet loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        DossierProjet entity = new DossierProjet();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        DossierProjet loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<DossierProjet> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<DossierProjet> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        DossierProjet given = constructSample(1);
        DossierProjet saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private DossierProjet constructSample(int i) {
		DossierProjet given = new DossierProjet();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
