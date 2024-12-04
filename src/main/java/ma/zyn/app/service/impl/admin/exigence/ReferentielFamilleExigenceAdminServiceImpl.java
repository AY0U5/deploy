package ma.zyn.app.service.impl.admin.exigence;


import ma.zyn.app.bean.core.exigence.ReferentielFamilleExigence;
import ma.zyn.app.dao.criteria.core.exigence.ReferentielFamilleExigenceCriteria;
import ma.zyn.app.dao.facade.core.exigence.ReferentielFamilleExigenceDao;
import ma.zyn.app.dao.specification.core.exigence.ReferentielFamilleExigenceSpecification;
import ma.zyn.app.service.facade.admin.exigence.ReferentielFamilleExigenceAdminService;
import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.zynerator.util.RefelexivityUtil;
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
public class ReferentielFamilleExigenceAdminServiceImpl implements ReferentielFamilleExigenceAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ReferentielFamilleExigence update(ReferentielFamilleExigence t) {
        ReferentielFamilleExigence loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ReferentielFamilleExigence.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ReferentielFamilleExigence findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ReferentielFamilleExigence findOrSave(ReferentielFamilleExigence t) {
        if (t != null) {
            ReferentielFamilleExigence result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ReferentielFamilleExigence> findAll() {
        return dao.findAll();
    }

    public List<ReferentielFamilleExigence> findByCriteria(ReferentielFamilleExigenceCriteria criteria) {
        List<ReferentielFamilleExigence> content = null;
        if (criteria != null) {
            ReferentielFamilleExigenceSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ReferentielFamilleExigenceSpecification constructSpecification(ReferentielFamilleExigenceCriteria criteria) {
        ReferentielFamilleExigenceSpecification mySpecification =  (ReferentielFamilleExigenceSpecification) RefelexivityUtil.constructObjectUsingOneParam(ReferentielFamilleExigenceSpecification.class, criteria);
        return mySpecification;
    }

    public List<ReferentielFamilleExigence> findPaginatedByCriteria(ReferentielFamilleExigenceCriteria criteria, int page, int pageSize, String order, String sortField) {
        ReferentielFamilleExigenceSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ReferentielFamilleExigenceCriteria criteria) {
        ReferentielFamilleExigenceSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public List<ReferentielFamilleExigence> delete(List<ReferentielFamilleExigence> list) {
		List<ReferentielFamilleExigence> result = new ArrayList();
        if (list != null) {
            for (ReferentielFamilleExigence t : list) {
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
    public ReferentielFamilleExigence create(ReferentielFamilleExigence t) {
        ReferentielFamilleExigence loaded = findByReferenceEntity(t);
        ReferentielFamilleExigence saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ReferentielFamilleExigence findWithAssociatedLists(Long id){
        ReferentielFamilleExigence result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ReferentielFamilleExigence> update(List<ReferentielFamilleExigence> ts, boolean createIfNotExist) {
        List<ReferentielFamilleExigence> result = new ArrayList<>();
        if (ts != null) {
            for (ReferentielFamilleExigence t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ReferentielFamilleExigence loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ReferentielFamilleExigence t, ReferentielFamilleExigence loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ReferentielFamilleExigence findByReferenceEntity(ReferentielFamilleExigence t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ReferentielFamilleExigence> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ReferentielFamilleExigence>> getToBeSavedAndToBeDeleted(List<ReferentielFamilleExigence> oldList, List<ReferentielFamilleExigence> newList) {
        List<List<ReferentielFamilleExigence>> result = new ArrayList<>();
        List<ReferentielFamilleExigence> resultDelete = new ArrayList<>();
        List<ReferentielFamilleExigence> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ReferentielFamilleExigence> oldList, List<ReferentielFamilleExigence> newList, List<ReferentielFamilleExigence> resultUpdateOrSave, List<ReferentielFamilleExigence> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ReferentielFamilleExigence myOld = oldList.get(i);
                ReferentielFamilleExigence t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ReferentielFamilleExigence myNew = newList.get(i);
                ReferentielFamilleExigence t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ReferentielFamilleExigenceAdminServiceImpl(ReferentielFamilleExigenceDao dao) {
        this.dao = dao;
    }

    private ReferentielFamilleExigenceDao dao;
}
