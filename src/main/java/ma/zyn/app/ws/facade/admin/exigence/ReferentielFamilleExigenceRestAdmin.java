package ma.zyn.app.ws.facade.admin.exigence;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.exigence.ReferentielFamilleExigence;
import ma.zyn.app.dao.criteria.core.exigence.ReferentielFamilleExigenceCriteria;
import ma.zyn.app.service.facade.admin.exigence.ReferentielFamilleExigenceAdminService;
import ma.zyn.app.ws.converter.exigence.ReferentielFamilleExigenceConverter;
import ma.zyn.app.ws.dto.exigence.ReferentielFamilleExigenceDto;
import ma.zyn.app.zynerator.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/referentielFamilleExigence/")
public class ReferentielFamilleExigenceRestAdmin {




    @Operation(summary = "Finds a list of all referentielFamilleExigences")
    @GetMapping("")
    public ResponseEntity<List<ReferentielFamilleExigenceDto>> findAll() throws Exception {
        ResponseEntity<List<ReferentielFamilleExigenceDto>> res = null;
        List<ReferentielFamilleExigence> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ReferentielFamilleExigenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all referentielFamilleExigences")
    @GetMapping("optimized")
    public ResponseEntity<List<ReferentielFamilleExigenceDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ReferentielFamilleExigenceDto>> res = null;
        List<ReferentielFamilleExigence> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ReferentielFamilleExigenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a referentielFamilleExigence by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ReferentielFamilleExigenceDto> findById(@PathVariable Long id) {
        ReferentielFamilleExigence t = service.findById(id);
        if (t != null) {
            ReferentielFamilleExigenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a referentielFamilleExigence by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ReferentielFamilleExigenceDto> findByLibelle(@PathVariable String libelle) {
	    ReferentielFamilleExigence t = service.findByReferenceEntity(new ReferentielFamilleExigence(libelle));
        if (t != null) {
            ReferentielFamilleExigenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  referentielFamilleExigence")
    @PostMapping("")
    public ResponseEntity<ReferentielFamilleExigenceDto> save(@RequestBody ReferentielFamilleExigenceDto dto) throws Exception {
        if(dto!=null){
            ReferentielFamilleExigence myT = converter.toItem(dto);
            ReferentielFamilleExigence t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ReferentielFamilleExigenceDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  referentielFamilleExigence")
    @PutMapping("")
    public ResponseEntity<ReferentielFamilleExigenceDto> update(@RequestBody ReferentielFamilleExigenceDto dto) throws Exception {
        ResponseEntity<ReferentielFamilleExigenceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ReferentielFamilleExigence t = service.findById(dto.getId());
            converter.copy(dto,t);
            ReferentielFamilleExigence updated = service.update(t);
            ReferentielFamilleExigenceDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of referentielFamilleExigence")
    @PostMapping("multiple")
    public ResponseEntity<List<ReferentielFamilleExigenceDto>> delete(@RequestBody List<ReferentielFamilleExigenceDto> dtos) throws Exception {
        ResponseEntity<List<ReferentielFamilleExigenceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ReferentielFamilleExigence> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified referentielFamilleExigence")
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


    @Operation(summary = "Finds a referentielFamilleExigence and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ReferentielFamilleExigenceDto> findWithAssociatedLists(@PathVariable Long id) {
        ReferentielFamilleExigence loaded =  service.findWithAssociatedLists(id);
        ReferentielFamilleExigenceDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds referentielFamilleExigences by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ReferentielFamilleExigenceDto>> findByCriteria(@RequestBody ReferentielFamilleExigenceCriteria criteria) throws Exception {
        ResponseEntity<List<ReferentielFamilleExigenceDto>> res = null;
        List<ReferentielFamilleExigence> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ReferentielFamilleExigenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated referentielFamilleExigences by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ReferentielFamilleExigenceCriteria criteria) throws Exception {
        List<ReferentielFamilleExigence> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ReferentielFamilleExigenceDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets referentielFamilleExigence data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ReferentielFamilleExigenceCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ReferentielFamilleExigenceDto> findDtos(List<ReferentielFamilleExigence> list){
        List<ReferentielFamilleExigenceDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ReferentielFamilleExigenceDto> getDtoResponseEntity(ReferentielFamilleExigenceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ReferentielFamilleExigenceRestAdmin(ReferentielFamilleExigenceAdminService service, ReferentielFamilleExigenceConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ReferentielFamilleExigenceAdminService service;
    private final ReferentielFamilleExigenceConverter converter;





}
