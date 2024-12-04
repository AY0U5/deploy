package ma.zyn.app.service.facade.admin.projet;

import java.util.List;
import ma.zyn.app.bean.core.projet.DossierProjetExigence;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetExigenceCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface DossierProjetExigenceAdminService {



    List<DossierProjetExigence> findByExigenceId(Long id);
    int deleteByExigenceId(Long id);
    long countByExigenceReference(String reference);
    List<DossierProjetExigence> findByDossierProjetId(Long id);
    int deleteByDossierProjetId(Long id);
    long countByDossierProjetCode(String code);




	DossierProjetExigence create(DossierProjetExigence t);

    DossierProjetExigence update(DossierProjetExigence t);

    List<DossierProjetExigence> update(List<DossierProjetExigence> ts,boolean createIfNotExist);

    DossierProjetExigence findById(Long id);

    DossierProjetExigence findOrSave(DossierProjetExigence t);

    DossierProjetExigence findByReferenceEntity(DossierProjetExigence t);

    DossierProjetExigence findWithAssociatedLists(Long id);

    List<DossierProjetExigence> findAllOptimized();

    List<DossierProjetExigence> findAll();

    List<DossierProjetExigence> findByCriteria(DossierProjetExigenceCriteria criteria);

    List<DossierProjetExigence> findPaginatedByCriteria(DossierProjetExigenceCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DossierProjetExigenceCriteria criteria);

    List<DossierProjetExigence> delete(List<DossierProjetExigence> ts);

    boolean deleteById(Long id);

    List<List<DossierProjetExigence>> getToBeSavedAndToBeDeleted(List<DossierProjetExigence> oldList, List<DossierProjetExigence> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
