package ma.zyn.app.service.impl.admin.projet;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetDocumentCriteria;
import ma.zyn.app.dao.facade.core.projet.DossierProjetDocumentDao;
import ma.zyn.app.dao.specification.core.projet.DossierProjetDocumentSpecification;
import ma.zyn.app.service.facade.admin.projet.DossierProjetDocumentAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.projet.DossierProjetExigenceAppliqueAdminService ;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique ;
import ma.zyn.app.service.facade.admin.projet.DossierProjetAdminService ;
import ma.zyn.app.bean.core.projet.DossierProjet ;

import java.util.List;
@Service
public class DossierProjetDocumentAdminServiceImpl implements DossierProjetDocumentAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DossierProjetDocument update(DossierProjetDocument t) {
        DossierProjetDocument loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DossierProjetDocument.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public DossierProjetDocument findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DossierProjetDocument findOrSave(DossierProjetDocument t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DossierProjetDocument result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<DossierProjetDocument> findAll() {
        return dao.findAll();
    }

    public List<DossierProjetDocument> findByCriteria(DossierProjetDocumentCriteria criteria) {
        List<DossierProjetDocument> content = null;
        if (criteria != null) {
            DossierProjetDocumentSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private DossierProjetDocumentSpecification constructSpecification(DossierProjetDocumentCriteria criteria) {
        DossierProjetDocumentSpecification mySpecification =  (DossierProjetDocumentSpecification) RefelexivityUtil.constructObjectUsingOneParam(DossierProjetDocumentSpecification.class, criteria);
        return mySpecification;
    }

    public List<DossierProjetDocument> findPaginatedByCriteria(DossierProjetDocumentCriteria criteria, int page, int pageSize, String order, String sortField) {
        DossierProjetDocumentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DossierProjetDocumentCriteria criteria) {
        DossierProjetDocumentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DossierProjetDocument> findByDossierProjetId(Long id){
        return dao.findByDossierProjetId(id);
    }
    public int deleteByDossierProjetId(Long id){
        return dao.deleteByDossierProjetId(id);
    }
    public long countByDossierProjetCode(String code){
        return dao.countByDossierProjetCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        dossierProjetExigenceAppliqueService.deleteByDossierProjetDocumentId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DossierProjetDocument> delete(List<DossierProjetDocument> list) {
		List<DossierProjetDocument> result = new ArrayList();
        if (list != null) {
            for (DossierProjetDocument t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DossierProjetDocument create(DossierProjetDocument t) {
        DossierProjetDocument loaded = findByReferenceEntity(t);
        DossierProjetDocument saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getDossierProjetExigenceAppliques() != null) {
                t.getDossierProjetExigenceAppliques().forEach(element-> {
                    element.setDossierProjetDocument(saved);
                    dossierProjetExigenceAppliqueService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public DossierProjetDocument findWithAssociatedLists(Long id){
        DossierProjetDocument result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setDossierProjetExigenceAppliques(dossierProjetExigenceAppliqueService.findByDossierProjetDocumentId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DossierProjetDocument> update(List<DossierProjetDocument> ts, boolean createIfNotExist) {
        List<DossierProjetDocument> result = new ArrayList<>();
        if (ts != null) {
            for (DossierProjetDocument t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DossierProjetDocument loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, DossierProjetDocument t, DossierProjetDocument loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(DossierProjetDocument dossierProjetDocument){
    if(dossierProjetDocument !=null && dossierProjetDocument.getId() != null){
        List<List<DossierProjetExigenceApplique>> resultDossierProjetExigenceAppliques= dossierProjetExigenceAppliqueService.getToBeSavedAndToBeDeleted(dossierProjetExigenceAppliqueService.findByDossierProjetDocumentId(dossierProjetDocument.getId()),dossierProjetDocument.getDossierProjetExigenceAppliques());
            dossierProjetExigenceAppliqueService.delete(resultDossierProjetExigenceAppliques.get(1));
        emptyIfNull(resultDossierProjetExigenceAppliques.get(0)).forEach(e -> e.setDossierProjetDocument(dossierProjetDocument));
        dossierProjetExigenceAppliqueService.update(resultDossierProjetExigenceAppliques.get(0),true);
        }
    }








    public DossierProjetDocument findByReferenceEntity(DossierProjetDocument t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(DossierProjetDocument t){
        if( t != null) {
            t.setDossierProjet(dossierProjetService.findOrSave(t.getDossierProjet()));
        }
    }



    public List<DossierProjetDocument> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DossierProjetDocument>> getToBeSavedAndToBeDeleted(List<DossierProjetDocument> oldList, List<DossierProjetDocument> newList) {
        List<List<DossierProjetDocument>> result = new ArrayList<>();
        List<DossierProjetDocument> resultDelete = new ArrayList<>();
        List<DossierProjetDocument> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<DossierProjetDocument> oldList, List<DossierProjetDocument> newList, List<DossierProjetDocument> resultUpdateOrSave, List<DossierProjetDocument> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                DossierProjetDocument myOld = oldList.get(i);
                DossierProjetDocument t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                DossierProjetDocument myNew = newList.get(i);
                DossierProjetDocument t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private DossierProjetExigenceAppliqueAdminService dossierProjetExigenceAppliqueService ;
    @Autowired
    private DossierProjetAdminService dossierProjetService ;

    public DossierProjetDocumentAdminServiceImpl(DossierProjetDocumentDao dao) {
        this.dao = dao;
    }

    private DossierProjetDocumentDao dao;
}
