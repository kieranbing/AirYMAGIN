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

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.FlowLayout;

import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;

import javax.swing.JLayeredPane;

import java.awt.CardLayout;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.Action;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class AirYMAGINForm {

	private JFrame frmAirymagin;
	private JTextField mainUsernameField;
	private JPasswordField mainPasswordField;
	private JTextField startUsernameField;
	private JTextField startPasswordField;
	private JTextField confirmationField;

	private JLabel lblLives; 
	private JLabel lblCodePhrase;
	
	private String username = ""; 
	//private String password = ""; 
	private String confirmationPhrase = ""; 
	
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
		//frmAirymagin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel lblAirymaginStartup = new JLabel("AirYMAGIN - Setup page");
		startTopPanel.add(lblAirymaginStartup);
		lblAirymaginStartup.setFont(new Font("Tahoma", Font.BOLD, 20));
		
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
		
		JLabel lblUsername_1 = new JLabel("Username:");
		startCenterPanel.add(lblUsername_1, "2, 2, right, default");
		
		startUsernameField = new JTextField();
		startCenterPanel.add(startUsernameField, "4, 2, fill, default");
		startUsernameField.setColumns(10);
		
		JLabel lblUserPassword = new JLabel("User Password:");
		startCenterPanel.add(lblUserPassword, "2, 4, right, default");
		
		startPasswordField = new JTextField();
		startCenterPanel.add(startPasswordField, "4, 4, fill, default");
		startPasswordField.setColumns(10);
		
		JLabel lblConfirmationPhrase = new JLabel("Confirmation Phrase:");
		startCenterPanel.add(lblConfirmationPhrase, "2, 6, right, default");
		
		confirmationField = new JTextField();
		startCenterPanel.add(confirmationField, "4, 6, fill, default");
		confirmationField.setColumns(10);
		
		JPanel startBottomPanel = new JPanel();
		startBottomPanel.setBackground(new Color(51, 153, 255));
		startupPage.add(startBottomPanel, BorderLayout.SOUTH);
		
		JButton btnStart = new JButton("Start");
		btnStart.setActionCommand("Start");
		btnStart.addActionListener(new ButtonClickListener()); 
		startBottomPanel.add(btnStart);
		
		JPanel mainPage = new JPanel();
		frmAirymagin.getContentPane().add(mainPage, "mainPageCard");
		mainPage.setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		mainPage.add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(new Color(51, 153, 255));
		
		JLabel lblAirymagin = new JLabel("AirYMAGIN");
		lblAirymagin.setIcon(new ImageIcon(AirYMAGINForm.class.getResource("/images/logo_40x40.jpg")));
		lblAirymagin.setFont(new Font("Tahoma", Font.BOLD, 20));
		topPanel.add(lblAirymagin);
		
		JPanel centerPanel = new JPanel();
		mainPage.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setBackground(new Color(153, 204, 255));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		mainUsernameField = new JTextField();
		mainUsernameField.setFont(new Font("SansSerif", Font.PLAIN, 15));
		mainUsernameField.setColumns(16);
		
		JLabel lblPassword = new JLabel("Password:");
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
		
		JButton clearButton = new JButton("Clear");
		clearButton.setActionCommand("Clear");
		clearButton.addActionListener(new ButtonClickListener()); 
		bottomPanel.add(clearButton);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		bottomPanel.add(horizontalStrut);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("Submit");
		submitButton.addActionListener(new ButtonClickListener());
		bottomPanel.add(submitButton);
		
		JPanel failPage = new JPanel();
		frmAirymagin.getContentPane().add(failPage, "failPageCard");
		failPage.setLayout(new BorderLayout(0, 0));
		
		JPanel failCenterPanel = new JPanel();
		failCenterPanel.setBackground(new Color(255, 102, 102));
		failPage.add(failCenterPanel, BorderLayout.CENTER);
		
		JLabel lblError = new JLabel("Incorrect");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblError.setIcon(new ImageIcon(AirYMAGINForm.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		failCenterPanel.setLayout(new MigLayout("", "[grow][146px][grow]", "[grow][32px][20px][37px][][grow]"));
		failCenterPanel.add(lblError, "cell 1 1,alignx left,aligny top");
		
		JLabel lblAttemptsRemaining = new JLabel("Attempts remaining:");
		lblAttemptsRemaining.setFont(new Font("Tahoma", Font.PLAIN, 16));
		failCenterPanel.add(lblAttemptsRemaining, "cell 1 3,alignx left,aligny top");
		
		lblLives = new JLabel(Integer.toString(remainingAttempts));
		lblLives.setFont(new Font("Tahoma", Font.BOLD, 30));
		failCenterPanel.add(lblLives, "cell 1 4,alignx center,aligny top");
		
		JPanel failBottomPanel = new JPanel();
		failBottomPanel.setBackground(new Color(255, 0, 0));
		failPage.add(failBottomPanel, BorderLayout.SOUTH);
		
		JButton failBackButton = new JButton("Back");
		failBackButton.setActionCommand("Back");
		failBackButton.addActionListener(new ButtonClickListener());
		failBottomPanel.add(failBackButton);
		
		JPanel successPage = new JPanel();
		successPage.setBackground(new Color(204, 255, 204));
		frmAirymagin.getContentPane().add(successPage, "successPageCard");
		successPage.setLayout(new MigLayout("", "[grow][300,grow 50][grow]", "[grow][][][grow,center][grow 50,center][grow]"));
		
		JLabel lblCongradulati = new JLabel("Congratulations!");
		lblCongradulati.setFont(new Font("SansSerif", Font.BOLD, 20));
		successPage.add(lblCongradulati, "cell 1 1,alignx center");
		
		JTextPane txtpnYouHaveSuccessfuly = new JTextPane();
		txtpnYouHaveSuccessfuly.setBackground(new Color(204, 255, 204));
		txtpnYouHaveSuccessfuly.setEditable(false);
		txtpnYouHaveSuccessfuly.setText("You have successfully checked in for your flight to the North Pole and have escaped summer! We thank you for having chosen Air-YMAGIN and we wish you safe travels to wherever your journey might take you. \r\nTo complete the escape room give us a call on the walkie talkie and tell us the destination of your flight and the code phrase below.");
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
		
		JLabel lblOhNoIt = new JLabel("OH NO! It seems you've been locked out of our system.");
		lblOhNoIt.setFont(new Font("SansSerif", Font.BOLD, 15));
		lockoutPage.add(lblOhNoIt, "cell 1 1,alignx center");
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AirYMAGINForm.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		lockoutPage.add(label, "cell 1 2,alignx center");
		
		JLabel lblNewLabel = new JLabel("Please call for assistance");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		lockoutPage.add(lblNewLabel, "cell 1 3,alignx center");
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
}
