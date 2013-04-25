package org.idream.moneymaker.beans;

import java.util.Calendar;
import java.util.Date;

public class SuggestionItem {
	private String market = "NSE";
	private String stockCode = "INFY";
	private String stockName = "Infosys Technologies Inc.";
	private float stockPrice = 2852.80f;
	private Date stockDate = Calendar.getInstance().getTime();
	
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public float getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(float stockPrice) {
		this.stockPrice = stockPrice;
	}
	public Date getStockDate() {
		return stockDate;
	}
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}
	
	
}
