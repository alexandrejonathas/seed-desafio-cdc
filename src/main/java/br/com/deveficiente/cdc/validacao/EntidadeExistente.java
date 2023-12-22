package br.com.deveficiente.cdc.validacao;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = EntidadeExistenteValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface EntidadeExistente {

	String message() default "{br.com.deveficiente.cdc.beanvalidation.entityexist}";
	
	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default { };
	
	Class<?> classe();

}
