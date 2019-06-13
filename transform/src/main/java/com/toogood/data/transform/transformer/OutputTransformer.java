package com.toogood.data.transform.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.toogood.data.transform.builder.Builder;
import com.toogood.data.transform.input.Input;
import com.toogood.data.transform.output.OutputDto;
import com.toogood.data.transform.util.Constants.OutputTransformerType;
import com.toogood.data.transform.validation.ValidateResults;

/*
 * This is the class to specify how to use the Builder to transform the input data to output
 */
public class OutputTransformer {
	/*
	 * Main method to transform the class implemented the Input interface to
	 * OutputDto based on the builder returned from OutputTransformerFactory
	 * 
	 * @param Input: a class implemented the Input interface to contain the input
	 * value
	 * 
	 * @return CompletableFuture<OutputDto>: a class contain an Future object for
	 * the builder class to update and return with transformed data
	 */
	public static CompletableFuture<Optional<OutputDto>> buildOutput(Input input) {
		CompletableFuture<Optional<OutputDto>> futureOutputDto = CompletableFuture.supplyAsync(() -> {
			if (input.getSource().equals(OutputTransformerType.SOURCE1)) {
				return buildSource1Output(input);
			} else if (input.getSource().equals(OutputTransformerType.SOURCE2)) {
				return buildSource2Output(input);
			} else {
				return null;
			}
		});

		return futureOutputDto;
	}

	/*
	 * Using the Singleton builder for Source 1 input to build the output Use
	 * synchronized to make sure it is a thread-safe method as static object will be
	 * used
	 * 
	 * @param Input: a class implemented the Input interface to contain the input
	 * value
	 * 
	 * @return OutputDto: a class contain an object for the builder class to update
	 * and return with transformed data
	 */
	private static Optional<OutputDto> buildSource1Output(Input input) {
		synchronized (OutputTransformer.class) {
			return buildGenericOutput(input, OutputTransformerFactory.retrieve(input.getSource()));
		}
	}

	/*
	 * Using the Singleton builder for Source 2 input to build the output Use
	 * synchronized to make sure it is a thread-safe method as static object will be
	 * used
	 * 
	 * @param Input: a class implemented the Input interface to contain the input
	 * value
	 * 
	 * @return OutputDto: a class contain an object for the builder class to update
	 * and return with transformed data
	 */
	private static Optional<OutputDto> buildSource2Output(Input input) {
		synchronized (OutputTransformer.class) {
			return buildGenericOutput(input, OutputTransformerFactory.retrieve(input.getSource()));
		}
	}

	/*
	 * Call Validator to validate the Input object. If the input object is valid,
	 * perform a generic way to transform input to ouput regardless which builder
	 * object is in used If the input object is invalid, add the error message and
	 * the invalid input object to the output Return the output object
	 * 
	 * @param Input: a class implemented the Input interface to contain the input
	 * value builder: a builder class for specific source
	 * 
	 * @return OutputDto: a class contain an object for the builder class to update
	 * and return with transformed data
	 */
	private static Optional<OutputDto> buildGenericOutput(Input input, Builder builder) {
		if (builder == null) {
			return null;
		}

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Input>> violations = validator.validate(input);

		OutputDto output = null;
		if (violations.isEmpty()) {
			builder.setInputOutput(input, new OutputDto());

			builder.buildAccountCode();
			builder.buildName();
			builder.buildType();
			builder.buildOpenDate();
			builder.buildCurrency();

			output = builder.getOutput();
			output.setValidateResults(new ValidateResults());
			output.getValidateResults().setValid(true);

			builder.resetInputOutput();
		} else {
			output = new OutputDto();
			output.setValidateResults(new ValidateResults());
			output.getValidateResults().setValid(false);
			output.getValidateResults().setInvalidInput(input);

			List<String> errorMessageList = new ArrayList<>();
			for (ConstraintViolation<Input> violation : violations) {
				errorMessageList.add(violation.getMessage());
			}
			output.getValidateResults().setErrorMessage(errorMessageList.stream().collect(Collectors.joining(",")));
		}

		return Optional.of(output);
	}
}
