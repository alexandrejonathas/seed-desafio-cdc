package br.com.deveficiente.cdc.validacao;

import org.springframework.util.Assert;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtributoValorUnicoValidator implements ConstraintValidator<AtributoValorUnico, Object> {

    @PersistenceContext
    private EntityManager manager;

    private String atributo;
    private Class<?> classe;

    @Override
    public void initialize(AtributoValorUnico params) {
        this.atributo = params.atributo();
        this.classe = params.classe();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select d from "+classe.getName()+" d where d."+atributo+" = :valor");
		query.setParameter("valor", value);

		var list = query.getResultList();
		Assert.state(list.size() <= 1, "Foi encontrado mais de um "+classe+" com o atributo "+atributo+" = "+value);

		return list.isEmpty();
    }
    
}
