package com.toogood.data.transform.transformer;

import com.toogood.data.transform.builder.Builder;
import com.toogood.data.transform.builder.Source1OutputBuilderImpl;
import com.toogood.data.transform.builder.Source2OutputBuilderImpl;
import com.toogood.data.transform.util.Constants.OutputTransformerType;

/*
 * Use Factory design pattern to return a builder class based on the enum Type in the Input Interface
 */
public class OutputTransformerFactory {
	/*
	 * Builder implemented class for Source 1
	 */
	private static Builder source1OutputBuilderImpl;
	
	/*
	 * Builder implemented class for Source 2
	 */
	private static Builder source2OutputBuilderImpl;

	/*
	 * Return a class implemented Builder Interface based on the enum Type in the Input Interface
	 * 
	 * @param OutputTransformerType: a type of source input from enum OutputTransformerType in constants
	 * 
	 * @return Builder: a builder object based on the source input
	 */
	public static Builder retrieve(OutputTransformerType type) {
		if (type.equals(OutputTransformerType.SOURCE1)) {
			if (source1OutputBuilderImpl == null) {
				source1OutputBuilderImpl = Source1OutputBuilderImpl.createBuilder();
			}
			return source1OutputBuilderImpl;
		} else if (type.equals(OutputTransformerType.SOURCE2)) {
			if (source2OutputBuilderImpl == null) {
				source2OutputBuilderImpl = Source2OutputBuilderImpl.createBuilder();
			}
			return source2OutputBuilderImpl;
		} else {
			return null;
		}
	}
}
