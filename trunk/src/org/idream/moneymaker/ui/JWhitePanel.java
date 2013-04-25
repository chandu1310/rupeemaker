package org.idream.moneymaker.ui;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class JWhitePanel extends JPanel{
	public JWhitePanel() {
		super();
		setBackground(Color.WHITE);
	}
	
	public JWhitePanel(LayoutManager layoutmanager){
		super(layoutmanager);
		setBackground(Color.WHITE);
	}
}
