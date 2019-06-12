package com.toogood.data.transform.builder;

import java.util.regex.Pattern;

import com.toogood.data.transform.input.Input;
import com.toogood.data.transform.input.Source1DtoInputImpl;
import com.toogood.data.transform.output.OutputDto;
import com.toogood.data.transform.util.Constants.Currency;
import com.toogood.data.transform.util.Constants.Type;

/*
 * Implementing Builder interface for Builder design pattern
 * Using Singleton design pattern as only a single object is needed
 * All method in Builder interface must be implemented for transform input from Source 1 to Output
 */
public class Source1OutputBuilderImpl implements Builder {
	/*
	 * Input class containing input from Source 1
	 */
	private Source1DtoInputImpl input;
	
	/*
	 * OutputDto class will contain all transformed input value from Input class
	 */
	private OutputDto output;

	/*
	 * Private static value for Singleton design pattern
	 */
	private static Source1OutputBuilderImpl builder;

	/*
	 * Private constructor for Singleton design pattern
	 */
	private Source1OutputBuilderImpl() {
	}
	
	/*
	 * Create a single copy of the class for Singleton design pattern
	 */
	public static Source1OutputBuilderImpl createBuilder() {
		if(builder == null) {
			builder = new Source1OutputBuilderImpl();
		}
		
		return builder;
	}
	
	/*
	 * Set the value for Input and OutputDto class
	 * Check the instance of the Input class to make sure it is the Input class from Source 1 can be handle by this class
	 */
	@Override
	public void setInputOutput(Input input, OutputDto output) {
		if (input instanceof Source1DtoInputImpl) {
			this.input = (Source1DtoInputImpl) input;
		}
		this.output = output;
	}

	/*
	 * Transform Identifier, input value from Source 1, to OutputDto AccountCode
	 * Split the value of Identifier by '|', and use the right side of the value as AccountCode
	 */
	@Override
	public void buildAccountCode() {
		if (input != null && input.getIdentifier() != null && input.getIdentifier().contains("|")) {
			output.setAccountCode(input.getIdentifier().split(Pattern.quote("|"))[1]);
		}
	}

	/*
	 * Transform Name, input value from Source 1, to OutputDto Name
	 */
	@Override
	public void buildName() {
		if (input != null && input.getName() != null) {
			output.setName(input.getName());
		}
	}

	/*
	 * Transform Type, input value from Source 1, to OutputDto Type
	 * Use the input id to find out the value from enum Type in Constants class
	 */
	@Override
	public void buildType() {
		if (input != null && input.getType() != null) {
			output.setType(Type.getTypeDetails(Integer.valueOf(input.getType())));
		}
	}

	/*
	 * Transform Opened, input value from Source 1, to OutputDto OpenDate
	 */
	@Override
	public void buildOpenDate() {
		if (input != null && input.getOpened() != null) {
			output.setOpenDate(input.getOpened());
		}
	}

	/*
	 * Transform Currency, input value from Source 1, to OutputDto Currency
	 * Use the input currency to find out the value from enum Currency in Constants class
	 */
	@Override
	public void buildCurrency() {
		if (input != null && input.getCurrency() != null) {
			output.setCurrency(Currency.getCurrency(input.getCurrency()));	
		}
	}

	/*
	 * Reset the value of the builder after transformation is completed
	 */
	@Override
	public void resetInputOutput() {
		input = null;
		output = null;
	}
	
	/*
	 * Return the transformed OutputDto class
	 */
	@Override
	public OutputDto getOutput() {
		return output;
	}
}
