package org.idream.moneymaker.beans;

import java.io.Serializable;


public class Profile implements Serializable {
	private String userid;
	private String password;
	private String username;
	private Portfolio portFolio;
	private String fileName;
	
	private ChangeRequest requests;
	
	public Profile(String fileName){
		this.fileName = fileName;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Portfolio getPortFolio() {
		return portFolio;
	}
	public void setPortFolio(Portfolio portFolio) {
		this.portFolio = portFolio;
	}
	public String getFileName() {
		return fileName;
	}
	public ChangeRequest getRequests() {
		return requests;
	}
	public void setRequests(ChangeRequest requests) {
		this.requests = requests;
	}	
}
