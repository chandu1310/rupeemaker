package org.idream.moneymaker.beans;

import java.io.Serializable;
import java.util.Vector;

public class Portfolio implements Serializable{
	private float capitalAmount;
	private float investedAmount;
	private Vector<StockItem> stocks = new Vector<StockItem>();
	
	public float getCapitalAmount() {
		return capitalAmount;
	}
	public void setCapitalAmount(float capitalAmount) {
		this.capitalAmount = capitalAmount;
	}
	public float getInvestedAmount() {
		return investedAmount;
	}
	public Vector<StockItem> getStocks() {
		return stocks;
	}
	public void setStocks(Vector<StockItem> stocks) {
		if(stocks==null){
			this.stocks = new Vector<StockItem>();
		}else{
			this.stocks = stocks;
			updateInvestedAmount();
		}
	}
	
	public void updateInvestedAmount(){
		this.investedAmount = 0.0f;
		for(StockItem item:stocks){
			this.investedAmount += item.getStockUnits()*item.getStockPrice();
		}
	}
	
	public void addStockItem(StockItem item){
		this.stocks.add(item);
		updateInvestedAmount();
	}
}
