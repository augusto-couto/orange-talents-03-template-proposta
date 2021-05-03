package br.com.zupacademy.augusto.proposta.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@ConstraintComposition(CompositionType.OR)
@CPF
@CNPJ
@ReportAsSingleViolation
@Documented
@Constraint(validatedBy = { })
@Target({FIELD})
@Retention(RUNTIME)
public @interface CPForCNPJ {
	String message() default "CPF ou CNPJ inválidos!";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}

