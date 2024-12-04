package ma.zyn.app.service.facade.admin.exigence;

import java.util.List;
import ma.zyn.app.bean.core.exigence.Exigence;
import ma.zyn.app.dao.criteria.core.exigence.ExigenceCriteria;
import ma.zyn.app.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface ExigenceAdminService {



    List<Exigence> findByFamilleExigenceCode(String code);
    List<Exigence> findByFamilleExigenceId(Long id);
    int deleteByFamilleExigenceId(Long id);
    int deleteByFamilleExigenceCode(String code);
    long countByFamilleExigenceCode(String code);




	Exigence create(Exigence t);

    Exigence update(Exigence t);

    List<Exigence> update(List<Exigence> ts,boolean createIfNotExist);

    Exigence findById(Long id);

    Exigence findOrSave(Exigence t);

    Exigence findByReferenceEntity(Exigence t);

    Exigence findWithAssociatedLists(Long id);

    List<Exigence> findAllOptimized();

    List<Exigence> findAll();

    List<Exigence> findByCriteria(ExigenceCriteria criteria);

    List<Exigence> findPaginatedByCriteria(ExigenceCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ExigenceCriteria criteria);

    List<Exigence> delete(List<Exigence> ts);

    boolean deleteById(Long id);

    List<List<Exigence>> getToBeSavedAndToBeDeleted(List<Exigence> oldList, List<Exigence> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Exigence> importExcel(MultipartFile file);
}
