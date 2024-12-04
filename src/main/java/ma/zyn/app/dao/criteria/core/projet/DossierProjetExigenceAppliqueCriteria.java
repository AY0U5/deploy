package ma.zyn.app.dao.criteria.core.projet;


import ma.zyn.app.dao.criteria.core.exigence.ExigenceCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class DossierProjetExigenceAppliqueCriteria extends  BaseCriteria  {

    private String commentaire;
    private String commentaireLike;
    private String tauxPrecision;
    private String tauxPrecisionMin;
    private String tauxPrecisionMax;
    private String pages;
    private String pagesLike;

    private DossierProjetDocumentCriteria dossierProjetDocument ;
    private List<DossierProjetDocumentCriteria> dossierProjetDocuments ;
    private ExigenceCriteria exigence ;
    private List<ExigenceCriteria> exigences ;
    private DossierProjetExigenceEtatCriteria dossierProjetExigenceEtat ;
    private List<DossierProjetExigenceEtatCriteria> dossierProjetExigenceEtats ;


    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }
    public String getCommentaireLike(){
        return this.commentaireLike;
    }
    public void setCommentaireLike(String commentaireLike){
        this.commentaireLike = commentaireLike;
    }

    public String getTauxPrecision(){
        return this.tauxPrecision;
    }
    public void setTauxPrecision(String tauxPrecision){
        this.tauxPrecision = tauxPrecision;
    }   
    public String getTauxPrecisionMin(){
        return this.tauxPrecisionMin;
    }
    public void setTauxPrecisionMin(String tauxPrecisionMin){
        this.tauxPrecisionMin = tauxPrecisionMin;
    }
    public String getTauxPrecisionMax(){
        return this.tauxPrecisionMax;
    }
    public void setTauxPrecisionMax(String tauxPrecisionMax){
        this.tauxPrecisionMax = tauxPrecisionMax;
    }
      
    public String getPages(){
        return this.pages;
    }
    public void setPages(String pages){
        this.pages = pages;
    }
    public String getPagesLike(){
        return this.pagesLike;
    }
    public void setPagesLike(String pagesLike){
        this.pagesLike = pagesLike;
    }


    public DossierProjetDocumentCriteria getDossierProjetDocument(){
        return this.dossierProjetDocument;
    }

    public void setDossierProjetDocument(DossierProjetDocumentCriteria dossierProjetDocument){
        this.dossierProjetDocument = dossierProjetDocument;
    }
    public List<DossierProjetDocumentCriteria> getDossierProjetDocuments(){
        return this.dossierProjetDocuments;
    }

    public void setDossierProjetDocuments(List<DossierProjetDocumentCriteria> dossierProjetDocuments){
        this.dossierProjetDocuments = dossierProjetDocuments;
    }
    public ExigenceCriteria getExigence(){
        return this.exigence;
    }

    public void setExigence(ExigenceCriteria exigence){
        this.exigence = exigence;
    }
    public List<ExigenceCriteria> getExigences(){
        return this.exigences;
    }

    public void setExigences(List<ExigenceCriteria> exigences){
        this.exigences = exigences;
    }
    public DossierProjetExigenceEtatCriteria getDossierProjetExigenceEtat(){
        return this.dossierProjetExigenceEtat;
    }

    public void setDossierProjetExigenceEtat(DossierProjetExigenceEtatCriteria dossierProjetExigenceEtat){
        this.dossierProjetExigenceEtat = dossierProjetExigenceEtat;
    }
    public List<DossierProjetExigenceEtatCriteria> getDossierProjetExigenceEtats(){
        return this.dossierProjetExigenceEtats;
    }

    public void setDossierProjetExigenceEtats(List<DossierProjetExigenceEtatCriteria> dossierProjetExigenceEtats){
        this.dossierProjetExigenceEtats = dossierProjetExigenceEtats;
    }
}
