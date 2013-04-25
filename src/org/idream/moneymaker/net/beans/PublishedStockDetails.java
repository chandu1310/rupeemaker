package org.idream.moneymaker.net.beans;

import java.util.Calendar;
import java.util.Date;

public class PublishedStockDetails {
	private String market;
	private String stockCode;
	private String stockName;
	
	
	private float high52Price;
	private float low52Price;
	
	private float previousClosePrice;
	private float openedPrice;
	private float highPriceToday;
	private float lowPriceToday;
	private float currentPrice;
	private float closePrice;
	
	private float changeFromPrevClose;
	private float percentChangeFromPrevClose;
	
	private float volumeWeightedAveragePrice;
	private long tradedVolume;
	private double tradedValue;
	
	private String stockDate;

	@Override
	public String toString() {
		String s = "\nmarket - "+market+
				"\nstockcode - " + stockCode+
				"\nstockname - " + stockName+
				"\n52high - " +high52Price+
				"\n52low - " +low52Price+
				"\nprev close - " +previousClosePrice+
				"\nopened price - " +openedPrice+
				"\nhigh today - " +highPriceToday+
				"\nlow today - " +lowPriceToday+
				"\ncurrent - " +currentPrice+
				"\nclose - " +closePrice+
				"\nchange - " +changeFromPrevClose+
				"\nperc change - " +percentChangeFromPrevClose+
				"\nVWWAP - " +volumeWeightedAveragePrice+
				"\ntraded vol - " +tradedVolume+
				"\ntraded value - " +tradedValue+
				"\nstock date - "+stockDate;
		return s;
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

	public float getHigh52Price() {
		return high52Price;
	}

	public void setHigh52Price(float high52Price) {
		this.high52Price = high52Price;
	}

	public float getLow52Price() {
		return low52Price;
	}

	public void setLow52Price(float low52Price) {
		this.low52Price = low52Price;
	}

	public float getPreviousClosePrice() {
		return previousClosePrice;
	}

	public void setPreviousClosePrice(float previousClosePrice) {
		this.previousClosePrice = previousClosePrice;
	}

	public float getOpenedPrice() {
		return openedPrice;
	}

	public void setOpenedPrice(float openedPrice) {
		this.openedPrice = openedPrice;
	}

	public float getHighPriceToday() {
		return highPriceToday;
	}

	public void setHighPriceToday(float highPriceToday) {
		this.highPriceToday = highPriceToday;
	}

	public float getLowPriceToday() {
		return lowPriceToday;
	}

	public void setLowPriceToday(float lowPriceToday) {
		this.lowPriceToday = lowPriceToday;
	}

	public float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}

	public float getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(float closePrice) {
		this.closePrice = closePrice;
	}

	public float getChangeFromPrevClose() {
		return changeFromPrevClose;
	}

	public void setChangeFromPrevClose(float changeFromPrevClose) {
		this.changeFromPrevClose = changeFromPrevClose;
	}

	public float getPercentChangeFromPrevClose() {
		return percentChangeFromPrevClose;
	}

	public void setPercentChangeFromPrevClose(float percentChangeFromPrevClose) {
		this.percentChangeFromPrevClose = percentChangeFromPrevClose;
	}

	public float getVolumeWeightedAveragePrice() {
		return volumeWeightedAveragePrice;
	}

	public void setVolumeWeightedAveragePrice(float volumeWeightedAveragePrice) {
		this.volumeWeightedAveragePrice = volumeWeightedAveragePrice;
	}

	public long getTradedVolume() {
		return tradedVolume;
	}

	public void setTradedVolume(long tradedVolume) {
		this.tradedVolume = tradedVolume;
	}

	public double getTradedValue() {
		return tradedValue;
	}

	public void setTradedValue(double tradedValue) {
		this.tradedValue = tradedValue;
	}

	public String getStockDate() {
		return stockDate;
	}

	public void setStockDate(String stockDate) {
		this.stockDate = stockDate;
	}	
	
}
