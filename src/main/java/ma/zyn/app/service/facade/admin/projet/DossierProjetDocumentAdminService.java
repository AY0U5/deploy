package ma.zyn.app.service.facade.admin.projet;

import java.util.List;
import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetDocumentCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface DossierProjetDocumentAdminService {



    List<DossierProjetDocument> findByDossierProjetId(Long id);
    int deleteByDossierProjetId(Long id);
    long countByDossierProjetCode(String code);




	DossierProjetDocument create(DossierProjetDocument t);

    DossierProjetDocument update(DossierProjetDocument t);

    List<DossierProjetDocument> update(List<DossierProjetDocument> ts,boolean createIfNotExist);

    DossierProjetDocument findById(Long id);

    DossierProjetDocument findOrSave(DossierProjetDocument t);

    DossierProjetDocument findByReferenceEntity(DossierProjetDocument t);

    DossierProjetDocument findWithAssociatedLists(Long id);

    List<DossierProjetDocument> findAllOptimized();

    List<DossierProjetDocument> findAll();

    List<DossierProjetDocument> findByCriteria(DossierProjetDocumentCriteria criteria);

    List<DossierProjetDocument> findPaginatedByCriteria(DossierProjetDocumentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DossierProjetDocumentCriteria criteria);

    List<DossierProjetDocument> delete(List<DossierProjetDocument> ts);

    boolean deleteById(Long id);

    List<List<DossierProjetDocument>> getToBeSavedAndToBeDeleted(List<DossierProjetDocument> oldList, List<DossierProjetDocument> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
