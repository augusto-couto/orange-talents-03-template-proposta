package br.com.zupacademy.augusto.proposta.validators;

public class FieldErrorOutputDto {
	private String field;
	private String error;
	
	public FieldErrorOutputDto(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}
}
