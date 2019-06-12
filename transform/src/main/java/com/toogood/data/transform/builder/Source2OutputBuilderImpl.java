package com.toogood.data.transform.builder;

import com.toogood.data.transform.input.Input;
import com.toogood.data.transform.input.Source2DtoInputImpl;
import com.toogood.data.transform.output.OutputDto;
import com.toogood.data.transform.util.Constants.Currency;

/*
 * Implementing Builder interface for Builder design pattern
 * Using Singleton design pattern as only a single object is needed
 * All method in Builder interface must be implemented for transform input from Source 2 to Output
 */
public class Source2OutputBuilderImpl implements Builder{
	/*
	 * Input class containing input from Source 2
	 */
	private Source2DtoInputImpl input;
	
	/*
	 * OutputDto class will contain all transformed input value from Input class
	 */
	private OutputDto output;
	
	/*
	 * Private static value for Singleton design pattern
	 */
	private static Source2OutputBuilderImpl builder;
	
	/*
	 * Private constructor for Singleton design pattern
	 */
	private Source2OutputBuilderImpl() {
	}
	
	/*
	 * Create a single copy of the class for Singleton design pattern
	 */
	public static Source2OutputBuilderImpl createBuilder() {
		if(builder == null) {
			builder = new Source2OutputBuilderImpl();
		}
		
		return builder;
	}
	
	/*
	 * Set the value for Input and OutputDto class
	 * Check the instance of the Input class to make sure it is the Input class from Source 2 can be handle by this class
	 */
	@Override
	public void setInputOutput(Input input, OutputDto output) {
		if(input instanceof Source2DtoInputImpl) {
			this.input = (Source2DtoInputImpl) input;
		}
		this.output = output;
	}

	/*
	 * Transform CustodianCode, input value from Source 2, to OutputDto AccountCode
	 */
	@Override
	public void buildAccountCode() {
		if (input != null && input.getCustodianCode() != null) {
			output.setAccountCode(input.getCustodianCode());
		}
	}

	/*
	 * Transform Name, input value from Source 2, to OutputDto Name
	 */
	@Override
	public void buildName() {
		if (input != null && input.getName() != null) {
			output.setName(input.getName());
		}
	}

	/*
	 * Transform Type, input value from Source 2, to OutputDto Type
	 */
	@Override
	public void buildType() {
		if (input != null && input.getType() != null) {
			output.setType(input.getType());
		}
	}

	/*
	 * Not need to implemented because Source 2 input didn't contain open date
	 */
	@Override
	public void buildOpenDate() {
	}

	/*
	 * Transform Currency, input value from Source 2, to OutputDto Currency
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
