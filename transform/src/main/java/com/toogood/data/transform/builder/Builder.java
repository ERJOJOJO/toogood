package com.toogood.data.transform.builder;

import com.toogood.data.transform.input.Input;
import com.toogood.data.transform.output.OutputDto;

/*
 * In order to use the Builder design pattern, an interface containing all the steps that the implemented class need to implement
 */
public interface Builder {
	/*
	 * The process to set up the input class and the output class
	 * 
	 * @param Input: a class implemented the Input interface to contain the input
	 * value
	 * 
	 * @param OutputDto: a class contain an object for the builder class to update
	 * and return with transformed data
	 */
	void setInputOutput(Input input, OutputDto output);

	/*
	 * Build the value of Account Code for the OutputDto object
	 */
	void buildAccountCode();

	/*
	 * Build the value of Name for the OutputDto object
	 */
	void buildName();

	/*
	 * Build the value of Type for the OutputDto object
	 */
	void buildType();

	/*
	 * Build the value of Open Date for the OutputDto object
	 */
	void buildOpenDate();

	/*
	 * Build the value of Currency for the OutputDto object
	 */
	void buildCurrency();
	
	/*
	 * Reset the value in the implemented class
	 */
	void resetInputOutput();

	/*
	 * Return the transformed class
	 * 
	 * @return OutputDto: a class contain an object with the transformed data from input value
	 */
	OutputDto getOutput();
}
