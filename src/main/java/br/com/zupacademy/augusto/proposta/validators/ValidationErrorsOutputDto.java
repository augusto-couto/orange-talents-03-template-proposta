package br.com.zupacademy.augusto.proposta.validators;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDto {
	
	private List<String> globalErrorMessages = new ArrayList<>();
	private List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();
	
	public void addError(String errorMessage) {
		globalErrorMessages.add(errorMessage);
	}

	public void addFieldError(String field, String errorMessage) {
		FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, errorMessage);
		fieldErrors.add(fieldError);
	}

	public List<String> getGlobalErrorMessages() {
		return globalErrorMessages;
	}

	public List<FieldErrorOutputDto> getFieldErrors() {
		return fieldErrors;
	}
}
