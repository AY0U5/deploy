package ma.zyn.app.ws.dto.exigence;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.dto.AuditBaseDto;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class FamilleExigenceDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String style  ;

    private ReferentielFamilleExigenceDto referentielFamilleExigence ;



    public FamilleExigenceDto(){
        super();
    }




    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }


    public ReferentielFamilleExigenceDto getReferentielFamilleExigence(){
        return this.referentielFamilleExigence;
    }

    public void setReferentielFamilleExigence(ReferentielFamilleExigenceDto referentielFamilleExigence){
        this.referentielFamilleExigence = referentielFamilleExigence;
    }






}
