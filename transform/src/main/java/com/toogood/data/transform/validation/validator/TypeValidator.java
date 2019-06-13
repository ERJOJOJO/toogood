package com.toogood.data.transform.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.toogood.data.transform.util.Constants;
import com.toogood.data.transform.validation.constraint.TypeConstraint;

public class TypeValidator implements ConstraintValidator<TypeConstraint, String> {

	@Override
	public void initialize(TypeConstraint identifier) {
	}

	@Override
	public boolean isValid(String type, ConstraintValidatorContext cxt) {
		return type != null && Constants.Type.getTypeDetails(Integer.valueOf(type)) != null;
	}

}
