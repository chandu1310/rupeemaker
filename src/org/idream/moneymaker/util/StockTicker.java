package org.idream.moneymaker.util;

import java.util.Iterator;

import org.idream.moneymaker.beans.AppSession;
import org.idream.moneymaker.beans.StockItem;


public class StockTicker extends Thread{
	private boolean flag = true;
	
	public void run() {
		synchronized (this) {				
			while(flag){			
				Iterator<StockItem> stkItr = AppSession.profile.getPortFolio().getStocks().iterator();
				while (stkItr.hasNext()) {
					StockItem stockItem = (StockItem) stkItr.next();
					stockItem.getUi().run();
				}
				
				try{
				Thread.sleep(30,000);
				}catch(Exception er){
					er.printStackTrace();
				}
				
			}
			notifyAll();
		}		
	};
	
	public void shutdown(){
		System.out.println("Shutting down ticker");
		flag = false;
	}

}
