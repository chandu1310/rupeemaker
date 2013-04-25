package org.idream.moneymaker.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.idream.moneymaker.beans.AppSession;
import org.idream.moneymaker.beans.StockItem;
import org.idream.moneymaker.net.beans.PublishedStockDetails;
import org.idream.moneymaker.util.CommonUtil;

public class StockTile extends JPanel implements Runnable {
	private TileColorProfile colorProfile;
	private StockItem stkItm;
	
	JLabel currentCost;
	JLabel growthIndicator;
	JLabel growthPercent;
	JLabel high52Value, low52Value;
	
	private ScheduledExecutorService execService; 
	
	protected StockTile(StockItem item, TileColorProfile colorProfile){
		super();		
		this.stkItm = item;
		this.colorProfile = colorProfile;
		buildUI();
		execService = Executors.newSingleThreadScheduledExecutor();
		execService.scheduleAtFixedRate(this, 5, 10, TimeUnit.SECONDS);		
	}
	
	
	
	public void buildUI(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(2, 5, 0, 0));
		setBackground(colorProfile.getBgColor());
		add(getHeader());		
		add(getContentHeader());
		add(getContent());
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
	
	
	private JPanel getHeader()
	{	
		JWhitePanel headerPanel = new JWhitePanel();		
		{
			headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
			headerPanel.setBackground(colorProfile.getBgColor());
			
			JPanel companyInfoPanel = new JPanel();
			{
				companyInfoPanel.setLayout(new BoxLayout(companyInfoPanel, BoxLayout.Y_AXIS));
				companyInfoPanel.setBackground(colorProfile.getBgColor());

				JLabel l1 = new JLabel(stkItm.getStockCode());
				l1.setToolTipText(stkItm.getStockName());
				l1.setAlignmentX(Component.LEFT_ALIGNMENT);
				l1.setFont(MetroFont.H2_FONT);
				l1.setForeground(colorProfile.getFgColor());	
				companyInfoPanel.add(l1);
				
				String stkName = stkItm.getStockName();
				if(stkName.length()>32){
					stkName = stkName.substring(0, 29)+"...";
				}
				JLabel l2 = new JLabel(stkName);
				l2.setToolTipText(stkItm.getStockName());
				l2.setAlignmentX(Component.LEFT_ALIGNMENT);
				l2.setFont(MetroFont.H4_FONT);
				l2.setForeground(colorProfile.getFgColor());	
				companyInfoPanel.add(l2);
			}						
			companyInfoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
			headerPanel.add(companyInfoPanel);
			
			JPanel growthIndicatorPanel = new JPanel();
			{
				growthIndicatorPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
				growthIndicatorPanel.setBackground(colorProfile.getBgColor());
				growthIndicator = new JLabel();
				growthIndicatorPanel.add(growthIndicator);
				
				growthPercent = new JLabel();
				growthPercent.setToolTipText("Percent change in price.");
				growthPercent.setFont(MetroFont.H3_FONT);
				growthPercent.setForeground(colorProfile.getFgColor());
				growthIndicatorPanel.add(growthPercent);
			}
			growthIndicatorPanel.setAlignmentY(Component.TOP_ALIGNMENT);
			headerPanel.add(growthIndicatorPanel);
		}
		headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		return headerPanel;
	}
	
	
	private JPanel getContentHeader(){
		JPanel panel = new JPanel(new GridLayout(1, 3));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBackground(colorProfile.getBgColor());
		
		JLabel h1 = new JLabel("Holdings");
		h1.setFont(MetroFont.H4_FONT);
		h1.setForeground(colorProfile.getFgColor());	
		panel.add(h1);
		
		JLabel h2 = new JLabel("History");
		h2.setFont(MetroFont.H4_FONT);
		h2.setForeground(colorProfile.getFgColor());	
		panel.add(h2);

		JLabel h3 = new JLabel("Current");
		h3.setFont(MetroFont.H4_FONT);
		h3.setForeground(colorProfile.getFgColor());	
		panel.add(h3);

		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		return panel;
	}
	
	private JPanel getContent(){
		JPanel contentPanel = new JPanel(new GridLayout(1, 3));
		contentPanel.setBackground(colorProfile.getBgColor());
		
		contentPanel.add(getDetailsPanel());
		contentPanel.add(getHistoryPanel());
		contentPanel.add(getCurrentDetaisPanel());
		
		contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		return contentPanel;
	}
	
	private JPanel getDetailsPanel(){
		JPanel panel = new JPanel(new GridLayout(3, 1));
		panel.setBackground(colorProfile.getBgColor());
		
		JLabel cost = new JLabel( CommonUtil.formatToCurrency(stkItm.getStockPrice()) );
		cost.setToolTipText("Investment price per share unit.");
		cost.setFont(MetroFont.H3_FONT);
		cost.setForeground(colorProfile.getFgColor());	
		panel.add(cost);
		
		String unitsStr = stkItm.getStockUnits()+" Units";
		JLabel units = new JLabel(unitsStr);
		units.setToolTipText("Share units bought.");
		units.setFont(MetroFont.H3_FONT);
		units.setForeground(colorProfile.getFgColor());	
		panel.add(units);
		
		
		return panel;		
	}
	
	
	
	private JPanel getHistoryPanel(){
		JPanel panel = new JPanel(new GridLayout(3,1));
		panel.setBackground(colorProfile.getBgColor());
		
		high52Value = new JLabel();
		high52Value.setToolTipText("52 week high price.");
		high52Value.setFont(MetroFont.H3_FONT);
		high52Value.setForeground(colorProfile.getFgColor());	
		panel.add(high52Value);
		
		low52Value = new JLabel();
		low52Value.setToolTipText("52 week low price.");
		low52Value.setFont(MetroFont.H3_FONT);
		low52Value.setForeground(colorProfile.getFgColor());	
		panel.add(low52Value);
		

		return panel;
	}
	
	private JPanel getCurrentDetaisPanel(){
		JPanel panel = new JPanel(new GridLayout(3,1));
		panel.setBackground(colorProfile.getBgColor());
		
		
		currentCost = new JLabel("...");
		currentCost.setToolTipText("Current trading price.");
		currentCost.setFont(MetroFont.H3_FONT);
		currentCost.setForeground(colorProfile.getFgColor());	
		panel.add(currentCost);

		return panel;
	}
	
	@Override
	public void run() {
		PublishedStockDetails details = AppSession.publisher.getStockDetails(this.stkItm.getStockCode());
		if(details.getChangeFromPrevClose()<0)		
			growthIndicator.setIcon(UIConstants.DOWN_ARROW);
		else
			growthIndicator.setIcon(UIConstants.UP_ARROW);
		
		currentCost.setText(CommonUtil.formatToCurrency(details.getCurrentPrice()) );
		growthPercent.setText(details.getPercentChangeFromPrevClose()+"%");
		high52Value.setText(CommonUtil.formatToCurrency(details.getHigh52Price() ));
		low52Value.setText(CommonUtil.formatToCurrency(details.getLow52Price()));
		growthIndicator.repaint();		
	}
}
