package org.idream.moneymaker.net;

import org.idream.moneymaker.net.beans.PublishedStockDetails;

public interface StockPublisher {
	public PublishedStockDetails getStockDetails(String stockCode);
}
