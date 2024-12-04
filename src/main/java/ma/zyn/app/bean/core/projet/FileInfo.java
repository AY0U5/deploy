package ma.zyn.app.bean.core.projet;


import jakarta.persistence.*;

@Entity
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    private DossierProjet dossierProjet;
    @Column(name = "path")
    private String path;
    @Column(name = "content")
    private String content;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DossierProjet getDossierProjet() {
        return dossierProjet;
    }

    public void setDossierProjet(DossierProjet dossierProjet) {
        this.dossierProjet = dossierProjet;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


