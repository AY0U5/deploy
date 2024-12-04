package ma.zyn.app.dao.specification.core.projet;

import ma.zyn.app.dao.criteria.core.projet.DossierProjetExigenceAppliqueCriteria;
import ma.zyn.app.bean.core.projet.DossierProjetExigenceApplique;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class DossierProjetExigenceAppliqueSpecification extends  AbstractSpecification<DossierProjetExigenceAppliqueCriteria, DossierProjetExigenceApplique>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("tauxPrecision", criteria.getTauxPrecision(), criteria.getTauxPrecisionMin(), criteria.getTauxPrecisionMax());
        addPredicate("pages", criteria.getPages(),criteria.getPagesLike());
        addPredicateFk("dossierProjetDocument","id", criteria.getDossierProjetDocument()==null?null:criteria.getDossierProjetDocument().getId());
        addPredicateFk("dossierProjetDocument","id", criteria.getDossierProjetDocuments());
        addPredicateFk("dossierProjetDocument","code", criteria.getDossierProjetDocument()==null?null:criteria.getDossierProjetDocument().getCode());
        addPredicateFk("exigence","id", criteria.getExigence()==null?null:criteria.getExigence().getId());
        addPredicateFk("exigence","id", criteria.getExigences());
        addPredicateFk("exigence","reference", criteria.getExigence()==null?null:criteria.getExigence().getReference());
        addPredicateFk("dossierProjetExigenceEtat","id", criteria.getDossierProjetExigenceEtat()==null?null:criteria.getDossierProjetExigenceEtat().getId());
        addPredicateFk("dossierProjetExigenceEtat","id", criteria.getDossierProjetExigenceEtats());
        addPredicateFk("dossierProjetExigenceEtat","code", criteria.getDossierProjetExigenceEtat()==null?null:criteria.getDossierProjetExigenceEtat().getCode());
    }

    public DossierProjetExigenceAppliqueSpecification(DossierProjetExigenceAppliqueCriteria criteria) {
        super(criteria);
    }

    public DossierProjetExigenceAppliqueSpecification(DossierProjetExigenceAppliqueCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
