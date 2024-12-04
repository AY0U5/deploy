package ma.zyn.app.service.impl.admin.exigence;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.exigence.Exigence;
import ma.zyn.app.dao.criteria.core.exigence.ExigenceCriteria;
import ma.zyn.app.dao.facade.core.exigence.ExigenceDao;
import ma.zyn.app.dao.specification.core.exigence.ExigenceSpecification;
import ma.zyn.app.service.facade.admin.exigence.ExigenceAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;


import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import ma.zyn.app.zynerator.util.ExcelUtil;

import java.io.IOException;
import java.io.InputStream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.exigence.FamilleExigenceAdminService ;
import ma.zyn.app.bean.core.exigence.FamilleExigence ;

import java.util.List;
@Service
public class ExigenceAdminServiceImpl implements ExigenceAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Exigence update(Exigence t) {
        Exigence loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Exigence.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Exigence findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Exigence findOrSave(Exigence t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Exigence result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Exigence> findAll() {
        return dao.findAll();
    }

    public List<Exigence> findByCriteria(ExigenceCriteria criteria) {
        List<Exigence> content = null;
        if (criteria != null) {
            ExigenceSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ExigenceSpecification constructSpecification(ExigenceCriteria criteria) {
        ExigenceSpecification mySpecification =  (ExigenceSpecification) RefelexivityUtil.constructObjectUsingOneParam(ExigenceSpecification.class, criteria);
        return mySpecification;
    }

    public List<Exigence> findPaginatedByCriteria(ExigenceCriteria criteria, int page, int pageSize, String order, String sortField) {
        ExigenceSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ExigenceCriteria criteria) {
        ExigenceSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Exigence> findByFamilleExigenceCode(String code){
        return dao.findByFamilleExigenceCode(code);
    }
    public List<Exigence> findByFamilleExigenceId(Long id){
        return dao.findByFamilleExigenceId(id);
    }
    public int deleteByFamilleExigenceCode(String code){
        return dao.deleteByFamilleExigenceCode(code);
    }
    public int deleteByFamilleExigenceId(Long id){
        return dao.deleteByFamilleExigenceId(id);
    }
    public long countByFamilleExigenceCode(String code){
        return dao.countByFamilleExigenceCode(code);
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
    public List<Exigence> delete(List<Exigence> list) {
		List<Exigence> result = new ArrayList();
        if (list != null) {
            for (Exigence t : list) {
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
    public Exigence create(Exigence t) {
        Exigence loaded = findByReferenceEntity(t);
        Exigence saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Exigence findWithAssociatedLists(Long id){
        Exigence result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Exigence> update(List<Exigence> ts, boolean createIfNotExist) {
        List<Exigence> result = new ArrayList<>();
        if (ts != null) {
            for (Exigence t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Exigence loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Exigence t, Exigence loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }







    public List<Exigence> importExcel(MultipartFile file) {
        if (ExcelUtil.isValidExcelFile(file)) {
            try {
                List<Exigence> items = readExcel(file.getInputStream());
                List<Exigence> result = importData(items);
                return result;
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
        return null;
    }

    private List<Exigence> readExcel(InputStream inputStream) throws IOException {
        List<Exigence> items = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    Exigence item = constructEntityFromRow(row, 0);
                    if (item != null) {
                        items.add(item);
                    }
                }
            }
        }
        return items;
    }

    private List<Exigence> importData(List<Exigence> items) {
        List<Exigence> list = new ArrayList<>();
        for (Exigence t : items) {
            Exigence founded = findByReferenceEntity(t);
            if (founded == null) {
                findOrSaveAssociatedObject(t);
                Exigence saved = dao.save(t);
                list.add(saved);
            }
        }
        return list;
    }


    private Exigence constructEntityFromRow(Row row, int startIndex) {
        int index = 0;
        try {
            Exigence item = new Exigence();
			item.setReference(ExcelUtil.getCellValueAsString(row, startIndex + (index++)));
			item.setLibelle(ExcelUtil.getCellValueAsString(row, startIndex + (index++)));
			item.setDescription(ExcelUtil.getCellValueAsString(row, startIndex + (index++)));
			if (item.getFamilleExigence() == null) {
                item.setFamilleExigence(new FamilleExigence());
            }
            String familleExigence = ExcelUtil.getCellValueAsString(row, startIndex + (index++));
            if (item.getFamilleExigence() == null) {
                item.setFamilleExigence(new FamilleExigence());
            }
            item.getFamilleExigence().setLibelle(familleExigence);

            return item;
        } catch (Exception e) {
            System.err.println("Error parsing row: " + e.getMessage());
            return null;
        }
    }




    public Exigence findByReferenceEntity(Exigence t){
        return t==null? null : dao.findByReference(t.getReference());
    }
    public void findOrSaveAssociatedObject(Exigence t){
        if( t != null) {
            t.setFamilleExigence(familleExigenceService.findOrSave(t.getFamilleExigence()));
        }
    }



    public List<Exigence> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Exigence>> getToBeSavedAndToBeDeleted(List<Exigence> oldList, List<Exigence> newList) {
        List<List<Exigence>> result = new ArrayList<>();
        List<Exigence> resultDelete = new ArrayList<>();
        List<Exigence> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Exigence> oldList, List<Exigence> newList, List<Exigence> resultUpdateOrSave, List<Exigence> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Exigence myOld = oldList.get(i);
                Exigence t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Exigence myNew = newList.get(i);
                Exigence t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private FamilleExigenceAdminService familleExigenceService ;

    public ExigenceAdminServiceImpl(ExigenceDao dao) {
        this.dao = dao;
    }

    private ExigenceDao dao;
}
