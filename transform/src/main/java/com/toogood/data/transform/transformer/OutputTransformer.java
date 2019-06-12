package com.toogood.data.transform.transformer;

import java.util.concurrent.CompletableFuture;

import com.toogood.data.transform.builder.Builder;
import com.toogood.data.transform.input.Input;
import com.toogood.data.transform.output.OutputDto;
import com.toogood.data.transform.util.Constants.OutputTransformerType;

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
	 * @return CompletableFuture<OutputDto>: a class contain an Future object for the builder class to update
	 * and return with transformed data
	 */
	public static CompletableFuture<OutputDto> buildOutput(Input input) {
		CompletableFuture<OutputDto> futureOutputDto = CompletableFuture.supplyAsync(() -> {
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
	 * Using the Singleton builder for Source 1 input to build the output
	 * Use synchronized to make sure it is a thread-safe method as static object will be used
	 * 
	 * @param Input: a class implemented the Input interface to contain the input
	 * value
	 * 
	 * @return OutputDto: a class contain an object for the builder class to update
	 * and return with transformed data
	 */
	private static OutputDto buildSource1Output(Input input) {
		synchronized (OutputTransformer.class) {
			return buildGenericOutput(input, OutputTransformerFactory.retrieve(input.getSource()));
		}
	}

	/*
	 * Using the Singleton builder for Source 2 input to build the output
	 * Use synchronized to make sure it is a thread-safe method as static object will be used
	 * 
	 * @param Input: a class implemented the Input interface to contain the input
	 * value
	 * 
	 * @return OutputDto: a class contain an object for the builder class to update
	 * and return with transformed data
	 */
	private static OutputDto buildSource2Output(Input input) {
		synchronized (OutputTransformer.class) {
			return buildGenericOutput(input, OutputTransformerFactory.retrieve(input.getSource()));
		}
	}

	/*
	 * Perform a generic way to transform input to ouput regardless which builder object is in used
	 * 
	 * @param Input: a class implemented the Input interface to contain the input
	 * value
	 *        builder: a builder class for specific source
	 * 
	 * @return OutputDto: a class contain an object for the builder class to update
	 * and return with transformed data
	 */
	private static OutputDto buildGenericOutput(Input input, Builder builder) {
		if (builder != null) {
			builder.setInputOutput(input, new OutputDto());

			builder.buildAccountCode();
			builder.buildName();
			builder.buildType();
			builder.buildOpenDate();
			builder.buildCurrency();

			OutputDto output = builder.getOutput();

			builder.resetInputOutput();

			return output;
		}

		return null;
	}
}
