package com.generation.mercadolimpo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})

@Retention(RetentionPolicy.RUNTIME)

@Constraint(validatedBy = {})



public @interface SKU {
	
	@OverridesAttribute(constraint = Pattern.class, name = "masseger")
	String messager() default "SKU devera seguir o formato JFDNDSLK";

	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
