package com.toogood.data.transform.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.toogood.data.transform.validation.constraint.IdentifierConstraint;

public class IdentifierValidator implements ConstraintValidator<IdentifierConstraint, String> {

	@Override
	public void initialize(IdentifierConstraint identifier) {
	}

	@Override
	public boolean isValid(String identifier, ConstraintValidatorContext cxt) {
		return identifier != null && identifier.contains("|");
	}

}
