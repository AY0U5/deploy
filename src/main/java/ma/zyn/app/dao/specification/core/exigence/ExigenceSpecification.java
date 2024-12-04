package ma.zyn.app.dao.specification.core.exigence;

import ma.zyn.app.dao.criteria.core.exigence.ExigenceCriteria;
import ma.zyn.app.bean.core.exigence.Exigence;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ExigenceSpecification extends  AbstractSpecification<ExigenceCriteria, Exigence>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("familleExigence","id", criteria.getFamilleExigence()==null?null:criteria.getFamilleExigence().getId());
        addPredicateFk("familleExigence","id", criteria.getFamilleExigences());
        addPredicateFk("familleExigence","code", criteria.getFamilleExigence()==null?null:criteria.getFamilleExigence().getCode());
    }

    public ExigenceSpecification(ExigenceCriteria criteria) {
        super(criteria);
    }

    public ExigenceSpecification(ExigenceCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
