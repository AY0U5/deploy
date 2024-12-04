package ma.zyn.app.bean.core.projet;






import ma.zyn.app.bean.core.exigence.Exigence;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dossier_projet_exigence")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="dossier_projet_exigence_seq",sequenceName="dossier_projet_exigence_seq",allocationSize=1, initialValue = 1)
public class DossierProjetExigence  extends BaseEntity     {




    private String commentaire;

    @Column(columnDefinition = "boolean default false")
    private Boolean enabled = false;

    private Exigence exigence ;
    private DossierProjet dossierProjet ;


    public DossierProjetExigence(){
        super();
    }

    public DossierProjetExigence(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="dossier_projet_exigence_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exigence")
    public Exigence getExigence(){
        return this.exigence;
    }
    public void setExigence(Exigence exigence){
        this.exigence = exigence;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dossier_projet")
    public DossierProjet getDossierProjet(){
        return this.dossierProjet;
    }
    public void setDossierProjet(DossierProjet dossierProjet){
        this.dossierProjet = dossierProjet;
    }
      @Column(columnDefinition="TEXT")
    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }
    public boolean  getEnabled(){
        return this.enabled;
    }
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DossierProjetExigence dossierProjetExigence = (DossierProjetExigence) o;
        return id != null && id.equals(dossierProjetExigence.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

