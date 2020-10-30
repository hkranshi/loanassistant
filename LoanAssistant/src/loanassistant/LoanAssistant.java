package loanassistant;
import java.awt.*;
import java.text.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.print.PrinterException;
public class LoanAssistant extends JFrame implements ActionListener {
	private JTextField loanBalance;
	private JTextField interestRate;
	private JTextField noPayment;
	private JTextField monthlyPayment;
	Color lightYellow = new Color(255, 255, 128);
	boolean computePayment;
	private JButton monthsButton;
	private JButton paymentButton;
	private JButton computeButton;
	private JButton btnExit;
	private JTextArea textArea;
	private JButton newLoanButton;
	private JButton btnNewButton;
	public LoanAssistant() {
		getContentPane().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
			}
		});
		setResizable(false);
		
		setTitle("Loan Assistant");
		Container c=getContentPane();
				getContentPane().setLayout(null);
				
				newLoanButton = new JButton("New Loan Analysis");
				newLoanButton.setEnabled(false);
				newLoanButton.setBounds(81, 193, 168, 23);
				newLoanButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						newLoanButtonActionPerformed(e);
					}

				});
				
				computeButton = new JButton("Compute Monthly Payment");
				
				computeButton.setBounds(59, 159, 223, 23);
				getContentPane().add(computeButton);
				getContentPane().add(newLoanButton);
				computeButton.addActionListener(new ActionListener()
				{
				public void actionPerformed(ActionEvent e)
				{
				computeButtonActionPerformed(e);
				}
				});
				
		
				JLabel lblLoanBalance = new JLabel("Loan Balance");
				lblLoanBalance.setBounds(10, 5, 87, 23);
				getContentPane().add(lblLoanBalance);
				
				loanBalance = new JTextField();
				loanBalance.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loanBalance.transferFocus();
					}
				});
				loanBalance.setBounds(161, 6, 103, 20);
				getContentPane().add(loanBalance);
				loanBalance.setColumns(10);
				
				JLabel lblInterestRate = new JLabel("Interest Rate");
				lblInterestRate.setBounds(10, 39, 121, 23);
				getContentPane().add(lblInterestRate);
				
				interestRate = new JTextField();
				interestRate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						interestRate.transferFocus();
					}
				});
				interestRate.setBounds(161, 37, 103, 20);
				getContentPane().add(interestRate);
				interestRate.setColumns(10);
				
				JLabel lblNumberOfPayment = new JLabel("Number of Payment");
				lblNumberOfPayment.setBounds(10, 73, 141, 23);
				getContentPane().add(lblNumberOfPayment);
				
				noPayment = new JTextField();
				noPayment.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						noPayment.transferFocus();
					}
				});
				noPayment.setBounds(161, 74, 103, 20);
				getContentPane().add(noPayment);
				noPayment.setColumns(10);
				
				JLabel lblMonthlyPayment = new JLabel("Monthly Payment");
				lblMonthlyPayment.setBounds(10, 112, 103, 24);
				getContentPane().add(lblMonthlyPayment);
				
				monthlyPayment = new JTextField();
				monthlyPayment.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						monthlyPayment.transferFocus();
					}
				});
				monthlyPayment.setBounds(161, 114, 103, 20);
				getContentPane().add(monthlyPayment);
				monthlyPayment.setColumns(10);
				
				paymentButton = new JButton("X");
				paymentButton.setFocusable(false);
				paymentButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						computePayment = false;
						paymentButton.setVisible(false);
						monthsButton.setVisible(true);
						monthlyPayment.setEditable(true);
						monthlyPayment.setBackground(Color.WHITE);
						monthlyPayment.setFocusable(true);
						noPayment.setText("");
						noPayment.setEditable(false);
						noPayment.setBackground(lightYellow);
						noPayment.setFocusable(false);
						computeButton.setText("Compute Number of Payments");
						loanBalance.requestFocus();
					}
				});
				paymentButton.setBounds(294, 73, 52, 23);
				getContentPane().add(paymentButton);
				
				monthsButton = new JButton("X");
				monthsButton.setFocusable(false);
				monthsButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						computePayment = true;
						paymentButton.setVisible(true);
						monthsButton.setVisible(false);
						monthlyPayment.setText("");
						monthlyPayment.setEditable(false);
						monthlyPayment.setBackground(lightYellow);
						monthlyPayment.setFocusable(false);
						noPayment.setEditable(true);
						noPayment.setBackground(Color.WHITE);
						noPayment.setFocusable(true);
						computeButton.setText("Compute Monthly Payment");
						loanBalance.requestFocus();
					}
				});
				monthsButton.setBounds(294, 113, 52, 23);
				getContentPane().add(monthsButton);
				
				JLabel lblLoanAnalysis = new JLabel("Loan Analysis");
				lblLoanAnalysis.setBounds(374, 5, 103, 23);
				getContentPane().add(lblLoanAnalysis);
				
				textArea = new JTextArea();
				textArea.setFocusable(false);
				textArea.setBorder(BorderFactory.createLineBorder(Color.black));
				textArea.setEditable(false);
				textArea.setFont(new Font("Courier New", Font.PLAIN, 14));
				textArea.setBounds(374, 26, 374, 172);
				getContentPane().add(textArea);
				
				JToolBar toolBar = new JToolBar();
				toolBar.setBounds(413, 209, 103, 47);
				getContentPane().add(toolBar);
				
				btnNewButton = new JButton("Print");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						printActionPerformed(e);
					}
				});
				toolBar.add(btnNewButton);
				
				btnExit = new JButton("EXIT");
				toolBar.add(btnExit);
				btnExit.setFocusable(false);
				btnExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			
		});
		
			
		Dimension screenSize =
				Toolkit.getDefaultToolkit().getScreenSize();
				setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height
				- getHeight())), 764, 311);
				setLocationRelativeTo(null);
		setVisible(true);
		
		// TODO Auto-generated constructor stub
	}
	private void printActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
			boolean c=textArea.print();
			if(c){
				JOptionPane.showMessageDialog(null,"Done Printing","Information",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"Printing!","Printer",JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(PrinterException e1){
			JOptionPane.showMessageDialog(null,e1);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				new LoanAssistant();
	}
	private void computeButtonActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		double balance, interest, payment;
		int months;
		double monthlyInterest, multiplier;
		double lBalance,finalPayment;
		if (validateDecimalNumber(loanBalance))
			{
		balance =Double.parseDouble(loanBalance.getText());
	}else{
		JOptionPane.showConfirmDialog(null, "Invalid or empty Loan Balance entry.\nPlease correct.", "Balance Input Error", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		return;}
		if (validateDecimalNumber(interestRate))
		{
		interest =Double.parseDouble(interestRate.getText());
		}
		else{
			JOptionPane.showConfirmDialog(null, "Invalid or empty Interest Rate entry.\nPlease correct.", "Interest Input Error", JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
			return;}
		monthlyInterest = interest / 1200;
		// Compute loan payment
		if(computePayment){
			if (validateDecimalNumber(noPayment))
			{
		months =Integer.parseInt(noPayment.getText());}
			else{
				JOptionPane.showConfirmDialog(null, "Invalid or empty Number of Payments entry.\nPlease correct.", "Number of Payments Input Error",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				return;}
		if(interest==0){
			payment=balance/months;
		}else{
		multiplier = Math.pow(1 + monthlyInterest, months);
		payment = balance * monthlyInterest * multiplier / (multiplier - 1);
		}
		monthlyPayment.setText(new DecimalFormat("0.00").format(payment));
		}//number of payments
		else{
			if (validateDecimalNumber(monthlyPayment))
			{
			payment =
					Double.parseDouble(monthlyPayment.getText());
			if (payment <= (balance * monthlyInterest + 1.0))
			{
			if (JOptionPane.showConfirmDialog(null, "Minimum payment must be $" +
			new DecimalFormat("0.00").format((int)(balance * monthlyInterest + 1.0)) + "\n" + "Do you want to use the minimum payment?", "Input Error", JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
			{
			monthlyPayment.setText(new DecimalFormat("0.00").format((int)
			(balance * monthlyInterest + 1.0)));
			payment =
			Double.valueOf(monthlyPayment.getText()).doubleValue();
			}
			else
			{
			monthlyPayment.requestFocus();
			return;
			}
			}
			}else{
				JOptionPane.showConfirmDialog(null, "Invalid or empty Monthly Payment entry.\nPlease correct.", "Payment Input Error", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				return;}
			if(interest==0){
				months=(int)(balance/payment);
			}else{
					months = (int)((Math.log(payment) - Math.log(payment - balance * monthlyInterest))
					/ Math.log(1 + monthlyInterest));
			}
					noPayment.setText(String.valueOf(months));
		}
		
		payment =
				Double.parseDouble(monthlyPayment.getText());
				// show analysis
				textArea.setText("Loan Balance: $" + new
				DecimalFormat("0.00").format(balance));
				textArea.append("\n" + "Interest Rate: " + new
				DecimalFormat("0.00").format(interest) + "%");
				// process all but last payment
				lBalance = balance;
				for (int paymentNumber = 1; paymentNumber <= months - 1; paymentNumber++)
				{
				lBalance += lBalance * monthlyInterest - payment;
				}
				// find final payment
				finalPayment = lBalance;
				if (finalPayment > payment)
				{
				// apply one more payment
				lBalance += lBalance * monthlyInterest - payment;
				finalPayment = lBalance;
				months++;
				noPayment.setText(String.valueOf(months));
				}
				textArea.append("\n\n" + String.valueOf(months - 1) + " Payments of $" + new
				DecimalFormat("0.00").format(payment));
				textArea.append("\n" + "Final Payment of: $" + new
				DecimalFormat("0.00").format(finalPayment));
				textArea.append("\n" + "Total Payments: $" + new
				DecimalFormat("0.00").format((months - 1) * payment + finalPayment));
				textArea.append("\n" + "Interest Paid $" + new
				DecimalFormat("0.00").format((months - 1) * payment + finalPayment - balance));
				computeButton.setEnabled(false);
				newLoanButton.setEnabled(true);
				newLoanButton.requestFocus();
		
		
	}
	private void newLoanButtonActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (computePayment)
		{
		monthlyPayment.setText("");
		}
		else
		{
		noPayment.setText("");
		}
		textArea.setText("");
		computeButton.setEnabled(true);
		newLoanButton.setEnabled(false);
		loanBalance.requestFocus();
		}
	public boolean validateDecimalNumber(JTextField tf)
	{
	// checks to see if text field contains
	// valid decimal number with only digits and a single decimal point
	String s = tf.getText().trim();
	boolean hasDecimal = false;
	boolean valid = true;
	if (s.length() == 0)
	{
	valid = false;
	}
	else
	{
	for (int i = 0; i < s.length(); i++)
	{
	char c = s.charAt(i);
	if (c >= '0' && c <= '9')
	{
	continue;
	}
	else if (c == '.' && !hasDecimal)
	{
	hasDecimal = true;
	}
	else
	{
	// invalid character found
	valid = false;
	}
	}
	}
	tf.setText(s);
	if (!valid)
	{
	tf.requestFocus();
	}
	return (valid);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
