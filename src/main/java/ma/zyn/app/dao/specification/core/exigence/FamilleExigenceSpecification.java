package ma.zyn.app.dao.specification.core.exigence;

import ma.zyn.app.bean.core.exigence.FamilleExigence;
import ma.zyn.app.dao.criteria.core.exigence.FamilleExigenceCriteria;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class FamilleExigenceSpecification extends  AbstractSpecification<FamilleExigenceCriteria, FamilleExigence>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicateFk("referentielFamilleExigence","id", criteria.getReferentielFamilleExigence()==null?null:criteria.getReferentielFamilleExigence().getId());
        addPredicateFk("referentielFamilleExigence","id", criteria.getReferentielFamilleExigences());
        addPredicateFk("referentielFamilleExigence","code", criteria.getReferentielFamilleExigence()==null?null:criteria.getReferentielFamilleExigence().getCode());
    }

    public FamilleExigenceSpecification(FamilleExigenceCriteria criteria) {
        super(criteria);
    }

    public FamilleExigenceSpecification(FamilleExigenceCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
