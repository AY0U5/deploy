package ma.zyn.app.dao.facade.core.projet;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import java.util.List;


@Repository
public interface DossierProjetDocumentDao extends AbstractRepository<DossierProjetDocument,Long>  {
    DossierProjetDocument findByCode(String code);
    int deleteByCode(String code);

    List<DossierProjetDocument> findByDossierProjetId(Long id);
    int deleteByDossierProjetId(Long id);
    long countByDossierProjetCode(String code);

    @Query("SELECT NEW DossierProjetDocument(item.id,item.libelle) FROM DossierProjetDocument item")
    List<DossierProjetDocument> findAllOptimized();

}
