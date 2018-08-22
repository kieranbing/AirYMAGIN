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
	private JTextField setupUsernameField;
	private JTextField setupPasswordField;
	private JTextField setupConfirmationField;
	
	// Buttons
	private JButton startButton; 
	private JButton clearButton; 
	private JButton submitButton;
	private JButton failBackButton;
	private JButton setupLangButton;
	private JButton mainLangButton;
	
	// Text pane
	private JTextPane txtpnVictoryText; 

	// Static labels (Change on translate) 
	private JLabel lblSetupTitle;
	private JLabel lblSetupUsername;
	private JLabel lblUserPassword; 
	private JLabel lblSetConfirmationPhrase; 
	private JLabel lblMainTitle; 
	private JLabel lblMainUsername; 
	private JLabel lblMainPassword;
	private JLabel lblFailTitle;
	private JLabel lblAttemptsRemaining;
	private JLabel lblVictoryTitle; 
	private JLabel lblLockoutTitle;
	private JLabel lblLockoutInstruction; 
	
	// Non-static labels (Change from functionality) 
	private JLabel lblLives; 
	private JLabel lblVictoryCodePhrase;
	
	// Functionality variables ------------------------------
	private String username = ""; 
	private String confirmationPhrase = ""; 
	private String language = "EN"; 
	
	private char[] correctPassword;
	
	private int remainingAttempts = 3; 
	private JLabel lblVictoryCopyright;
	private JLabel lblLockoutCopyright;

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
		
		// Setup page ---------------------------------------------------------------------
		JPanel setupPage = new JPanel();
		setupPage.setBackground(new Color(102, 204, 255));
		frmAirymagin.getContentPane().add(setupPage, "startPageCard");
		setupPage.setLayout(new BorderLayout(0, 0));
		
		JPanel setupTopPanel = new JPanel();
		setupTopPanel.setBackground(new Color(51, 153, 255));
		setupPage.add(setupTopPanel, BorderLayout.NORTH);
		setupTopPanel.setLayout(new MigLayout("", "[250px][76px,grow,right]", "[28px]"));
		
		lblSetupTitle = new JLabel(Messages.getString("AirYMAGINForm.lblSetupTitle.text")); //$NON-NLS-1$
		lblSetupTitle.setIcon(new ImageIcon(AirYMAGINForm.class.getResource("/images/logo_40x40.jpg")));
		setupTopPanel.add(lblSetupTitle, "cell 0 0,alignx left,aligny center");
		lblSetupTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		setupLangButton = new JButton(Messages.getString("AirYMAGINForm.startLangButton.text")); //$NON-NLS-1$
		setupLangButton.setActionCommand("Language");
		setupLangButton.addActionListener(new ButtonClickListener()); 
		setupTopPanel.add(setupLangButton, "cell 1 0,alignx right,aligny center");
		
		JPanel setupCenterPanel = new JPanel();
		setupCenterPanel.setBackground(new Color(153, 204, 255));
		setupPage.add(setupCenterPanel, BorderLayout.CENTER);
		setupCenterPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
		
		lblSetupUsername = new JLabel(Messages.getString("AirYMAGINForm.lblSetupUsername.text")); //$NON-NLS-1$
		setupCenterPanel.add(lblSetupUsername, "2, 2, right, default");
		
		setupUsernameField = new JTextField();
		setupCenterPanel.add(setupUsernameField, "4, 2, fill, default");
		setupUsernameField.setColumns(10);
		
		lblUserPassword = new JLabel(Messages.getString("AirYMAGINForm.lblUserPassword.text")); //$NON-NLS-1$
		setupCenterPanel.add(lblUserPassword, "2, 4, right, default");
		
		setupPasswordField = new JTextField();
		setupCenterPanel.add(setupPasswordField, "4, 4, fill, default");
		setupPasswordField.setColumns(10);
		
		lblSetConfirmationPhrase = new JLabel(Messages.getString("AirYMAGINForm.lblSetConfirmationPhrase.text")); //$NON-NLS-1$
		setupCenterPanel.add(lblSetConfirmationPhrase, "2, 6, right, default");
		
		setupConfirmationField = new JTextField();
		setupCenterPanel.add(setupConfirmationField, "4, 6, fill, default");
		setupConfirmationField.setColumns(10);
		
		JPanel setupBottomPanel = new JPanel();
		setupBottomPanel.setBackground(new Color(51, 153, 255));
		setupPage.add(setupBottomPanel, BorderLayout.SOUTH);
		
		startButton = new JButton(Messages.getString("AirYMAGINForm.startButton.text")); //$NON-NLS-1$
		startButton.setActionCommand("Start");
		startButton.addActionListener(new ButtonClickListener()); 
		setupBottomPanel.add(startButton);
		
		// main page -----------------------------------------------------------------------
		JPanel mainPage = new JPanel();
		frmAirymagin.getContentPane().add(mainPage, "mainPageCard");
		mainPage.setLayout(new BorderLayout(0, 0));
		
		JPanel mainTopPanel = new JPanel();
		mainPage.add(mainTopPanel, BorderLayout.NORTH);
		mainTopPanel.setBackground(new Color(51, 153, 255));
		mainTopPanel.setLayout(new MigLayout("", "[158px][grow,right]", "[40px]"));
		
		lblMainTitle = new JLabel(Messages.getString("AirYMAGINForm.lblMainTitle.text")); //$NON-NLS-1$
		lblMainTitle.setIcon(new ImageIcon(AirYMAGINForm.class.getResource("/images/logo_40x40.jpg")));
		lblMainTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		mainTopPanel.add(lblMainTitle, "cell 0 0,alignx left,aligny top");
		
		mainLangButton = new JButton(Messages.getString("AirYMAGINForm.mainLangButton.text")); //$NON-NLS-1$
		mainLangButton.setActionCommand("Language");
		mainLangButton.addActionListener(new ButtonClickListener()); 
		mainTopPanel.add(mainLangButton, "cell 1 0,alignx right,aligny center");
		
		JPanel centerPanel = new JPanel();
		mainPage.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setBackground(new Color(153, 204, 255));
		
		lblMainUsername = new JLabel(Messages.getString("AirYMAGINForm.lblMainUsername.text")); //$NON-NLS-1$
		lblMainUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		mainUsernameField = new JTextField();
		mainUsernameField.setFont(new Font("SansSerif", Font.PLAIN, 15));
		mainUsernameField.setColumns(16);
		
		lblMainPassword = new JLabel(Messages.getString("AirYMAGINForm.lblMainPassword.text")); //$NON-NLS-1$
		lblMainPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
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
		centerPanel.add(lblMainUsername, "2, 2, right, center");
		centerPanel.add(mainUsernameField, "4, 2, left, top");
		centerPanel.add(lblMainPassword, "2, 4, right, center");
		centerPanel.add(mainPasswordField, "4, 4, left, top");
		
		JPanel mainBottomPanel = new JPanel();
		mainPage.add(mainBottomPanel, BorderLayout.SOUTH);
		mainBottomPanel.setBackground(new Color(51, 153, 255));
		mainBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		clearButton = new JButton(Messages.getString("AirYMAGINForm.clearButton.text")); //$NON-NLS-1$
		clearButton.setActionCommand("Clear");
		clearButton.addActionListener(new ButtonClickListener()); 
		mainBottomPanel.add(clearButton);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		mainBottomPanel.add(horizontalStrut);
		
		submitButton = new JButton(Messages.getString("AirYMAGINForm.submitButton.text")); //$NON-NLS-1$
		submitButton.setActionCommand("Submit");
		submitButton.addActionListener(new ButtonClickListener());
		mainBottomPanel.add(submitButton);
		
		// Fail page -----------------------------------------------------------------------
		JPanel failPage = new JPanel();
		frmAirymagin.getContentPane().add(failPage, "failPageCard");
		failPage.setLayout(new BorderLayout(0, 0));
		
		JPanel failCenterPanel = new JPanel();
		failCenterPanel.setBackground(new Color(255, 102, 102));
		failPage.add(failCenterPanel, BorderLayout.CENTER);
		
		lblFailTitle = new JLabel(Messages.getString("AirYMAGINForm.lblFailTitle.text")); //$NON-NLS-1$
		lblFailTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFailTitle.setIcon(new ImageIcon(AirYMAGINForm.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		failCenterPanel.setLayout(new MigLayout("", "[grow][146px][grow]", "[grow][32px][20px][37px][][grow]"));
		failCenterPanel.add(lblFailTitle, "cell 1 1,alignx center,aligny top");
		
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
		
		// Victory page --------------------------------------------------------------------
		JPanel victoryPage = new JPanel();
		victoryPage.setBackground(new Color(204, 255, 204));
		frmAirymagin.getContentPane().add(victoryPage, "successPageCard");
		victoryPage.setLayout(new MigLayout("", "[grow][300,grow 50][grow]", "[grow][][][grow,center][grow 50,center][grow]"));
		
		lblVictoryTitle = new JLabel(Messages.getString("AirYMAGINForm.lblVictoryTitle.text")); //$NON-NLS-1$
		lblVictoryTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
		victoryPage.add(lblVictoryTitle, "cell 1 1,alignx center");
		
		txtpnVictoryText = new JTextPane();
		txtpnVictoryText.setBackground(new Color(204, 255, 204));
		txtpnVictoryText.setEditable(false);
		txtpnVictoryText.setText(Messages.getString("AirYMAGINForm.txtpnVictoryText.text")); //$NON-NLS-1$
		victoryPage.add(txtpnVictoryText, "cell 1 3,alignx center,growy");
		StyledDocument doc = txtpnVictoryText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		lblVictoryCodePhrase = new JLabel("Code Phrase Placehoder");
		lblVictoryCodePhrase.setFont(new Font("SansSerif", Font.BOLD, 16));
		victoryPage.add(lblVictoryCodePhrase, "cell 1 4,alignx center,aligny center");
		
		lblVictoryCopyright = new JLabel(Messages.getString("AirYMAGINForm.lblVictoryCopyright.text")); //$NON-NLS-1$
		lblVictoryCopyright.setForeground(Color.GRAY);
		victoryPage.add(lblVictoryCopyright, "cell 1 5,alignx center,aligny bottom");
		
		// lockout page --------------------------------------------------------------------
		JPanel lockoutPage = new JPanel();
		lockoutPage.setBackground(new Color(255, 153, 153));
		frmAirymagin.getContentPane().add(lockoutPage, "lockoutPageCard");
		lockoutPage.setLayout(new MigLayout("", "[grow][][grow]", "[grow][][][][grow]"));
		
		lblLockoutTitle = new JLabel(Messages.getString("AirYMAGINForm.lblLockoutTitle.text")); //$NON-NLS-1$
		lblLockoutTitle.setFont(new Font("SansSerif", Font.BOLD, 15));
		lockoutPage.add(lblLockoutTitle, "cell 1 1,alignx center");
		
		JLabel lblLockoutSymbol = new JLabel("");
		lblLockoutSymbol.setIcon(new ImageIcon(AirYMAGINForm.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		lockoutPage.add(lblLockoutSymbol, "cell 1 2,alignx center");
		
		lblLockoutInstruction = new JLabel(Messages.getString("AirYMAGINForm.lblLockoutInstruction.text")); //$NON-NLS-1$
		lblLockoutInstruction.setFont(new Font("SansSerif", Font.BOLD, 15));
		lockoutPage.add(lblLockoutInstruction, "cell 1 3,alignx center");
		
		lblLockoutCopyright = new JLabel(Messages.getString("AirYMAGINForm.lblLockoutCopyright.text")); //$NON-NLS-1$
		lblLockoutCopyright.setForeground(Color.GRAY);
		lockoutPage.add(lblLockoutCopyright, "cell 1 4,alignx center,aligny bottom");
	}
	
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();  
	         
			// Start button script
	         if( command.equals( "Start" ))  {
	        	 username = setupUsernameField.getText().trim();
	        	 correctPassword = setupPasswordField.getText().trim().toCharArray();
	        	 confirmationPhrase = setupConfirmationField.getText().trim();
	        	 lblVictoryCodePhrase.setText(confirmationPhrase); 
	        	 
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
		
		// Change language of retrieved strings
		Messages.changeLanguage(language);
		
		// Update Buttons
		startButton.setText(Messages.getString("AirYMAGINForm.startButton.text")); 
		clearButton.setText(Messages.getString("AirYMAGINForm.clearButton.text")); 
		submitButton.setText(Messages.getString("AirYMAGINForm.submitButton.text"));
		failBackButton.setText(Messages.getString("AirYMAGINForm.failBackButton.text"));
		setupLangButton.setText(Messages.getString("AirYMAGINForm.startLangButton.text"));
		mainLangButton.setText(Messages.getString("AirYMAGINForm.mainLangButton.text"));
		
		// Update Text Pane
		txtpnVictoryText.setText(Messages.getString("AirYMAGINForm.txtpnVictoryText.text")); 
		
		// Update labels
		lblSetupTitle.setText(Messages.getString("AirYMAGINForm.lblSetupTitle.text")); 
		lblSetupUsername.setText(Messages.getString("AirYMAGINForm.lblSetupUsername.text"));
		lblUserPassword.setText(Messages.getString("AirYMAGINForm.lblUserPassword.text"));
		lblSetConfirmationPhrase.setText(Messages.getString("AirYMAGINForm.lblSetConfirmationPhrase.text"));
		lblMainTitle.setText(Messages.getString("AirYMAGINForm.lblMainTitle.text"));
		lblMainUsername.setText(Messages.getString("AirYMAGINForm.lblMainUsername.text"));
		lblMainPassword.setText(Messages.getString("AirYMAGINForm.lblMainPassword.text"));
		lblFailTitle.setText(Messages.getString("AirYMAGINForm.lblFailTitle.text"));
		lblAttemptsRemaining.setText(Messages.getString("AirYMAGINForm.lblAttemptsRemaining.text"));
		lblVictoryTitle.setText(Messages.getString("AirYMAGINForm.lblVictoryTitle.text"));
		lblLockoutTitle.setText(Messages.getString("AirYMAGINForm.lblLockoutTitle.text"));
		lblLockoutInstruction.setText(Messages.getString("AirYMAGINForm.lblLockoutInstruction.text")); 	
		lblVictoryCopyright.setText(Messages.getString("AirYMAGINForm.lblVictoryCopyright.text"));
		lblLockoutCopyright.setText(Messages.getString("AirYMAGINForm.lblLockoutCopyright.text"));
	}
	
	// Getter(s) --------------------------
	public String getLanguage(){
		return language; 
	}
}
