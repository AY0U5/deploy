package ma.zyn.app.service.impl.admin.projet;


import ma.zyn.app.bean.core.exigence.Exigence;
import ma.zyn.app.bean.core.projet.DossierProjet;
import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import ma.zyn.app.bean.core.projet.DossierProjetExigence;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetCriteria;
import ma.zyn.app.dao.facade.core.projet.DossierProjetDao;
import ma.zyn.app.dao.specification.core.projet.DossierProjetSpecification;
import ma.zyn.app.service.facade.admin.projet.DossierProjetAdminService;
import ma.zyn.app.service.facade.admin.projet.DossierProjetDocumentAdminService;
import ma.zyn.app.service.facade.admin.projet.DossierProjetExigenceAdminService;
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

import static ma.zyn.app.zynerator.util.ListUtil.*;

@Service
public class DossierProjetAdminServiceImpl implements DossierProjetAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DossierProjet update(DossierProjet t) {
        DossierProjet loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DossierProjet.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public DossierProjet findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DossierProjet findOrSave(DossierProjet t) {
        if (t != null) {
            DossierProjet result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<DossierProjet> findAll() {
        return dao.findAll();
    }

    public List<DossierProjet> findByCriteria(DossierProjetCriteria criteria) {
        List<DossierProjet> content = null;
        if (criteria != null) {
            DossierProjetSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private DossierProjetSpecification constructSpecification(DossierProjetCriteria criteria) {
        DossierProjetSpecification mySpecification = (DossierProjetSpecification) RefelexivityUtil.constructObjectUsingOneParam(DossierProjetSpecification.class, criteria);
        return mySpecification;
    }

    public List<DossierProjet> findPaginatedByCriteria(DossierProjetCriteria criteria, int page, int pageSize, String order, String sortField) {
        DossierProjetSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DossierProjetCriteria criteria) {
        DossierProjetSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
        dossierProjetExigenceService.deleteByDossierProjetId(id);
        dossierProjetDocumentService.deleteByDossierProjetId(id);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DossierProjet> delete(List<DossierProjet> list) {
        List<DossierProjet> result = new ArrayList();
        if (list != null) {
            for (DossierProjet t : list) {
                if (dao.findById(t.getId()).isEmpty()) {
                    result.add(t);
                } else {
                    dao.deleteById(t.getId());
                }
            }
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DossierProjet create(DossierProjet t) {
        DossierProjet loaded = findByReferenceEntity(t);
        DossierProjet saved;
        if (loaded == null) {
            saved = dao.save(t);
            List<DossierProjetExigence> dossierProjetExigences = t.getDossierProjetExigences();
            List<DossierProjetDocument> dossierProjetDocuments = t.getDossierProjetDocuments();

            if (dossierProjetExigences != null) {
                dossierProjetExigences.forEach(element -> {
                    element.setDossierProjet(saved);
                    dossierProjetExigenceService.create(element);
                });
            }
            if (dossierProjetDocuments != null) {
                dossierProjetDocuments.forEach(element -> {
                    element.setDossierProjet(saved);
                    dossierProjetDocumentService.create(element);
                });
            }

            if (dossierProjetDocuments != null && dossierProjetExigences != null) {

                dossierProjetDocuments.forEach(element -> {
                    // IA must determine for each document (content) which exigence is applied
                    DossierProjetDocument myDocument = element;
                    for (DossierProjetExigence dossierProjetExigence : dossierProjetExigences) {
                        String pages = isDocumentIlligbleForExigence(myDocument, dossierProjetExigence.getExigence());
                        System.out.println("pages = " + pages);
                    }

                });
            }

        } else {
            saved = null;
        }
        return saved;
    }

    //TODO: amalou IA
    //@return pages in which the exigence is applied else return null
    private String isDocumentIlligbleForExigence(DossierProjetDocument myDocument, Exigence exigence) {
        String content = myDocument.getContent();
        String description = exigence.getDescription();
        // amalo write code here : send the exigence description and the document content
        return "awedaaa amalo ba9i ma gadate walo";
    }

    public DossierProjet findWithAssociatedLists(Long id) {
        DossierProjet result = dao.findById(id).orElse(null);
        if (result != null && result.getId() != null) {
            result.setDossierProjetExigences(dossierProjetExigenceService.findByDossierProjetId(id));
            result.setDossierProjetDocuments(dossierProjetDocumentService.findByDossierProjetId(id));
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DossierProjet> update(List<DossierProjet> ts, boolean createIfNotExist) {
        List<DossierProjet> result = new ArrayList<>();
        if (ts != null) {
            for (DossierProjet t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DossierProjet loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, DossierProjet t, DossierProjet loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(DossierProjet dossierProjet) {
        if (dossierProjet != null && dossierProjet.getId() != null) {
            List<List<DossierProjetExigence>> resultDossierProjetExigences = dossierProjetExigenceService.getToBeSavedAndToBeDeleted(dossierProjetExigenceService.findByDossierProjetId(dossierProjet.getId()), dossierProjet.getDossierProjetExigences());
            dossierProjetExigenceService.delete(resultDossierProjetExigences.get(1));
            emptyIfNull(resultDossierProjetExigences.get(0)).forEach(e -> e.setDossierProjet(dossierProjet));
            dossierProjetExigenceService.update(resultDossierProjetExigences.get(0), true);
            List<List<DossierProjetDocument>> resultDossierProjetDocuments = dossierProjetDocumentService.getToBeSavedAndToBeDeleted(dossierProjetDocumentService.findByDossierProjetId(dossierProjet.getId()), dossierProjet.getDossierProjetDocuments());
            dossierProjetDocumentService.delete(resultDossierProjetDocuments.get(1));
            emptyIfNull(resultDossierProjetDocuments.get(0)).forEach(e -> e.setDossierProjet(dossierProjet));
            dossierProjetDocumentService.update(resultDossierProjetDocuments.get(0), true);
        }
    }


    public DossierProjet findByReferenceEntity(DossierProjet t) {
        return t == null ? null : dao.findByCode(t.getCode());
    }


    public List<DossierProjet> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DossierProjet>> getToBeSavedAndToBeDeleted(List<DossierProjet> oldList, List<DossierProjet> newList) {
        List<List<DossierProjet>> result = new ArrayList<>();
        List<DossierProjet> resultDelete = new ArrayList<>();
        List<DossierProjet> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<DossierProjet> oldList, List<DossierProjet> newList, List<DossierProjet> resultUpdateOrSave, List<DossierProjet> resultDelete) {
        for (int i = 0; i < oldList.size(); i++) {
            DossierProjet myOld = oldList.get(i);
            DossierProjet t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
            if (t != null) {
                resultUpdateOrSave.add(t); // update
            } else {
                resultDelete.add(myOld);
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            DossierProjet myNew = newList.get(i);
            DossierProjet t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private DossierProjetDocumentAdminService dossierProjetDocumentService;
    @Autowired
    private DossierProjetExigenceAdminService dossierProjetExigenceService;

    public DossierProjetAdminServiceImpl(DossierProjetDao dao) {
        this.dao = dao;
    }

    private DossierProjetDao dao;
}
