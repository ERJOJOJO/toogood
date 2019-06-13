package com.toogood.data.transform.input;

import javax.validation.constraints.NotBlank;

import com.toogood.data.transform.util.Constants.OutputTransformerType;
import com.toogood.data.transform.validation.constraint.CurrencyConstraint;

/*
 * Class implemented Input interface representing the input from Source 2
 */
public class Source2DtoInputImpl implements Input {
	@NotBlank (message = "Name cannot be empty")
	private String name;
	@NotBlank (message = "Type cannot be empty")
	private String type;
	@NotBlank (message = "Currency cannot be empty")
	@CurrencyConstraint (message = "Invalid Currency")
	private String currency;
	@NotBlank (message = "Custodian Code cannot be empty")
	private String custodianCode;
	
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
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCustodianCode() {
		return custodianCode;
	}
	public void setCustodianCode(String custodianCode) {
		this.custodianCode = custodianCode;
	}
	
	/*
	 * Return the enum value to identify where Source 2 is the source of input for this class
	 */
	@Override
	public OutputTransformerType getSource() {
		return OutputTransformerType.SOURCE2;
	}
}
