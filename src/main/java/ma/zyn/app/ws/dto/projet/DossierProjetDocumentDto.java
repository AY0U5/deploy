package ma.zyn.app.ws.dto.projet;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.zyn.app.ws.dto.exigence.ExigenceDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DossierProjetDocumentDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String path  ;
    private String content  ;

    private DossierProjetDto dossierProjet ;

    private List<DossierProjetExigenceAppliqueDto> dossierProjetExigenceAppliques ;


    public DossierProjetDocumentDto(){
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


    public String getPath(){
        return this.path;
    }
    public void setPath(String path){
        this.path = path;
    }


    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }


    public DossierProjetDto getDossierProjet(){
        return this.dossierProjet;
    }

    public void setDossierProjet(DossierProjetDto dossierProjet){
        this.dossierProjet = dossierProjet;
    }



    public List<DossierProjetExigenceAppliqueDto> getDossierProjetExigenceAppliques(){
        return this.dossierProjetExigenceAppliques;
    }

    public void setDossierProjetExigenceAppliques(List<DossierProjetExigenceAppliqueDto> dossierProjetExigenceAppliques){
        this.dossierProjetExigenceAppliques = dossierProjetExigenceAppliques;
    }



}
