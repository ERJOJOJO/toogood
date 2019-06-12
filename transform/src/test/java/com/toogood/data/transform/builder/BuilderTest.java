package com.toogood.data.transform.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.toogood.data.transform.input.Source1DtoInputImpl;
import com.toogood.data.transform.input.Source2DtoInputImpl;
import com.toogood.data.transform.output.OutputDto;

public class BuilderTest {
	@Test
	void TestSource1OutputBuilderImpl() {
		Builder builder = Source1OutputBuilderImpl.createBuilder();
		Source1DtoInputImpl input = new Source1DtoInputImpl();
		input.setIdentifier("123|AbcCode");
		input.setName("My Account");
		input.setType("2");
		input.setOpened("01-01-2018");
		input.setCurrency("CD");
		
		if (builder != null) {
			builder.setInputOutput(input, new OutputDto());

			builder.buildAccountCode();
			builder.buildName();
			builder.buildType();
			builder.buildOpenDate();
			builder.buildCurrency();
		}
		
		assertEquals("AbcCode", builder.getOutput().getAccountCode());
		assertEquals("My Account", builder.getOutput().getName());
		assertEquals("RRSP", builder.getOutput().getType());
		assertEquals("01-01-2018", builder.getOutput().getOpenDate());
		assertEquals("CAD", builder.getOutput().getCurrency());
	}
	
	@Test
	void TestSource2OutputBuilderImpl() {
		Builder builder = Source2OutputBuilderImpl.createBuilder();
		Source2DtoInputImpl input = new Source2DtoInputImpl();
		input.setName("My Account");
		input.setType("RESP");
		input.setCurrency("U");
		input.setCustodianCode("Custodian Code");
		
		if (builder != null) {
			builder.setInputOutput(input, new OutputDto());

			builder.buildAccountCode();
			builder.buildName();
			builder.buildType();
			builder.buildOpenDate();
			builder.buildCurrency();
		}
		
		assertEquals("Custodian Code", builder.getOutput().getAccountCode());
		assertEquals("My Account", builder.getOutput().getName());
		assertEquals("RESP", builder.getOutput().getType());
		assertEquals("USD", builder.getOutput().getCurrency());
	}
}
