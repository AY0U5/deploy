package ma.zyn.app.bean.core.exigence;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import ma.zyn.app.zynerator.bean.BaseEntity;

import java.util.Objects;

@Entity
@Table(name = "famille_exigence")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FamilleExigence  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    @Column(length = 500)
    private String style;

    private ReferentielFamilleExigence referentielFamilleExigence ;


    public FamilleExigence(){
        super();
    }

    public FamilleExigence(Long id){
        this.id = id;
    }

    public FamilleExigence(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public FamilleExigence(String libelle){
        this.libelle = libelle ;
    }
    public FamilleExigence(String libelle,String code){
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referentiel_famille_exigence")
    public ReferentielFamilleExigence getReferentielFamilleExigence(){
        return this.referentielFamilleExigence;
    }
    public void setReferentielFamilleExigence(ReferentielFamilleExigence referentielFamilleExigence){
        this.referentielFamilleExigence = referentielFamilleExigence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilleExigence familleExigence = (FamilleExigence) o;
        return id != null && id.equals(familleExigence.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

