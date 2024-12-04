package ma.zyn.app.dao.specification.core.projet;

import ma.zyn.app.dao.criteria.core.projet.DossierProjetDocumentCriteria;
import ma.zyn.app.bean.core.projet.DossierProjetDocument;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class DossierProjetDocumentSpecification extends  AbstractSpecification<DossierProjetDocumentCriteria, DossierProjetDocument>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("path", criteria.getPath(),criteria.getPathLike());
        addPredicateFk("dossierProjet","id", criteria.getDossierProjet()==null?null:criteria.getDossierProjet().getId());
        addPredicateFk("dossierProjet","id", criteria.getDossierProjets());
        addPredicateFk("dossierProjet","code", criteria.getDossierProjet()==null?null:criteria.getDossierProjet().getCode());
    }

    public DossierProjetDocumentSpecification(DossierProjetDocumentCriteria criteria) {
        super(criteria);
    }

    public DossierProjetDocumentSpecification(DossierProjetDocumentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
