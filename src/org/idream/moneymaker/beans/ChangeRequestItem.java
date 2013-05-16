package org.idream.moneymaker.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/*
 * A new stock can only be added.
 * An existing stock can be bought more or sold.
 * 
 * The changing stock details are defined in the parent class.
 */
public class ChangeRequestItem extends StockItem implements Serializable, Cloneable{
  private boolean isNew;
  private String transaction;

  public ChangeRequestItem(String mkt, String stkCode, int units, float stkPrice, String transaction)  {
	super(mkt, stkCode, stkCode, units, stkPrice, Calendar.getInstance().getTime()) ;
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
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return (ChangeRequestItem)super.clone();
	}
}
