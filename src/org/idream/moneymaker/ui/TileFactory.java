package org.idream.moneymaker.ui;

import org.idream.moneymaker.beans.ChangeRequestItem;
import org.idream.moneymaker.beans.StockItem;
import org.idream.moneymaker.beans.SuggestionItem;

public final class TileFactory {
	
	public static RequestTile getChangeRequestItemTile(ChangeRequestItem item){
		RequestTile ui = new RequestTile(item, UserInterfaceUtilities.getBgColorForTile());	
		return ui;
	}
	
	public static StockTile getCurrentStockTile(StockItem item){
		StockTile ui = new StockTile(item, UserInterfaceUtilities.getBgColorForTile());	
		return ui;
	}
	
	public static SuggestionTile getSuggestedStockTile(SuggestionItem item){
		SuggestionTile ui = new SuggestionTile(item, UserInterfaceUtilities.getBgColorForTile());	
		return ui;
	}
}
