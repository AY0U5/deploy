package ma.zyn.app.dao.criteria.core.projet;


import ma.zyn.app.dao.criteria.core.exigence.ExigenceCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class DossierProjetExigenceCriteria extends  BaseCriteria  {

    private String commentaire;
    private String commentaireLike;
    private Boolean enabled;

    private ExigenceCriteria exigence ;
    private List<ExigenceCriteria> exigences ;
    private DossierProjetCriteria dossierProjet ;
    private List<DossierProjetCriteria> dossierProjets ;


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

    public Boolean getEnabled(){
        return this.enabled;
    }
    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
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
    public DossierProjetCriteria getDossierProjet(){
        return this.dossierProjet;
    }

    public void setDossierProjet(DossierProjetCriteria dossierProjet){
        this.dossierProjet = dossierProjet;
    }
    public List<DossierProjetCriteria> getDossierProjets(){
        return this.dossierProjets;
    }

    public void setDossierProjets(List<DossierProjetCriteria> dossierProjets){
        this.dossierProjets = dossierProjets;
    }
}
