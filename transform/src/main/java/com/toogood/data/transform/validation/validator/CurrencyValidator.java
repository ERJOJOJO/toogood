package com.toogood.data.transform.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.toogood.data.transform.util.Constants;
import com.toogood.data.transform.validation.constraint.CurrencyConstraint;

public class CurrencyValidator implements ConstraintValidator<CurrencyConstraint, String> {

	@Override
	public void initialize(CurrencyConstraint identifier) {
	}

	@Override
	public boolean isValid(String currency, ConstraintValidatorContext cxt) {
		return Constants.Currency.getCurrency(currency) != null;
	}

}
