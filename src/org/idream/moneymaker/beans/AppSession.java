package org.idream.moneymaker.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;
import java.util.concurrent.ScheduledExecutorService;

import org.idream.moneymaker.net.StockPublisher;
import org.idream.moneymaker.net.StockPublisherFactory;
import org.idream.moneymaker.ui.UIHookPoints;
import org.idream.moneymaker.util.ChangeRequestUtil;

public class AppSession {
	public static Profile profile;
	
	public static UIHookPoints ui = new UIHookPoints();
	
	public static StockPublisher publisher = StockPublisherFactory.getPublisher();
	
	public static Vector<ScheduledExecutorService > execServices = new Vector<ScheduledExecutorService>();
	
	public static boolean saveState(){
		try{
			File fo = new File(profile.getFileName());
			if(!fo.exists())
				fo.createNewFile();
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fo));
			oos.writeObject(profile);
			oos.close();
			return true;
		}
		catch(Exception er){
			er.printStackTrace();
		}
		return false;
	}
	
	public static boolean readState(String fileName){
		try{
			File fi = new File(fileName);
			if(!fi.exists())
				fi.createNewFile();
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fi));
			Object o = ois.readObject();
			if(o != null && o instanceof Profile){
				profile = (Profile)o;
			}
			ois.close();	
			ChangeRequestUtil.requests = profile.getRequests();
			return true;
		}
		catch(Exception er){
			er.printStackTrace();
		}
		return false;
	}
	
	public static synchronized void shutdownTicker(){
		for(ScheduledExecutorService serv : execServices){
			serv.shutdownNow();
		}
	}
}
