package ma.zyn.app.service.impl.admin.exigence;


import ma.zyn.app.bean.core.exigence.FamilleExigence;
import ma.zyn.app.dao.criteria.core.exigence.FamilleExigenceCriteria;
import ma.zyn.app.dao.facade.core.exigence.FamilleExigenceDao;
import ma.zyn.app.dao.specification.core.exigence.FamilleExigenceSpecification;
import ma.zyn.app.service.facade.admin.exigence.FamilleExigenceAdminService;
import ma.zyn.app.service.facade.admin.exigence.ReferentielFamilleExigenceAdminService;
import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.zynerator.util.RefelexivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static ma.zyn.app.zynerator.util.ListUtil.isEmpty;
import static ma.zyn.app.zynerator.util.ListUtil.isNotEmpty;

@Service
public class FamilleExigenceAdminServiceImpl implements FamilleExigenceAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public FamilleExigence update(FamilleExigence t) {
        FamilleExigence loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{FamilleExigence.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public FamilleExigence findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public FamilleExigence findOrSave(FamilleExigence t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            FamilleExigence result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<FamilleExigence> findAll() {
        return dao.findAll();
    }

    public List<FamilleExigence> findByCriteria(FamilleExigenceCriteria criteria) {
        List<FamilleExigence> content = null;
        if (criteria != null) {
            FamilleExigenceSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private FamilleExigenceSpecification constructSpecification(FamilleExigenceCriteria criteria) {
        FamilleExigenceSpecification mySpecification =  (FamilleExigenceSpecification) RefelexivityUtil.constructObjectUsingOneParam(FamilleExigenceSpecification.class, criteria);
        return mySpecification;
    }

    public List<FamilleExigence> findPaginatedByCriteria(FamilleExigenceCriteria criteria, int page, int pageSize, String order, String sortField) {
        FamilleExigenceSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(FamilleExigenceCriteria criteria) {
        FamilleExigenceSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<FamilleExigence> findByReferentielFamilleExigenceCode(String code){
        return dao.findByReferentielFamilleExigenceCode(code);
    }
    public List<FamilleExigence> findByReferentielFamilleExigenceId(Long id){
        return dao.findByReferentielFamilleExigenceId(id);
    }
    public int deleteByReferentielFamilleExigenceCode(String code){
        return dao.deleteByReferentielFamilleExigenceCode(code);
    }
    public int deleteByReferentielFamilleExigenceId(Long id){
        return dao.deleteByReferentielFamilleExigenceId(id);
    }
    public long countByReferentielFamilleExigenceCode(String code){
        return dao.countByReferentielFamilleExigenceCode(code);
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
    public List<FamilleExigence> delete(List<FamilleExigence> list) {
		List<FamilleExigence> result = new ArrayList();
        if (list != null) {
            for (FamilleExigence t : list) {
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
    public FamilleExigence create(FamilleExigence t) {
        FamilleExigence loaded = findByReferenceEntity(t);
        FamilleExigence saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public FamilleExigence findWithAssociatedLists(Long id){
        FamilleExigence result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<FamilleExigence> update(List<FamilleExigence> ts, boolean createIfNotExist) {
        List<FamilleExigence> result = new ArrayList<>();
        if (ts != null) {
            for (FamilleExigence t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    FamilleExigence loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, FamilleExigence t, FamilleExigence loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public FamilleExigence findByReferenceEntity(FamilleExigence t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(FamilleExigence t){
        if( t != null) {
            t.setReferentielFamilleExigence(referentielFamilleExigenceService.findOrSave(t.getReferentielFamilleExigence()));
        }
    }



    public List<FamilleExigence> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<FamilleExigence>> getToBeSavedAndToBeDeleted(List<FamilleExigence> oldList, List<FamilleExigence> newList) {
        List<List<FamilleExigence>> result = new ArrayList<>();
        List<FamilleExigence> resultDelete = new ArrayList<>();
        List<FamilleExigence> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<FamilleExigence> oldList, List<FamilleExigence> newList, List<FamilleExigence> resultUpdateOrSave, List<FamilleExigence> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                FamilleExigence myOld = oldList.get(i);
                FamilleExigence t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                FamilleExigence myNew = newList.get(i);
                FamilleExigence t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ReferentielFamilleExigenceAdminService referentielFamilleExigenceService ;

    public FamilleExigenceAdminServiceImpl(FamilleExigenceDao dao) {
        this.dao = dao;
    }

    private FamilleExigenceDao dao;
}
