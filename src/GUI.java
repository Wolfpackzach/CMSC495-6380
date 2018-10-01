/*
 * File Name:		GUI.java
 * Class:			CMSC 495-6380
 * Group:			Group 6
 * Authors:			Sheldon Jeffers, Stephen Parsons
 * Date:			9/30/2018
 * Purpose:			This class digests the data input into the program and parses it out to different parts of the program.
 * 					It is also responsible for performing the operations to calculate victories and rankings for the games.
 */
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events
import java.net.URL;
import java.io.IOException;


public class GUI extends JPanel {
    private String newline = "\n";
    protected static final String textFieldString = "JTextField";
    protected static final String passwordFieldString = "JPasswordField";
    protected static final String ftfString = "JFormattedTextField";
    protected static final String buttonString = "JButton";
    protected JLabel actionLabel;

JTextField textField;
JTextField textField2;
JTextField textField3;
JTextField textField4;

JTextArea textArea;

public  static int j;

    public GUI() {

	//no more than 4096 players - tennis math simulation  1vs1 not a team game//
	Player[] players = new Player[4096];	
	for(int i=0;i<4096;i++)
		//We need to have this for loop made after the input file is taken in, so we can send Scanner object to 
		//Player constructor 
    	players[i] = new Player();
	//initialize object array
        this.j=0;

        setLayout(new BorderLayout());
        //Create a regular text field.
        textField = new JTextField(30);
        //textField.setActionCommand(textFieldString);
        //textField.addActionListener(this);

        //Create a password field.
        textField2 = new JTextField(30);
        //passwordField.setActionCommand(passwordFieldString);
        //passwordField.addActionListener(this);

        //Create a formatted text field.
        textField3 = new JTextField(30);
        //ftf.setActionCommand(textFieldString);
        //ftf.addActionListener(this);
        textField4 = new JTextField(30);
        
        //create button
        JButton button0 = new JButton();
        button0.setText("ADD");
        button0.setCursor(Cursor.getDefaultCursor());
        button0.setMargin(new Insets(1,1,1,1));
        button0.setActionCommand(buttonString);
        //button0.addActionListener(this);

	button0.addActionListener(
  	new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        
        System.out.println(e.getActionCommand());
	System.out.println("OIASDSASA");

	int no=0;//=Integer.parseInt(textField.getText());
        String name=null;//=textField2.getText();
        int rank=0;//=Integer.parseInt(textField3.getText());
        int games=0;//=Integer.parseInt(textField4.getText());

	boolean correct;
	correct=false;
 	//while (correct ==false){
  		try{   

		no=Integer.parseInt(textField.getText());
        	name=textField2.getText();
        	rank=Integer.parseInt(textField3.getText());
        	games=Integer.parseInt(textField4.getText());    
////////////////////////////////////////////////
        	   if (name != null && !name.isEmpty()){
		    correct=true;
	           System.out.println("Not empy");

        	      }
	
	          }catch (NumberFormatException ex) {
	           System.out.println("Input is not a valid integer");
	           correct=false;
	          }
	  //   }
      if (correct==true){
	System.out.println(no);
	System.out.println(name);
	System.out.println(rank);
	System.out.println(games);
	System.out.println(j);

	//set player name
	players[j].setName(name);
	players[j].setWinCount(0);
	players[j].setLossCount(0);
        players[j].setRank(rank);
      //  players[j].setGames(games); 
	j++;

	}
     else {textField.setText("Should be an integer value , please try again");
	textField2.setText("Should be a string value , please try again");
	textField3.setText("Should be an integer value , please try again");
	textField4.setText("Should be an integer value , please try again");
      }
////////////////////////////////////////////////////////////
	}
 		}
					); 
	
        //create button (edit)
        //JButton button01 = new JButton();

        //create button (status)
        //JButton button02 = new JButton();

/////////////////
       //////////////////////
	JButton buttonA = new JButton();
        buttonA.setMargin(new Insets(5,5,5,5));
        buttonA.setEnabled(false);
        buttonA.setBorderPainted(false);
        //buttonA.setVisible(false);
////////////
	//JButton buttonB = new JButton();
        //buttonB.setVisible(false);
//////////////////////
        JButton button1 = new JButton();
        button1.setText("ONE WEEK SIMULATION");
        button1.setCursor(Cursor.getDefaultCursor());
        button1.setMargin(new Insets(20,20,20,20));
       // button1.addActionListener(this);

	button1.addActionListener(
  	new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       
	System.out.println("XSDSDSD");
    					}
  			}
					);

        JButton button2 = new JButton();
        button2.setText("FULL SEASON SIMULATION");
        button2.setCursor(Cursor.getDefaultCursor());
        button2.setMargin(new Insets(20,20,20,20));
       // button1.addActionListener(this);
	button2.addActionListener(
  	new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       
	System.out.println("9876543");
    					}
  			});

        JButton button3 = new JButton();
        button3.setText("OPEN FILE");
        button3.setCursor(Cursor.getDefaultCursor());
        button3.setMargin(new Insets(1,1,1,1));
       // button1.addActionListener(this);
	button3.addActionListener(
  	new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
       
	System.out.println("../.../.../.../..//./././..//");
    					}
  			});

        //Create some labels for the fields.
        JLabel textFieldLabel = new JLabel("Number of Player" + ": ");
        textFieldLabel.setLabelFor(textField);
        JLabel textFieldLabel2 = new JLabel("Name of Player" + ": ");
        textFieldLabel2.setLabelFor(textField2);
        JLabel textFieldLabel3 = new JLabel("Rank of Player" + ": ");
        textFieldLabel3.setLabelFor(textField3);
        JLabel textFieldLabel4 = new JLabel("Number of Games in a Season" + ": ");
        textFieldLabel4.setLabelFor(textField4);
//
        JLabel btnALabel = new JLabel("");
        btnALabel.setLabelFor(buttonA);
//
        JLabel btn0Label = new JLabel("Insert Player details" + ": ");
        btn0Label.setLabelFor(button0);

        //JLabel btn01Label = new JLabel("Edit Player details" + ": ");
        //btn01Label.setLabelFor(button01);

        //JLabel btn02Label = new JLabel("Show status" + ": ");
        //btn02Label.setLabelFor(button02);

       JLabel btn1Label = new JLabel("");
        btn1Label.setLabelFor(button1);
        
       JLabel btn2Label = new JLabel("");
        btn2Label.setLabelFor(button2);

        JLabel btn3Label = new JLabel("Open File");
        btn3Label.setLabelFor(button3);

        //Lay out the text controls and the labels.
        JPanel textControlsPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        textControlsPane.setLayout(gridbag);

        JLabel[] labels = {textFieldLabel, textFieldLabel2, textFieldLabel3, textFieldLabel4,btn0Label ,btn3Label,btnALabel,btn1Label,btn2Label};
        JTextField[] textFields = {textField, textField2, textField3, textField4};
        JButton[] buttons= {button0,button3, buttonA,button1, button2 };
        addLabelTextRows(buttons,labels, textFields, gridbag, textControlsPane);

        c.gridwidth = GridBagConstraints.REMAINDER; //last
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        //textControlsPane.add(actionLabel, c);
        textControlsPane.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("SIMULATION OPTIONS"),
                                BorderFactory.createEmptyBorder(50,50,50,50)));  //MIALINISKEI PERIGRAMA

        //Create a text area.
        textArea = new JTextArea(
                "This is an editable JTextArea to OUTPUT MATCHES SIMULATION RESULTS "    );
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
        areaScrollPane.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("SIMULATION OUTPUT"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                areaScrollPane.getBorder()));

        //Put everything together.
        JPanel leftPane = new JPanel(new BorderLayout());
        leftPane.add(textControlsPane, 
                     BorderLayout.PAGE_START);
        leftPane.add(areaScrollPane,
                     BorderLayout.CENTER);

        add(leftPane, BorderLayout.CENTER);  //KETRARISKW ULA

    }


    private void addLabelTextRows(JButton[] buttons,JLabel[] labels,
                                  JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        //int numLabels = labels.length;

        //FIRST ADD TEXTFILEDS
        for (int i = 0; i < 4; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
            c.fill = GridBagConstraints.NONE;      //reset to default
            c.weightx = 0.0;                       //reset to default
            container.add(labels[i], c);

            c.gridwidth = GridBagConstraints.REMAINDER;     //end row
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
		
        //THEN ADD BUTTONS   //increase # when adding
        for (int i = 0; i < 5; i++) {
            
            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
            c.fill = GridBagConstraints.NONE;      //reset to default
            c.weightx = 0.0;                       //reset to default

           container.add(labels[i+4], c);

	     //add buttons//
	    c.gridwidth = GridBagConstraints.REMAINDER;     //end row
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 2.0;

            container.add(buttons[i], c);
	}
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void makeGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Team Season Predictor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Add content to the window.
        frame.add(new GUI());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 //Turn off metal's use of bold fonts
		 UIManager.put("swing.boldMetal", Boolean.FALSE);
		 makeGUI();
            }
        });
    }
}
