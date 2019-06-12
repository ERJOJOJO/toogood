package com.toogood.data.transform.input;

import com.toogood.data.transform.util.Constants.OutputTransformerType;

/*
 * Class implemented Input interface representing the input from Source 1
 */
public class Source1DtoInputImpl implements Input{
	private String identifier;
	private String name;
	private String type;
	private String opened;
	private String currency;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOpened() {
		return opened;
	}
	public void setOpened(String opened) {
		this.opened = opened;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	/*
	 * Return the enum value to identify where Source 1 is the source of input for this class
	 */
	@Override
	public OutputTransformerType getSource() {
		return OutputTransformerType.SOURCE1;
	}
}
