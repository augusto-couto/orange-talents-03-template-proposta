package br.com.zupacademy.augusto.proposta.validators;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

import br.com.zupacademy.augusto.proposta.annotations.UniqueValue;


public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object>{
	
	private String field;
	private Class<?> clazz;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void initialize(UniqueValue valorUnico) {
		field = valorUnico.field();
		clazz = valorUnico.clazz();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		Query query = entityManager.createQuery("SELECT 1 FROM " + clazz.getName() + " WHERE " +
				field + " = :value");
		query.setParameter("value", value);
		
		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1, "O " + field + " " + value 
				+ " já está cadastrado e deve ser único.");
		return list.isEmpty();
	}

}
