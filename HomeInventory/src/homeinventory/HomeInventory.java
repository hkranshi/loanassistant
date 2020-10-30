package homeinventory;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.*;
import java.text.*;
import java.awt.geom.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;

import javax.swing.*;

import java.util.*;
import java.text.*;

import com.toedter.calendar.JDateChooser;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
public class HomeInventory extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 JToolBar toolBar=new JToolBar();
	 JButton btnNew= new JButton("New");
	 JButton btnDelete= new JButton("Delete");
	JButton btnSave = new JButton("Save");
  JButton btnPrevious= new JButton("Previous");;
	 JButton btnNext = new JButton("Next");;
	JButton btnPrint= new JButton("Print");;
	 JButton btnExit= new JButton("Exit");;
	 JTextField inventoryitemtextField;
	 JTextField serialnotextfield;
	 JTextField textfieldpurchaseprice;
	 JTextField websitetextField;
 JTextField noteTextfield;	
	JPanel searchpanel;
	
	 GridBagConstraints gridConstraints;
	 JComboBox comboBoxlocation;
	 JCheckBox chckbxMarked;
	 JDateChooser dateChooser;
 static JTextArea phototextArea= new JTextArea();;
	 JButton photoButton;
	public PhotoPanel photoPanel ;
	private JLabel lblDateOfPurchase;
	InventoryItem myItem;
static	final int maximumEntries = 300;
static	int numberEntries;
	int currentEntry;
	static final int entriesPerPage = 2;
	static int lastPage;
	static InventoryItem[] myInventory = new InventoryItem[maximumEntries];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HomeInventory().show();
				
	}
	public HomeInventory() {
		getContentPane().setForeground(Color.BLUE);
		setBackground(Color.BLUE);
		setForeground(Color.BLUE);
		
		setTitle("Home Inventory Manager");
		setResizable(false);
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
		public void windowClosing(WindowEvent evt)
		{
			exitForm(evt);
			}
			});
		getContentPane().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
			}
		});
		Container c=getContentPane();
		getContentPane().setLayout(null);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBackground(Color.BLUE);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setBounds(0, 11, 62, 361);
		getContentPane().add(toolBar);
		Dimension bSize = new Dimension(100, 50);
		
		 btnNew.setFocusable(false);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewActionPerformed(e);
			}
		});
		btnNew.setIcon(new ImageIcon(HomeInventory.class.getResource("/images/rsz_new.png")));
		sizeButton(btnNew, bSize);
		btnNew.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNew.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNew.setToolTipText("Add New Item");
		toolBar.add(btnNew);
		 btnDelete.setFocusable(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteActionPerformed(e);
			}
		});
		btnDelete.setIcon(new ImageIcon(HomeInventory.class.getResource("/images/delete.png")));
		sizeButton(btnDelete, bSize);
		btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDelete.setToolTipText("Delete Current Item");
		btnDelete.setVerticalAlignment(SwingConstants.BOTTOM);
		toolBar.add(btnDelete);
		
		
		btnSave.setFocusable(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveActionPerformed(e);
			}
		});
		btnSave.setIcon(new ImageIcon(HomeInventory.class.getResource("/images/rsz_save_2.png")));
		sizeButton(btnSave, bSize);
		btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);

		btnSave.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSave.setToolTipText("Save Current Item");
		toolBar.add(btnSave);
		
		
		btnPrevious.setFocusable(false);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPreviousActionPerformed(e);
			}

		
		});
		btnPrevious.setToolTipText("Display Previous Item");
		btnPrevious.setIcon(new ImageIcon(HomeInventory.class.getResource("/images/rsz_previous.png")));
		sizeButton(btnPrevious, bSize);
		btnPrevious.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPrevious.setVerticalTextPosition(SwingConstants.BOTTOM);

		toolBar.add(btnPrevious);
		
		
		btnNext.setFocusable(false);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNextActionPerformed(e);
			}

					});
		btnNext.setIcon(new ImageIcon(HomeInventory.class.getResource("/images/rsz_next.png")));
		sizeButton(btnNext, bSize);
		btnNext.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNext.setVerticalTextPosition(SwingConstants.BOTTOM);

		btnNext.setToolTipText("Display Next Item");
		toolBar.add(btnNext);
		
		
		btnPrint.setFocusable(false);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPrintActionPerformed(e);
			}
		});
		btnPrint.setToolTipText("Print inventory Item");
		btnPrint.setIcon(new ImageIcon(HomeInventory.class.getResource("/images/rsz_print_2.png")));
		sizeButton(btnPrint, bSize);
		btnPrint.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPrint.setVerticalTextPosition(SwingConstants.BOTTOM);
		toolBar.add(btnPrint);
		btnExit.setFocusable(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitForm(null);
			}
		});
		btnExit.setToolTipText("Exit Program");
		sizeButton(btnExit, bSize);

		toolBar.add(btnExit);
		
		JLabel lblInventoryItem = new JLabel("Inventory Item");
		lblInventoryItem.setBounds(93, 11, 101, 22);
		getContentPane().add(lblInventoryItem);
		
		inventoryitemtextField = new JTextField();
		inventoryitemtextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemActionPerformed(e);
			}

			
		});
		inventoryitemtextField.setBounds(204, 12, 598, 20);
		getContentPane().add(inventoryitemtextField);
		inventoryitemtextField.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(93, 44, 73, 14);
		getContentPane().add(lblLocation);
		
		comboBoxlocation = new JComboBox();
		comboBoxlocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			locationActionPerformed(e);
			}	
		});
		comboBoxlocation.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxlocation.setEditable(true);
		comboBoxlocation.setBackground(Color.WHITE);
		comboBoxlocation.setBounds(204, 41, 378, 20);
		getContentPane().add(comboBoxlocation);
		
		chckbxMarked = new JCheckBox("Marked?");
		chckbxMarked.setFocusable(false);
		chckbxMarked.setBounds(604, 40, 118, 23);
		getContentPane().add(chckbxMarked);
		
		JLabel lblSerialNumber = new JLabel("Serial Number");
		lblSerialNumber.setBounds(93, 69, 90, 24);
		getContentPane().add(lblSerialNumber);
		
		serialnotextfield = new JTextField();
		serialnotextfield.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serialActionPerformed(e);
			}

			
		});
		serialnotextfield.setBounds(204, 71, 378, 20);
		getContentPane().add(serialnotextfield);
		serialnotextfield.setColumns(10);
		
		JLabel lblPurchase = new JLabel("Purchase Price");
		lblPurchase.setBounds(93, 101, 83, 22);
		getContentPane().add(lblPurchase);
		
		textfieldpurchaseprice = new JTextField();
		textfieldpurchaseprice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				purchaseActionPerformed(e);
			}

					});
		textfieldpurchaseprice.setBounds(204, 102, 200, 20);
		getContentPane().add(textfieldpurchaseprice);
		textfieldpurchaseprice.setColumns(10);
		
		lblDateOfPurchase = new JLabel("Date of Purchase");
		lblDateOfPurchase.setBounds(464, 102, 118, 21);
		getContentPane().add(lblDateOfPurchase);
		
		dateChooser = new JDateChooser();
		dateChooser.setPreferredSize(new Dimension(120, 25));
		dateChooser.addPropertyChangeListener(new PropertyChangeListener()
		{
		//@Override
		public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub
			dateChooserPropertyChange(evt);
	}
		});
		dateChooser.setBounds(577, 101, 111, 22);
				getContentPane().add(dateChooser);
		
		JLabel website = new JLabel("Store/Website");
		website.setBounds(93, 145, 90, 14);
		getContentPane().add(website);
		
		websitetextField = new JTextField();
		websitetextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				websiteActionPerformed(e);
			}

			
		});
		websitetextField.setBounds(204, 142, 598, 20);
		getContentPane().add(websitetextField);
		websitetextField.setColumns(10);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setBounds(93, 176, 73, 14);
		getContentPane().add(lblNote);
		
		noteTextfield = new JTextField();
		noteTextfield.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noteActionPerformed(e);
			}

					});
		noteTextfield.setBounds(204, 173, 598, 20);
		getContentPane().add(noteTextfield);
		noteTextfield.setColumns(10);
		
		JLabel lblPhoto = new JLabel("Photo");
		lblPhoto.setBounds(93, 208, 62, 14);
		getContentPane().add(lblPhoto);
		
		photoButton = new JButton("...");
		photoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				photoButtonActionPerformed(e);
			}
		});
		photoButton.setBounds(764, 204, 38, 23);
		getContentPane().add(photoButton);
		
		
		phototextArea.setFocusable(false);
		phototextArea.setFont(new Font("Arial", Font.PLAIN, 12));
		phototextArea.setLineWrap(true);
		phototextArea.setEditable(false);
		phototextArea.setWrapStyleWord(true);
		phototextArea.setBackground(new Color(255,255,192));
		phototextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		phototextArea.setBounds(204, 203, 550, 22);
		getContentPane().add(phototextArea);
		
		searchpanel = new JPanel();
		searchpanel.setBounds(93, 262, 358, 306);
		searchpanel.setFocusable(false);
		searchpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Item Search", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(searchpanel);
	
		
		JButton[] searchButton = new JButton[26];
		int x = 0, y = 0;
		// create and position 26 buttons
		for (int i = 0; i < 26; i++)
		{
		// create new button
		searchButton[i] = new JButton();
		// set text property
		searchButton[i].setText(String.valueOf((char) (65 + i)));
		searchButton[i].setFont(new Font("Arial", Font.BOLD, 12));
		searchButton[i].setMargin(new Insets(-8, -8, -8, -8));
		sizeButton(searchButton[i], new Dimension(10,15));
		searchButton[i].setBackground(Color.YELLOW);
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = x;
		gridConstraints.gridy = y;
		searchpanel.add(searchButton[i], gridConstraints);
		searchpanel.add(searchButton[i]);
		// add method
		searchButton[i].addActionListener(new ActionListener ()
		{
		public void actionPerformed(ActionEvent e)
		{
		searchButtonActionPerformed(e);
		}
		});
		x++;
		// six buttons per row
		if (x % 6 == 0)
		{
		x = 0;
		y++;
		}
		}
		
	
			photoPanel=new PhotoPanel();
			photoPanel.setFocusable(false);
			photoPanel.setPreferredSize(new Dimension(500, 200));
			photoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			photoPanel.setBounds(487, 273, 350, 270);
			getContentPane().add(photoPanel);
			int n;
			// open file for entries try
		
			try{
				FileReader f=new FileReader("inventory.txt");
			BufferedReader inputFile = new BufferedReader(f);
			numberEntries =	Integer.parseInt(inputFile.readLine());
			//numberEntries=inputFile.read();
			if (numberEntries != 0)
			{
			for (int i = 0; i < numberEntries; i++)
			{
			myInventory[i] = new InventoryItem();
			myInventory[i].description = inputFile.readLine();
			myInventory[i].location = inputFile.readLine();
			myInventory[i].serialNumber = inputFile.readLine();
			myInventory[i].marked =	Boolean.valueOf(inputFile.readLine()).booleanValue();
			myInventory[i].purchasePrice = inputFile.readLine();
			myInventory[i].purchaseDate = inputFile.readLine();
			myInventory[i].purchaseLocation = inputFile.readLine();
			myInventory[i].note = inputFile.readLine();
			myInventory[i].photoFile = inputFile.readLine();
			}
			}
			n=Integer.valueOf(inputFile.readLine()).intValue();
			if(n!=0){
			
			for (int i = 0; i < n; i++)
			{
				comboBoxlocation.addItem(inputFile.readLine());
			}
			}
			inputFile.close();
			currentEntry = 1;
			showEntry(currentEntry);
			}
		
			catch(Exception ex)
			{
			numberEntries = 0;
			currentEntry=0;
			}
			if (numberEntries == 0)
			{
			btnNew.setEnabled(false);
			btnDelete.setEnabled(false);
			btnNext.setEnabled(false);
			btnPrevious.setEnabled(false);
			btnPrint.setEnabled(false);
			}
			
			
		Dimension screenSize =
				Toolkit.getDefaultToolkit().getScreenSize();
				setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height
				- getHeight())), 986, 784);
				setLocationRelativeTo(null);
		setVisible(true);
	}
	private void blankValues()
	{
	// blank input screen
		btnNew.setEnabled(false);
	btnDelete.setEnabled(false);
	btnSave.setEnabled(true);
	btnPrevious.setEnabled(false);
	btnNext.setEnabled(false);
	btnPrevious.setEnabled(false);
	inventoryitemtextField.setText("");
	comboBoxlocation.setSelectedItem("");
	chckbxMarked.setSelected(false);
	serialnotextfield.setText("");
	textfieldpurchaseprice.setText("");
	dateChooser.setDate(new Date());
	websitetextField.setText("");
	noteTextfield.setText("");
	phototextArea.setText("");
	photoPanel.repaint();
	inventoryitemtextField.requestFocus();
	}
	private void photoButtonActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser openChooser = new JFileChooser();
		openChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		openChooser.setDialogTitle("Open Photo File");
		openChooser.addChoosableFileFilter(new FileNameExtensionFilter("Photo Files","jpg"));
		if (openChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		showPhoto(openChooser.getSelectedFile().toString());
		
	}
	private void btnPrintActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			lastPage = (int) (1 + (numberEntries - 1) / entriesPerPage);
			PrinterJob inventoryPrinterJob = PrinterJob.getPrinterJob();
			inventoryPrinterJob.setPrintable(new InventoryDocument());
			if (inventoryPrinterJob.printDialog())
			{
			try
			{
			inventoryPrinterJob.print();
			}
			catch (PrinterException ex)
			{
			JOptionPane.showConfirmDialog(null, ex.getMessage(), "Print Error",
			JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
			}
		
	}
	private void btnSaveActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (JOptionPane.showConfirmDialog(null, "Are you sure you want to save this item?",
				"Save Inventory Item", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
		inventoryitemtextField.setText(inventoryitemtextField.getText().trim());
		if (inventoryitemtextField.getText().equals(""))
		{
		JOptionPane.showConfirmDialog(null, "Must have item description.", "Error",
		JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
		inventoryitemtextField.requestFocus();
		return;
		}
		if (btnNew.isEnabled())
		{
		// delete edit entry then resave
		deleteEntry(currentEntry);
		}
		// capitalize first letter
		String s = inventoryitemtextField.getText();
		inventoryitemtextField.setText(s.substring(0, 1).toUpperCase() + s.substring(1));
		numberEntries++;
		// determine new current entry location based on description
		currentEntry = 1;
		if (numberEntries != 1)
		{
		do
		{
		if
		(inventoryitemtextField.getText().compareTo(myInventory[currentEntry - 1].description) < 0)
		break;
		currentEntry++;
		}
		while (currentEntry < numberEntries);
		}
		// move all entries below new value down one position unless at end
		if (currentEntry != numberEntries)
		{
		for (int i = numberEntries; i >= currentEntry + 1; i--)
		{
		myInventory[i - 1] = myInventory[i - 2];
		myInventory[i - 2] = new InventoryItem();
		}
		}
		myInventory[currentEntry - 1] = new InventoryItem();
		myInventory[currentEntry - 1].description = inventoryitemtextField.getText();
		myInventory[currentEntry - 1].location =
		comboBoxlocation.getSelectedItem().toString();
		myInventory[currentEntry - 1].marked = chckbxMarked.isSelected();
		myInventory[currentEntry - 1].serialNumber = serialnotextfield.getText();
		myInventory[currentEntry - 1].purchasePrice = textfieldpurchaseprice.getText();
		myInventory[currentEntry - 1].purchaseDate =
		dateToString(dateChooser.getDate());
		myInventory[currentEntry - 1].purchaseLocation = websitetextField.getText();
		myInventory[currentEntry - 1].photoFile = phototextArea.getText();
		myInventory[currentEntry - 1].note = noteTextfield.getText();
		showEntry(currentEntry);
		if (numberEntries < maximumEntries)
		btnNew.setEnabled(true);
		else
		btnNew.setEnabled(false);
		btnDelete.setEnabled(true);
		btnPrint.setEnabled(true);	
	}
	private void btnNewActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		checkSave();
		blankValues();
		
	}	
	private void btnDeleteActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?",
				"Delete Inventory Item", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
				return;
				deleteEntry(currentEntry);
				if (numberEntries == 0)
				{
				currentEntry = 0;
				blankValues();
				}
				else
				{
				currentEntry--;
				if (currentEntry == 0)
				currentEntry = 1;
				showEntry(currentEntry);
				}
		
	}
	private void deleteEntry(int j) {
		// TODO Auto-generated method stub
		if (j != numberEntries)
		{
		// move all entries under j up one level
		for (int i = j; i < numberEntries; i++)
		{
		myInventory[i - 1] = new InventoryItem();
		myInventory[i - 1] = myInventory[i];
		}
		}
		numberEntries--;
		
	}
	private void searchButtonActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int i;
		if (numberEntries == 0)
		return;
		// search for item letter
		String letterClicked = e.getActionCommand();
		i = 0;
		do
		{
		if (myInventory[i].description.substring(0, 1).equals(letterClicked))
		{
		currentEntry = i + 1;
		showEntry(currentEntry);
		return;
		}
		i++;
		}
		while (i < numberEntries);
		JOptionPane.showConfirmDialog(null, "No " + letterClicked + " inventory items.",
		"None Found", JOptionPane.DEFAULT_OPTION,
		JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void itemActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		comboBoxlocation.requestFocus();
	}
	private void locationActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (comboBoxlocation.getItemCount() != 0)
		{
		for (int i = 0; i <comboBoxlocation.getItemCount(); i++)
		{
		if
		(comboBoxlocation.getSelectedItem().toString().equals(comboBoxlocation.getItemAt(i).toString()))
		{
			serialnotextfield.requestFocus();
		return;
		}
		}
		}
		// If not found, add to list box
		comboBoxlocation.addItem(comboBoxlocation.getSelectedItem( ));
		
	}
	private void serialActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		textfieldpurchaseprice.requestFocus();
	}
	private void purchaseActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dateChooser.requestFocus();
	}
	private void dateChooserPropertyChange(PropertyChangeEvent e) {
		// TODO Auto-generated method stub
		websitetextField.requestFocus();
	}
	private void websiteActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		noteTextfield.requestFocus();
	}
	private void noteActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		photoButton.requestFocus();
	}

	
	private void sizeButton(JButton b, Dimension d) {
		
		// TODO Auto-generated method stub
		b.setPreferredSize(new Dimension(50, 50));
		b.setMinimumSize(new Dimension(50, 50));
		b.setMaximumSize(new Dimension(50, 50));
		
	}
	private void showEntry(int j)
	{
	// display entry j (1 to numberEntries)
		if(j>=1&&j<=numberEntries){
		inventoryitemtextField.setText(myInventory[j - 1].description);
		comboBoxlocation.setSelectedItem(myInventory[j - 1].location);
		chckbxMarked.setSelected(myInventory[j - 1].marked);
		serialnotextfield.setText(myInventory[j - 1].serialNumber);
		textfieldpurchaseprice.setText(myInventory[j - 1].purchasePrice);
		dateChooser.setDate(stringToDate(myInventory[j - 1].purchaseDate));
		websitetextField.setText(myInventory[j - 1].purchaseLocation);
	noteTextfield.setText(myInventory[j - 1].note);
	showPhoto(myInventory[j - 1].photoFile);
	btnNext.setEnabled(true);
	btnPrevious.setEnabled(true);
	if (j == 1)
	btnPrevious.setEnabled(false);
	if (j == numberEntries)
	btnNext.setEnabled(false);
	inventoryitemtextField.requestFocus();
		}
	}
	private Date stringToDate(String s)
	{
	int m = Integer.valueOf(s.substring(0, 2)).intValue() - 1;
	int d = Integer.valueOf(s.substring(3, 5)).intValue();
	int y = Integer.valueOf(s.substring(6)).intValue() - 1900;
	return(new Date(y, m, d));
	}
	private String dateToString(Date dd)
	{
	String yString = String.valueOf(dd.getYear() + 1900);
	int m = dd.getMonth() + 1;
	String mString = new DecimalFormat("00").format(m);
	int d = dd.getDate();
	String dString = new DecimalFormat("00").format(d);
	return(mString + "/" + dString + "/" + yString);
	}
	private void showPhoto(String photoFile)
	{
	if (!photoFile.equals(""))
	{
	try
	{
		phototextArea.setText(photoFile);
	}
	catch (Exception ex)
	{
		phototextArea.setText("");
	}
	}
	else
	{
		phototextArea.setText("");
	}
	photoPanel.repaint();
	}
	private void btnPreviousActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		checkSave();
		currentEntry--;
		showEntry(currentEntry);
	}
	private void checkSave() {
		// TODO Auto-generated method stub
		
			boolean edited = false;
			if (!myInventory[currentEntry - 1].description.equals(inventoryitemtextField.getText()))
			edited = true;
			else if (!myInventory[currentEntry -1].location.equals(comboBoxlocation.getSelectedItem().toString()))
			                      edited = true;
			                      else if (myInventory[currentEntry - 1].marked !=chckbxMarked.isSelected())
			                      edited = true;
			                      else if (!myInventory[currentEntry - 1].serialNumber.equals(serialnotextfield.getText()))
			                      edited = true;
			                      else if (!myInventory[currentEntry - 1].purchasePrice.equals(textfieldpurchaseprice.getText()))
			                      edited = true;
			                      else if (!myInventory[currentEntry -
			                      1].purchaseDate.equals(dateToString(dateChooser.getDate())))
			                      edited = true;
			                      else if (!myInventory[currentEntry -
			                      1].purchaseLocation.equals(websitetextField.getText()))
			                      edited = true;
			                      else if (!myInventory[currentEntry - 1].note.equals(noteTextfield.getText()))
			                      edited = true;
			                      else if (!myInventory[currentEntry - 1].photoFile.equals(phototextArea.getText()))
			                      edited = true;
			                      if (edited)
			                      {
			                      if (JOptionPane.showConfirmDialog(null, "You have edited this item. Do you want to save the changes?", "Save Item", JOptionPane.YES_NO_OPTION,
			                      JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
			                      btnSave.doClick();
			                      }
	}
	private void btnNextActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		checkSave();
		currentEntry++;
		showEntry(currentEntry);
	}
	private void exitForm(Object object) {
		// TODO Auto-generated method stub
		if (JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost.\nAre you sure you want to exit?", "Exit Program", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
				return;
		PrintWriter outputFile=null;
		try
		{
			FileWriter f=new FileWriter("inventory.txt");
			BufferedWriter inputFile = new BufferedWriter(f);
		 outputFile = new PrintWriter(inputFile);
		outputFile.println(numberEntries);
		if (numberEntries != 0)
		{
		for (int i = 0; i < numberEntries; i++)
		{
		outputFile.println(myInventory[i].description);
		outputFile.println(myInventory[i].location);
		outputFile.println(myInventory[i].serialNumber);
		outputFile.println(myInventory[i].marked);
		outputFile.println(myInventory[i].purchasePrice);
		outputFile.println(myInventory[i].purchaseDate);
		outputFile.println(myInventory[i].purchaseLocation);
		outputFile.println(myInventory[i].note);
		outputFile.println(myInventory[i].photoFile);
		}
		}
		// write combo box entries
		outputFile.println(comboBoxlocation.getItemCount());
		if (comboBoxlocation.getItemCount() != 0)
		{
		for (int i = 0; i < comboBoxlocation.getItemCount(); i++)
		outputFile.println(comboBoxlocation.getItemAt(i));
		}
		outputFile.close();
		}
		catch (Exception ex)
		{
			System.out.println("sd");
		}
		System.exit(0);
		}
		
	

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	 class PhotoPanel extends JPanel {
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g)
	{
	Graphics2D g2D = (Graphics2D) g;
	super.paintComponent(g);
	// draw border
	g2D.setPaint(Color.BLACK);
	g2D.draw(new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1));
	Image photoImage=new ImageIcon(HomeInventory.phototextArea.getText()).getImage( );
	int w = getWidth();
	int h = getHeight();
	double rWidth = (double) getWidth() / (double) photoImage.getWidth(null);
	double rHeight = (double) getHeight() / (double) photoImage.getHeight(null);
	if (rWidth > rHeight)
	{
		// leave height at display height, change width by amount height is changed
		w = (int) (photoImage.getWidth(null) * rHeight);
		}
		else
		{
		// leave width at display width, change height by amount width is changed
		h = (int) (photoImage.getHeight(null) * rWidth);
		}
		// center in panel
		g2D.drawImage(photoImage, (int) (0.5 * (getWidth() - w)), (int) (0.5 * (getHeight() - h)),
		w, h, null);
	g2D.dispose();
	}
  }
	
	 class InventoryDocument implements Printable
	 {
	 public int print(Graphics g, PageFormat pf, int pageIndex)
	 {
	 Graphics2D g2D = (Graphics2D) g;
	 if ((pageIndex + 1) > HomeInventory.lastPage)
	 {
	 return NO_SUCH_PAGE;
	 }
	 int i, iEnd;
	 // here you decide what goes on each page and draw it
	 // header
	 g2D.setFont(new Font("Arial", Font.BOLD, 14));
	 g2D.drawString("Home Inventory Items - Page " + String.valueOf(pageIndex + 1),(int) pf.getImageableX(), (int) (pf.getImageableY() + 25));
	 // get starting y
	 int dy = (int) g2D.getFont().getStringBounds("S",
	 g2D.getFontRenderContext()).getHeight();
	 int y = (int) (pf.getImageableY() + 4 * dy);
	 iEnd = HomeInventory.entriesPerPage * (pageIndex + 1);
	 if (iEnd > HomeInventory.numberEntries)
 iEnd = HomeInventory.numberEntries;
	 for (i = 0 + HomeInventory.entriesPerPage * pageIndex; i < iEnd; i++)
	 {
	 // dividing line
	 Line2D.Double dividingLine = new Line2D.Double(pf.getImageableX(), y, pf.getImageableX() + pf.getImageableWidth(), y);
	 g2D.draw(dividingLine);
	 y += dy;
	 g2D.setFont(new Font("Arial", Font.BOLD, 12));
	 g2D.drawString(HomeInventory.myInventory[i].description, (int) pf.getImageableX(), y);
	 y += dy;
	 g2D.setFont(new Font("Arial", Font.PLAIN, 12));
	 g2D.drawString("Location: " + HomeInventory.myInventory[i].location, (int)(pf.getImageableX() + 25), y);
	 y += dy;
	 if (HomeInventory.myInventory[i].marked)
	 g2D.drawString("Item is marked with identifying information.", (int)(pf.getImageableX() + 25), y);
	 else
	 g2D.drawString("Item is NOT marked with identifying information.", (int) (pf.getImageableX() + 25), y);
	 y += dy;
	 g2D.drawString("Serial Number: " +HomeInventory.myInventory[i].serialNumber, (int) (pf.getImageableX() + 25), y);
	 y += dy;
	 g2D.drawString("Price: $" + HomeInventory.myInventory[i].purchasePrice + ", Purchased on: " + HomeInventory.myInventory[i].purchaseDate, (int) (pf.getImageableX() +25), y);
	 y += dy;
	 g2D.drawString("Purchased at: " + HomeInventory.myInventory[i].purchaseLocation, (int) (pf.getImageableX() + 25), y);
	 y += dy;
	 g2D.drawString("Note: " + HomeInventory.myInventory[i].note, (int) (pf.getImageableX() + 25), y);
	 y += dy;
	 try
	 {
		 Image inventoryImage = new
				 ImageIcon(HomeInventory.myInventory[i].photoFile).getImage ();
				 double ratio = (double) (inventoryImage.getWidth(null)) / (double)
				 inventoryImage.getHeight(null);
				 g2D.drawImage(inventoryImage, (int) (pf.getImageableX() + 25), y, (int) (100 *
				 ratio), 100, null);
				 }
				 catch (Exception ex)
				 {
				 // have place to go in case image file doesn't open
				 }
				 y += 2 * dy + 100;
				 }
				 return PAGE_EXISTS;
				 }
				 }
	 