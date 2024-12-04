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

import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetExigenceEtatCriteria;
import ma.zyn.app.service.facade.admin.projet.DossierProjetExigenceEtatAdminService;
import ma.zyn.app.ws.converter.projet.DossierProjetExigenceEtatConverter;
import ma.zyn.app.ws.dto.projet.DossierProjetExigenceEtatDto;
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
@RequestMapping("/api/admin/dossierProjetExigenceEtat/")
public class DossierProjetExigenceEtatRestAdmin {




    @Operation(summary = "Finds a list of all dossierProjetExigenceEtats")
    @GetMapping("")
    public ResponseEntity<List<DossierProjetExigenceEtatDto>> findAll() throws Exception {
        ResponseEntity<List<DossierProjetExigenceEtatDto>> res = null;
        List<DossierProjetExigenceEtat> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DossierProjetExigenceEtatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all dossierProjetExigenceEtats")
    @GetMapping("optimized")
    public ResponseEntity<List<DossierProjetExigenceEtatDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DossierProjetExigenceEtatDto>> res = null;
        List<DossierProjetExigenceEtat> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DossierProjetExigenceEtatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a dossierProjetExigenceEtat by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DossierProjetExigenceEtatDto> findById(@PathVariable Long id) {
        DossierProjetExigenceEtat t = service.findById(id);
        if (t != null) {
            DossierProjetExigenceEtatDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a dossierProjetExigenceEtat by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DossierProjetExigenceEtatDto> findByLibelle(@PathVariable String libelle) {
	    DossierProjetExigenceEtat t = service.findByReferenceEntity(new DossierProjetExigenceEtat(libelle));
        if (t != null) {
            DossierProjetExigenceEtatDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  dossierProjetExigenceEtat")
    @PostMapping("")
    public ResponseEntity<DossierProjetExigenceEtatDto> save(@RequestBody DossierProjetExigenceEtatDto dto) throws Exception {
        if(dto!=null){
            DossierProjetExigenceEtat myT = converter.toItem(dto);
            DossierProjetExigenceEtat t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DossierProjetExigenceEtatDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  dossierProjetExigenceEtat")
    @PutMapping("")
    public ResponseEntity<DossierProjetExigenceEtatDto> update(@RequestBody DossierProjetExigenceEtatDto dto) throws Exception {
        ResponseEntity<DossierProjetExigenceEtatDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DossierProjetExigenceEtat t = service.findById(dto.getId());
            converter.copy(dto,t);
            DossierProjetExigenceEtat updated = service.update(t);
            DossierProjetExigenceEtatDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of dossierProjetExigenceEtat")
    @PostMapping("multiple")
    public ResponseEntity<List<DossierProjetExigenceEtatDto>> delete(@RequestBody List<DossierProjetExigenceEtatDto> dtos) throws Exception {
        ResponseEntity<List<DossierProjetExigenceEtatDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<DossierProjetExigenceEtat> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified dossierProjetExigenceEtat")
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


    @Operation(summary = "Finds a dossierProjetExigenceEtat and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DossierProjetExigenceEtatDto> findWithAssociatedLists(@PathVariable Long id) {
        DossierProjetExigenceEtat loaded =  service.findWithAssociatedLists(id);
        DossierProjetExigenceEtatDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds dossierProjetExigenceEtats by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DossierProjetExigenceEtatDto>> findByCriteria(@RequestBody DossierProjetExigenceEtatCriteria criteria) throws Exception {
        ResponseEntity<List<DossierProjetExigenceEtatDto>> res = null;
        List<DossierProjetExigenceEtat> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DossierProjetExigenceEtatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated dossierProjetExigenceEtats by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DossierProjetExigenceEtatCriteria criteria) throws Exception {
        List<DossierProjetExigenceEtat> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<DossierProjetExigenceEtatDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets dossierProjetExigenceEtat data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DossierProjetExigenceEtatCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DossierProjetExigenceEtatDto> findDtos(List<DossierProjetExigenceEtat> list){
        List<DossierProjetExigenceEtatDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DossierProjetExigenceEtatDto> getDtoResponseEntity(DossierProjetExigenceEtatDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public DossierProjetExigenceEtatRestAdmin(DossierProjetExigenceEtatAdminService service, DossierProjetExigenceEtatConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final DossierProjetExigenceEtatAdminService service;
    private final DossierProjetExigenceEtatConverter converter;





}
