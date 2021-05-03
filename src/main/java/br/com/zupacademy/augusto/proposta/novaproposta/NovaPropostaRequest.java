package br.com.zupacademy.augusto.proposta.novaproposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import br.com.zupacademy.augusto.proposta.annotations.CPForCNPJ;
import br.com.zupacademy.augusto.proposta.annotations.UniqueValue;
import br.com.zupacademy.augusto.proposta.security.CustomEncryptor;

public class NovaPropostaRequest {

	@CPForCNPJ
	private String documento;
	@NotBlank
	@Email
	@UniqueValue(clazz = Proposta.class, field = "email")
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	@NotNull
	@Positive
	private BigDecimal salario;
	
	public NovaPropostaRequest(String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotBlank @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Proposta toModel(CustomEncryptor encryptor) {
		return new Proposta(encryptor.encrypt(documento, email), this.email, this.nome, this.endereco, this.salario);
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

}
