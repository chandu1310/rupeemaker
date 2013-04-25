package org.idream.moneymaker.beans;

import java.util.Date;

/*
 * A new stock can only be added.
 * An existing stock can be bought more or sold.
 * 
 * The changing stock details are defined in the parent class.
 */
public class ChangeRequestItem extends StockItem{
  boolean isNew;
  String transaction;
  
  public ChangeRequestItem(String mkt, String stkCode, String stkName, int units, float stkPrice, Date stkDate, boolean isNew, String transaction)  {
	super(mkt, stkCode, stkName, units, stkPrice, stkDate) ;
	this.isNew = isNew;
	this.transaction = transaction;
  }

	public boolean isNew() {
		return isNew;
	}
	
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	public String getTransaction() {
		return transaction;
	}
	
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
}
