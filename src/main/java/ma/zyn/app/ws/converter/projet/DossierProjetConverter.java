package ma.zyn.app.ws.converter.projet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.projet.DossierProjetDocumentConverter;
import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import ma.zyn.app.ws.converter.projet.DossierProjetExigenceAppliqueConverter;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique;
import ma.zyn.app.ws.converter.projet.DossierProjetExigenceConverter;
import ma.zyn.app.bean.core.projet.DossierProjetExigence;
import ma.zyn.app.ws.converter.exigence.ExigenceConverter;
import ma.zyn.app.bean.core.exigence.Exigence;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.projet.DossierProjet;
import ma.zyn.app.ws.dto.projet.DossierProjetDto;

@Component
public class DossierProjetConverter {

    @Autowired
    private DossierProjetDocumentConverter dossierProjetDocumentConverter ;
    @Autowired
    private DossierProjetExigenceAppliqueConverter dossierProjetExigenceAppliqueConverter ;
    @Autowired
    private DossierProjetExigenceConverter dossierProjetExigenceConverter ;
    @Autowired
    private ExigenceConverter exigenceConverter ;
    private boolean dossierProjetExigences;
    private boolean dossierProjetDocuments;

    public  DossierProjetConverter() {
        initList(true);
    }

    public DossierProjet toItem(DossierProjetDto dto) {
        if (dto == null) {
            return null;
        } else {
        DossierProjet item = new DossierProjet();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());

            if(this.dossierProjetExigences && ListUtil.isNotEmpty(dto.getDossierProjetExigences()))
                item.setDossierProjetExigences(dossierProjetExigenceConverter.toItem(dto.getDossierProjetExigences()));
            if(this.dossierProjetDocuments && ListUtil.isNotEmpty(dto.getDossierProjetDocuments()))
                item.setDossierProjetDocuments(dossierProjetDocumentConverter.toItem(dto.getDossierProjetDocuments()));


        return item;
        }
    }


    public DossierProjetDto toDto(DossierProjet item) {
        if (item == null) {
            return null;
        } else {
            DossierProjetDto dto = new DossierProjetDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
        if(this.dossierProjetExigences && ListUtil.isNotEmpty(item.getDossierProjetExigences())){
            dossierProjetExigenceConverter.init(true);
            dossierProjetExigenceConverter.setDossierProjet(false);
            dto.setDossierProjetExigences(dossierProjetExigenceConverter.toDto(item.getDossierProjetExigences()));
            dossierProjetExigenceConverter.setDossierProjet(true);

        }
        if(this.dossierProjetDocuments && ListUtil.isNotEmpty(item.getDossierProjetDocuments())){
            dossierProjetDocumentConverter.init(true);
            dossierProjetDocumentConverter.setDossierProjet(false);
            dto.setDossierProjetDocuments(dossierProjetDocumentConverter.toDto(item.getDossierProjetDocuments()));
            dossierProjetDocumentConverter.setDossierProjet(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.dossierProjetExigences = value;
        this.dossierProjetDocuments = value;
    }
	
    public List<DossierProjet> toItem(List<DossierProjetDto> dtos) {
        List<DossierProjet> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DossierProjetDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DossierProjetDto> toDto(List<DossierProjet> items) {
        List<DossierProjetDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DossierProjet item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DossierProjetDto dto, DossierProjet t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getDossierProjetExigences() != null)
            t.setDossierProjetExigences(dossierProjetExigenceConverter.copy(dto.getDossierProjetExigences()));
        if (dto.getDossierProjetDocuments() != null)
            t.setDossierProjetDocuments(dossierProjetDocumentConverter.copy(dto.getDossierProjetDocuments()));
    }

    public List<DossierProjet> copy(List<DossierProjetDto> dtos) {
        List<DossierProjet> result = new ArrayList<>();
        if (dtos != null) {
            for (DossierProjetDto dto : dtos) {
                DossierProjet instance = new DossierProjet();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public DossierProjetDocumentConverter getDossierProjetDocumentConverter(){
        return this.dossierProjetDocumentConverter;
    }
    public void setDossierProjetDocumentConverter(DossierProjetDocumentConverter dossierProjetDocumentConverter ){
        this.dossierProjetDocumentConverter = dossierProjetDocumentConverter;
    }
    public DossierProjetExigenceAppliqueConverter getDossierProjetExigenceAppliqueConverter(){
        return this.dossierProjetExigenceAppliqueConverter;
    }
    public void setDossierProjetExigenceAppliqueConverter(DossierProjetExigenceAppliqueConverter dossierProjetExigenceAppliqueConverter ){
        this.dossierProjetExigenceAppliqueConverter = dossierProjetExigenceAppliqueConverter;
    }
    public DossierProjetExigenceConverter getDossierProjetExigenceConverter(){
        return this.dossierProjetExigenceConverter;
    }
    public void setDossierProjetExigenceConverter(DossierProjetExigenceConverter dossierProjetExigenceConverter ){
        this.dossierProjetExigenceConverter = dossierProjetExigenceConverter;
    }
    public ExigenceConverter getExigenceConverter(){
        return this.exigenceConverter;
    }
    public void setExigenceConverter(ExigenceConverter exigenceConverter ){
        this.exigenceConverter = exigenceConverter;
    }
    public boolean  isDossierProjetExigences(){
        return this.dossierProjetExigences ;
    }
    public void  setDossierProjetExigences(boolean dossierProjetExigences ){
        this.dossierProjetExigences  = dossierProjetExigences ;
    }
    public boolean  isDossierProjetDocuments(){
        return this.dossierProjetDocuments ;
    }
    public void  setDossierProjetDocuments(boolean dossierProjetDocuments ){
        this.dossierProjetDocuments  = dossierProjetDocuments ;
    }
}
