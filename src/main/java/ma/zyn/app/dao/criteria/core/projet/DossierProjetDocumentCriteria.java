package ma.zyn.app.dao.criteria.core.projet;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class DossierProjetDocumentCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String path;
    private String pathLike;
    private String content;
    private String contentLike;

    private DossierProjetCriteria dossierProjet ;
    private List<DossierProjetCriteria> dossierProjets ;


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

    public String getPath(){
        return this.path;
    }
    public void setPath(String path){
        this.path = path;
    }
    public String getPathLike(){
        return this.pathLike;
    }
    public void setPathLike(String pathLike){
        this.pathLike = pathLike;
    }

    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContentLike(){
        return this.contentLike;
    }
    public void setContentLike(String contentLike){
        this.contentLike = contentLike;
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
