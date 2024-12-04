package ma.zyn.app.unit.dao.facade.core.exigence;

import ma.zyn.app.bean.core.exigence.Exigence;
import ma.zyn.app.dao.facade.core.exigence.ExigenceDao;

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

import ma.zyn.app.bean.core.exigence.FamilleExigence ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ExigenceDaoTest {

@Autowired
    private ExigenceDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByReference(){
        String reference = "reference-1";
        Exigence entity = new Exigence();
        entity.setReference(reference);
        underTest.save(entity);
        Exigence loaded = underTest.findByReference(reference);
        assertThat(loaded.getReference()).isEqualTo(reference);
    }

    @Test
    void shouldDeleteByReference() {
        String reference = "reference-12345678";
        int result = underTest.deleteByReference(reference);

        Exigence loaded = underTest.findByReference(reference);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Exigence entity = new Exigence();
        entity.setId(id);
        underTest.save(entity);
        Exigence loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Exigence entity = new Exigence();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Exigence loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Exigence> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Exigence> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Exigence given = constructSample(1);
        Exigence saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Exigence constructSample(int i) {
		Exigence given = new Exigence();
        given.setReference("reference-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setFamilleExigence(new FamilleExigence(1L));
        return given;
    }

}
