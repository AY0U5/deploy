package ma.zyn.app.bean.core.projet;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dossier_projet_exigence_etat")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DossierProjetExigenceEtat  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;



    public DossierProjetExigenceEtat(){
        super();
    }

    public DossierProjetExigenceEtat(Long id){
        this.id = id;
    }

    public DossierProjetExigenceEtat(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public DossierProjetExigenceEtat(String libelle){
        this.libelle = libelle ;
    }
    public DossierProjetExigenceEtat(String libelle,String code){
        this.libelle=libelle;
        this.code=code;
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
    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DossierProjetExigenceEtat dossierProjetExigenceEtat = (DossierProjetExigenceEtat) o;
        return id != null && id.equals(dossierProjetExigenceEtat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

