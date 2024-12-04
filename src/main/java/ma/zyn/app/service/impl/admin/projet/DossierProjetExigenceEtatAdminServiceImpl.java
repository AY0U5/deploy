package ma.zyn.app.service.impl.admin.projet;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetExigenceEtatCriteria;
import ma.zyn.app.dao.facade.core.projet.DossierProjetExigenceEtatDao;
import ma.zyn.app.dao.specification.core.projet.DossierProjetExigenceEtatSpecification;
import ma.zyn.app.service.facade.admin.projet.DossierProjetExigenceEtatAdminService;
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


import java.util.List;
@Service
public class DossierProjetExigenceEtatAdminServiceImpl implements DossierProjetExigenceEtatAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DossierProjetExigenceEtat update(DossierProjetExigenceEtat t) {
        DossierProjetExigenceEtat loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DossierProjetExigenceEtat.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DossierProjetExigenceEtat findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DossierProjetExigenceEtat findOrSave(DossierProjetExigenceEtat t) {
        if (t != null) {
            DossierProjetExigenceEtat result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<DossierProjetExigenceEtat> findAll() {
        return dao.findAll();
    }

    public List<DossierProjetExigenceEtat> findByCriteria(DossierProjetExigenceEtatCriteria criteria) {
        List<DossierProjetExigenceEtat> content = null;
        if (criteria != null) {
            DossierProjetExigenceEtatSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private DossierProjetExigenceEtatSpecification constructSpecification(DossierProjetExigenceEtatCriteria criteria) {
        DossierProjetExigenceEtatSpecification mySpecification =  (DossierProjetExigenceEtatSpecification) RefelexivityUtil.constructObjectUsingOneParam(DossierProjetExigenceEtatSpecification.class, criteria);
        return mySpecification;
    }

    public List<DossierProjetExigenceEtat> findPaginatedByCriteria(DossierProjetExigenceEtatCriteria criteria, int page, int pageSize, String order, String sortField) {
        DossierProjetExigenceEtatSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DossierProjetExigenceEtatCriteria criteria) {
        DossierProjetExigenceEtatSpecification mySpecification = constructSpecification(criteria);
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
    public List<DossierProjetExigenceEtat> delete(List<DossierProjetExigenceEtat> list) {
		List<DossierProjetExigenceEtat> result = new ArrayList();
        if (list != null) {
            for (DossierProjetExigenceEtat t : list) {
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
    public DossierProjetExigenceEtat create(DossierProjetExigenceEtat t) {
        DossierProjetExigenceEtat loaded = findByReferenceEntity(t);
        DossierProjetExigenceEtat saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public DossierProjetExigenceEtat findWithAssociatedLists(Long id){
        DossierProjetExigenceEtat result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DossierProjetExigenceEtat> update(List<DossierProjetExigenceEtat> ts, boolean createIfNotExist) {
        List<DossierProjetExigenceEtat> result = new ArrayList<>();
        if (ts != null) {
            for (DossierProjetExigenceEtat t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DossierProjetExigenceEtat loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, DossierProjetExigenceEtat t, DossierProjetExigenceEtat loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public DossierProjetExigenceEtat findByReferenceEntity(DossierProjetExigenceEtat t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<DossierProjetExigenceEtat> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DossierProjetExigenceEtat>> getToBeSavedAndToBeDeleted(List<DossierProjetExigenceEtat> oldList, List<DossierProjetExigenceEtat> newList) {
        List<List<DossierProjetExigenceEtat>> result = new ArrayList<>();
        List<DossierProjetExigenceEtat> resultDelete = new ArrayList<>();
        List<DossierProjetExigenceEtat> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<DossierProjetExigenceEtat> oldList, List<DossierProjetExigenceEtat> newList, List<DossierProjetExigenceEtat> resultUpdateOrSave, List<DossierProjetExigenceEtat> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                DossierProjetExigenceEtat myOld = oldList.get(i);
                DossierProjetExigenceEtat t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                DossierProjetExigenceEtat myNew = newList.get(i);
                DossierProjetExigenceEtat t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public DossierProjetExigenceEtatAdminServiceImpl(DossierProjetExigenceEtatDao dao) {
        this.dao = dao;
    }

    private DossierProjetExigenceEtatDao dao;
}
