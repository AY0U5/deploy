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

import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique;
import ma.zyn.app.dao.criteria.core.projet.DossierProjetExigenceAppliqueCriteria;
import ma.zyn.app.service.facade.admin.projet.DossierProjetExigenceAppliqueAdminService;
import ma.zyn.app.ws.converter.projet.DossierProjetExigenceAppliqueConverter;
import ma.zyn.app.ws.dto.projet.DossierProjetExigenceAppliqueDto;
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
@RequestMapping("/api/admin/dossierProjetExigenceApplique/")
public class DossierProjetExigenceAppliqueRestAdmin {




    @Operation(summary = "Finds a list of all dossierProjetExigenceAppliques")
    @GetMapping("")
    public ResponseEntity<List<DossierProjetExigenceAppliqueDto>> findAll() throws Exception {
        ResponseEntity<List<DossierProjetExigenceAppliqueDto>> res = null;
        List<DossierProjetExigenceApplique> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DossierProjetExigenceAppliqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a dossierProjetExigenceApplique by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DossierProjetExigenceAppliqueDto> findById(@PathVariable Long id) {
        DossierProjetExigenceApplique t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DossierProjetExigenceAppliqueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  dossierProjetExigenceApplique")
    @PostMapping("")
    public ResponseEntity<DossierProjetExigenceAppliqueDto> save(@RequestBody DossierProjetExigenceAppliqueDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DossierProjetExigenceApplique myT = converter.toItem(dto);
            DossierProjetExigenceApplique t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DossierProjetExigenceAppliqueDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  dossierProjetExigenceApplique")
    @PutMapping("")
    public ResponseEntity<DossierProjetExigenceAppliqueDto> update(@RequestBody DossierProjetExigenceAppliqueDto dto) throws Exception {
        ResponseEntity<DossierProjetExigenceAppliqueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DossierProjetExigenceApplique t = service.findById(dto.getId());
            converter.copy(dto,t);
            DossierProjetExigenceApplique updated = service.update(t);
            DossierProjetExigenceAppliqueDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of dossierProjetExigenceApplique")
    @PostMapping("multiple")
    public ResponseEntity<List<DossierProjetExigenceAppliqueDto>> delete(@RequestBody List<DossierProjetExigenceAppliqueDto> dtos) throws Exception {
        ResponseEntity<List<DossierProjetExigenceAppliqueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DossierProjetExigenceApplique> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified dossierProjetExigenceApplique")
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


    @Operation(summary = "Finds a dossierProjetExigenceApplique and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DossierProjetExigenceAppliqueDto> findWithAssociatedLists(@PathVariable Long id) {
        DossierProjetExigenceApplique loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DossierProjetExigenceAppliqueDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds dossierProjetExigenceAppliques by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DossierProjetExigenceAppliqueDto>> findByCriteria(@RequestBody DossierProjetExigenceAppliqueCriteria criteria) throws Exception {
        ResponseEntity<List<DossierProjetExigenceAppliqueDto>> res = null;
        List<DossierProjetExigenceApplique> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DossierProjetExigenceAppliqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated dossierProjetExigenceAppliques by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DossierProjetExigenceAppliqueCriteria criteria) throws Exception {
        List<DossierProjetExigenceApplique> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DossierProjetExigenceAppliqueDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets dossierProjetExigenceApplique data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DossierProjetExigenceAppliqueCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DossierProjetExigenceAppliqueDto> findDtos(List<DossierProjetExigenceApplique> list){
        converter.initObject(true);
        List<DossierProjetExigenceAppliqueDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DossierProjetExigenceAppliqueDto> getDtoResponseEntity(DossierProjetExigenceAppliqueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public DossierProjetExigenceAppliqueRestAdmin(DossierProjetExigenceAppliqueAdminService service, DossierProjetExigenceAppliqueConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final DossierProjetExigenceAppliqueAdminService service;
    private final DossierProjetExigenceAppliqueConverter converter;





}
