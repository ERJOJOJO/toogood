package com.toogood.data.transform.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;

import com.toogood.data.transform.builder.Builder;
import com.toogood.data.transform.builder.Source1OutputBuilderImpl;
import com.toogood.data.transform.builder.Source2OutputBuilderImpl;
import com.toogood.data.transform.input.Input;
import com.toogood.data.transform.input.Source1DtoInputImpl;
import com.toogood.data.transform.input.Source2DtoInputImpl;
import com.toogood.data.transform.output.OutputDto;
import com.toogood.data.transform.util.Constants.OutputTransformerType;

public class TransformerTest {
	@Test
	void TestSource1TransformerFactory() {
		Builder builder = OutputTransformerFactory.retrieve(OutputTransformerType.SOURCE1);
		
		assertEquals(true, builder instanceof Source1OutputBuilderImpl);
	}

	@Test
	void TestSource2TransformerFactory() {
		Builder builder = OutputTransformerFactory.retrieve(OutputTransformerType.SOURCE2);
		
		assertEquals(true, builder instanceof Source2OutputBuilderImpl);
	}
	
	@Test
	void TestSource1Transformer() {
		Source1DtoInputImpl input = new Source1DtoInputImpl();
		input.setIdentifier("123|AbcCode");
		input.setName("My Account");
		input.setType("2");
		input.setOpened("01-01-2018");
		input.setCurrency("CD");
		
		CompletableFuture<OutputDto> futureOutput = OutputTransformer.buildOutput(input);
		
		OutputDto output = null;
		try {
			output = futureOutput.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		assertEquals("AbcCode", output.getAccountCode());
		assertEquals("My Account", output.getName());
		assertEquals("RRSP", output.getType());
		assertEquals("01-01-2018", output.getOpenDate());
		assertEquals("CAD", output.getCurrency());
	}
	
	@Test
	void TestSource2Transformer() {
		Source2DtoInputImpl input = new Source2DtoInputImpl();
		input.setName("My Account");
		input.setType("RESP");
		input.setCurrency("U");
		input.setCustodianCode("Custodian Code");
		
		CompletableFuture<OutputDto> futureOutput = OutputTransformer.buildOutput(input);
		
		OutputDto output = null;
		try {
			output = futureOutput.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		assertEquals("Custodian Code", output.getAccountCode());
		assertEquals("My Account", output.getName());
		assertEquals("RESP", output.getType());
		assertEquals("USD", output.getCurrency());
	}
}
