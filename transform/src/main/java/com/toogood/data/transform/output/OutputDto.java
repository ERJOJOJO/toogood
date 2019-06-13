package com.toogood.data.transform.output;

import com.toogood.data.transform.validation.ValidateResults;

/*
 * OutputDto contain all generic value that would be used by other part of the system
 * All input classes needed to transform their data to the data in this class
 */
public class OutputDto {
	private String accountCode;
	private String name;
	private String type;
	private String openDate;
	private String currency;
	private ValidateResults validateResults;
	
	public String getAccountCode() {
		return (accountCode != null) ? accountCode : "";
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getName() {
		return (name != null) ? name : "";
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return (type != null) ? type : "";
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOpenDate() {
		return (openDate != null) ? openDate : "";
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getCurrency() {
		return (currency != null) ? currency : "";
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public ValidateResults getValidateResults() {
		return validateResults;
	}
	public void setValidateResults(ValidateResults validateResults) {
		this.validateResults = validateResults;
	}
	
	/*
	 * Override the toString() method to display output 
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Account Code: " + getAccountCode() + "\n");
		sb.append("Name: " + getName() + "\n");
		sb.append("Type: " + getType() + "\n");
		sb.append("Open Date: " + getOpenDate() + "\n");
		sb.append("Currency: " + getCurrency() + "\n");
		
		if(getValidateResults() != null && !getValidateResults().isValid()) {
			sb.append("Error Message: " + getValidateResults().getErrorMessage() + "\n");
		}
		
		return sb.toString();
	}
}
