package ma.zyn.app.dao.criteria.core.exigence;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ExigenceCriteria extends  BaseCriteria  {

    private String reference;
    private String referenceLike;
    private String libelle;
    private String libelleLike;
    private String description;
    private String descriptionLike;

    private FamilleExigenceCriteria familleExigence ;
    private List<FamilleExigenceCriteria> familleExigences ;


    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public String getReferenceLike(){
        return this.referenceLike;
    }
    public void setReferenceLike(String referenceLike){
        this.referenceLike = referenceLike;
    }

    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelleLike(){
        return this.libelleLike;
    }
    public void setLibelleLike(String libelleLike){
        this.libelleLike = libelleLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }


    public FamilleExigenceCriteria getFamilleExigence(){
        return this.familleExigence;
    }

    public void setFamilleExigence(FamilleExigenceCriteria familleExigence){
        this.familleExigence = familleExigence;
    }
    public List<FamilleExigenceCriteria> getFamilleExigences(){
        return this.familleExigences;
    }

    public void setFamilleExigences(List<FamilleExigenceCriteria> familleExigences){
        this.familleExigences = familleExigences;
    }
}
