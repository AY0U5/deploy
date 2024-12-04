package ma.zyn.app.ws.facade.admin.exigence;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.exigence.FamilleExigence;
import ma.zyn.app.dao.criteria.core.exigence.FamilleExigenceCriteria;
import ma.zyn.app.service.facade.admin.exigence.FamilleExigenceAdminService;
import ma.zyn.app.ws.converter.exigence.FamilleExigenceConverter;
import ma.zyn.app.ws.dto.exigence.FamilleExigenceDto;
import ma.zyn.app.zynerator.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/familleExigence/")
public class FamilleExigenceRestAdmin {




    @Operation(summary = "Finds a list of all familleExigences")
    @GetMapping("")
    public ResponseEntity<List<FamilleExigenceDto>> findAll() throws Exception {
        ResponseEntity<List<FamilleExigenceDto>> res = null;
        List<FamilleExigence> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<FamilleExigenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all familleExigences")
    @GetMapping("optimized")
    public ResponseEntity<List<FamilleExigenceDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<FamilleExigenceDto>> res = null;
        List<FamilleExigence> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<FamilleExigenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a familleExigence by id")
    @GetMapping("id/{id}")
    public ResponseEntity<FamilleExigenceDto> findById(@PathVariable Long id) {
        FamilleExigence t = service.findById(id);
        if (t != null) {
            converter.init(true);
            FamilleExigenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a familleExigence by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<FamilleExigenceDto> findByLibelle(@PathVariable String libelle) {
	    FamilleExigence t = service.findByReferenceEntity(new FamilleExigence(libelle));
        if (t != null) {
            converter.init(true);
            FamilleExigenceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  familleExigence")
    @PostMapping("")
    public ResponseEntity<FamilleExigenceDto> save(@RequestBody FamilleExigenceDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            FamilleExigence myT = converter.toItem(dto);
            FamilleExigence t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                FamilleExigenceDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  familleExigence")
    @PutMapping("")
    public ResponseEntity<FamilleExigenceDto> update(@RequestBody FamilleExigenceDto dto) throws Exception {
        ResponseEntity<FamilleExigenceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            FamilleExigence t = service.findById(dto.getId());
            converter.copy(dto,t);
            FamilleExigence updated = service.update(t);
            FamilleExigenceDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of familleExigence")
    @PostMapping("multiple")
    public ResponseEntity<List<FamilleExigenceDto>> delete(@RequestBody List<FamilleExigenceDto> dtos) throws Exception {
        ResponseEntity<List<FamilleExigenceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<FamilleExigence> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified familleExigence")
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

    @Operation(summary = "find by referentielFamilleExigence code")
    @GetMapping("referentielFamilleExigence/code/{code}")
    public List<FamilleExigenceDto> findByReferentielFamilleExigenceCode(@PathVariable String code){
        return findDtos(service.findByReferentielFamilleExigenceCode(code));
    }
    @Operation(summary = "delete by referentielFamilleExigence code")
    @DeleteMapping("referentielFamilleExigence/code/{code}")
    public int deleteByReferentielFamilleExigenceCode(@PathVariable String code){
        return service.deleteByReferentielFamilleExigenceCode(code);
    }

    @Operation(summary = "Finds a familleExigence and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<FamilleExigenceDto> findWithAssociatedLists(@PathVariable Long id) {
        FamilleExigence loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        FamilleExigenceDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds familleExigences by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<FamilleExigenceDto>> findByCriteria(@RequestBody FamilleExigenceCriteria criteria) throws Exception {
        ResponseEntity<List<FamilleExigenceDto>> res = null;
        List<FamilleExigence> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<FamilleExigenceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated familleExigences by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody FamilleExigenceCriteria criteria) throws Exception {
        List<FamilleExigence> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<FamilleExigenceDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets familleExigence data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody FamilleExigenceCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<FamilleExigenceDto> findDtos(List<FamilleExigence> list){
        converter.initObject(true);
        List<FamilleExigenceDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<FamilleExigenceDto> getDtoResponseEntity(FamilleExigenceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public FamilleExigenceRestAdmin(FamilleExigenceAdminService service, FamilleExigenceConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final FamilleExigenceAdminService service;
    private final FamilleExigenceConverter converter;





}
