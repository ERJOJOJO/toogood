package com.toogood.data.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.toogood.data.transform.input.Input;
import com.toogood.data.transform.input.Source1DtoInputImpl;
import com.toogood.data.transform.input.Source2DtoInputImpl;
import com.toogood.data.transform.output.OutputDto;
import com.toogood.data.transform.transformer.OutputTransformer;

/*
 * The main purpose of the class is to transform a list of class implement Input interface and return a list of OutputDto objects for other part of the system to use
 * 
 * Assumption: Data from Source 1 will save to Source1DtoInputImpl, Data from Source 2 will save to Source2DtoInputImpl, etc
 * 
 * To add another Input source, please perform the following
 * - add a new enum value for Input.OutputTransformerType
 * - add a new Singleton class implementing Input Interface
 * - add a new class implementing Builder Interface
 * - add a new instance value and condition to create new instance of the class in OutputTransformerFactory
 * - add a new condition and private method in OutputTransformer
 */
public class Transform {

	/*
	 * Transform a list of class implement Input interface and return a list of
	 * OutputDto objects for other part of the system to use By reading
	 * CompletableFuture from OutputTransformer, all request is running in
	 * asynchronously
	 * 
	 * @param inputList: a list of class implement Input interface needed to be
	 * transform
	 * 
	 * @return a list of OutputDto objects contain the transformed input value
	 */
	public List<OutputDto> transform(List<Input> inputList) {
		List<CompletableFuture<Optional<OutputDto>>> futureOutputList = new ArrayList<>();
		List<OutputDto> outputList = new ArrayList<>();

		for (Input input : inputList) {
			CompletableFuture<Optional<OutputDto>> futureOutputDto = OutputTransformer.buildOutput(input);
			futureOutputList.add(futureOutputDto);
		}

		for (CompletableFuture<Optional<OutputDto>> future : futureOutputList) {
			try {
				if (future != null) {
					OutputDto outputDto = future.get(10, TimeUnit.SECONDS).orElse(null);
					
					if (outputDto != null) {
						outputList.add(outputDto);
					}
				}
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				e.printStackTrace();
			}
		}

		return outputList;
	}

	/*
	 * Temporary main class to demonstrate how to use the Transform class
	 */
	public static void main(String[] args) {
		Source1DtoInputImpl input1 = new Source1DtoInputImpl();
		Source1DtoInputImpl input2 = new Source1DtoInputImpl();
		Source2DtoInputImpl input3 = new Source2DtoInputImpl();
		Source2DtoInputImpl input4 = new Source2DtoInputImpl();

		List<Input> inputList = new ArrayList<>();
		List<OutputDto> outputList = new ArrayList<>();

		input1.setIdentifier("123|AbcCode");
		input1.setName("My Account");
		input1.setType("10");
		input1.setOpened("01-01-2018");
		input1.setCurrency("CDD");

		input2.setIdentifier("456|DefCode");
		input2.setName("My Account 1");
		input2.setType("1");
		input2.setOpened("02-02-2018");
		input2.setCurrency("US");

		input3.setName("My Account");
		input3.setType("RRSP");
		input3.setCurrency("C");

		input4.setName("My Account 2");
		input4.setType("RESP");
		input4.setCurrency("U");
		input4.setCustodianCode("Code");

		inputList.add(input1);
		inputList.add(input2);
		inputList.add(input3);
		inputList.add(input4);

		System.out.println("Data Transform System started.\n");

		Transform transform = new Transform();
		outputList = transform.transform(inputList);

		for (OutputDto output : outputList) {
			if (output != null) {
				System.out.println(output.toString());
			}
		}

		System.out.println("Data Transform System ended.");
	}
}
