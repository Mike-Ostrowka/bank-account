package BankAccount;

import javax.swing.JOptionPane;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;





public class TalkToPHP {
	String url = "https://jcorls01.000webhostapp.com/php/";
	
	 boolean login(int accountNum, String password)
	{	
		String url = "https://jcorls01.000webhostapp.com/php/";
		BAHttpURLConnection http = new BAHttpURLConnection();
	    
		
	            
	            try {
	    			http.sendPost(url+"login.php?", 
	    					   "&accountNum=" + accountNum + 
	    					   "&password=" + password);
	    					   
	    				
	    			if (http.response != null)
	    			{
	    				if (http.response.toString().trim().contains("Connection failed"))
	    				{
	    					// handle 	
	    					JOptionPane.showMessageDialog (null, "Connection error.", "Connection Error", JOptionPane.ERROR_MESSAGE);
	    				
	    					return false;
	    				}
	    				else if (http.response.toString().trim().contains("Login Successful"))
	    				{	
	    					System.out.println("success");
	    					
	    					return true;
	    				}
	    				else if (http.response.toString().trim().contains("Login Failed"))
	    				{
	    					// handle login failed!
	    					JOptionPane.showMessageDialog (null, "Incorrect username/password.", "Please try again.", JOptionPane.ERROR_MESSAGE);
	    					
	    					return false;
	    				}
	    				else
	    				{
	    					// handle php error
	    					System.out.println("Php Error!\n"+http.response.toString());
	    					
	    					return false;
	    				}
	    			}
	    		} 
	    		catch (Exception e) 
	    		{ 
	    			e.printStackTrace();
	    			
	    			JOptionPane.showMessageDialog(null, "Server Unreachable.\nPlease try again after checking your Internet connection.", "Network Error", JOptionPane.ERROR_MESSAGE);
	    			return false;
	    		}
	       
	    		return false;
	        }

	       
	  
		
		
		
		
	 
	 void insertData(int accountNum, double balance, String firstName, String lastName, String password)
	 {
	 	BAHttpURLConnection http = new BAHttpURLConnection();
	 	String url = "https://jcorls01.000webhostapp.com/php/";
	 		
	 	try {
	 		http.sendPost(url+"submit.php?", 
	 				   "accountNum=" + accountNum +
	 				   "&balance=" + balance + 
	 				   "&firstName=" + firstName +
	 				   "&lastName=" + lastName +
	 				   "&password=" + password);
	 		
	 			
	 		if (http.response != null)
	 		{
	 			System.out.println(http.response.toString());
	 		}
	 		else {
	 			JOptionPane.showMessageDialog(null, "error");
	 		}
	 	} 
	 	catch  (Exception e) { e.printStackTrace(); }
	 }
	 
	 
	 
	 static BankAccount parseResult(String result)
	 {	
	  	System.out.println(result);
	 	try 
	 	{
	 		if (result!=null && !result.equals("[]") && !result.equals("") && result.startsWith("{"))
	 		{	
	 			JSONObject obj = new JSONObject(result);
	 			BankAccount check = new BankAccount(obj.getInt("accountNum"), obj.getDouble("balance"), obj.getString("firstName"), obj.getString("lastName"), obj.getString("password"), obj.getString("history"));
	 			return check;
	 			
	 		}
	 	} 
	 	
	 	catch (Exception e) 
	 	{
	 		e.printStackTrace();
	 	}
	 	return null;
	 }
	
	  BankAccount getUserInfo(int accountNum)
	 {
	 	BAHttpURLConnection http = new BAHttpURLConnection();
	 	String url = "https://jcorls01.000webhostapp.com/php/";

	 	try {
	 		
	 			http.sendPost(url+"getUserInfo.php?", 
	 					   "accountNum=" + accountNum);
	 		
	 		
	 		
	 		if (http.response != null)
	 		{
	 			// retrieves entire row
	 			BankAccount check = parseResult(http.response.toString());
	 			return check;
	 		}
	 		else
	 		{
	 			System.out.println("No User Info Retrieved!");
	 			return null;
	 		}
	 	} 
	 	catch (Exception e) { e.printStackTrace(); }
	 	return null;
	 }
	 
	 
	 void updateHistory(BankAccount a) {
		
		 	BAHttpURLConnection http = new BAHttpURLConnection();
		 		
		 	try {
		 		http.sendPost(url+"update.php?", 
		 				   "accountNum=" + a.accountNum +
		 				   "&history=" + a.getHistory());
		 			
		 		if(http.response.toString().trim().contains("success")) {
		 			System.out.println(http.response.toString());
		 		}
		 		else if (http.response.toString().trim().contains("error")) {
		 			System.out.println(http.response.toString());
		 		}
		 		if (http.response != null)
		 		{
		 			System.out.println(http.response.toString());
		 		}
		 	} 
		 	catch (Exception e) { e.printStackTrace(); }
		 
	 }
	 
	 void updatePassword(String password, BankAccount a) {
			BAHttpURLConnection http = new BAHttpURLConnection();
	 		
		 	try {
		 		http.sendPost(url+"updatePassword.php?", 
		 				   "accountNum=" + a.accountNum +
		 				   "&password=" + password );
		 			
		 		if(http.response.toString().trim().contains("success")) {
		 			System.out.println(http.response.toString());
		 		}
		 		else if (http.response.toString().trim().contains("error")) {
		 			System.out.println(http.response.toString());
		 		}
		 		if (http.response != null)
		 		{
		 			System.out.println(http.response.toString());
		 		}
		 	} 
		 	catch (Exception e) { e.printStackTrace(); }
	 }
	 
	 void updateBalance(BankAccount a) {
			BAHttpURLConnection http = new BAHttpURLConnection();
	 		
		 	try {
		 		http.sendPost(url+"updateBalance.php?", 
		 				   "accountNum=" + a.accountNum +
		 				   "&balance=" + a.getBalance() );
		 			
		 		if(http.response.toString().trim().contains("success")) {
		 			System.out.println(http.response.toString());
		 		}
		 		else if (http.response.toString().trim().contains("error")) {
		 			System.out.println(http.response.toString());
		 		}
		 		if (http.response != null)
		 		{
		 			System.out.println(http.response.toString());
		 		}
		 	} 
		 	catch (Exception e) { e.printStackTrace(); }
	 }
	 
	 
	
		       
		
	 
	

}

