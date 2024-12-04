package ma.zyn.app.ws.dto.projet;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zyn.app.ws.dto.exigence.ExigenceDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DossierProjetExigenceDto  extends AuditBaseDto {

    private String commentaire  ;
    private Boolean enabled  ;

    private ExigenceDto exigence ;
    private DossierProjetDto dossierProjet ;



    public DossierProjetExigenceDto(){
        super();
    }




    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }


    public Boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }


    public ExigenceDto getExigence(){
        return this.exigence;
    }

    public void setExigence(ExigenceDto exigence){
        this.exigence = exigence;
    }
    public DossierProjetDto getDossierProjet(){
        return this.dossierProjet;
    }

    public void setDossierProjet(DossierProjetDto dossierProjet){
        this.dossierProjet = dossierProjet;
    }






}
