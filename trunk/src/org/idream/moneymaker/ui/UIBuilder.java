package org.idream.moneymaker.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.idream.moneymaker.beans.AppSession;
import org.idream.moneymaker.beans.ChangeRequestItem;
import org.idream.moneymaker.beans.StockItem;
import org.idream.moneymaker.beans.SuggestionItem;
import org.idream.moneymaker.thirdparty.WrapLayout;
import org.idream.moneymaker.util.ChangeRequestUtil;
import org.idream.moneymaker.util.CommonUtil;

public final class UIBuilder {
	public static JWhitePanel buildContentPanel(){
		JWhitePanel contentPanel = new JWhitePanel(new BorderLayout());		
		
		//Stocks Suggestions Panel
		JScrollPane suggestionsPanel = buildSuggestionsPanel();		
		contentPanel.add(suggestionsPanel, BorderLayout.EAST);	
		
		//Main Content displayed in middle including the headers.
		JWhitePanel mainContentPane = new JWhitePanel();
		{
			mainContentPane.setLayout(new BoxLayout(mainContentPane, BoxLayout.Y_AXIS));
			
			//Header Panel where profile information, capital and investment is displayed.
			JWhitePanel headerPanel = new JWhitePanel(new FlowLayout(FlowLayout.LEFT));
			{
				headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
				
				//Profile information section
				JWhitePanel profileInfoPanel = new JWhitePanel();
				{
					profileInfoPanel.setLayout(new BoxLayout(profileInfoPanel, BoxLayout.Y_AXIS));
					
					JLabel profileFirstNameLabel = new JLabel(AppSession.profile.getUsername());
					profileFirstNameLabel.setFont(MetroFont.H2_FONT);
					profileFirstNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
					profileInfoPanel.add(profileFirstNameLabel);
				}
				headerPanel.add(profileInfoPanel);
				
				JWhitePanel capitalDetailsPanel = new JWhitePanel();
				{
					JWhitePanel capitalDetailsLabelPanel = new JWhitePanel();
					{
						capitalDetailsLabelPanel.setLayout(new BoxLayout(capitalDetailsLabelPanel, BoxLayout.Y_AXIS));
					
						JLabel l1 = new JLabel("Total");
						l1.setFont(MetroFont.H3_FONT);
						l1.setAlignmentX(Component.RIGHT_ALIGNMENT);
						capitalDetailsLabelPanel.add(l1);
						
						JLabel l2 = new JLabel("Capital");
						l2.setFont(MetroFont.H3_FONT);
						l2.setAlignmentX(Component.RIGHT_ALIGNMENT);					
						capitalDetailsLabelPanel.add(l2);
					}
					capitalDetailsPanel.add(capitalDetailsLabelPanel);
					
					JWhitePanel capitalDetailsValuePanel = new JWhitePanel();
					{
						capitalDetailsValuePanel.setLayout(new BoxLayout(capitalDetailsValuePanel, BoxLayout.Y_AXIS));
						
						JLabel capitalValueLabel = new JLabel(
										CommonUtil.formatToCurrency(AppSession.profile.getPortFolio().getCapitalAmount())
										);
						capitalValueLabel.setFont(MetroFont.H0_FONT);
						capitalValueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
						capitalValueLabel.setAlignmentY(Component.TOP_ALIGNMENT);
						capitalDetailsValuePanel.add(capitalValueLabel);
					}
					capitalDetailsPanel.add(capitalDetailsValuePanel);				
				}
				headerPanel.add(capitalDetailsPanel);
				
				JWhitePanel investmentDetailsPanel = new JWhitePanel(new FlowLayout(FlowLayout.LEFT));
				{
					JWhitePanel investmentDetailsLabelPanel = new JWhitePanel();
					{
						investmentDetailsLabelPanel.setLayout(new BoxLayout(investmentDetailsLabelPanel, BoxLayout.Y_AXIS));
					
						JLabel l1 = new JLabel("Invested");
						l1.setFont(MetroFont.H3_FONT);
						l1.setAlignmentX(Component.RIGHT_ALIGNMENT);
						investmentDetailsLabelPanel.add(l1);
						
						JLabel l2 = new JLabel("Amount");
						l2.setFont(MetroFont.H3_FONT);
						l2.setAlignmentX(Component.RIGHT_ALIGNMENT);					
						investmentDetailsLabelPanel.add(l2);
					}
					investmentDetailsPanel.add(investmentDetailsLabelPanel);
					
					JWhitePanel investmentDetailsValuePanel = new JWhitePanel();
					{
						investmentDetailsValuePanel.setLayout(new BoxLayout(investmentDetailsValuePanel, BoxLayout.Y_AXIS));
						
						JLabel investmentValueLabel = new JLabel(
								CommonUtil.formatToCurrency(AppSession.profile.getPortFolio().getInvestedAmount())
								);
						AppSession.ui.investmentValueLabel = investmentValueLabel;		
						investmentValueLabel.setFont(MetroFont.H0_FONT);
						investmentValueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
						investmentValueLabel.setAlignmentY(Component.TOP_ALIGNMENT);
						investmentDetailsValuePanel.add(investmentValueLabel);
					}
					investmentDetailsPanel.add(investmentDetailsValuePanel);	
					
					JButton addStockToInvestmentButton = new JButton("Add Stock");
					addStockToInvestmentButton.addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							StockItem newItem = null;
							
							//Show a dialog for new stock details
							newItem = showNewStockItemDialog();
							
							AppSession.shutdownTicker();
							
							
							//Add the new stock item to vector.
							AppSession.profile.getPortFolio().addStockItem(newItem);							
							AppSession.ui.currentStocksPanel.add( TileFactory.getCurrentStockTile(newItem) );
							AppSession.ui.currentStocksPanel.validate(); 
							AppSession.ui.currentStocksPanel.repaint(); 
							AppSession.ui.investmentValueLabel.setText(CommonUtil.formatToCurrency(AppSession.profile.getPortFolio().getInvestedAmount()));
						}
							
					});
					investmentDetailsPanel.add(addStockToInvestmentButton);
				}
				headerPanel.add(investmentDetailsPanel);				
			}
			headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);	
	        mainContentPane.add(headerPanel);
			
	        JSplitPane mainSplitterPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	        {
				JWhitePanel currentInvestmentsPanel = buildCurrentInvestmentsPanel();
				currentInvestmentsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);	
//		        mainContentPane.add(currentInvestmentsPanel);
				mainSplitterPanel.add(currentInvestmentsPanel);
				
		        JWhitePanel changeRequestsPanel = buildChangeRequestsPanel();
		        changeRequestsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//				mainContentPane.add(changeRequestsPanel);
				mainSplitterPanel.add(changeRequestsPanel);
	        }
	        mainSplitterPanel.setOneTouchExpandable(true);
	        mainSplitterPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        mainSplitterPanel.setResizeWeight(0.5);  
	        mainContentPane.add(mainSplitterPanel);
		}
		contentPanel.add(mainContentPane, BorderLayout.CENTER);		
		return contentPanel;
	}

	private static StockItem showNewStockItemDialog(){
		JTextField code = new JTextField();
		JTextField name = new JTextField();
		JTextField units = new JTextField();
		JTextField price = new JTextField();
		
		final JComponent[] inputs = new JComponent[] {
				new JLabel("Code"),
				code,
				new JLabel("Name"),
				name,
				new JLabel("Units"),
				units,
				new JLabel("Price"),
				price
		};
		
		
		while(
			(code.getText()==null || "".equals(code.getText())) ||
			(name.getText()==null || "".equals(name.getText())) ||
			(units.getText()==null || "".equals(units.getText())) ||
			(price.getText()==null || "".equals(price.getText()))  
		){
			JOptionPane.showMessageDialog(null, inputs, "New Stock Details", JOptionPane.PLAIN_MESSAGE);				
		}
		
		StockItem item = new StockItem("NSE", code.getText(), name.getText(), Integer.parseInt(units.getText()), Float.parseFloat(price.getText()), Calendar.getInstance().getTime());
		
		return item;
	}
	

	public static JWhitePanel buildCurrentInvestmentsPanel() {

		JWhitePanel currentInvestmentsPanel = new JWhitePanel(new BorderLayout());	
		
		JWhitePanel currentStocksPanel = new JWhitePanel();
		AppSession.ui.currentStocksPanel = currentStocksPanel;
		currentStocksPanel.setLayout(new WrapLayout(WrapLayout.LEFT, 10,10));

		for(StockItem item: AppSession.profile.getPortFolio().getStocks()){
			StockTile stockTile = TileFactory.getCurrentStockTile(item);
			item.setUi(stockTile);
			currentStocksPanel.add(stockTile);			
		}
		
		JScrollPane scrollableStocksPanel = new JScrollPane(currentStocksPanel);
		scrollableStocksPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		currentInvestmentsPanel.add(scrollableStocksPanel, BorderLayout.CENTER);
		return currentInvestmentsPanel;
	}
	
	public static JScrollPane buildSuggestionsPanel() {
		
		JWhitePanel suggestionsPanel = new JWhitePanel(new GridLayout(10,1,10, 10));
		for(int i=1; i<=10; i++){
			SuggestionItem suggItem = new SuggestionItem();
			SuggestionTile tile  = TileFactory.getSuggestedStockTile(suggItem);
			suggestionsPanel.add(tile);			
		}
		JScrollPane scrollableSuggestionsPanel = new JScrollPane(suggestionsPanel);
		
		return scrollableSuggestionsPanel;
	}
	
	public static JWhitePanel buildChangeRequestsPanel() {
		JWhitePanel changeRequestPanel = new JWhitePanel(new BorderLayout());
		
		JWhitePanel changeSelectionPanel = new JWhitePanel(new FlowLayout(FlowLayout.LEFT)); 
		{
			JWhitePanel changeSelectionListPanel = new JWhitePanel(new FlowLayout(FlowLayout.LEFT));
			{
				changeSelectionListPanel.add(new JLabel("Select a change: "));
				
				String[] savedChangesArray = ChangeRequestUtil.getSavedChanges();				
				JComboBox savedChangesList = new JComboBox(savedChangesArray);
				changeSelectionListPanel.add(savedChangesList);
			}
			changeSelectionPanel.add(changeSelectionListPanel);	
			
			JWhitePanel changerequestCommandsPanel = new JWhitePanel(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton addStockToRequestBtn = new JButton("Add");
				addStockToRequestBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						 
					}
				});
				changerequestCommandsPanel.add(addStockToRequestBtn);

				JButton saveRequestBtn = new JButton("Save");
				changerequestCommandsPanel.add(saveRequestBtn);
				
				JButton reviewRequestBtn = new JButton("Review");
				changerequestCommandsPanel.add(reviewRequestBtn);
				
				JButton submitRequestBtn = new JButton("Submit");
				changerequestCommandsPanel.add(submitRequestBtn);
				
				JButton resetRequestBtn = new JButton("Reset");
				changerequestCommandsPanel.add(resetRequestBtn);
			}
			changeSelectionPanel.add(changerequestCommandsPanel);		
		
		}
		changeRequestPanel.add(changeSelectionPanel, BorderLayout.NORTH);
		
		JWhitePanel requestStocksPanel = new JWhitePanel(new WrapLayout(FlowLayout.LEFT,10,10));
		{
			for(int i=1; i<=10; i++)
			{
				ChangeRequestItem item = new ChangeRequestItem("NSE", "INFY", "Infosys Technologies", 100, 1234.50f, Calendar.getInstance().getTime(), false, "BUY");
				RequestTile tile = TileFactory.getChangeRequestItemTile(item); 
				requestStocksPanel.add(tile);
			}
		}
		changeRequestPanel.add(new JScrollPane(requestStocksPanel), BorderLayout.CENTER);	
		
		return changeRequestPanel;
	}	
	
	private static ChangeRequestItem showNewRequestItemDialog(){		
		
		JComboBox<StockItem> code = new JComboBox<StockItem>(AppSession.profile.getPortFolio().getStocks());
		
		ButtonGroup transType = new ButtonGroup();
		JRadioButton buy = new JRadioButton("Buy"), sell = new JRadioButton("Sell");
		transType.add(sell); transType.add(buy); 
		sell.setSelected(true);
		
		JTextField units = new JTextField();
		JTextField price = new JTextField();
		 
		final JComponent[] inputs = new JComponent[] {
				new JLabel("Code"),
				code,
				sell,
				buy,
				new JLabel("Units"),
				units,
				new JLabel("Price"),
				price
		};
		
		
		while(
			(code.getSelectedItem() == null) ||
			(units.getText()==null || "".equals(units.getText())) ||
			(price.getText()==null || "".equals(price.getText()))  
		){
			JOptionPane.showMessageDialog(null, inputs, "Change Details", JOptionPane.PLAIN_MESSAGE);				
		}
		
		ChangeRequestItem item = new ChangeRequestItem("NSE", "INFY", "Infosys Technologies", 100, 1234.50f, Calendar.getInstance().getTime(), false, "BUY");
		
		return item;
	}

	
}
