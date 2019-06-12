package com.toogood.data.transform.util;

import java.util.Arrays;

/*
 * Constants value
 */
public class Constants {
	/*
	 * Enum containing all different kind of input source
	 */
	public static enum OutputTransformerType {SOURCE1, SOURCE2};
	
	/*
	 * Enum type for identify the Output Type value from the input value
	 */
	public static enum Type {
		TRADING(1, "Trading"), RRSP(2, "RRSP"), RESP(3, "RESP"), FUND(4, "Fund");

		private final int id;
		private final String details;

		Type(int id, String details) {
			this.id = id;
			this.details = details;
		}

		public static String getTypeDetails(int id) {
			Type type = Arrays.stream(values()).filter(value -> value.id == id).findFirst().orElse(null);
			if (type != null) {
				return type.details;
			} else {
				return null;
			}
		}
	};

	/*
	 * Enum type for identify the Output Currency value from the input value
	 */
	public static enum Currency {
		CAD, USD;

		public static String getCurrency(String s) {
			switch (s) {
			case "CD":
				return CAD.name();
			case "C":
				return CAD.name();
			case "US":
				return USD.name();
			case "U":
				return USD.name();
			default:
				return null;
			}
		}
	}
}
