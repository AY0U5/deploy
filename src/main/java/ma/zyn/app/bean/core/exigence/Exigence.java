package ma.zyn.app.bean.core.exigence;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "exigence")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Exigence  extends BaseEntity     {




    @Column(length = 500)
    private String reference;

    @Column(length = 500)
    private String libelle;

    private String description;

    private FamilleExigence familleExigence ;


    public Exigence(){
        super();
    }

    public Exigence(Long id){
        this.id = id;
    }

    public Exigence(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Exigence(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="exigence_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "famille_exigence")
    public FamilleExigence getFamilleExigence(){
        return this.familleExigence;
    }
    public void setFamilleExigence(FamilleExigence familleExigence){
        this.familleExigence = familleExigence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exigence exigence = (Exigence) o;
        return id != null && id.equals(exigence.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

