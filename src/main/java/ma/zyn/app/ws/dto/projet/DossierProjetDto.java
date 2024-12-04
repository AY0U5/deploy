package ma.zyn.app.ws.dto.projet;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.zyn.app.ws.dto.exigence.ExigenceDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DossierProjetDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;


    private List<DossierProjetExigenceDto> dossierProjetExigences ;
    private List<DossierProjetDocumentDto> dossierProjetDocuments ;


    public DossierProjetDto(){
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


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }





    public List<DossierProjetExigenceDto> getDossierProjetExigences(){
        return this.dossierProjetExigences;
    }

    public void setDossierProjetExigences(List<DossierProjetExigenceDto> dossierProjetExigences){
        this.dossierProjetExigences = dossierProjetExigences;
    }
    public List<DossierProjetDocumentDto> getDossierProjetDocuments(){
        return this.dossierProjetDocuments;
    }

    public void setDossierProjetDocuments(List<DossierProjetDocumentDto> dossierProjetDocuments){
        this.dossierProjetDocuments = dossierProjetDocuments;
    }



}
