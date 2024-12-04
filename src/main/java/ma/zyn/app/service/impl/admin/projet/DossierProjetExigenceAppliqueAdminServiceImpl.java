package ma.zyn.app.service.impl.admin.projet;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetExigenceAppliqueCriteria;
import ma.zyn.app.dao.facade.core.projet.DossierProjetExigenceAppliqueDao;
import ma.zyn.app.dao.specification.core.projet.DossierProjetExigenceAppliqueSpecification;
import ma.zyn.app.service.facade.admin.projet.DossierProjetExigenceAppliqueAdminService;
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

import ma.zyn.app.service.facade.admin.projet.DossierProjetDocumentAdminService ;
import ma.zyn.app.bean.core.projet.DossierProjetDocument ;
import ma.zyn.app.service.facade.admin.projet.DossierProjetExigenceEtatAdminService ;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat ;
import ma.zyn.app.service.facade.admin.exigence.ExigenceAdminService ;
import ma.zyn.app.bean.core.exigence.Exigence ;

import java.util.List;
@Service
public class DossierProjetExigenceAppliqueAdminServiceImpl implements DossierProjetExigenceAppliqueAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DossierProjetExigenceApplique update(DossierProjetExigenceApplique t) {
        DossierProjetExigenceApplique loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DossierProjetExigenceApplique.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DossierProjetExigenceApplique findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DossierProjetExigenceApplique findOrSave(DossierProjetExigenceApplique t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DossierProjetExigenceApplique result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<DossierProjetExigenceApplique> findAll() {
        return dao.findAll();
    }

    public List<DossierProjetExigenceApplique> findByCriteria(DossierProjetExigenceAppliqueCriteria criteria) {
        List<DossierProjetExigenceApplique> content = null;
        if (criteria != null) {
            DossierProjetExigenceAppliqueSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private DossierProjetExigenceAppliqueSpecification constructSpecification(DossierProjetExigenceAppliqueCriteria criteria) {
        DossierProjetExigenceAppliqueSpecification mySpecification =  (DossierProjetExigenceAppliqueSpecification) RefelexivityUtil.constructObjectUsingOneParam(DossierProjetExigenceAppliqueSpecification.class, criteria);
        return mySpecification;
    }

    public List<DossierProjetExigenceApplique> findPaginatedByCriteria(DossierProjetExigenceAppliqueCriteria criteria, int page, int pageSize, String order, String sortField) {
        DossierProjetExigenceAppliqueSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DossierProjetExigenceAppliqueCriteria criteria) {
        DossierProjetExigenceAppliqueSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DossierProjetExigenceApplique> findByDossierProjetDocumentId(Long id){
        return dao.findByDossierProjetDocumentId(id);
    }
    public int deleteByDossierProjetDocumentId(Long id){
        return dao.deleteByDossierProjetDocumentId(id);
    }
    public long countByDossierProjetDocumentCode(String code){
        return dao.countByDossierProjetDocumentCode(code);
    }
    public List<DossierProjetExigenceApplique> findByExigenceId(Long id){
        return dao.findByExigenceId(id);
    }
    public int deleteByExigenceId(Long id){
        return dao.deleteByExigenceId(id);
    }
    public long countByExigenceReference(String reference){
        return dao.countByExigenceReference(reference);
    }
    public List<DossierProjetExigenceApplique> findByDossierProjetExigenceEtatCode(String code){
        return dao.findByDossierProjetExigenceEtatCode(code);
    }
    public List<DossierProjetExigenceApplique> findByDossierProjetExigenceEtatId(Long id){
        return dao.findByDossierProjetExigenceEtatId(id);
    }
    public int deleteByDossierProjetExigenceEtatCode(String code){
        return dao.deleteByDossierProjetExigenceEtatCode(code);
    }
    public int deleteByDossierProjetExigenceEtatId(Long id){
        return dao.deleteByDossierProjetExigenceEtatId(id);
    }
    public long countByDossierProjetExigenceEtatCode(String code){
        return dao.countByDossierProjetExigenceEtatCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DossierProjetExigenceApplique> delete(List<DossierProjetExigenceApplique> list) {
		List<DossierProjetExigenceApplique> result = new ArrayList();
        if (list != null) {
            for (DossierProjetExigenceApplique t : list) {
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
    public DossierProjetExigenceApplique create(DossierProjetExigenceApplique t) {
        DossierProjetExigenceApplique loaded = findByReferenceEntity(t);
        DossierProjetExigenceApplique saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public DossierProjetExigenceApplique findWithAssociatedLists(Long id){
        DossierProjetExigenceApplique result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DossierProjetExigenceApplique> update(List<DossierProjetExigenceApplique> ts, boolean createIfNotExist) {
        List<DossierProjetExigenceApplique> result = new ArrayList<>();
        if (ts != null) {
            for (DossierProjetExigenceApplique t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DossierProjetExigenceApplique loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, DossierProjetExigenceApplique t, DossierProjetExigenceApplique loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public DossierProjetExigenceApplique findByReferenceEntity(DossierProjetExigenceApplique t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(DossierProjetExigenceApplique t){
        if( t != null) {
            t.setDossierProjetDocument(dossierProjetDocumentService.findOrSave(t.getDossierProjetDocument()));
            t.setExigence(exigenceService.findOrSave(t.getExigence()));
            t.setDossierProjetExigenceEtat(dossierProjetExigenceEtatService.findOrSave(t.getDossierProjetExigenceEtat()));
        }
    }



    public List<DossierProjetExigenceApplique> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<DossierProjetExigenceApplique>> getToBeSavedAndToBeDeleted(List<DossierProjetExigenceApplique> oldList, List<DossierProjetExigenceApplique> newList) {
        List<List<DossierProjetExigenceApplique>> result = new ArrayList<>();
        List<DossierProjetExigenceApplique> resultDelete = new ArrayList<>();
        List<DossierProjetExigenceApplique> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<DossierProjetExigenceApplique> oldList, List<DossierProjetExigenceApplique> newList, List<DossierProjetExigenceApplique> resultUpdateOrSave, List<DossierProjetExigenceApplique> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                DossierProjetExigenceApplique myOld = oldList.get(i);
                DossierProjetExigenceApplique t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                DossierProjetExigenceApplique myNew = newList.get(i);
                DossierProjetExigenceApplique t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private DossierProjetDocumentAdminService dossierProjetDocumentService ;
    @Autowired
    private DossierProjetExigenceEtatAdminService dossierProjetExigenceEtatService ;
    @Autowired
    private ExigenceAdminService exigenceService ;

    public DossierProjetExigenceAppliqueAdminServiceImpl(DossierProjetExigenceAppliqueDao dao) {
        this.dao = dao;
    }

    private DossierProjetExigenceAppliqueDao dao;
}
