package org.idream.moneymaker.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class CommonUtil {

	public static String formatToCurrency(float value){
		BigDecimal b = new BigDecimal(value);
		return NumberFormat.getCurrencyInstance().format(b);
	}
}
