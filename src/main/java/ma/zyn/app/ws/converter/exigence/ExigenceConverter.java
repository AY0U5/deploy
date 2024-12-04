package ma.zyn.app.ws.converter.exigence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.exigence.FamilleExigenceConverter;
import ma.zyn.app.bean.core.exigence.FamilleExigence;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.exigence.Exigence;
import ma.zyn.app.ws.dto.exigence.ExigenceDto;

@Component
public class ExigenceConverter {

    @Autowired
    private FamilleExigenceConverter familleExigenceConverter ;
    private boolean familleExigence;

    public  ExigenceConverter() {
        initObject(true);
    }

    public Exigence toItem(ExigenceDto dto) {
        if (dto == null) {
            return null;
        } else {
        Exigence item = new Exigence();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.familleExigence && dto.getFamilleExigence()!=null)
                item.setFamilleExigence(familleExigenceConverter.toItem(dto.getFamilleExigence())) ;




        return item;
        }
    }


    public ExigenceDto toDto(Exigence item) {
        if (item == null) {
            return null;
        } else {
            ExigenceDto dto = new ExigenceDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.familleExigence && item.getFamilleExigence()!=null) {
                dto.setFamilleExigence(familleExigenceConverter.toDto(item.getFamilleExigence())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.familleExigence = value;
    }
	
    public List<Exigence> toItem(List<ExigenceDto> dtos) {
        List<Exigence> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ExigenceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ExigenceDto> toDto(List<Exigence> items) {
        List<ExigenceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Exigence item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ExigenceDto dto, Exigence t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getFamilleExigence() == null  && dto.getFamilleExigence() != null){
            t.setFamilleExigence(new FamilleExigence());
        }else if (t.getFamilleExigence() != null  && dto.getFamilleExigence() != null){
            t.setFamilleExigence(null);
            t.setFamilleExigence(new FamilleExigence());
        }
        if (dto.getFamilleExigence() != null)
        familleExigenceConverter.copy(dto.getFamilleExigence(), t.getFamilleExigence());
    }

    public List<Exigence> copy(List<ExigenceDto> dtos) {
        List<Exigence> result = new ArrayList<>();
        if (dtos != null) {
            for (ExigenceDto dto : dtos) {
                Exigence instance = new Exigence();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public FamilleExigenceConverter getFamilleExigenceConverter(){
        return this.familleExigenceConverter;
    }
    public void setFamilleExigenceConverter(FamilleExigenceConverter familleExigenceConverter ){
        this.familleExigenceConverter = familleExigenceConverter;
    }
    public boolean  isFamilleExigence(){
        return this.familleExigence;
    }
    public void  setFamilleExigence(boolean familleExigence){
        this.familleExigence = familleExigence;
    }
}
