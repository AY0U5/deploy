package ma.zyn.app.ws.converter.projet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.projet.DossierProjetConverter;
import ma.zyn.app.bean.core.projet.DossierProjet;
import ma.zyn.app.ws.converter.exigence.ExigenceConverter;
import ma.zyn.app.bean.core.exigence.Exigence;

import ma.zyn.app.bean.core.projet.DossierProjet;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.projet.DossierProjetExigence;
import ma.zyn.app.ws.dto.projet.DossierProjetExigenceDto;

@Component
public class DossierProjetExigenceConverter {

    @Autowired
    private DossierProjetConverter dossierProjetConverter ;
    @Autowired
    private ExigenceConverter exigenceConverter ;
    private boolean exigence;
    private boolean dossierProjet;

    public  DossierProjetExigenceConverter() {
        initObject(true);
    }

    public DossierProjetExigence toItem(DossierProjetExigenceDto dto) {
        if (dto == null) {
            return null;
        } else {
        DossierProjetExigence item = new DossierProjetExigence();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCommentaire()))
                item.setCommentaire(dto.getCommentaire());
            if(dto.getEnabled() != null)
                item.setEnabled(dto.getEnabled());
            if(this.exigence && dto.getExigence()!=null)
                item.setExigence(exigenceConverter.toItem(dto.getExigence())) ;

            if(dto.getDossierProjet() != null && dto.getDossierProjet().getId() != null){
                item.setDossierProjet(new DossierProjet());
                item.getDossierProjet().setId(dto.getDossierProjet().getId());
                item.getDossierProjet().setLibelle(dto.getDossierProjet().getLibelle());
            }




        return item;
        }
    }


    public DossierProjetExigenceDto toDto(DossierProjetExigence item) {
        if (item == null) {
            return null;
        } else {
            DossierProjetExigenceDto dto = new DossierProjetExigenceDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCommentaire()))
                dto.setCommentaire(item.getCommentaire());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(this.exigence && item.getExigence()!=null) {
                dto.setExigence(exigenceConverter.toDto(item.getExigence())) ;

            }
            if(this.dossierProjet && item.getDossierProjet()!=null) {
                dto.setDossierProjet(dossierProjetConverter.toDto(item.getDossierProjet())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.exigence = value;
        this.dossierProjet = value;
    }
	
    public List<DossierProjetExigence> toItem(List<DossierProjetExigenceDto> dtos) {
        List<DossierProjetExigence> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DossierProjetExigenceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DossierProjetExigenceDto> toDto(List<DossierProjetExigence> items) {
        List<DossierProjetExigenceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DossierProjetExigence item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DossierProjetExigenceDto dto, DossierProjetExigence t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getExigence() == null  && dto.getExigence() != null){
            t.setExigence(new Exigence());
        }else if (t.getExigence() != null  && dto.getExigence() != null){
            t.setExigence(null);
            t.setExigence(new Exigence());
        }
        if(t.getDossierProjet() == null  && dto.getDossierProjet() != null){
            t.setDossierProjet(new DossierProjet());
        }else if (t.getDossierProjet() != null  && dto.getDossierProjet() != null){
            t.setDossierProjet(null);
            t.setDossierProjet(new DossierProjet());
        }
        if (dto.getExigence() != null)
        exigenceConverter.copy(dto.getExigence(), t.getExigence());
        if (dto.getDossierProjet() != null)
        dossierProjetConverter.copy(dto.getDossierProjet(), t.getDossierProjet());
    }

    public List<DossierProjetExigence> copy(List<DossierProjetExigenceDto> dtos) {
        List<DossierProjetExigence> result = new ArrayList<>();
        if (dtos != null) {
            for (DossierProjetExigenceDto dto : dtos) {
                DossierProjetExigence instance = new DossierProjetExigence();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
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
    public boolean  isExigence(){
        return this.exigence;
    }
    public void  setExigence(boolean exigence){
        this.exigence = exigence;
    }
    public boolean  isDossierProjet(){
        return this.dossierProjet;
    }
    public void  setDossierProjet(boolean dossierProjet){
        this.dossierProjet = dossierProjet;
    }
}
