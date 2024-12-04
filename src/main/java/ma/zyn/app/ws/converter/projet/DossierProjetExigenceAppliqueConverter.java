package ma.zyn.app.ws.converter.projet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.projet.DossierProjetDocumentConverter;
import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import ma.zyn.app.ws.converter.projet.DossierProjetExigenceEtatConverter;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import ma.zyn.app.ws.converter.exigence.ExigenceConverter;
import ma.zyn.app.bean.core.exigence.Exigence;

import ma.zyn.app.bean.core.projet.DossierProjetDocument;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique;
import ma.zyn.app.ws.dto.projet.DossierProjetExigenceAppliqueDto;

@Component
public class DossierProjetExigenceAppliqueConverter {

    @Autowired
    private DossierProjetDocumentConverter dossierProjetDocumentConverter ;
    @Autowired
    private DossierProjetExigenceEtatConverter dossierProjetExigenceEtatConverter ;
    @Autowired
    private ExigenceConverter exigenceConverter ;
    private boolean dossierProjetDocument;
    private boolean exigence;
    private boolean dossierProjetExigenceEtat;

    public  DossierProjetExigenceAppliqueConverter() {
        initObject(true);
    }

    public DossierProjetExigenceApplique toItem(DossierProjetExigenceAppliqueDto dto) {
        if (dto == null) {
            return null;
        } else {
        DossierProjetExigenceApplique item = new DossierProjetExigenceApplique();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCommentaire()))
                item.setCommentaire(dto.getCommentaire());
            if(StringUtil.isNotEmpty(dto.getTauxPrecision()))
                item.setTauxPrecision(dto.getTauxPrecision());
            if(StringUtil.isNotEmpty(dto.getPages()))
                item.setPages(dto.getPages());
            if(dto.getDossierProjetDocument() != null && dto.getDossierProjetDocument().getId() != null){
                item.setDossierProjetDocument(new DossierProjetDocument());
                item.getDossierProjetDocument().setId(dto.getDossierProjetDocument().getId());
                item.getDossierProjetDocument().setLibelle(dto.getDossierProjetDocument().getLibelle());
            }

            if(this.exigence && dto.getExigence()!=null)
                item.setExigence(exigenceConverter.toItem(dto.getExigence())) ;

            if(this.dossierProjetExigenceEtat && dto.getDossierProjetExigenceEtat()!=null)
                item.setDossierProjetExigenceEtat(dossierProjetExigenceEtatConverter.toItem(dto.getDossierProjetExigenceEtat())) ;




        return item;
        }
    }


    public DossierProjetExigenceAppliqueDto toDto(DossierProjetExigenceApplique item) {
        if (item == null) {
            return null;
        } else {
            DossierProjetExigenceAppliqueDto dto = new DossierProjetExigenceAppliqueDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCommentaire()))
                dto.setCommentaire(item.getCommentaire());
            if(StringUtil.isNotEmpty(item.getTauxPrecision()))
                dto.setTauxPrecision(item.getTauxPrecision());
            if(StringUtil.isNotEmpty(item.getPages()))
                dto.setPages(item.getPages());
            if(this.dossierProjetDocument && item.getDossierProjetDocument()!=null) {
                dto.setDossierProjetDocument(dossierProjetDocumentConverter.toDto(item.getDossierProjetDocument())) ;

            }
            if(this.exigence && item.getExigence()!=null) {
                dto.setExigence(exigenceConverter.toDto(item.getExigence())) ;

            }
            if(this.dossierProjetExigenceEtat && item.getDossierProjetExigenceEtat()!=null) {
                dto.setDossierProjetExigenceEtat(dossierProjetExigenceEtatConverter.toDto(item.getDossierProjetExigenceEtat())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.dossierProjetDocument = value;
        this.exigence = value;
        this.dossierProjetExigenceEtat = value;
    }
	
    public List<DossierProjetExigenceApplique> toItem(List<DossierProjetExigenceAppliqueDto> dtos) {
        List<DossierProjetExigenceApplique> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DossierProjetExigenceAppliqueDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DossierProjetExigenceAppliqueDto> toDto(List<DossierProjetExigenceApplique> items) {
        List<DossierProjetExigenceAppliqueDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DossierProjetExigenceApplique item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DossierProjetExigenceAppliqueDto dto, DossierProjetExigenceApplique t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getDossierProjetDocument() == null  && dto.getDossierProjetDocument() != null){
            t.setDossierProjetDocument(new DossierProjetDocument());
        }else if (t.getDossierProjetDocument() != null  && dto.getDossierProjetDocument() != null){
            t.setDossierProjetDocument(null);
            t.setDossierProjetDocument(new DossierProjetDocument());
        }
        if(t.getExigence() == null  && dto.getExigence() != null){
            t.setExigence(new Exigence());
        }else if (t.getExigence() != null  && dto.getExigence() != null){
            t.setExigence(null);
            t.setExigence(new Exigence());
        }
        if(t.getDossierProjetExigenceEtat() == null  && dto.getDossierProjetExigenceEtat() != null){
            t.setDossierProjetExigenceEtat(new DossierProjetExigenceEtat());
        }else if (t.getDossierProjetExigenceEtat() != null  && dto.getDossierProjetExigenceEtat() != null){
            t.setDossierProjetExigenceEtat(null);
            t.setDossierProjetExigenceEtat(new DossierProjetExigenceEtat());
        }
        if (dto.getDossierProjetDocument() != null)
        dossierProjetDocumentConverter.copy(dto.getDossierProjetDocument(), t.getDossierProjetDocument());
        if (dto.getExigence() != null)
        exigenceConverter.copy(dto.getExigence(), t.getExigence());
        if (dto.getDossierProjetExigenceEtat() != null)
        dossierProjetExigenceEtatConverter.copy(dto.getDossierProjetExigenceEtat(), t.getDossierProjetExigenceEtat());
    }

    public List<DossierProjetExigenceApplique> copy(List<DossierProjetExigenceAppliqueDto> dtos) {
        List<DossierProjetExigenceApplique> result = new ArrayList<>();
        if (dtos != null) {
            for (DossierProjetExigenceAppliqueDto dto : dtos) {
                DossierProjetExigenceApplique instance = new DossierProjetExigenceApplique();
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
    public DossierProjetExigenceEtatConverter getDossierProjetExigenceEtatConverter(){
        return this.dossierProjetExigenceEtatConverter;
    }
    public void setDossierProjetExigenceEtatConverter(DossierProjetExigenceEtatConverter dossierProjetExigenceEtatConverter ){
        this.dossierProjetExigenceEtatConverter = dossierProjetExigenceEtatConverter;
    }
    public ExigenceConverter getExigenceConverter(){
        return this.exigenceConverter;
    }
    public void setExigenceConverter(ExigenceConverter exigenceConverter ){
        this.exigenceConverter = exigenceConverter;
    }
    public boolean  isDossierProjetDocument(){
        return this.dossierProjetDocument;
    }
    public void  setDossierProjetDocument(boolean dossierProjetDocument){
        this.dossierProjetDocument = dossierProjetDocument;
    }
    public boolean  isExigence(){
        return this.exigence;
    }
    public void  setExigence(boolean exigence){
        this.exigence = exigence;
    }
    public boolean  isDossierProjetExigenceEtat(){
        return this.dossierProjetExigenceEtat;
    }
    public void  setDossierProjetExigenceEtat(boolean dossierProjetExigenceEtat){
        this.dossierProjetExigenceEtat = dossierProjetExigenceEtat;
    }
}
