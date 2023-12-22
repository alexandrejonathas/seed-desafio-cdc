package br.com.deveficiente.cdc.validacao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EntidadeExistenteValidator implements ConstraintValidator<EntidadeExistente, Long> {

	@PersistenceContext
	private EntityManager manager;
	
	private Class<?> classe;
	
	@Override
	public void initialize(EntidadeExistente params) {
		this.classe = params.classe();
	}

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
		try {			
			Query query = manager.createQuery("select d from "+classe.getName()+" d where d.id = :valor");
			query.setParameter("valor", value);
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
    }
    
}
