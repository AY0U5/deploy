package ma.zyn.app.ws.converter.projet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.projet.DossierProjetExigenceEtatConverter;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import ma.zyn.app.ws.converter.projet.DossierProjetExigenceAppliqueConverter;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique;
import ma.zyn.app.ws.converter.projet.DossierProjetConverter;
import ma.zyn.app.bean.core.projet.DossierProjet;
import ma.zyn.app.ws.converter.exigence.ExigenceConverter;
import ma.zyn.app.bean.core.exigence.Exigence;

import ma.zyn.app.bean.core.projet.DossierProjet;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import ma.zyn.app.ws.dto.projet.DossierProjetDocumentDto;

@Component
public class DossierProjetDocumentConverter {

    @Autowired
    private DossierProjetExigenceEtatConverter dossierProjetExigenceEtatConverter ;
    @Autowired
    private DossierProjetExigenceAppliqueConverter dossierProjetExigenceAppliqueConverter ;
    @Autowired
    private DossierProjetConverter dossierProjetConverter ;
    @Autowired
    private ExigenceConverter exigenceConverter ;
    private boolean dossierProjet;
    private boolean dossierProjetExigenceAppliques;

    public  DossierProjetDocumentConverter() {
        init(true);
    }

    public DossierProjetDocument toItem(DossierProjetDocumentDto dto) {
        if (dto == null) {
            return null;
        } else {
        DossierProjetDocument item = new DossierProjetDocument();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getPath()))
                item.setPath(dto.getPath());
            if(StringUtil.isNotEmpty(dto.getContent()))
                item.setContent(dto.getContent());
            if(dto.getDossierProjet() != null && dto.getDossierProjet().getId() != null){
                item.setDossierProjet(new DossierProjet());
                item.getDossierProjet().setId(dto.getDossierProjet().getId());
                item.getDossierProjet().setLibelle(dto.getDossierProjet().getLibelle());
            }


            if(this.dossierProjetExigenceAppliques && ListUtil.isNotEmpty(dto.getDossierProjetExigenceAppliques()))
                item.setDossierProjetExigenceAppliques(dossierProjetExigenceAppliqueConverter.toItem(dto.getDossierProjetExigenceAppliques()));


        return item;
        }
    }


    public DossierProjetDocumentDto toDto(DossierProjetDocument item) {
        if (item == null) {
            return null;
        } else {
            DossierProjetDocumentDto dto = new DossierProjetDocumentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getPath()))
                dto.setPath(item.getPath());
            if(StringUtil.isNotEmpty(item.getContent()))
                dto.setContent(item.getContent());
            if(this.dossierProjet && item.getDossierProjet()!=null) {
                dto.setDossierProjet(dossierProjetConverter.toDto(item.getDossierProjet())) ;

            }
        if(this.dossierProjetExigenceAppliques && ListUtil.isNotEmpty(item.getDossierProjetExigenceAppliques())){
            dossierProjetExigenceAppliqueConverter.init(true);
            dossierProjetExigenceAppliqueConverter.setDossierProjetDocument(false);
            dto.setDossierProjetExigenceAppliques(dossierProjetExigenceAppliqueConverter.toDto(item.getDossierProjetExigenceAppliques()));
            dossierProjetExigenceAppliqueConverter.setDossierProjetDocument(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.dossierProjetExigenceAppliques = value;
    }
    public void initObject(boolean value) {
        this.dossierProjet = value;
    }
	
    public List<DossierProjetDocument> toItem(List<DossierProjetDocumentDto> dtos) {
        List<DossierProjetDocument> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DossierProjetDocumentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DossierProjetDocumentDto> toDto(List<DossierProjetDocument> items) {
        List<DossierProjetDocumentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DossierProjetDocument item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DossierProjetDocumentDto dto, DossierProjetDocument t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getDossierProjet() == null  && dto.getDossierProjet() != null){
            t.setDossierProjet(new DossierProjet());
        }else if (t.getDossierProjet() != null  && dto.getDossierProjet() != null){
            t.setDossierProjet(null);
            t.setDossierProjet(new DossierProjet());
        }
        if (dto.getDossierProjet() != null)
        dossierProjetConverter.copy(dto.getDossierProjet(), t.getDossierProjet());
        if (dto.getDossierProjetExigenceAppliques() != null)
            t.setDossierProjetExigenceAppliques(dossierProjetExigenceAppliqueConverter.copy(dto.getDossierProjetExigenceAppliques()));
    }

    public List<DossierProjetDocument> copy(List<DossierProjetDocumentDto> dtos) {
        List<DossierProjetDocument> result = new ArrayList<>();
        if (dtos != null) {
            for (DossierProjetDocumentDto dto : dtos) {
                DossierProjetDocument instance = new DossierProjetDocument();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public DossierProjetExigenceEtatConverter getDossierProjetExigenceEtatConverter(){
        return this.dossierProjetExigenceEtatConverter;
    }
    public void setDossierProjetExigenceEtatConverter(DossierProjetExigenceEtatConverter dossierProjetExigenceEtatConverter ){
        this.dossierProjetExigenceEtatConverter = dossierProjetExigenceEtatConverter;
    }
    public DossierProjetExigenceAppliqueConverter getDossierProjetExigenceAppliqueConverter(){
        return this.dossierProjetExigenceAppliqueConverter;
    }
    public void setDossierProjetExigenceAppliqueConverter(DossierProjetExigenceAppliqueConverter dossierProjetExigenceAppliqueConverter ){
        this.dossierProjetExigenceAppliqueConverter = dossierProjetExigenceAppliqueConverter;
    }
    public DossierProjetConverter getDossierProjetConverter(){
        return this.dossierProjetConverter;
    }
    public void setDossierProjetConverter(DossierProjetConverter dossierProjetConverter ){
        this.dossierProjetConverter = dossierProjetConverter;
    }
    public ExigenceConverter getExigenceConverter(){
        return this.exigenceConverter;
    }
    public void setExigenceConverter(ExigenceConverter exigenceConverter ){
        this.exigenceConverter = exigenceConverter;
    }
    public boolean  isDossierProjet(){
        return this.dossierProjet;
    }
    public void  setDossierProjet(boolean dossierProjet){
        this.dossierProjet = dossierProjet;
    }
    public boolean  isDossierProjetExigenceAppliques(){
        return this.dossierProjetExigenceAppliques ;
    }
    public void  setDossierProjetExigenceAppliques(boolean dossierProjetExigenceAppliques ){
        this.dossierProjetExigenceAppliques  = dossierProjetExigenceAppliques ;
    }
}
