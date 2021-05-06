package BankAccount;

import java.awt.BorderLayout;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BankAccount {
	public	int accountNum;
	private	double balance;
	private	String firstName;
	private	String middleName;
	private	String lastName;
	private	String password;
	private	String message;
	private String fullName;
	private String history = "";
	
	TalkToPHP php = new TalkToPHP();
	
	BankAccount(double amt, String fName, String mName,String lName, int acctNum) {
		
				
		
		balance = amt;
		firstName = fName;
		middleName = mName;
		lastName = lName;
		password = firstName.substring(0, 3) + lastName.substring(lastName.length() - 2);
		message = " ";
		fullName = firstName.substring(0, firstName.length()) + " " + middleName.substring(0, middleName.length()) + " " + lastName.substring(0, lastName.length());
			
		
	}
	
	BankAccount(int acct, double bal, String fName, String lName, String pswrd, String histry) {
		accountNum = acct;
		balance = bal;
		firstName = fName;
		lastName = lName;
		password = pswrd;
		middleName = " ";
		history = histry;
		fullName = firstName.substring(0, firstName.length()) + " " + lastName.substring(0, lastName.length());
		
		
	}
	
	BankAccount(String fName, String lName, int acctNum) {
		
		accountNum = acctNum;
		balance = 1000;
		firstName = fName;
		middleName = " ";
		lastName = lName;
		password = firstName.substring(0, 3) + lastName.substring(lastName.length() - 2);
		fullName = firstName.substring(0, firstName.length()) + " " + lastName.substring(0, lastName.length());
		message = " ";
	}
	boolean deposit(double amount) {
		if(amount > 0) {
			php.updateBalance(this);
			balance += amount;
			message = "Deposit of amount $" + amount + " made.";
			history += "\nDeposit of amount $" + amount + " made [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]\n";
			
			return true;
			
		}
		else {
			message = "Deposit unsuccssful: invalid amount.";
			history += "\nDeposit unsuccssful: invalid amount [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]"; 
			return false;
		}
	
	}
	
	boolean withdraw(double amount) {
		if(amount <= balance) {
			balance = balance - amount;
			message = "Withdrawal of amount $" + amount + " made.";
			history += "\nWithdrawal of amount $" + amount + " made [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]"; 
			return true;
		}
		else if(amount >=balance){
			balance = balance - 25;
			message = "Amount greater than balance, bank overdraft fee of $25 will be charged to your account.";
			history += "\nBank overdraft fee of $25 charged [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]";
			return false;
			
		}
		else {
			message = "Error, invalid amount";
			history += "\nInvalid amount entered [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]";
			return false;
		}
	}
	
	double getBalance() {
		return balance;
	}
	String getFName() {
		return firstName;
	}
	
	String getLName() {
		return lastName;
	}
	
	
	String getFullName() {
		return fullName;
		
	}
	
	boolean transferTo (double amount, int otherAcct, int acct) {
		BankAccount transfer = php.getUserInfo(otherAcct);
		BankAccount current = php.getUserInfo(acct);
		if(amount >= balance) {
			current.withdraw(25);
			message = ("Value larger than balance. A fee of $25 dollars has been charged to your account");
			history += "\nTransfer failed due to amount: $" + amount + " being larger than balance [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]";
			return false;
		}
		if(amount <= balance) {
			if(current.withdraw(amount)) {
				transfer.deposit(amount);
				php.updateBalance(current);
				php.updateBalance(transfer);
				message = ("Transfer of $" + amount + " complete.");
				history += "\n amount of $" + amount + " has been transfered to the account number " + accountNum + " [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]";
				return true;
			}
			else {
				message = ("Transfer of $" + amount + " incomplete.");
				history += "\nTransfer failed [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]";	
				return false;
			}
			
			
		}
		else {
			message = ("Transfer of $" + amount + " incomplete.");
			history += "\nTransfer failed [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]";	
			return false;
		}
	}
	
	boolean transferFrom (double amount, int otherAcct, String pswd, int acct) {
		BankAccount other = php.getUserInfo(otherAcct);
		BankAccount current = php.getUserInfo(acct);
		
		if(!php.login(otherAcct, pswd)) {
			message = ("Transfer of $" + amount + " from account " + other.accountNum + " unsuccessful. Please check password");
			history += "\nTransfer failed due to incorrect password [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]";
			return false;
		}
		if(other.withdraw(amount)) {
			if(current.deposit(amount)) {
			php.updateBalance(other);
			php.updateBalance(current);
			current.deposit(amount);
			message = ("Transfer of $" + amount + " from account " + other.accountNum + "complete");
			history += "\nTransfer of $" + amount + " deposited [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]";
			return true;
			}
			return false;
		}
		else {
			message = ("Transfer of $" + amount + " from account " + other.accountNum + " unsuccessful due to insufficient funds. A fee of 25 dollars has been charged");
			history += "\nTransfer failed [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]";
			balance -= 25;
			php.updateBalance(current);
			return false;
		}
	}
	
	boolean resetPassword (String currentPassword, String newPassword) {
		
		
		if(this.checkPassword(currentPassword)) {
			if(this.checkCapital(newPassword)) {
			if(this.checkNumber(newPassword)) {
			if(newPassword.length() > 4) {
							password = newPassword;
							message = "Password succesfully changed!";
							history += "\nPassword changed [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]";
							return true;
					}
				
			}
			}
		}
		
		
		message = "Reset unsucessful. Number, capital and length over 4.";
		history += "\nPassword reset failed [" + new SimpleDateFormat("HH:mm").format(new Date()) + "]";
		return false;
		
	}
	
	boolean checkCapital (String newPass) {
		for (int x = 0; x < newPass.length();) {
			char c = newPass.charAt(x);
			x++;
			
			if (c > 64 && c < 91) {
				
					return true;
			
		}
			
	}	
		return false;
		
	}
	
	boolean checkNumber (String newPass) {
		for (int x = 0; x < newPass.length();) {
			char c = newPass.charAt(x);
			x++;
			
				if (c > 47 && c < 58) {
					return true;
			}
		
			
	}
		return false;
		
	}
	 
	void getPassword() {
		message = "HA! If only it were that easy! ECB will be notified of your attempt to hack us, criminal!";
	}
	
	public boolean checkPassword (String pswd) {
		if(pswd.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	String getMessage() {
		return message;
	}
	
	String getHistory() {
		return history;
	}
	
	void emptyAccount() {
		balance = 0;
		message = "\"So much for leaving some for a rainy day\"";
	}
	
	public boolean checkAccountNum (int acctNum) {
		if (acctNum == accountNum) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
		
}
	
	
	
	
