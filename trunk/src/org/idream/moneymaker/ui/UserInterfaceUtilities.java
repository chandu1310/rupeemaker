package org.idream.moneymaker.ui;


public class UserInterfaceUtilities {
	private static int nextColor = 0;
	
	private static TileColorProfile[] tileColors = {
			TileColorProfile.BLUE,
			TileColorProfile.DARK_PURPLE,
			TileColorProfile.GREEN,
			TileColorProfile.ORANGE,
			TileColorProfile.PURPLE,
			TileColorProfile.RED,
			TileColorProfile.SKYBLUE,
			TileColorProfile.TEAL
		}; 

	public static TileColorProfile getBgColorForTile(){
		TileColorProfile c = tileColors[nextColor];
		nextColor = (nextColor+1)%tileColors.length;
		return c;
	}
	
	public static TileColorProfile BUY_TILE_COLOR =  TileColorProfile.BLUE;
	public static TileColorProfile SELL_TILE_COLOR = TileColorProfile.RED;
}
