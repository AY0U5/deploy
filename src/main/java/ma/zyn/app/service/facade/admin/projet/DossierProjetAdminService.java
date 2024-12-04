package ma.zyn.app.service.facade.admin.projet;

import java.util.List;
import ma.zyn.app.bean.core.projet.DossierProjet;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface DossierProjetAdminService {







	DossierProjet create(DossierProjet t);

    DossierProjet update(DossierProjet t);

    List<DossierProjet> update(List<DossierProjet> ts,boolean createIfNotExist);

    DossierProjet findById(Long id);

    DossierProjet findOrSave(DossierProjet t);

    DossierProjet findByReferenceEntity(DossierProjet t);

    DossierProjet findWithAssociatedLists(Long id);

    List<DossierProjet> findAllOptimized();

    List<DossierProjet> findAll();

    List<DossierProjet> findByCriteria(DossierProjetCriteria criteria);

    List<DossierProjet> findPaginatedByCriteria(DossierProjetCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DossierProjetCriteria criteria);

    List<DossierProjet> delete(List<DossierProjet> ts);

    boolean deleteById(Long id);

    List<List<DossierProjet>> getToBeSavedAndToBeDeleted(List<DossierProjet> oldList, List<DossierProjet> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
