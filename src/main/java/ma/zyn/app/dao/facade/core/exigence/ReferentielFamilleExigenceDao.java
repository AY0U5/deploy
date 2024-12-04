package ma.zyn.app.dao.facade.core.exigence;

import ma.zyn.app.bean.core.exigence.ReferentielFamilleExigence;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReferentielFamilleExigenceDao extends AbstractRepository<ReferentielFamilleExigence,Long>  {
    ReferentielFamilleExigence findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ReferentielFamilleExigence(item.id,item.libelle) FROM ReferentielFamilleExigence item")
    List<ReferentielFamilleExigence> findAllOptimized();

}
