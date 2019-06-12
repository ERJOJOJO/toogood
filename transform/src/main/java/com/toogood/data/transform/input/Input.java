package com.toogood.data.transform.input;

import com.toogood.data.transform.util.Constants.OutputTransformerType;

/*
 * Interface all input class from different source needed to implement
 * All implemented class needed to return a type of source in the getSource method
 */
public interface Input {
	/*
	 * Method to return the source of the implemented class
	 * 
	 * @return OutputTransformerType: A enum Type of the source
	 */
	public OutputTransformerType getSource();
}
