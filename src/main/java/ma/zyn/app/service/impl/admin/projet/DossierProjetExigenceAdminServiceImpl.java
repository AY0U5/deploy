package ma.zyn.app.service.impl.admin.projet;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.projet.DossierProjetExigence;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetExigenceCriteria;
import ma.zyn.app.dao.facade.core.projet.DossierProjetExigenceDao;
import ma.zyn.app.dao.specification.core.projet.DossierProjetExigenceSpecification;
import ma.zyn.app.service.facade.admin.projet.DossierProjetExigenceAdminService;
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

import ma.zyn.app.service.facade.admin.projet.DossierProjetAdminService ;
import ma.zyn.app.bean.core.projet.DossierProjet ;
import ma.zyn.app.service.facade.admin.exigence.ExigenceAdminService ;
import ma.zyn.app.bean.core.exigence.Exigence ;

import java.util.List;
@Service
public class DossierProjetExigenceAdminServiceImpl implements DossierProjetExigenceAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DossierProjetExigence update(DossierProjetExigence t) {
        DossierProjetExigence loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DossierProjetExigence.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DossierProjetExigence findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DossierProjetExigence findOrSave(DossierProjetExigence t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DossierProjetExigence result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<DossierProjetExigence> findAll() {
        return dao.findAll();
    }

    public List<DossierProjetExigence> findByCriteria(DossierProjetExigenceCriteria criteria) {
        List<DossierProjetExigence> content = null;
        if (criteria != null) {
            DossierProjetExigenceSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private DossierProjetExigenceSpecification constructSpecification(DossierProjetExigenceCriteria criteria) {
        DossierProjetExigenceSpecification mySpecification =  (DossierProjetExigenceSpecification) RefelexivityUtil.constructObjectUsingOneParam(DossierProjetExigenceSpecification.class, criteria);
        return mySpecification;
    }

    public List<DossierProjetExigence> findPaginatedByCriteria(DossierProjetExigenceCriteria criteria, int page, int pageSize, String order, String sortField) {
        DossierProjetExigenceSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DossierProjetExigenceCriteria criteria) {
        DossierProjetExigenceSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DossierProjetExigence> findByExigenceId(Long id){
        return dao.findByExigenceId(id);
    }
    public int deleteByExigenceId(Long id){
        return dao.deleteByExigenceId(id);
    }
    public long countByExigenceReference(String reference){
        return dao.countByExigenceReference(reference);
    }
    public List<DossierProjetExigence> findByDossierProjetId(Long id){
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
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DossierProjetExigence> delete(List<DossierProjetExigence> list) {
		List<DossierProjetExigence> result = new ArrayList();
        if (list != null) {
            for (DossierProjetExigence t : list) {
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
    public DossierProjetExigence create(DossierProjetExigence t) {
        DossierProjetExigence loaded = findByReferenceEntity(t);
        DossierProjetExigence saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public DossierProjetExigence findWithAssociatedLists(Long id){
        DossierProjetExigence result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DossierProjetExigence> update(List<DossierProjetExigence> ts, boolean createIfNotExist) {
        List<DossierProjetExigence> result = new ArrayList<>();
        if (ts != null) {
            for (DossierProjetExigence t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DossierProjetExigence loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, DossierProjetExigence t, DossierProjetExigence loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public DossierProjetExigence findByReferenceEntity(DossierProjetExigence t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(DossierProjetExigence t){
        if( t != null) {
            t.setExigence(exigenceService.findOrSave(t.getExigence()));
            t.setDossierProjet(dossierProjetService.findOrSave(t.getDossierProjet()));
        }
    }



    public List<DossierProjetExigence> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<DossierProjetExigence>> getToBeSavedAndToBeDeleted(List<DossierProjetExigence> oldList, List<DossierProjetExigence> newList) {
        List<List<DossierProjetExigence>> result = new ArrayList<>();
        List<DossierProjetExigence> resultDelete = new ArrayList<>();
        List<DossierProjetExigence> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<DossierProjetExigence> oldList, List<DossierProjetExigence> newList, List<DossierProjetExigence> resultUpdateOrSave, List<DossierProjetExigence> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                DossierProjetExigence myOld = oldList.get(i);
                DossierProjetExigence t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                DossierProjetExigence myNew = newList.get(i);
                DossierProjetExigence t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private DossierProjetAdminService dossierProjetService ;
    @Autowired
    private ExigenceAdminService exigenceService ;

    public DossierProjetExigenceAdminServiceImpl(DossierProjetExigenceDao dao) {
        this.dao = dao;
    }

    private DossierProjetExigenceDao dao;
}
