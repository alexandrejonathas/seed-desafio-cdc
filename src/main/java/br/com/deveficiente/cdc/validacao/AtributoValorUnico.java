package br.com.deveficiente.cdc.validacao;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = AtributoValorUnicoValidator.class)
@Retention(RUNTIME)
@Target({ FIELD })
public @interface AtributoValorUnico {

    String message() default "{br.com.deveficiente.cdc.beanvalidation.uniquevalue}";
	
	Class<?>[] groups() default { }; 
	
	Class<? extends Payload>[] payload() default { };

    String atributo();
    Class<?> classe();
}
