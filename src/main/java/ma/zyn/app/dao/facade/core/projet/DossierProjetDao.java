package ma.zyn.app.dao.facade.core.projet;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.projet.DossierProjet;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.projet.DossierProjet;
import java.util.List;


@Repository
public interface DossierProjetDao extends AbstractRepository<DossierProjet,Long>  {
    DossierProjet findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW DossierProjet(item.id,item.libelle) FROM DossierProjet item")
    List<DossierProjet> findAllOptimized();

}
