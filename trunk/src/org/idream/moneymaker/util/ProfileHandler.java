package org.idream.moneymaker.util;

public final class ProfileHandler {
	private static ProfileHandler classInstance = null; 
	private ProfileHandler(){
		
	}
	
	public static ProfileHandler getInstance(){
		if(classInstance == null){
			classInstance = new ProfileHandler();
		}
		return classInstance;
	}
	
	public boolean userValid() {		
		return true;
	}
}
