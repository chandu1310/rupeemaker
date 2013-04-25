package org.idream.moneymaker.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.idream.moneymaker.ui.StockTile;

public class StockItem implements Serializable {
	private String market = "NSE";
	private String stockCode = "INFY";
	private String stockName = "Infosys Technologies Inc.";
	private int stockUnits = 2500;
	private float stockPrice = 2852.80f;
	private Date stockDate = Calendar.getInstance().getTime();
	
	private transient StockTile ui;
	
	public StockItem(String mkt, String stkCode, String stkName, int units, float stkPrice, Date stkDate) 
	{
		market = mkt;
		stockCode = stkCode;
		stockName = stkName;
		stockUnits = units;
		stockPrice = stkPrice;
		stockDate = stkDate;
	}
	
	
	
	public StockTile getUi() {
		return ui;
	}



	public void setUi(StockTile ui) {
		this.ui = ui;
	}



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
	public int getStockUnits() {
		return stockUnits;
	}
	public void setStockUnits(int stockUnits) {
		this.stockUnits = stockUnits;
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
