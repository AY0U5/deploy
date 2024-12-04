package ma.zyn.app.dao.criteria.core.exigence;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class FamilleExigenceCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String style;
    private String styleLike;

    private ReferentielFamilleExigenceCriteria referentielFamilleExigence ;
    private List<ReferentielFamilleExigenceCriteria> referentielFamilleExigences ;


    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
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

    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }
    public String getStyleLike(){
        return this.styleLike;
    }
    public void setStyleLike(String styleLike){
        this.styleLike = styleLike;
    }


    public ReferentielFamilleExigenceCriteria getReferentielFamilleExigence(){
        return this.referentielFamilleExigence;
    }

    public void setReferentielFamilleExigence(ReferentielFamilleExigenceCriteria referentielFamilleExigence){
        this.referentielFamilleExigence = referentielFamilleExigence;
    }
    public List<ReferentielFamilleExigenceCriteria> getReferentielFamilleExigences(){
        return this.referentielFamilleExigences;
    }

    public void setReferentielFamilleExigences(List<ReferentielFamilleExigenceCriteria> referentielFamilleExigences){
        this.referentielFamilleExigences = referentielFamilleExigences;
    }
}
