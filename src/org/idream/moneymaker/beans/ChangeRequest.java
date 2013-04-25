package org.idream.moneymaker.beans;

import java.io.Serializable;
import java.util.Vector;

public class ChangeRequest implements Serializable{
	public static final String BUY = "B";
	public static final String SELL = "S";
	
	private String name;
	private Vector<ChangeRequestItem> changesList = new Vector<ChangeRequestItem>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vector<ChangeRequestItem> getChangesList() {
		return changesList;
	}
	public void setChangesList(Vector<ChangeRequestItem> changesList) {
		this.changesList = changesList;
	}	
}
