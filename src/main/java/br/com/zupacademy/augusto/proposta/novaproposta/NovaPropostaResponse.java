package br.com.zupacademy.augusto.proposta.novaproposta;

import java.math.BigDecimal;

public class NovaPropostaResponse {
	
	private String documento;
	private String email;
	private String nome;
	private String endereco;
	private BigDecimal salario;
	
	public NovaPropostaResponse(Proposta proposta) {
		super();
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
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
