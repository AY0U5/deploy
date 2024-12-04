package ma.zyn.app.ws.converter.exigence;

import ma.zyn.app.bean.core.exigence.ReferentielFamilleExigence;
import ma.zyn.app.ws.dto.exigence.ReferentielFamilleExigenceDto;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;
import ma.zyn.app.zynerator.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReferentielFamilleExigenceConverter {



    public ReferentielFamilleExigence toItem(ReferentielFamilleExigenceDto dto) {
        if (dto == null) {
            return null;
        } else {
        ReferentielFamilleExigence item = new ReferentielFamilleExigence();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());



        return item;
        }
    }


    public ReferentielFamilleExigenceDto toDto(ReferentielFamilleExigence item) {
        if (item == null) {
            return null;
        } else {
            ReferentielFamilleExigenceDto dto = new ReferentielFamilleExigenceDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());


        return dto;
        }
    }


	
    public List<ReferentielFamilleExigence> toItem(List<ReferentielFamilleExigenceDto> dtos) {
        List<ReferentielFamilleExigence> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ReferentielFamilleExigenceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ReferentielFamilleExigenceDto> toDto(List<ReferentielFamilleExigence> items) {
        List<ReferentielFamilleExigenceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ReferentielFamilleExigence item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ReferentielFamilleExigenceDto dto, ReferentielFamilleExigence t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ReferentielFamilleExigence> copy(List<ReferentielFamilleExigenceDto> dtos) {
        List<ReferentielFamilleExigence> result = new ArrayList<>();
        if (dtos != null) {
            for (ReferentielFamilleExigenceDto dto : dtos) {
                ReferentielFamilleExigence instance = new ReferentielFamilleExigence();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
