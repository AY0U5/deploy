package ma.zyn.app.service.facade.admin.projet;

import java.util.List;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetExigenceAppliqueCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface DossierProjetExigenceAppliqueAdminService {



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




	DossierProjetExigenceApplique create(DossierProjetExigenceApplique t);

    DossierProjetExigenceApplique update(DossierProjetExigenceApplique t);

    List<DossierProjetExigenceApplique> update(List<DossierProjetExigenceApplique> ts,boolean createIfNotExist);

    DossierProjetExigenceApplique findById(Long id);

    DossierProjetExigenceApplique findOrSave(DossierProjetExigenceApplique t);

    DossierProjetExigenceApplique findByReferenceEntity(DossierProjetExigenceApplique t);

    DossierProjetExigenceApplique findWithAssociatedLists(Long id);

    List<DossierProjetExigenceApplique> findAllOptimized();

    List<DossierProjetExigenceApplique> findAll();

    List<DossierProjetExigenceApplique> findByCriteria(DossierProjetExigenceAppliqueCriteria criteria);

    List<DossierProjetExigenceApplique> findPaginatedByCriteria(DossierProjetExigenceAppliqueCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DossierProjetExigenceAppliqueCriteria criteria);

    List<DossierProjetExigenceApplique> delete(List<DossierProjetExigenceApplique> ts);

    boolean deleteById(Long id);

    List<List<DossierProjetExigenceApplique>> getToBeSavedAndToBeDeleted(List<DossierProjetExigenceApplique> oldList, List<DossierProjetExigenceApplique> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
