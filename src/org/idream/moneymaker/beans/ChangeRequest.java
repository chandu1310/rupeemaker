package org.idream.moneymaker.beans;

import java.io.Serializable;
import java.util.Vector;

public class ChangeRequest implements Serializable, Cloneable{

	private String requestName;
	private Vector<ChangeRequestItem> changesList = new Vector<ChangeRequestItem>();
	
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public Vector<ChangeRequestItem> getChangesList() {
		return changesList;
	}
	public void setChangesList(Vector<ChangeRequestItem> changesList) {
		this.changesList = changesList;
	}
	
	public void addChangeRequestItem(ChangeRequestItem item){
		this.changesList.add(item);
	}
	
	@Override
	public Object clone() {
		ChangeRequest newReq = null;
		try{
		newReq = (ChangeRequest) super.clone();
		Vector<ChangeRequestItem> itemsCopy = new Vector<ChangeRequestItem>();
		for(ChangeRequestItem i: changesList){
			itemsCopy.add((ChangeRequestItem)i.clone());
		}
		newReq.setChangesList(itemsCopy);
		}catch(Exception er){ er.printStackTrace(); }
		return newReq;
	}
}
