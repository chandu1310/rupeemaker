package org.idream.moneymaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.idream.moneymaker.beans.AppSession;
import org.idream.moneymaker.beans.Portfolio;
import org.idream.moneymaker.beans.Profile;
import org.idream.moneymaker.beans.StockItem;
import org.idream.moneymaker.ui.JWhitePanel;
import org.idream.moneymaker.ui.UIBuilder;
import org.idream.moneymaker.util.ProfileHandler;

public class MoneyMaker {
	JFrame appWindow;
	ProfileHandler profileHandler;

	public static void main(String[] args) {
		MoneyMaker app = new MoneyMaker();
		app.initialize();
//		app.loginScreen();
		app.appScreen();
	}
	
	private void initialize() {
		profileHandler = ProfileHandler.getInstance();
		appWindow = new JFrame("Moneymaker 2013 - idream");
		appWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				int i = JOptionPane.showConfirmDialog(appWindow,
						"Exit Application?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					AppSession.saveState();
					AppSession.shutdownTicker();
					System.exit(0);
				}
			}
		});
		appWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		appWindow.setVisible(true);
	}

	private void loginScreen() {
		Container co = appWindow.getContentPane();
		co.removeAll();
		co.setBackground(Color.WHITE);
		co.setLayout(new GridLayout(3, 2));

		JLabel l1 = new JLabel("Username");
		JTextField t1 = new JTextField();

		co.add(l1);
		co.add(t1);

		JLabel l2 = new JLabel("Password");
		JTextField t2 = new JTextField();

		co.add(l2);
		co.add(t2);

		JButton loginBtn = new JButton("Login"), cancel = new JButton("Cancel");

		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (profileHandler.userValid()) {
					appScreen();
				}
			}
		});

		co.add(loginBtn);
		co.add(cancel);
	
		appWindow.setPreferredSize(new Dimension(300, 150));
		appWindow.setLocation(100, 100);
		appWindow.pack();
	}

	private void appScreen() 
	{
		initializeAppData();
//		initializeTestAppData();
		Container co = appWindow.getContentPane();
		co.removeAll();
		co.setLayout(new GridLayout(1, 1));
		JWhitePanel mainPanel = new JWhitePanel(new BorderLayout());
		{

			JWhitePanel contentPanel = UIBuilder.buildContentPanel();
			mainPanel.add(contentPanel, BorderLayout.CENTER);
			
			JLabel statusBar = new JLabel("Application Status.");
			mainPanel.add(statusBar, BorderLayout.SOUTH);
		}
		co.add(mainPanel);

		appWindow.setSize(800, 600);
		// appWindow.setResizable(false);
		co.validate();
		co.repaint();
	}


	private void initializeAppData()
	{
		Profile prof;
		if(!AppSession.readState("apptest.mm"))
		{
			System.out.println("FAILED TO LOAD APP DATA");
		}
	}

	private void initializeTestAppData()
	{
		/*
		 * Strategy is to load profile first and have a method in profile to load portfolio.
		 * Do not populate profile and portfolio separately.
		 */
		Portfolio pf = new Portfolio();
		{
			pf.setCapitalAmount(200000.00f);
		
			StockItem i1 = new StockItem("NSE", "INFY", "Infosys Limited", 500, 2852.80f, Calendar.getInstance().getTime());
			StockItem i2 = new StockItem("NSE", "SATYAMCOMP", "Satyam Computer Services Limited", 500, 127.40f, Calendar.getInstance().getTime());
			StockItem i3 = new StockItem("NSE", "BHARTIARTL", "Bharti Airtel Limited", 1000, 297.50f, Calendar.getInstance().getTime());
			StockItem i4 = new StockItem("NSE", "TATAMOTORS", "Tata Motors Limited", 700, 274.95f, Calendar.getInstance().getTime());
			StockItem i5 = new StockItem("NSE", "HDFC", "Housing Development Finance Corporation Limited", 200, 818.25f, Calendar.getInstance().getTime());
			
			Vector<StockItem> stocks = new Vector<StockItem>();
			stocks.add(i1); stocks.add(i2);stocks.add(i3);stocks.add(i4);stocks.add(i5);
			pf.setStocks(stocks);
		}		
		
		Profile prof = new Profile("apptest.mm");
		{
			prof.setUserid("chandu"); 
			prof.setUsername("Chandrashekar Chennamsetty"); 
			prof.setPassword("password"); 
			prof.setPortFolio(pf);
		}
		AppSession.profile = prof;
	}
}
