package org.idream.moneymaker.util;

import java.util.Vector;

import org.idream.moneymaker.beans.AppSession;
import org.idream.moneymaker.beans.ChangeRequest;
import org.idream.moneymaker.beans.ChangeRequestItem;

public class ChangeRequestUtil {
	public static Vector<ChangeRequest> requests;
	
	public static void addChangeRequest(ChangeRequest chngReq){
		requests.add(chngReq);
	}
	
	public static void saveDefaultAsNew(String newChangeRequestName){
		String currentSelection = (String)AppSession.ui.savedChangesList.getSelectedItem();
		ChangeRequest req = (ChangeRequest)getChangeRequest(currentSelection).clone();
		req.setRequestName(newChangeRequestName);
		requests.add(req);
	}
	
	public static ChangeRequest getChangeRequest(String requestName){
		ChangeRequest r = null;
		for(ChangeRequest c: requests){
			if(requestName.equals(c.getRequestName())){
				r = c;
				break;
			}
		}
		return r;
	}
	
	public static void removeChangeRequest(String requestName){
		for(ChangeRequest c: requests){
			if(requestName.equals(c.getRequestName())){
				requests.removeElement(c);
			}
		}
	}
	
	public static void resetCurrentSelectedRequest(){
		String currentSelection = (String)AppSession.ui.savedChangesList.getSelectedItem();
		ChangeRequest req = (ChangeRequest)getChangeRequest(currentSelection);
		req.setChangesList(new Vector<ChangeRequestItem>());
	}
	
	public static String[] getChangeRequestNames(){
		String[] names = new String[requests.size()];
		int i=0;
		for(ChangeRequest c: requests){
			names[i] = c.getRequestName();
			i++;
		}
		return names;
	}	
}
