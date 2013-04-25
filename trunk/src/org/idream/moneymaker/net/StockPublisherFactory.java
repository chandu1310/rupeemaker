package org.idream.moneymaker.net;

import org.idream.moneymaker.net.impl.NSEStockPublisher;

public class StockPublisherFactory {
	private static NSEStockPublisher nsePublisherInstance = null;
	
	public static StockPublisher getPublisher(){		
		if(nsePublisherInstance == null){
			nsePublisherInstance = new NSEStockPublisher();
		}
		return nsePublisherInstance;
	}	
}
