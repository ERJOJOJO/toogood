package com.toogood.data.transform.input;

import javax.validation.constraints.NotBlank;

import com.toogood.data.transform.util.Constants.OutputTransformerType;
import com.toogood.data.transform.validation.constraint.CurrencyConstraint;
import com.toogood.data.transform.validation.constraint.IdentifierConstraint;
import com.toogood.data.transform.validation.constraint.TypeConstraint;

/*
 * Class implemented Input interface representing the input from Source 1
 */
public class Source1DtoInputImpl implements Input{
	@NotBlank (message = "Identifier cannot be empty")
	@IdentifierConstraint (message = "Identifier need to contain '|'")
	private String identifier;
	@NotBlank (message = "Name cannot be empty")
	private String name;
	@NotBlank (message = "Type cannot be empty")
	@TypeConstraint (message = "Invalid Type")
	private String type;
	private String opened;
	@NotBlank (message = "Currency cannot be empty")
	@CurrencyConstraint (message = "Invalid Currency")
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
