package BankAccount;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JSlider;

import java.awt.FlowLayout;

import javax.swing.JTextPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JScrollBar;

import java.awt.ScrollPane;
import javax.swing.JProgressBar;

public class ATM {
	
	private JFrame frame;
	CardLayout cards = new CardLayout(0, 0);
	ArrayList<BankAccount> accountList = new ArrayList<BankAccount>();
	int numAccounts = 1;
	BankAccount b;
	BankAccount check;
		
	final int BASENUM = 11111;
	Random a = new Random();
	BankAccount b1 = new BankAccount ("Mike", "Ostrowka", 11111);
	
	BankAccount b2 = new BankAccount ("Mike", "Ostrowka", 11110);
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	boolean info = false;
	String newPass = " ";
	private JPasswordField passField;
	private JPasswordField passField1;
	private JTextField textFieldWithdraw;
	private JLabel lblMessageWithdraw;
	private JLabel lblPasswordOutcome;
	private JTextField textFieldDeposit;
	boolean showWithdraw = true;
	boolean showDeposit = true;
	boolean showPassReset = true;
	private JTextField textFieldAmount;
	private JTextField textFieldAcctNum;
	private JTextField textFieldPassword;
	private JTextField textFieldTransferToAmount;
	private JTextField TFAcctNum;
	private JTextArea textAreaHistory;
	private JTextField tfFName;
	private JTextField tfLName;
	Random ran = new Random();
	boolean logIn = false;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM window = new ATM();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ATM()
	{
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	
	
	
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(cards);
		
		TalkToPHP php = new TalkToPHP();
		
		JPanel loginJPanel = new JPanel();
		frame.getContentPane().add(loginJPanel, "LOGIN");
		loginJPanel.setLayout(null);
		
		//php.insertData(b1.accountNum, b1.getBalance() , b1.getFName(), b1.getLName() , b1.getFName().substring(0, 3) + b1.getLName().substring(b1.getLName().length() - 2), b1.getHistory());
		accountList.add(b1);
		JLabel lblMessagePassword = new JLabel(" ");
		lblMessagePassword.setBounds(90, 153, 334, 48);
		loginJPanel.add(lblMessagePassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				
				
					if(php.login(Integer.parseInt(textFieldUsername.getText()), String.valueOf(passwordField.getPassword()))) {
						
						check = php.getUserInfo(Integer.parseInt(textFieldUsername.getText()));
						logIn = true;
						btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						
						
							
						textFieldUsername.setText("");
						passwordField.setText("");
						cards.show(frame.getContentPane(), "MAIN PANEL");
					}
						else {
							lblMessagePassword.setText("ERROR CHECK USERNAME AND PASSWORD");
							btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							textFieldUsername.setText("");
							passwordField.setText("");
							
						}
					
					
				
				
				
			}
		});
		btnLogin.setBounds(100, 212, 89, 23);
		loginJPanel.add(btnLogin);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setToolTipText("Username");
		textFieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUsername.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent arg0) {
				lblMessagePassword.setText(" ");
				textFieldUsername.setText("");
				
			}

			public void focusLost(FocusEvent arg0) {
				
				
			}
			
		});
		textFieldUsername.setBounds(124, 58, 188, 20);
		loginJPanel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(41, 61, 73, 14);
		loginJPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(41, 104, 60, 14);
		loginJPanel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent arg0) {
				lblMessagePassword.setText(" ");
				passwordField.setText("");
			}

			public void focusLost(FocusEvent arg0) {
				
				
			}
			
		});
		passwordField.setBounds(124, 101, 188, 20);
		loginJPanel.add(passwordField);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(frame.getContentPane(), "REGISTER PANEL");
			}
		});
		btnRegister.setBounds(223, 212, 89, 23);
		loginJPanel.add(btnRegister);
		
		
		
		 
		
		JPanel registerJPanel = new JPanel();
		frame.getContentPane().add(registerJPanel, "REGISTER PANEL");
		registerJPanel.setLayout(null);
		
		JLabel lblRegister = new JLabel("Please enter info");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setBounds(158, 11, 123, 20);
		registerJPanel.add(lblRegister);
		
		JButton btnRegisterBack = new JButton("Back");
		btnRegisterBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(frame.getContentPane(), "LOGIN");
			}
		});
		btnRegisterBack.setBounds(10, 10, 89, 23);
		registerJPanel.add(btnRegisterBack);
		
		tfFName = new JTextField();
		tfFName.setBounds(101, 100, 180, 20);
		registerJPanel.add(tfFName);
		tfFName.setColumns(10);
		
		tfLName = new JTextField();
		tfLName.setBounds(101, 165, 180, 20);
		registerJPanel.add(tfLName);
		tfLName.setColumns(10);
		
		JButton btn_Register = new JButton("Register");
		btn_Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfFName.getText().length() > 2 && tfLName.getText().length() > 1) {
					BankAccount add = new BankAccount(tfFName.getText(), tfLName.getText(), findAccountNumber());
					addAccount(add);
					php.insertData(add.accountNum, add.getBalance(), add.getFName(), add.getLName(), add.getFName().substring(0, 3) + add.getLName().substring(add.getLName().length() - 2));
					
					JOptionPane.showMessageDialog(null, "Your account number is: " + accountList.get(accountList.size()-1).accountNum + " and your password is: " + tfFName.getText().substring(0, 3) + tfLName.getText().substring(tfLName.getText().length() - 2));
					tfFName.setText("");
					tfLName.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null, "ERROR");
					tfFName.setText("");
					tfLName.setText("");
				}
				
				
			}
		});
		btn_Register.setBounds(158, 228, 89, 23);
		registerJPanel.add(btn_Register);
		
		JLabel lblFName = new JLabel("First Name");
		lblFName.setBounds(10, 103, 81, 14);
		registerJPanel.add(lblFName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 171, 81, 14);
		registerJPanel.add(lblLastName);
		
		
		
		
		
		
		JPanel mainJPanel = new JPanel();
		frame.getContentPane().add(mainJPanel, "MAIN PANEL");
		mainJPanel.setLayout(null);
		
		JMenuBar Menu = new JMenuBar();
		Menu.setBounds(0, 0, 434, 25);
		mainJPanel.add(Menu);
		
		JMenu mnActions = new JMenu("Actions");
		Menu.add(mnActions);
		
		//Account info
		JMenuItem mntmAccount = new JMenuItem("Display Account Info");
		mntmAccount.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	String num = Integer.toString(check.accountNum);
		    	String bal = Double.toString(check.getBalance());
		    	JOptionPane.showMessageDialog(mainJPanel, "Account number: " + num + "\nFull name: " + check.getFullName() + "\nBalance: $" + bal, "Account Info", 1);
		    	
		    }
		});
		mnActions.add(mntmAccount);
		
		//Logout
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				php.updateHistory(check);
				cards.show(frame.getContentPane(), "LOGIN");
				textFieldUsername.setText("");
				passwordField.setText("");
				lblMessagePassword.setText("");
	    }
		});
		
		JMenuItem mntmPassReset = new JMenuItem("Reset Password");
		mntmPassReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					showPassReset = true;
					cards.show(frame.getContentPane(), "PASSWORDPANEL");
		
			}
		});
		mnActions.add(mntmPassReset);
		
		JMenuItem mntmBalance = new JMenuItem("Balance");
		mntmBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainJPanel, "$" + Double.toString(check.getBalance()), "Balance", JOptionPane.INFORMATION_MESSAGE);
				
			}
			
		});
		mnActions.add(mntmBalance);
		mnActions.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("Exit and Logout");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				php.updateHistory(check);
	            System.exit(1);
	    }
		});
		
		mnActions.add(mntmExit);
		
		JPanel withdrawJPanel = new JPanel();
		frame.getContentPane().add(withdrawJPanel, "WITHDRAW");
		withdrawJPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("What would you like to do?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(128, 36, 188, 14);
		mainJPanel.add(lblNewLabel);
		
		JButton btn_Withdraw = new JButton("Withdraw");
		btn_Withdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if (check.withdraw(Integer.parseInt(textFieldWithdraw.getText()))) {
					php.updateBalance(check);
					lblMessageWithdraw.setForeground(Color.green);
					lblMessageWithdraw.setText(check.getMessage());
					textFieldWithdraw.setText("");
				}
				else {
					php.updateBalance(check);
					lblMessageWithdraw.setForeground(Color.red);
					lblMessageWithdraw.setText(check.getMessage());
					textFieldWithdraw.setText("");	
				}
				
			}
			
		});
		btn_Withdraw.setBounds(175, 228, 89, 23);
		withdrawJPanel.add(btn_Withdraw);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				showWithdraw = true;
				textFieldWithdraw.setText("");
				lblMessageWithdraw.setText("");
				cards.show(frame.getContentPane(), "WITHDRAW");
			}
			
		});
		btnWithdraw.setBounds(40, 65, 110, 23);
		mainJPanel.add(btnWithdraw);
		
				
		
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDeposit = true;
				cards.show(frame.getContentPane(), "DEPOSIT");
			}
		});
		
		btnDeposit.setBounds(40, 150, 110, 23);
		mainJPanel.add(btnDeposit);
		
		JButton btnTransferTo = new JButton("Transfer To");
		btnTransferTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cards.show(frame.getContentPane(), "TRANSFER TO");
			}
		});
		btnTransferTo.setBounds(234, 65, 173, 23);
		mainJPanel.add(btnTransferTo);
		
		JButton btnTransferFrom = new JButton("Transfer From");
		btnTransferFrom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnTransferFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cards.show(frame.getContentPane(), "TRANSFER FROM");
			}
		});
		btnTransferFrom.setBounds(234, 150, 173, 23);
		mainJPanel.add(btnTransferFrom);
		
		JButton btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cards.show(frame.getContentPane(), "HISTORY PANEL");
				
			}
			
		});
		btnHistory.setBounds(40, 107, 110, 23);
		mainJPanel.add(btnHistory);
		
		
		
		JLabel lblWithdraw = DefaultComponentFactory.getInstance().createTitle("Please enter amount to withdraw");
		lblWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWithdraw.setBounds(115, 11, 240, 14);
		withdrawJPanel.add(lblWithdraw);
		
		textFieldWithdraw = new JTextField();
		textFieldWithdraw.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldWithdraw.setBounds(125, 99, 168, 20);
		withdrawJPanel.add(textFieldWithdraw);
		textFieldWithdraw.setColumns(10);
		
		JLabel lblMoney = DefaultComponentFactory.getInstance().createLabel("$");
		lblMoney.setBounds(104, 102, 22, 14);
		withdrawJPanel.add(lblMoney);
		
		
		
	
		
		lblMessageWithdraw = new JLabel("");
		lblMessageWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMessageWithdraw.setBounds(88, 43, 256, 14);
		withdrawJPanel.add(lblMessageWithdraw);
		
		JButton btnBackWithdraw = new JButton("Back");
		btnBackWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cards.show(frame.getContentPane(), "MAIN PANEL");
				lblMessageWithdraw.setText("");
				
			}
			
		});
		btnBackWithdraw.setBounds(16, 8, 89, 23);
		withdrawJPanel.add(btnBackWithdraw);
		
		
		JPanel passwordJPanel = new JPanel();
		frame.getContentPane().add(passwordJPanel, "PASSWORDPANEL");
		
		passwordJPanel.setLayout(null);
				
		JLabel lblInfo = new JLabel("Password Reset");
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInfo.setBounds(178, 5, 113, 29);
		passwordJPanel.add(lblInfo);
		
		passField = new JPasswordField();
		passField.setBounds(126, 82, 234, 20);
		passwordJPanel.add(passField);
		passField.setColumns(10);
		
		
		passField1 = new JPasswordField();
		passField1.setBounds(126, 131, 234, 20);
		passwordJPanel.add(passField1);
		passField1.setColumns(10);
		
		
		JLabel lblOldPass = new JLabel("Old Password");
		lblOldPass.setBounds(10, 85, 106, 14);
		passwordJPanel.add(lblOldPass);
		
		JLabel lblNewPass = new JLabel("New Password");
		lblNewPass.setBounds(10, 134, 106, 14);
		passwordJPanel.add(lblNewPass);
		
		JLabel lblMessagePassReset = new JLabel("");
		lblMessagePassReset.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMessagePassReset.setBounds(20, 45, 380, 14);
		passwordJPanel.add(lblMessagePassReset);
		
		JButton btnPassReset = new JButton("Reset Password");
		if(showPassReset) {
			btnPassReset.setVisible(true);
		}
		btnPassReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (check.resetPassword(String.valueOf(passField.getPassword()), String.valueOf(passField1.getPassword()))) {
						php.updatePassword(String.valueOf(passField1.getPassword()), check);
						passField.setText("");
						passField1.setText("");
						lblMessagePassReset.setForeground(Color.green);
						lblMessagePassReset.setText(check.getMessage());
						
				}
						
						
				else {
					passField.setText("");
					passField1.setText("");
					lblMessagePassReset.setForeground(Color.red);
					lblMessagePassReset.setText(check.getMessage());
					
					
				
				}
				
				
			}
			
		});
		btnPassReset.setBounds(178, 196, 135, 23);
		passwordJPanel.add(btnPassReset);
		
		JButton btnBackPassword = new JButton("Back");
		btnBackPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passField.setText("");
				passField1.setText("");
				lblMessagePassReset.setText("");
				cards.show(frame.getContentPane(), "MAIN PANEL");
				
			}
			
		});
		btnBackPassword.setBounds(10, 11, 106, 23);
		passwordJPanel.add(btnBackPassword);
		
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	if(logIn) {
		    	php.updateHistory(check);
		    	System.exit(1);
		    	}
		    	else {
		    		System.exit(1);
		    	}
		    }
	    }
	);


		
		
		JPanel TransferToJPanel = new JPanel();
		frame.getContentPane().add(TransferToJPanel, "TRANSFER TO");
		TransferToJPanel.setLayout(null);
		
		JLabel lblTransferTo = new JLabel("Transfer To");
		lblTransferTo.setBounds(204, 13, 70, 19);
		TransferToJPanel.add(lblTransferTo);
		
		JButton btnBackTransferTo = new JButton("Back");
		btnBackTransferTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(frame.getContentPane(), "MAIN PANEL");
			}
		});
		btnBackTransferTo.setBounds(5, 11, 89, 23);
		TransferToJPanel.add(btnBackTransferTo);
		
		JLabel lblMessageTransferTo = new JLabel("");
		lblMessageTransferTo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMessageTransferTo.setBounds(34, 44, 390, 19);
		TransferToJPanel.add(lblMessageTransferTo);
		
		textFieldTransferToAmount = new JTextField();
		textFieldTransferToAmount.setBounds(109, 92, 231, 20);
		TransferToJPanel.add(textFieldTransferToAmount);
		textFieldTransferToAmount.setColumns(10);
		
		TFAcctNum = new JTextField();
		TFAcctNum.setBounds(109, 137, 231, 20);
		TransferToJPanel.add(TFAcctNum);
		TFAcctNum.setColumns(10);
		
		JButton btn_TransferTo = new JButton("Transfer");
		btn_TransferTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double amt = Double.parseDouble(textFieldTransferToAmount.getText());
				
					if(check.transferTo(amt, Integer.parseInt(TFAcctNum.getText()), check.accountNum)) {
						lblMessageTransferTo.setForeground(Color.GREEN);
						lblMessageTransferTo.setText(check.getMessage());
						
					}
					else {
						lblMessageTransferTo.setForeground(Color.red);
						lblMessageTransferTo.setText(check.getMessage());
						
					}
				
			}
			
		});
		btn_TransferTo.setBounds(185, 228, 89, 23);
		TransferToJPanel.add(btn_TransferTo);
		
		JLabel lblTransferToAmount = new JLabel("Amount");
		lblTransferToAmount.setBounds(34, 95, 46, 14);
		TransferToJPanel.add(lblTransferToAmount);
		
		JLabel lblTransferToAcctNum = new JLabel("Account Number");
		lblTransferToAcctNum.setBounds(10, 140, 84, 14);
		TransferToJPanel.add(lblTransferToAcctNum);
		
		JPanel TransferFromJPanel = new JPanel();
		frame.getContentPane().add(TransferFromJPanel, "TRANSFER FROM");
		TransferFromJPanel.setLayout(null);
		
		JButton btnBackTransferFrom = new JButton("Back");
		btnBackTransferFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(frame.getContentPane(), "MAIN PANEL");
			}
		});
		btnBackTransferFrom.setBounds(10, 11, 81, 23);
		TransferFromJPanel.add(btnBackTransferFrom);
		
		JLabel lblTransferFrom = new JLabel("Transfer From");
		lblTransferFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTransferFrom.setBounds(156, 10, 209, 23);
		TransferFromJPanel.add(lblTransferFrom);
		
		JLabel lblMessageTransferFrom = new JLabel("");
		lblMessageTransferFrom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMessageTransferFrom.setBounds(27, 58, 362, 23);
		TransferFromJPanel.add(lblMessageTransferFrom);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(111, 92, 181, 20);
		TransferFromJPanel.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		textFieldAcctNum = new JTextField();
		textFieldAcctNum.setBounds(111, 144, 181, 20);
		TransferFromJPanel.add(textFieldAcctNum);
		textFieldAcctNum.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(111, 195, 181, 20);
		TransferFromJPanel.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblTransferFromAmount = new JLabel("Amount");
		lblTransferFromAmount.setBounds(45, 95, 56, 14);
		TransferFromJPanel.add(lblTransferFromAmount);
		
		JLabel lblTransferFromAcct = new JLabel("Account Number");
		lblTransferFromAcct.setBounds(10, 147, 81, 14);
		TransferFromJPanel.add(lblTransferFromAcct);
		
		JLabel lblTransferFromPassword = new JLabel("Password");
		lblTransferFromPassword.setBounds(27, 198, 64, 14);
		TransferFromJPanel.add(lblTransferFromPassword);
		
		JButton btn_TransferFrom = new JButton("Transfer");
		btn_TransferFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double amt = Double.parseDouble(textFieldAmount.getText());
				String pswd = textFieldPassword.getText();
				BankAccount temp = findAccount(Integer.parseInt(textFieldAcctNum.getText()));
				try {
					if(check.transferFrom(amt, Integer.parseInt(textFieldAcctNum.getText()), pswd, check.accountNum)) {
						lblMessageTransferFrom.setForeground(Color.green);
						lblMessageTransferFrom.setText(check.getMessage());
					//TODO
				}
				else {
					lblMessageTransferFrom.setForeground(Color.red);
					lblMessageTransferFrom.setText(check.getMessage());
				}
				}
				catch(NumberFormatException a) {
					lblMessageTransferFrom.setText("ERROR");
				}
			
		}});
		btn_TransferFrom.setBounds(180, 228, 89, 23);
		TransferFromJPanel.add(btn_TransferFrom);
		
		JPanel depositJPanel = new JPanel();
		frame.getContentPane().add(depositJPanel, "DEPOSIT");
		depositJPanel.setLayout(null);
		
		JLabel lblDeposit = DefaultComponentFactory.getInstance().createTitle("Please enter amount to deposit");
		lblDeposit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDeposit.setBounds(142, 11, 204, 14);
		depositJPanel.add(lblDeposit);
		
	
		
		JLabel lblMessageDeposit = new JLabel("");
		lblMessageDeposit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMessageDeposit.setBounds(31, 43, 355, 14);
		depositJPanel.add(lblMessageDeposit);
		
		JLabel lblMoneyDeposit = new JLabel("$");
		lblMoneyDeposit.setBounds(104, 102, 22, 14);
		depositJPanel.add(lblMoneyDeposit);
		
		textFieldDeposit = new JTextField();
		textFieldDeposit.setBounds(125, 99, 168, 20);
		depositJPanel.add(textFieldDeposit);
		textFieldDeposit.setColumns(10);
		
		JButton btn_Deposit = new JButton("Deposit");
		btn_Deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
			textFieldDeposit.setText("");
			if (check.deposit(Double.parseDouble(textFieldDeposit.getText()))) {
				
				lblMessageDeposit.setForeground(Color.green);
				lblMessageDeposit.setText(check.getMessage());
			}
			else {
				
				lblMessageDeposit.setForeground(Color.red);
				lblMessageDeposit.setText(check.getMessage());
			}
			
		}
		
	});
				
	
		btn_Deposit.setBounds(175, 228, 89, 23);
		depositJPanel.add(btn_Deposit);	
		
		JButton btnBackDeposit = new JButton("Back");
		btnBackDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblMessageDeposit.setText("");
				textFieldDeposit.setText("");
				cards.show(frame.getContentPane(), "MAIN PANEL");
				
				
			}
			
		});
		btnBackDeposit.setBounds(21, 8, 89, 23);
		depositJPanel.add(btnBackDeposit);
		
		JPanel historyJPanel = new JPanel();
		frame.getContentPane().add(historyJPanel, "HISTORY PANEL");
		
		JButton btn_History = new JButton("Update History");
		btn_History.setBounds(154, 1, 123, 23);
		btn_History.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaHistory.setText(check.getHistory());
			}
		});
		
		JButton btnBackHistory = new JButton("Back");
		btnBackHistory.setBounds(0, 1, 89, 23);
		btnBackHistory.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				cards.show(frame.getContentPane(), "MAIN PANEL");
			}
		});
		historyJPanel.setLayout(null);
		historyJPanel.add(btn_History);
		historyJPanel.add(btnBackHistory);
		
		textAreaHistory = new JTextArea();
		textAreaHistory.setBounds(10, 35, 414, 216);
		textAreaHistory.setColumns(10);
		historyJPanel.add(textAreaHistory);
		
		JScrollPane scrollPane = new JScrollPane(textAreaHistory);
		scrollPane.setBounds(10, 32, 414, 219);
		historyJPanel.add(scrollPane);
		
		
		
		
		

	}
	
	

	
	
	BankAccount findAccount (int accNumb) {
		
		if(accNumb > 0) {
		
			
				return b;
		}
		else {
			return null;
		}
		
	}
	
	int addAccount(BankAccount b) {
		accountList.add(b);
		numAccounts++;
		return numAccounts;
	}
	int findAccountNumber() {
		int newNum = (accountList.get(accountList.size()-1).accountNum) + ran.nextInt(10) + 2;
		return newNum;
		
	}
	
	int updateBar (int newValue) {
	    return newValue;
	  }
}
