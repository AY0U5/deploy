package ma.zyn.app.dao.facade.core.projet;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.projet.DossierProjetExigence;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DossierProjetExigenceDao extends AbstractRepository<DossierProjetExigence,Long>  {

    List<DossierProjetExigence> findByExigenceId(Long id);
    int deleteByExigenceId(Long id);
    long countByExigenceReference(String reference);
    List<DossierProjetExigence> findByDossierProjetId(Long id);
    int deleteByDossierProjetId(Long id);
    long countByDossierProjetCode(String code);


}
