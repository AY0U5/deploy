package ma.zyn.app.ws.converter.exigence;

import ma.zyn.app.bean.core.exigence.FamilleExigence;
import ma.zyn.app.bean.core.exigence.ReferentielFamilleExigence;
import ma.zyn.app.ws.dto.exigence.FamilleExigenceDto;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;
import ma.zyn.app.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FamilleExigenceConverter {

    @Autowired
    private ReferentielFamilleExigenceConverter referentielFamilleExigenceConverter ;
    private boolean referentielFamilleExigence;

    public  FamilleExigenceConverter() {
        initObject(true);
    }

    public FamilleExigence toItem(FamilleExigenceDto dto) {
        if (dto == null) {
            return null;
        } else {
        FamilleExigence item = new FamilleExigence();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(this.referentielFamilleExigence && dto.getReferentielFamilleExigence()!=null)
                item.setReferentielFamilleExigence(referentielFamilleExigenceConverter.toItem(dto.getReferentielFamilleExigence())) ;




        return item;
        }
    }


    public FamilleExigenceDto toDto(FamilleExigence item) {
        if (item == null) {
            return null;
        } else {
            FamilleExigenceDto dto = new FamilleExigenceDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(this.referentielFamilleExigence && item.getReferentielFamilleExigence()!=null) {
                dto.setReferentielFamilleExigence(referentielFamilleExigenceConverter.toDto(item.getReferentielFamilleExigence())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.referentielFamilleExigence = value;
    }
	
    public List<FamilleExigence> toItem(List<FamilleExigenceDto> dtos) {
        List<FamilleExigence> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (FamilleExigenceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<FamilleExigenceDto> toDto(List<FamilleExigence> items) {
        List<FamilleExigenceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (FamilleExigence item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(FamilleExigenceDto dto, FamilleExigence t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getReferentielFamilleExigence() == null  && dto.getReferentielFamilleExigence() != null){
            t.setReferentielFamilleExigence(new ReferentielFamilleExigence());
        }else if (t.getReferentielFamilleExigence() != null  && dto.getReferentielFamilleExigence() != null){
            t.setReferentielFamilleExigence(null);
            t.setReferentielFamilleExigence(new ReferentielFamilleExigence());
        }
        if (dto.getReferentielFamilleExigence() != null)
        referentielFamilleExigenceConverter.copy(dto.getReferentielFamilleExigence(), t.getReferentielFamilleExigence());
    }

    public List<FamilleExigence> copy(List<FamilleExigenceDto> dtos) {
        List<FamilleExigence> result = new ArrayList<>();
        if (dtos != null) {
            for (FamilleExigenceDto dto : dtos) {
                FamilleExigence instance = new FamilleExigence();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ReferentielFamilleExigenceConverter getReferentielFamilleExigenceConverter(){
        return this.referentielFamilleExigenceConverter;
    }
    public void setReferentielFamilleExigenceConverter(ReferentielFamilleExigenceConverter referentielFamilleExigenceConverter ){
        this.referentielFamilleExigenceConverter = referentielFamilleExigenceConverter;
    }
    public boolean  isReferentielFamilleExigence(){
        return this.referentielFamilleExigence;
    }
    public void  setReferentielFamilleExigence(boolean referentielFamilleExigence){
        this.referentielFamilleExigence = referentielFamilleExigence;
    }
}
