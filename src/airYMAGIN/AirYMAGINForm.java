package airYMAGIN;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JPasswordField;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.FlowLayout;

import net.miginfocom.swing.MigLayout;

import javax.swing.JOptionPane;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Color;

import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class AirYMAGINForm {

	// GUI Component variables -------------------------------
	// Frame
	private JFrame frmAirymagin;
	
	// Text Fields 
	private JTextField mainUsernameField;
	private JPasswordField mainPasswordField;
	private JTextField startUsernameField;
	private JTextField startPasswordField;
	private JTextField confirmationField;
	
	// Buttons
	private JButton btnStart; 
	private JButton clearButton; 
	private JButton submitButton;
	private JButton failBackButton;
	private JButton startLangButton;
	private JButton mainLangButton;
	
	// Text pane
	private JTextPane txtpnYouHaveSuccessfuly; 

	// Static labels (Change on translate) 
	private JLabel lblAirymaginStartup;
	private JLabel lblUsername_1;
	private JLabel lblUserPassword; 
	private JLabel lblConfirmationPhrase; 
	private JLabel lblAirymagin; 
	private JLabel lblUsername; 
	private JLabel lblPassword;
	private JLabel lblError;
	private JLabel lblAttemptsRemaining;
	private JLabel lblCongradulati; 
	private JLabel lblOhNoIt;
	private JLabel lblLockoutInstruction; 
	
	// Non-static labels (Change from functionality) 
	private JLabel lblLives; 
	private JLabel lblCodePhrase;
	
	// Functionality variables ------------------------------
	private String username = ""; 
	private String confirmationPhrase = ""; 
	private String language = "EN"; 
	
	private char[] correctPassword;
	
	private int remainingAttempts = 3; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AirYMAGINForm window = new AirYMAGINForm();
					window.frmAirymagin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AirYMAGINForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAirymagin = new JFrame();
		frmAirymagin.setPreferredSize(new Dimension(570, 380));
		frmAirymagin.setIconImage(Toolkit.getDefaultToolkit().getImage(AirYMAGINForm.class.getResource("/logo_400x400.jpeg")));
		frmAirymagin.setTitle("AirYMAGIN");
		frmAirymagin.setBounds(100, 100, 570, 380);
		frmAirymagin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAirymagin.getContentPane().setLayout(new CardLayout(0, 0));
		frmAirymagin.addWindowListener(
			    new WindowAdapter() {
			        public void windowClosing(WindowEvent e) {
			            exit();
			        }
			    }
			);
		
		JPanel startupPage = new JPanel();
		startupPage.setBackground(new Color(102, 204, 255));
		frmAirymagin.getContentPane().add(startupPage, "startPageCard");
		startupPage.setLayout(new BorderLayout(0, 0));
		
		JPanel startTopPanel = new JPanel();
		startTopPanel.setBackground(new Color(51, 153, 255));
		startupPage.add(startTopPanel, BorderLayout.NORTH);
		startTopPanel.setLayout(new MigLayout("", "[250px][76px,grow,right]", "[28px]"));
		
		lblAirymaginStartup = new JLabel(Messages.getString("AirYMAGINForm.lblAirymaginStartup.text")); //$NON-NLS-1$
		lblAirymaginStartup.setIcon(new ImageIcon(AirYMAGINForm.class.getResource("/images/logo_40x40.jpg")));
		startTopPanel.add(lblAirymaginStartup, "cell 0 0,alignx left,aligny center");
		lblAirymaginStartup.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		startLangButton = new JButton(Messages.getString("AirYMAGINForm.startLangButton.text")); //$NON-NLS-1$
		startLangButton.setActionCommand("Language");
		startLangButton.addActionListener(new ButtonClickListener()); 
		startTopPanel.add(startLangButton, "cell 1 0,alignx right,aligny center");
		
		JPanel startCenterPanel = new JPanel();
		startCenterPanel.setBackground(new Color(153, 204, 255));
		startupPage.add(startCenterPanel, BorderLayout.CENTER);
		startCenterPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.UNRELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		lblUsername_1 = new JLabel(Messages.getString("AirYMAGINForm.lblUsername_1.text")); //$NON-NLS-1$
		startCenterPanel.add(lblUsername_1, "2, 2, right, default");
		
		startUsernameField = new JTextField();
		startCenterPanel.add(startUsernameField, "4, 2, fill, default");
		startUsernameField.setColumns(10);
		
		lblUserPassword = new JLabel(Messages.getString("AirYMAGINForm.lblUserPassword.text")); //$NON-NLS-1$
		startCenterPanel.add(lblUserPassword, "2, 4, right, default");
		
		startPasswordField = new JTextField();
		startCenterPanel.add(startPasswordField, "4, 4, fill, default");
		startPasswordField.setColumns(10);
		
		lblConfirmationPhrase = new JLabel(Messages.getString("AirYMAGINForm.lblConfirmationPhrase.text")); //$NON-NLS-1$
		startCenterPanel.add(lblConfirmationPhrase, "2, 6, right, default");
		
		confirmationField = new JTextField();
		startCenterPanel.add(confirmationField, "4, 6, fill, default");
		confirmationField.setColumns(10);
		
		JPanel startBottomPanel = new JPanel();
		startBottomPanel.setBackground(new Color(51, 153, 255));
		startupPage.add(startBottomPanel, BorderLayout.SOUTH);
		
		btnStart = new JButton(Messages.getString("AirYMAGINForm.btnStart.text")); //$NON-NLS-1$
		btnStart.setActionCommand("Start");
		btnStart.addActionListener(new ButtonClickListener()); 
		startBottomPanel.add(btnStart);
		
		JPanel mainPage = new JPanel();
		frmAirymagin.getContentPane().add(mainPage, "mainPageCard");
		mainPage.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		mainPage.add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(new Color(51, 153, 255));
		topPanel.setLayout(new MigLayout("", "[158px][grow,right]", "[40px]"));
		
		lblAirymagin = new JLabel(Messages.getString("AirYMAGINForm.lblAirymagin.text")); //$NON-NLS-1$
		lblAirymagin.setIcon(new ImageIcon(AirYMAGINForm.class.getResource("/images/logo_40x40.jpg")));
		lblAirymagin.setFont(new Font("Tahoma", Font.BOLD, 20));
		topPanel.add(lblAirymagin, "cell 0 0,alignx left,aligny top");
		
		mainLangButton = new JButton(Messages.getString("AirYMAGINForm.mainLangButton.text")); //$NON-NLS-1$
		mainLangButton.setActionCommand("Language");
		mainLangButton.addActionListener(new ButtonClickListener()); 
		topPanel.add(mainLangButton, "cell 1 0,alignx right,aligny center");
		
		JPanel centerPanel = new JPanel();
		mainPage.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setBackground(new Color(153, 204, 255));
		
		lblUsername = new JLabel(Messages.getString("AirYMAGINForm.lblUsername.text")); //$NON-NLS-1$
		lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		mainUsernameField = new JTextField();
		mainUsernameField.setFont(new Font("SansSerif", Font.PLAIN, 15));
		mainUsernameField.setColumns(16);
		
		lblPassword = new JLabel(Messages.getString("AirYMAGINForm.lblPassword.text")); //$NON-NLS-1$
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		mainPasswordField = new JPasswordField();
		mainPasswordField.setFont(new Font("SansSerif", Font.PLAIN, 15));
		mainPasswordField.setColumns(16);
		centerPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormFactory.PREF_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),
				RowSpec.decode("20px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				RowSpec.decode("default:grow"),}));
		centerPanel.add(lblUsername, "2, 2, right, center");
		centerPanel.add(mainUsernameField, "4, 2, left, top");
		centerPanel.add(lblPassword, "2, 4, right, center");
		centerPanel.add(mainPasswordField, "4, 4, left, top");
		
		JPanel bottomPanel = new JPanel();
		mainPage.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setBackground(new Color(51, 153, 255));
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		clearButton = new JButton(Messages.getString("AirYMAGINForm.clearButton.text")); //$NON-NLS-1$
		clearButton.setActionCommand("Clear");
		clearButton.addActionListener(new ButtonClickListener()); 
		bottomPanel.add(clearButton);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		bottomPanel.add(horizontalStrut);
		
		submitButton = new JButton(Messages.getString("AirYMAGINForm.submitButton.text")); //$NON-NLS-1$
		submitButton.setActionCommand("Submit");
		submitButton.addActionListener(new ButtonClickListener());
		bottomPanel.add(submitButton);
		
		JPanel failPage = new JPanel();
		frmAirymagin.getContentPane().add(failPage, "failPageCard");
		failPage.setLayout(new BorderLayout(0, 0));
		
		JPanel failCenterPanel = new JPanel();
		failCenterPanel.setBackground(new Color(255, 102, 102));
		failPage.add(failCenterPanel, BorderLayout.CENTER);
		
		lblError = new JLabel(Messages.getString("AirYMAGINForm.lblError.text")); //$NON-NLS-1$
		lblError.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblError.setIcon(new ImageIcon(AirYMAGINForm.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		failCenterPanel.setLayout(new MigLayout("", "[grow][146px][grow]", "[grow][32px][20px][37px][][grow]"));
		failCenterPanel.add(lblError, "cell 1 1,alignx center,aligny top");
		
		lblAttemptsRemaining = new JLabel(Messages.getString("AirYMAGINForm.lblAttemptsRemaining.text")); //$NON-NLS-1$
		lblAttemptsRemaining.setFont(new Font("Tahoma", Font.PLAIN, 16));
		failCenterPanel.add(lblAttemptsRemaining, "cell 1 3,alignx center,aligny top");
		
		lblLives = new JLabel(Integer.toString(remainingAttempts));
		lblLives.setFont(new Font("Tahoma", Font.BOLD, 30));
		failCenterPanel.add(lblLives, "cell 1 4,alignx center,aligny top");
		
		JPanel failBottomPanel = new JPanel();
		failBottomPanel.setBackground(new Color(255, 0, 0));
		failPage.add(failBottomPanel, BorderLayout.SOUTH);
		
		failBackButton = new JButton(Messages.getString("AirYMAGINForm.failBackButton.text")); //$NON-NLS-1$
		failBackButton.setActionCommand("Back");
		failBackButton.addActionListener(new ButtonClickListener());
		failBottomPanel.add(failBackButton);
		
		JPanel successPage = new JPanel();
		successPage.setBackground(new Color(204, 255, 204));
		frmAirymagin.getContentPane().add(successPage, "successPageCard");
		successPage.setLayout(new MigLayout("", "[grow][300,grow 50][grow]", "[grow][][][grow,center][grow 50,center][grow]"));
		
		lblCongradulati = new JLabel(Messages.getString("AirYMAGINForm.lblCongradulati.text")); //$NON-NLS-1$
		lblCongradulati.setFont(new Font("SansSerif", Font.BOLD, 20));
		successPage.add(lblCongradulati, "cell 1 1,alignx center");
		
		txtpnYouHaveSuccessfuly = new JTextPane();
		txtpnYouHaveSuccessfuly.setBackground(new Color(204, 255, 204));
		txtpnYouHaveSuccessfuly.setEditable(false);
		txtpnYouHaveSuccessfuly.setText(Messages.getString("AirYMAGINForm.txtpnYouHaveSuccessfuly.text")); //$NON-NLS-1$
		successPage.add(txtpnYouHaveSuccessfuly, "cell 1 3,alignx center,growy");
		StyledDocument doc = txtpnYouHaveSuccessfuly.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		lblCodePhrase = new JLabel("Code Phrase Placehoder");
		lblCodePhrase.setFont(new Font("SansSerif", Font.BOLD, 16));
		successPage.add(lblCodePhrase, "cell 1 4,alignx center,aligny center");
		
		JPanel lockoutPage = new JPanel();
		lockoutPage.setBackground(new Color(255, 153, 153));
		frmAirymagin.getContentPane().add(lockoutPage, "lockoutPageCard");
		lockoutPage.setLayout(new MigLayout("", "[grow][][grow]", "[grow][][][][grow]"));
		
		lblOhNoIt = new JLabel(Messages.getString("AirYMAGINForm.lblOhNoIt.text")); //$NON-NLS-1$
		lblOhNoIt.setFont(new Font("SansSerif", Font.BOLD, 15));
		lockoutPage.add(lblOhNoIt, "cell 1 1,alignx center");
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AirYMAGINForm.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		lockoutPage.add(label, "cell 1 2,alignx center");
		
		lblLockoutInstruction = new JLabel(Messages.getString("AirYMAGINForm.lblNewLabel.text")); //$NON-NLS-1$
		lblLockoutInstruction.setFont(new Font("SansSerif", Font.BOLD, 15));
		lockoutPage.add(lblLockoutInstruction, "cell 1 3,alignx center");
	}
	
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();  
	         
			// Start button script
	         if( command.equals( "Start" ))  {
	        	 username = startUsernameField.getText().trim();
	        	 correctPassword = startPasswordField.getText().trim().toCharArray();
	        	 confirmationPhrase = confirmationField.getText().trim();
	        	 lblCodePhrase.setText(confirmationPhrase); 
	        	 
	        	 CardLayout cl = (CardLayout)(frmAirymagin.getContentPane().getLayout()); 
	        	 cl.show(frmAirymagin.getContentPane(), "mainPageCard"); 
	        	 
	        // Clear button script	 
	         } else if( command.equals( "Clear" ) )  {
	        	 mainUsernameField.setText("");
	        	 mainPasswordField.setText("");
	        	
	        // Submit button script	 
	         } else if( command.equals( "Submit" ) )  {	 
	        	 // Check username and password
	        	 if (mainUsernameField.getText().trim().equals(username) &&
	        			 Arrays.equals (mainPasswordField.getPassword(), correctPassword)){
	        		 // If valid go to victory page
	        		 CardLayout cl = (CardLayout)(frmAirymagin.getContentPane().getLayout()); 
		        	 cl.show(frmAirymagin.getContentPane(), "successPageCard"); 
	        	 } else {
	        		 // If invalid lose a life and get sent to fail page or lockout page
	        		 remainingAttempts -= 1; 
	        		 lblLives.setText(Integer.toString(remainingAttempts));
	        		 
	        		 if(remainingAttempts <= 0){
	        			 CardLayout cl = (CardLayout)(frmAirymagin.getContentPane().getLayout()); 
			        	 cl.show(frmAirymagin.getContentPane(), "lockoutPageCard"); 
	        		 } else {
	        			 CardLayout cl = (CardLayout)(frmAirymagin.getContentPane().getLayout()); 
			        	 cl.show(frmAirymagin.getContentPane(), "failPageCard"); 
	        		 }
	        	 }
	        
	         // Fail page back button script
	         } else if( command.equals( "Back" ) )  {
	        	 mainUsernameField.setText("");
	        	 mainPasswordField.setText("");
	        	 CardLayout cl = (CardLayout)(frmAirymagin.getContentPane().getLayout()); 
	        	 cl.show(frmAirymagin.getContentPane(), "mainPageCard"); 
	        	
	         // Change language button script 
	         } else if ( command.equals( "Language" ) ){
	        	 updateLanguage(); 
	        	 
	         } else {
	        	 System.out.println(" ACTION COMMAND ERROR ");
	         }  	
		}
	}
	
	private void exit(){
        //Display confirm dialog
        int confirmed = JOptionPane.showConfirmDialog(frmAirymagin,
                "Are you sure you want to quit?", "Confirm Quit",
                JOptionPane.YES_NO_OPTION);
       
        //Close if user confirmed
        if (confirmed == JOptionPane.YES_OPTION){                            
            //Close frame
        	frmAirymagin.dispose();
        }
	}
	
	private void updateLanguage(){
		if (language.equals("EN")){ language = "FR"; }
		else if (language.equals("FR")){ language = "EN"; }
		else { System.out.println("! LANGUAGE ERROR: Unrecognized language '"+language+"' !"); }
		
		Messages.changeLanguage(language);
		
		// Update Buttons
		btnStart.setText(Messages.getString("AirYMAGINForm.btnStart.text")); 
		clearButton.setText(Messages.getString("AirYMAGINForm.clearButton.text")); 
		submitButton.setText(Messages.getString("AirYMAGINForm.submitButton.text"));
		failBackButton.setText(Messages.getString("AirYMAGINForm.failBackButton.text"));
		startLangButton.setText(Messages.getString("AirYMAGINForm.startLangButton.text"));
		mainLangButton.setText(Messages.getString("AirYMAGINForm.mainLangButton.text"));
		
		// Update Text Pane
		txtpnYouHaveSuccessfuly.setText(Messages.getString("AirYMAGINForm.txtpnYouHaveSuccessfuly.text")); 
		
		// Update labels
		lblAirymaginStartup.setText(Messages.getString("AirYMAGINForm.lblAirymaginStartup.text")); 
		lblUsername_1.setText(Messages.getString("AirYMAGINForm.lblUsername_1.text"));
		lblUserPassword.setText(Messages.getString("AirYMAGINForm.lblUserPassword.text"));
		lblConfirmationPhrase.setText(Messages.getString("AirYMAGINForm.lblConfirmationPhrase.text"));
		lblAirymagin.setText(Messages.getString("AirYMAGINForm.lblAirymagin.text"));
		lblUsername.setText(Messages.getString("AirYMAGINForm.lblUsername.text"));
		lblPassword.setText(Messages.getString("AirYMAGINForm.lblPassword.text"));
		lblError.setText(Messages.getString("AirYMAGINForm.lblError.text"));
		lblAttemptsRemaining.setText(Messages.getString("AirYMAGINForm.lblAttemptsRemaining.text"));
		lblCongradulati.setText(Messages.getString("AirYMAGINForm.lblCongradulati.text"));
		lblOhNoIt.setText(Messages.getString("AirYMAGINForm.lblOhNoIt.text"));
		lblLockoutInstruction.setText(Messages.getString("AirYMAGINForm.lblLockoutInstruction.text")); 	
	}
	
	// Getter(s) --------------------------
	public String getLanguage(){
		return language; 
	}
}
