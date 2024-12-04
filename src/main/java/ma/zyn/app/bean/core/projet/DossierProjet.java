package ma.zyn.app.bean.core.projet;

import java.util.List;





import ma.zyn.app.bean.core.exigence.Exigence;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dossier_projet")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="dossier_projet_seq",sequenceName="dossier_projet_seq",allocationSize=1, initialValue = 1)
public class DossierProjet  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;


    private List<DossierProjetExigence> dossierProjetExigences ;
    private List<DossierProjetDocument> dossierProjetDocuments ;

    public DossierProjet(){
        super();
    }

    public DossierProjet(Long id){
        this.id = id;
    }

    public DossierProjet(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public DossierProjet(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="dossier_projet_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @OneToMany(mappedBy = "dossierProjet")
    public List<DossierProjetExigence> getDossierProjetExigences(){
        return this.dossierProjetExigences;
    }

    public void setDossierProjetExigences(List<DossierProjetExigence> dossierProjetExigences){
        this.dossierProjetExigences = dossierProjetExigences;
    }
    @OneToMany(mappedBy = "dossierProjet")
    public List<DossierProjetDocument> getDossierProjetDocuments(){
        return this.dossierProjetDocuments;
    }

    public void setDossierProjetDocuments(List<DossierProjetDocument> dossierProjetDocuments){
        this.dossierProjetDocuments = dossierProjetDocuments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DossierProjet dossierProjet = (DossierProjet) o;
        return id != null && id.equals(dossierProjet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

