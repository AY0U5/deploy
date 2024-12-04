package ma.zyn.app.dao.specification.core.projet;

import ma.zyn.app.dao.criteria.core.projet.DossierProjetCriteria;
import ma.zyn.app.bean.core.projet.DossierProjet;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class DossierProjetSpecification extends  AbstractSpecification<DossierProjetCriteria, DossierProjet>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public DossierProjetSpecification(DossierProjetCriteria criteria) {
        super(criteria);
    }

    public DossierProjetSpecification(DossierProjetCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
