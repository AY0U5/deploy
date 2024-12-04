package ma.zyn.app.bean.core.projet;

import java.util.List;





import ma.zyn.app.bean.core.exigence.Exigence;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dossier_projet_document")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DossierProjetDocument  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String path;

    private String content;

    private DossierProjet dossierProjet ;

    private List<DossierProjetExigenceApplique> dossierProjetExigenceAppliques ;

    public DossierProjetDocument(){
        super();
    }

    public DossierProjetDocument(Long id){
        this.id = id;
    }

    public DossierProjetDocument(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public DossierProjetDocument(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dossier_projet")
    public DossierProjet getDossierProjet(){
        return this.dossierProjet;
    }
    public void setDossierProjet(DossierProjet dossierProjet){
        this.dossierProjet = dossierProjet;
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
      @Column(columnDefinition="TEXT")
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    @OneToMany(mappedBy = "dossierProjetDocument")
    public List<DossierProjetExigenceApplique> getDossierProjetExigenceAppliques(){
        return this.dossierProjetExigenceAppliques;
    }

    public void setDossierProjetExigenceAppliques(List<DossierProjetExigenceApplique> dossierProjetExigenceAppliques){
        this.dossierProjetExigenceAppliques = dossierProjetExigenceAppliques;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DossierProjetDocument dossierProjetDocument = (DossierProjetDocument) o;
        return id != null && id.equals(dossierProjetDocument.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

