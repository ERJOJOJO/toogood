package com.toogood.data.transform;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.toogood.data.transform.input.Input;
import com.toogood.data.transform.input.Source1DtoInputImpl;
import com.toogood.data.transform.input.Source2DtoInputImpl;
import com.toogood.data.transform.output.OutputDto;

public class TransformTest {
	@Test
	void testSource1TransformOutput() {
		Source1DtoInputImpl input = new Source1DtoInputImpl();
		input.setIdentifier("123|AbcCode");
		input.setName("My Account");
		input.setType("2");
		input.setOpened("01-01-2018");
		input.setCurrency("CD");
		
		List<Input> inputList = new ArrayList<>();
		List<OutputDto> outputList = new ArrayList<>();
		
		inputList.add(input);
		
		Transform transform = new Transform();
		outputList = transform.transform(inputList);
		
		assertEquals(1, outputList.size());
		assertEquals("AbcCode", outputList.get(0).getAccountCode());
		assertEquals("My Account", outputList.get(0).getName());
		assertEquals("RRSP", outputList.get(0).getType());
		assertEquals("01-01-2018", outputList.get(0).getOpenDate());
		assertEquals("CAD", outputList.get(0).getCurrency());
	}
	
	@Test
	void testSource2TransformOutput() {
		Source2DtoInputImpl input = new Source2DtoInputImpl();
		input.setName("My Account");
		input.setType("RESP");
		input.setCurrency("U");
		input.setCustodianCode("Custodian Code");
		
		List<Input> inputList = new ArrayList<>();
		List<OutputDto> outputList = new ArrayList<>();
		
		inputList.add(input);
		
		Transform transform = new Transform();
		outputList = transform.transform(inputList);
		
		assertEquals(1, outputList.size());
		assertEquals("Custodian Code", outputList.get(0).getAccountCode());
		assertEquals("My Account", outputList.get(0).getName());
		assertEquals("RESP", outputList.get(0).getType());
		assertEquals("USD", outputList.get(0).getCurrency());
	}
}
