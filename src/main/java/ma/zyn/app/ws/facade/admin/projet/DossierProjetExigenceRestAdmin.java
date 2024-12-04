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

import ma.zyn.app.bean.core.projet.DossierProjetExigence;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetExigenceCriteria;
import ma.zyn.app.service.facade.admin.projet.DossierProjetExigenceAdminService;
import ma.zyn.app.ws.converter.projet.DossierProjetExigenceConverter;
import ma.zyn.app.ws.dto.projet.DossierProjetExigenceDto;
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
@RequestMapping("/api/admin/dossierProjetExigence/")
public class DossierProjetExigenceRestAdmin {




    @Operation(summary = "Finds a list of all dossierProjetExigences")
    @GetMapping("")
    public ResponseEntity<List<DossierProjetExigenceDto>> findAll() throws Exception {
        ResponseEntity<List<DossierProjetExigenceDto>> res = null;
        List<DossierProjetExigence> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DossierProjetExigenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a dossierProjetExigence by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DossierProjetExigenceDto> findById(@PathVariable Long id) {
        DossierProjetExigence t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DossierProjetExigenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  dossierProjetExigence")
    @PostMapping("")
    public ResponseEntity<DossierProjetExigenceDto> save(@RequestBody DossierProjetExigenceDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DossierProjetExigence myT = converter.toItem(dto);
            DossierProjetExigence t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DossierProjetExigenceDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  dossierProjetExigence")
    @PutMapping("")
    public ResponseEntity<DossierProjetExigenceDto> update(@RequestBody DossierProjetExigenceDto dto) throws Exception {
        ResponseEntity<DossierProjetExigenceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DossierProjetExigence t = service.findById(dto.getId());
            converter.copy(dto,t);
            DossierProjetExigence updated = service.update(t);
            DossierProjetExigenceDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of dossierProjetExigence")
    @PostMapping("multiple")
    public ResponseEntity<List<DossierProjetExigenceDto>> delete(@RequestBody List<DossierProjetExigenceDto> dtos) throws Exception {
        ResponseEntity<List<DossierProjetExigenceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DossierProjetExigence> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified dossierProjetExigence")
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


    @Operation(summary = "Finds a dossierProjetExigence and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DossierProjetExigenceDto> findWithAssociatedLists(@PathVariable Long id) {
        DossierProjetExigence loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DossierProjetExigenceDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds dossierProjetExigences by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DossierProjetExigenceDto>> findByCriteria(@RequestBody DossierProjetExigenceCriteria criteria) throws Exception {
        ResponseEntity<List<DossierProjetExigenceDto>> res = null;
        List<DossierProjetExigence> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DossierProjetExigenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated dossierProjetExigences by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DossierProjetExigenceCriteria criteria) throws Exception {
        List<DossierProjetExigence> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DossierProjetExigenceDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets dossierProjetExigence data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DossierProjetExigenceCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DossierProjetExigenceDto> findDtos(List<DossierProjetExigence> list){
        converter.initObject(true);
        List<DossierProjetExigenceDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DossierProjetExigenceDto> getDtoResponseEntity(DossierProjetExigenceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public DossierProjetExigenceRestAdmin(DossierProjetExigenceAdminService service, DossierProjetExigenceConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final DossierProjetExigenceAdminService service;
    private final DossierProjetExigenceConverter converter;





}
