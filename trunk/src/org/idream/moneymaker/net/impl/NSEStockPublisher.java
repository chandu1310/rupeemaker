package org.idream.moneymaker.net.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

import org.idream.moneymaker.net.StockPublisher;
import org.idream.moneymaker.net.beans.PublishedStockDetails;

public final class NSEStockPublisher implements StockPublisher{	
	
	@Override
	public synchronized PublishedStockDetails getStockDetails(String stockCode) {
		PublishedStockDetails stockDetails = new PublishedStockDetails();
		stockDetails.setMarket("NSE");
		stockDetails.setStockCode(stockCode);

		try{
	        String urlString = "http://www.nseindia.com/marketinfo/companyTracker/ajaxquote.jsp?symbol="+stockCode;
	        //String urlString = "http://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/ajaxGetQuoteJSON.jsp?symbol=RELIANCE&series=EQ";

			URL url = new URL(urlString);
		    URLConnection con = url.openConnection();
		    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    StringBuilder responseString = new StringBuilder();
		    String inputLine;
		    while ((inputLine = in.readLine()) != null){
		        responseString.append(inputLine);
		    }
		    parseResponseString(responseString, stockDetails);
		    in.close();		    
		}catch(Exception er){
			er.printStackTrace();
		}		
		return stockDetails;
	}
	
	private synchronized void parseResponseString(StringBuilder responseString, PublishedStockDetails stockDetails){
		String str = responseString.toString();
		if(str.startsWith("SUCCESS")){
			str = str.substring(str.indexOf(";")+2);
			System.out.println("PARSING RESPONSE STRING: \n "+str);
			StringTokenizer strTok = new StringTokenizer(str, ":");
			int i=0;
			while(strTok.hasMoreTokens()){
				String token = strTok.nextToken();
//				System.out.println(i+" "+token);
				switch (i) {
				case 6:					
					stockDetails.setHigh52Price(Float.parseFloat(token));
					break;
				case 7:					
					stockDetails.setLow52Price(Float.parseFloat(token));
					break;
				case 8:					
					stockDetails.setPreviousClosePrice(Float.parseFloat(token));
					break;
				case 9:					
					stockDetails.setOpenedPrice(Float.parseFloat(token));
					break;
				case 10:	
					stockDetails.setHighPriceToday(Float.parseFloat(token));
					break;
				case 11:					
					stockDetails.setLowPriceToday(Float.parseFloat(token));
					break;
				case 12:					
					stockDetails.setVolumeWeightedAveragePrice(Float.parseFloat(token));
					break;
				case 13:
					stockDetails.setCurrentPrice(Float.parseFloat(token));
					break;
				case 14:					
					stockDetails.setChangeFromPrevClose(Float.parseFloat(token));
					break;
				case 15:					
					stockDetails.setPercentChangeFromPrevClose(Float.parseFloat(token));
					break;
				case 16:	
					token = token.replaceAll(",", "");
					stockDetails.setTradedVolume(Long.parseLong(token));
					break;
				case 17:					
					stockDetails.setTradedValue(Double.parseDouble(token));
					break;
				case 19:
					if(!"-".equals(token))
						stockDetails.setClosePrice(Float.parseFloat(token));
					break;						
				case 20:				
					if(!"-".equals(token))
						stockDetails.setClosePrice(Float.parseFloat(token));
					break;
				case 41:
					token = token.replaceAll(";", ":");
					stockDetails.setStockDate(token);
					break;					
				}
				i++;
			}
		}
	}
	
	public static void main(String[] args) {
		NSEStockPublisher pub = new NSEStockPublisher();
		PublishedStockDetails stkDetails =  pub.getStockDetails("INFY");
		System.out.println(stkDetails);
		PublishedStockDetails stkDetails1 =  pub.getStockDetails("TATAMOTORS");
		System.out.println(stkDetails1);
	}
}
