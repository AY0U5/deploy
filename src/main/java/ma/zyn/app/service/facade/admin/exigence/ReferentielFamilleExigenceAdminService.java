package ma.zyn.app.service.facade.admin.exigence;

import ma.zyn.app.bean.core.exigence.ReferentielFamilleExigence;
import ma.zyn.app.dao.criteria.core.exigence.ReferentielFamilleExigenceCriteria;

import java.util.List;


public interface ReferentielFamilleExigenceAdminService {







	ReferentielFamilleExigence create(ReferentielFamilleExigence t);

    ReferentielFamilleExigence update(ReferentielFamilleExigence t);

    List<ReferentielFamilleExigence> update(List<ReferentielFamilleExigence> ts,boolean createIfNotExist);

    ReferentielFamilleExigence findById(Long id);

    ReferentielFamilleExigence findOrSave(ReferentielFamilleExigence t);

    ReferentielFamilleExigence findByReferenceEntity(ReferentielFamilleExigence t);

    ReferentielFamilleExigence findWithAssociatedLists(Long id);

    List<ReferentielFamilleExigence> findAllOptimized();

    List<ReferentielFamilleExigence> findAll();

    List<ReferentielFamilleExigence> findByCriteria(ReferentielFamilleExigenceCriteria criteria);

    List<ReferentielFamilleExigence> findPaginatedByCriteria(ReferentielFamilleExigenceCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ReferentielFamilleExigenceCriteria criteria);

    List<ReferentielFamilleExigence> delete(List<ReferentielFamilleExigence> ts);

    boolean deleteById(Long id);

    List<List<ReferentielFamilleExigence>> getToBeSavedAndToBeDeleted(List<ReferentielFamilleExigence> oldList, List<ReferentielFamilleExigence> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
