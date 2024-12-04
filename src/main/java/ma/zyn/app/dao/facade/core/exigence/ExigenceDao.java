package ma.zyn.app.dao.facade.core.exigence;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.exigence.Exigence;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.exigence.Exigence;
import java.util.List;


@Repository
public interface ExigenceDao extends AbstractRepository<Exigence,Long>  {
    Exigence findByReference(String reference);
    int deleteByReference(String reference);

    List<Exigence> findByFamilleExigenceCode(String code);
    List<Exigence> findByFamilleExigenceId(Long id);
    int deleteByFamilleExigenceId(Long id);
    int deleteByFamilleExigenceCode(String code);
    long countByFamilleExigenceCode(String code);

    @Query("SELECT NEW Exigence(item.id,item.libelle) FROM Exigence item")
    List<Exigence> findAllOptimized();

}
