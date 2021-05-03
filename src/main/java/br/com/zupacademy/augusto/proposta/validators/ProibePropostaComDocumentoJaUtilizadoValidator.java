package br.com.zupacademy.augusto.proposta.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.augusto.proposta.novaproposta.NovaPropostaRequest;
import br.com.zupacademy.augusto.proposta.novaproposta.Proposta;
import br.com.zupacademy.augusto.proposta.security.CustomEncryptor;

@Component
public class ProibePropostaComDocumentoJaUtilizadoValidator implements Validator {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CustomEncryptor encryptor;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaPropostaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		NovaPropostaRequest request = (NovaPropostaRequest) target;
		
		TypedQuery<Proposta> buscaPropostas = entityManager.createQuery("FROM Proposta", Proposta.class);
		List<Proposta> propostas = buscaPropostas.getResultList();
		List<String> documentosDecriptados = new ArrayList<String>();
		
		propostas.forEach(proposta -> documentosDecriptados.add(
				encryptor.decrypt(proposta.getDocumento(), proposta.getEmail())));
		
		if (documentosDecriptados.contains(request.getDocumento())) {
			errors.rejectValue("documento", null, "Este documento já esta cadastrado e deve ser único!");
		}
	}

}
