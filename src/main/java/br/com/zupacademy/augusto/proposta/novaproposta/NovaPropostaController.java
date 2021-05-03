package br.com.zupacademy.augusto.proposta.novaproposta;

import java.net.URI;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.augusto.proposta.security.CustomEncryptor;
import br.com.zupacademy.augusto.proposta.validators.ProibePropostaComDocumentoJaUtilizadoValidator;

@RestController
public class NovaPropostaController {
	
	private final EntityManager entityManager;
	private final CustomEncryptor encryptor;
	private final ProibePropostaComDocumentoJaUtilizadoValidator proibePropostaComDocumentoJaUtilizadoValidator;

	public NovaPropostaController(EntityManager entityManager, CustomEncryptor encryptor,
			ProibePropostaComDocumentoJaUtilizadoValidator proibePropostaComDocumentoJaUtilizadoValidator) {
		super();
		this.entityManager = entityManager;
		this.encryptor = encryptor;
		this.proibePropostaComDocumentoJaUtilizadoValidator = proibePropostaComDocumentoJaUtilizadoValidator;
	}
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibePropostaComDocumentoJaUtilizadoValidator);
	}

	@PostMapping("/propostas")
	@Transactional
	public ResponseEntity<NovaPropostaResponse> cadastraProposta(@Valid @RequestBody NovaPropostaRequest request,
			UriComponentsBuilder uriBuilder) {
		
		Proposta proposta = request.toModel(encryptor);
		entityManager.persist(proposta);
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(new NovaPropostaResponse(proposta));
	}
}
