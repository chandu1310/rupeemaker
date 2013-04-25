package org.idream.moneymaker.ui;

import java.awt.Color;
import java.io.Serializable;

public final class TileColorProfile implements Serializable{
	public static final TileColorProfile TEAL = new TileColorProfile(0x008299, 0xFFFFFF); //TEAL
	public static final TileColorProfile BLUE =  new TileColorProfile(0x2672EC, 0xFFFFFF); //BLUE
	public static final TileColorProfile PURPLE =  new TileColorProfile(0x8C0095, 0xFFFFFF); //PURPLE
	public static final TileColorProfile DARK_PURPLE =  new TileColorProfile(0x5133AB, 0xFFFFFF); //DARK PURPLE
	public static final TileColorProfile RED =  new TileColorProfile(0xAC193D, 0xFFFFFF); //RED
	public static final TileColorProfile ORANGE =  new TileColorProfile(0xD24726, 0xFFFFFF); //ORANGE
	public static final TileColorProfile GREEN =  new TileColorProfile(0x008A00, 0xFFFFFF); //GREEN
	public static final TileColorProfile SKYBLUE =  new TileColorProfile(0x094AB2, 0xFFFFFF); //SKYBLUE
	
	private int bgColor;
	private int fgColor;
	
	public TileColorProfile(int bgColor, int fgColor){
		this.bgColor = bgColor;
		this.fgColor = fgColor;
	}
	
	public Color getBgColor() {
		return new Color(bgColor);
	}
	public Color getFgColor() {
		return new Color(fgColor);
	}
}
