package ma.zyn.app.service.facade.admin.projet;

import java.util.List;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetExigenceEtatCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface DossierProjetExigenceEtatAdminService {







	DossierProjetExigenceEtat create(DossierProjetExigenceEtat t);

    DossierProjetExigenceEtat update(DossierProjetExigenceEtat t);

    List<DossierProjetExigenceEtat> update(List<DossierProjetExigenceEtat> ts,boolean createIfNotExist);

    DossierProjetExigenceEtat findById(Long id);

    DossierProjetExigenceEtat findOrSave(DossierProjetExigenceEtat t);

    DossierProjetExigenceEtat findByReferenceEntity(DossierProjetExigenceEtat t);

    DossierProjetExigenceEtat findWithAssociatedLists(Long id);

    List<DossierProjetExigenceEtat> findAllOptimized();

    List<DossierProjetExigenceEtat> findAll();

    List<DossierProjetExigenceEtat> findByCriteria(DossierProjetExigenceEtatCriteria criteria);

    List<DossierProjetExigenceEtat> findPaginatedByCriteria(DossierProjetExigenceEtatCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DossierProjetExigenceEtatCriteria criteria);

    List<DossierProjetExigenceEtat> delete(List<DossierProjetExigenceEtat> ts);

    boolean deleteById(Long id);

    List<List<DossierProjetExigenceEtat>> getToBeSavedAndToBeDeleted(List<DossierProjetExigenceEtat> oldList, List<DossierProjetExigenceEtat> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
