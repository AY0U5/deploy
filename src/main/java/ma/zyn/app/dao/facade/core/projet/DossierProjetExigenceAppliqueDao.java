package ma.zyn.app.dao.facade.core.projet;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DossierProjetExigenceAppliqueDao extends AbstractRepository<DossierProjetExigenceApplique,Long>  {

    List<DossierProjetExigenceApplique> findByDossierProjetDocumentId(Long id);
    int deleteByDossierProjetDocumentId(Long id);
    long countByDossierProjetDocumentCode(String code);
    List<DossierProjetExigenceApplique> findByExigenceId(Long id);
    int deleteByExigenceId(Long id);
    long countByExigenceReference(String reference);
    List<DossierProjetExigenceApplique> findByDossierProjetExigenceEtatCode(String code);
    List<DossierProjetExigenceApplique> findByDossierProjetExigenceEtatId(Long id);
    int deleteByDossierProjetExigenceEtatId(Long id);
    int deleteByDossierProjetExigenceEtatCode(String code);
    long countByDossierProjetExigenceEtatCode(String code);


}
