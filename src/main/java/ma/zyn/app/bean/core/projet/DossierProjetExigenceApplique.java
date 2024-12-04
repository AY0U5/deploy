package ma.zyn.app.bean.core.projet;






import ma.zyn.app.bean.core.exigence.Exigence;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "dossier_projet_exigence_applique")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DossierProjetExigenceApplique  extends BaseEntity     {




    private String commentaire;

    private BigDecimal tauxPrecision = BigDecimal.ZERO;

    @Column(length = 500)
    private String pages;

    private DossierProjetDocument dossierProjetDocument ;
    private Exigence exigence ;
    private DossierProjetExigenceEtat dossierProjetExigenceEtat ;


    public DossierProjetExigenceApplique(){
        super();
    }

    public DossierProjetExigenceApplique(Long id){
        this.id = id;
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
    @JoinColumn(name = "dossier_projet_document")
    public DossierProjetDocument getDossierProjetDocument(){
        return this.dossierProjetDocument;
    }
    public void setDossierProjetDocument(DossierProjetDocument dossierProjetDocument){
        this.dossierProjetDocument = dossierProjetDocument;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exigence")
    public Exigence getExigence(){
        return this.exigence;
    }
    public void setExigence(Exigence exigence){
        this.exigence = exigence;
    }
      @Column(columnDefinition="TEXT")
    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dossier_projet_exigence_etat")
    public DossierProjetExigenceEtat getDossierProjetExigenceEtat(){
        return this.dossierProjetExigenceEtat;
    }
    public void setDossierProjetExigenceEtat(DossierProjetExigenceEtat dossierProjetExigenceEtat){
        this.dossierProjetExigenceEtat = dossierProjetExigenceEtat;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DossierProjetExigenceApplique dossierProjetExigenceApplique = (DossierProjetExigenceApplique) o;
        return id != null && id.equals(dossierProjetExigenceApplique.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

