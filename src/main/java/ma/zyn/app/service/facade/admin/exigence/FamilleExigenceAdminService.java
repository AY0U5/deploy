package ma.zyn.app.service.facade.admin.exigence;

import ma.zyn.app.bean.core.exigence.FamilleExigence;
import ma.zyn.app.dao.criteria.core.exigence.FamilleExigenceCriteria;

import java.util.List;


public interface FamilleExigenceAdminService {



    List<FamilleExigence> findByReferentielFamilleExigenceCode(String code);
    List<FamilleExigence> findByReferentielFamilleExigenceId(Long id);
    int deleteByReferentielFamilleExigenceId(Long id);
    int deleteByReferentielFamilleExigenceCode(String code);
    long countByReferentielFamilleExigenceCode(String code);




	FamilleExigence create(FamilleExigence t);

    FamilleExigence update(FamilleExigence t);

    List<FamilleExigence> update(List<FamilleExigence> ts,boolean createIfNotExist);

    FamilleExigence findById(Long id);

    FamilleExigence findOrSave(FamilleExigence t);

    FamilleExigence findByReferenceEntity(FamilleExigence t);

    FamilleExigence findWithAssociatedLists(Long id);

    List<FamilleExigence> findAllOptimized();

    List<FamilleExigence> findAll();

    List<FamilleExigence> findByCriteria(FamilleExigenceCriteria criteria);

    List<FamilleExigence> findPaginatedByCriteria(FamilleExigenceCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(FamilleExigenceCriteria criteria);

    List<FamilleExigence> delete(List<FamilleExigence> ts);

    boolean deleteById(Long id);

    List<List<FamilleExigence>> getToBeSavedAndToBeDeleted(List<FamilleExigence> oldList, List<FamilleExigence> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
