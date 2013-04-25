package org.idream.moneymaker.ui.runnables;

import org.idream.moneymaker.beans.AppSession;
import org.idream.moneymaker.beans.StockItem;
import org.idream.moneymaker.ui.TileFactory;
import org.idream.moneymaker.util.CommonUtil;

public class AddNewStockItem implements Runnable {
	StockItem newItem;
	
	public AddNewStockItem(StockItem item) {
		this.newItem = item;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		//Update UI Elements for new stocks panel, invested amount
		
	}
}
