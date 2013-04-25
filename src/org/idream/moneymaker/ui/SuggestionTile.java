package org.idream.moneymaker.ui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.idream.moneymaker.beans.SuggestionItem;

public class SuggestionTile extends JPanel{
	private TileColorProfile colorProfile;
	private SuggestionItem suggestionItem;
	
	JLabel currentCost;
	JLabel growthIndicator;
	JLabel growthPercent;
	JLabel high52Value, low52Value;
	
	protected SuggestionTile(SuggestionItem item, TileColorProfile colorProfile){
		super();		
		this.suggestionItem = item;
		this.colorProfile = colorProfile;
		buildUI();
	}
	
	
	
	public void buildUI(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(2, 5, 0, 0));
		setBackground(colorProfile.getBgColor());
		setPreferredSize(new Dimension(250,125));
	}
	
	public void reBuildUI(){
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				removeAll();
				buildUI();
				repaint();
			}
		});
	}
}
