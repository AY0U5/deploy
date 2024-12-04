package ma.zyn.app.ws.converter.projet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import ma.zyn.app.ws.dto.projet.DossierProjetExigenceEtatDto;

@Component
public class DossierProjetExigenceEtatConverter {



    public DossierProjetExigenceEtat toItem(DossierProjetExigenceEtatDto dto) {
        if (dto == null) {
            return null;
        } else {
        DossierProjetExigenceEtat item = new DossierProjetExigenceEtat();
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


    public DossierProjetExigenceEtatDto toDto(DossierProjetExigenceEtat item) {
        if (item == null) {
            return null;
        } else {
            DossierProjetExigenceEtatDto dto = new DossierProjetExigenceEtatDto();
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


	
    public List<DossierProjetExigenceEtat> toItem(List<DossierProjetExigenceEtatDto> dtos) {
        List<DossierProjetExigenceEtat> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DossierProjetExigenceEtatDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DossierProjetExigenceEtatDto> toDto(List<DossierProjetExigenceEtat> items) {
        List<DossierProjetExigenceEtatDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DossierProjetExigenceEtat item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DossierProjetExigenceEtatDto dto, DossierProjetExigenceEtat t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<DossierProjetExigenceEtat> copy(List<DossierProjetExigenceEtatDto> dtos) {
        List<DossierProjetExigenceEtat> result = new ArrayList<>();
        if (dtos != null) {
            for (DossierProjetExigenceEtatDto dto : dtos) {
                DossierProjetExigenceEtat instance = new DossierProjetExigenceEtat();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
