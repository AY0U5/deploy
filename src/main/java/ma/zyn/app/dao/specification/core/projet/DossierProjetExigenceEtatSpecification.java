package ma.zyn.app.dao.specification.core.projet;

import ma.zyn.app.dao.criteria.core.projet.DossierProjetExigenceEtatCriteria;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceEtat;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class DossierProjetExigenceEtatSpecification extends  AbstractSpecification<DossierProjetExigenceEtatCriteria, DossierProjetExigenceEtat>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public DossierProjetExigenceEtatSpecification(DossierProjetExigenceEtatCriteria criteria) {
        super(criteria);
    }

    public DossierProjetExigenceEtatSpecification(DossierProjetExigenceEtatCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
