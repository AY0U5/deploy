package ma.zyn.app.dao.specification.core.projet;

import ma.zyn.app.dao.criteria.core.projet.DossierProjetExigenceCriteria;
import ma.zyn.app.bean.core.projet.DossierProjetExigence;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class DossierProjetExigenceSpecification extends  AbstractSpecification<DossierProjetExigenceCriteria, DossierProjetExigence>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBool("enabled", criteria.getEnabled());
        addPredicateFk("exigence","id", criteria.getExigence()==null?null:criteria.getExigence().getId());
        addPredicateFk("exigence","id", criteria.getExigences());
        addPredicateFk("exigence","reference", criteria.getExigence()==null?null:criteria.getExigence().getReference());
        addPredicateFk("dossierProjet","id", criteria.getDossierProjet()==null?null:criteria.getDossierProjet().getId());
        addPredicateFk("dossierProjet","id", criteria.getDossierProjets());
        addPredicateFk("dossierProjet","code", criteria.getDossierProjet()==null?null:criteria.getDossierProjet().getCode());
    }

    public DossierProjetExigenceSpecification(DossierProjetExigenceCriteria criteria) {
        super(criteria);
    }

    public DossierProjetExigenceSpecification(DossierProjetExigenceCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
