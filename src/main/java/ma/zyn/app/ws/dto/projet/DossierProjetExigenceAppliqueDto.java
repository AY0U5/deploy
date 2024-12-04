package ma.zyn.app.ws.dto.projet;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.exigence.ExigenceDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DossierProjetExigenceAppliqueDto  extends AuditBaseDto {

    private String commentaire  ;
    private BigDecimal tauxPrecision  ;
    private String pages  ;

    private DossierProjetDocumentDto dossierProjetDocument ;
    private ExigenceDto exigence ;
    private DossierProjetExigenceEtatDto dossierProjetExigenceEtat ;



    public DossierProjetExigenceAppliqueDto(){
        super();
    }




    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }


    public BigDecimal getTauxPrecision(){
        return this.tauxPrecision;
    }
    public void setTauxPrecision(BigDecimal tauxPrecision){
        this.tauxPrecision = tauxPrecision;
    }


    public String getPages(){
        return this.pages;
    }
    public void setPages(String pages){
        this.pages = pages;
    }


    public DossierProjetDocumentDto getDossierProjetDocument(){
        return this.dossierProjetDocument;
    }

    public void setDossierProjetDocument(DossierProjetDocumentDto dossierProjetDocument){
        this.dossierProjetDocument = dossierProjetDocument;
    }
    public ExigenceDto getExigence(){
        return this.exigence;
    }

    public void setExigence(ExigenceDto exigence){
        this.exigence = exigence;
    }
    public DossierProjetExigenceEtatDto getDossierProjetExigenceEtat(){
        return this.dossierProjetExigenceEtat;
    }

    public void setDossierProjetExigenceEtat(DossierProjetExigenceEtatDto dossierProjetExigenceEtat){
        this.dossierProjetExigenceEtat = dossierProjetExigenceEtat;
    }






}
