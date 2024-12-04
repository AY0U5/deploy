package ma.zyn.app.ws.dto.exigence;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExigenceDto  extends AuditBaseDto {

    private String reference  ;
    private String libelle  ;
    private String description  ;

    private FamilleExigenceDto familleExigence ;



    public ExigenceDto(){
        super();
    }




    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }


    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public FamilleExigenceDto getFamilleExigence(){
        return this.familleExigence;
    }

    public void setFamilleExigence(FamilleExigenceDto familleExigence){
        this.familleExigence = familleExigence;
    }






}
