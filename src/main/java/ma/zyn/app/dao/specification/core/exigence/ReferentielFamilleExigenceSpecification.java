package ma.zyn.app.dao.specification.core.exigence;

import ma.zyn.app.bean.core.exigence.ReferentielFamilleExigence;
import ma.zyn.app.dao.criteria.core.exigence.ReferentielFamilleExigenceCriteria;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ReferentielFamilleExigenceSpecification extends  AbstractSpecification<ReferentielFamilleExigenceCriteria, ReferentielFamilleExigence>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public ReferentielFamilleExigenceSpecification(ReferentielFamilleExigenceCriteria criteria) {
        super(criteria);
    }

    public ReferentielFamilleExigenceSpecification(ReferentielFamilleExigenceCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
