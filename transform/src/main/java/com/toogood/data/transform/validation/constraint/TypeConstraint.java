package com.toogood.data.transform.validation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.toogood.data.transform.validation.validator.TypeValidator;

@Documented
@Constraint(validatedBy = TypeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeConstraint {
	String message() default "Invalid Type";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}