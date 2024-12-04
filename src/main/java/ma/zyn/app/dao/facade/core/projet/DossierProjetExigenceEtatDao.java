package ma.zyn.app.dao.facade.core.projet;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import java.util.List;


@Repository
public interface DossierProjetExigenceEtatDao extends AbstractRepository<DossierProjetExigenceEtat,Long>  {
    DossierProjetExigenceEtat findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW DossierProjetExigenceEtat(item.id,item.libelle) FROM DossierProjetExigenceEtat item")
    List<DossierProjetExigenceEtat> findAllOptimized();

}
