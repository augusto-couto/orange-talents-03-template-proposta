package br.com.zupacademy.augusto.proposta.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zupacademy.augusto.proposta.validators.UniqueValueValidator;

@Documented
@Constraint(validatedBy = UniqueValueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueValue {
	
	String message() default "{br.com.zupacademy.augusto.proposta.annotations.UniqueValue}";
	
	Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    
    String field();
    
    Class<?> clazz();
}
