package ma.zyn.app.ws.facade.admin.projet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetDocumentCriteria;
import ma.zyn.app.service.facade.admin.projet.DossierProjetDocumentAdminService;
import ma.zyn.app.ws.converter.projet.DossierProjetDocumentConverter;
import ma.zyn.app.ws.dto.projet.DossierProjetDocumentDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/dossierProjetDocument/")
public class DossierProjetDocumentRestAdmin {




    @Operation(summary = "Finds a list of all dossierProjetDocuments")
    @GetMapping("")
    public ResponseEntity<List<DossierProjetDocumentDto>> findAll() throws Exception {
        ResponseEntity<List<DossierProjetDocumentDto>> res = null;
        List<DossierProjetDocument> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<DossierProjetDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all dossierProjetDocuments")
    @GetMapping("optimized")
    public ResponseEntity<List<DossierProjetDocumentDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DossierProjetDocumentDto>> res = null;
        List<DossierProjetDocument> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<DossierProjetDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a dossierProjetDocument by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DossierProjetDocumentDto> findById(@PathVariable Long id) {
        DossierProjetDocument t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DossierProjetDocumentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a dossierProjetDocument by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DossierProjetDocumentDto> findByLibelle(@PathVariable String libelle) {
	    DossierProjetDocument t = service.findByReferenceEntity(new DossierProjetDocument(libelle));
        if (t != null) {
            converter.init(true);
            DossierProjetDocumentDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  dossierProjetDocument")
    @PostMapping("")
    public ResponseEntity<DossierProjetDocumentDto> save(@RequestBody DossierProjetDocumentDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DossierProjetDocument myT = converter.toItem(dto);
            DossierProjetDocument t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DossierProjetDocumentDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  dossierProjetDocument")
    @PutMapping("")
    public ResponseEntity<DossierProjetDocumentDto> update(@RequestBody DossierProjetDocumentDto dto) throws Exception {
        ResponseEntity<DossierProjetDocumentDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DossierProjetDocument t = service.findById(dto.getId());
            converter.copy(dto,t);
            DossierProjetDocument updated = service.update(t);
            DossierProjetDocumentDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of dossierProjetDocument")
    @PostMapping("multiple")
    public ResponseEntity<List<DossierProjetDocumentDto>> delete(@RequestBody List<DossierProjetDocumentDto> dtos) throws Exception {
        ResponseEntity<List<DossierProjetDocumentDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DossierProjetDocument> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified dossierProjetDocument")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by dossierProjet id")
    @GetMapping("dossierProjet/id/{id}")
    public List<DossierProjetDocumentDto> findByDossierProjetId(@PathVariable Long id){
        return findDtos(service.findByDossierProjetId(id));
    }
    @Operation(summary = "delete by dossierProjet id")
    @DeleteMapping("dossierProjet/id/{id}")
    public int deleteByDossierProjetId(@PathVariable Long id){
        return service.deleteByDossierProjetId(id);
    }

    @Operation(summary = "Finds a dossierProjetDocument and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DossierProjetDocumentDto> findWithAssociatedLists(@PathVariable Long id) {
        DossierProjetDocument loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DossierProjetDocumentDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds dossierProjetDocuments by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DossierProjetDocumentDto>> findByCriteria(@RequestBody DossierProjetDocumentCriteria criteria) throws Exception {
        ResponseEntity<List<DossierProjetDocumentDto>> res = null;
        List<DossierProjetDocument> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<DossierProjetDocumentDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated dossierProjetDocuments by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DossierProjetDocumentCriteria criteria) throws Exception {
        List<DossierProjetDocument> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<DossierProjetDocumentDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets dossierProjetDocument data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DossierProjetDocumentCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DossierProjetDocumentDto> findDtos(List<DossierProjetDocument> list){
        converter.initList(false);
        converter.initObject(true);
        List<DossierProjetDocumentDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DossierProjetDocumentDto> getDtoResponseEntity(DossierProjetDocumentDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public DossierProjetDocumentRestAdmin(DossierProjetDocumentAdminService service, DossierProjetDocumentConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final DossierProjetDocumentAdminService service;
    private final DossierProjetDocumentConverter converter;





}
