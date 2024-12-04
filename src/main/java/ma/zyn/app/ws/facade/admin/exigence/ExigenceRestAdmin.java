package ma.zyn.app.ws.facade.admin.exigence;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.exigence.Exigence;
import ma.zyn.app.dao.criteria.core.exigence.ExigenceCriteria;
import ma.zyn.app.service.facade.admin.exigence.ExigenceAdminService;
import ma.zyn.app.ws.converter.exigence.ExigenceConverter;
import ma.zyn.app.ws.dto.exigence.ExigenceDto;
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
@RequestMapping("/api/admin/exigence/")
public class ExigenceRestAdmin {


    @Operation(summary = "Import excel")
    @PostMapping("import-excel")
    public ResponseEntity<List<Exigence>> importExcel(@RequestParam("file") MultipartFile file){
        List<Exigence> items = this.service.importExcel(file);
        return ResponseEntity.ok(items);
    }


    @Operation(summary = "Finds a list of all exigences")
    @GetMapping("")
    public ResponseEntity<List<ExigenceDto>> findAll() throws Exception {
        ResponseEntity<List<ExigenceDto>> res = null;
        List<Exigence> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ExigenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all exigences")
    @GetMapping("optimized")
    public ResponseEntity<List<ExigenceDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ExigenceDto>> res = null;
        List<Exigence> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ExigenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a exigence by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ExigenceDto> findById(@PathVariable Long id) {
        Exigence t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ExigenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a exigence by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ExigenceDto> findByLibelle(@PathVariable String libelle) {
	    Exigence t = service.findByReferenceEntity(new Exigence(libelle));
        if (t != null) {
            converter.init(true);
            ExigenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  exigence")
    @PostMapping("")
    public ResponseEntity<ExigenceDto> save(@RequestBody ExigenceDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Exigence myT = converter.toItem(dto);
            Exigence t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ExigenceDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  exigence")
    @PutMapping("")
    public ResponseEntity<ExigenceDto> update(@RequestBody ExigenceDto dto) throws Exception {
        ResponseEntity<ExigenceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Exigence t = service.findById(dto.getId());
            converter.copy(dto,t);
            Exigence updated = service.update(t);
            ExigenceDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of exigence")
    @PostMapping("multiple")
    public ResponseEntity<List<ExigenceDto>> delete(@RequestBody List<ExigenceDto> dtos) throws Exception {
        ResponseEntity<List<ExigenceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Exigence> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified exigence")
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

    @Operation(summary = "find by familleExigence code")
    @GetMapping("familleExigence/code/{code}")
    public List<ExigenceDto> findByFamilleExigenceCode(@PathVariable String code){
        return findDtos(service.findByFamilleExigenceCode(code));
    }
    @Operation(summary = "delete by familleExigence code")
    @DeleteMapping("familleExigence/code/{code}")
    public int deleteByFamilleExigenceCode(@PathVariable String code){
        return service.deleteByFamilleExigenceCode(code);
    }

    @Operation(summary = "Finds a exigence and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ExigenceDto> findWithAssociatedLists(@PathVariable Long id) {
        Exigence loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ExigenceDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds exigences by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ExigenceDto>> findByCriteria(@RequestBody ExigenceCriteria criteria) throws Exception {
        ResponseEntity<List<ExigenceDto>> res = null;
        List<Exigence> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ExigenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated exigences by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ExigenceCriteria criteria) throws Exception {
        List<Exigence> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ExigenceDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets exigence data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ExigenceCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ExigenceDto> findDtos(List<Exigence> list){
        converter.initObject(true);
        List<ExigenceDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ExigenceDto> getDtoResponseEntity(ExigenceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ExigenceRestAdmin(ExigenceAdminService service, ExigenceConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ExigenceAdminService service;
    private final ExigenceConverter converter;





}
