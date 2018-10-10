/*
 * File Name:   GUI.java
 * Class:       CMSC 495-6380
 * Group:       Group 6
 * Authors:     Sheldon Jeffers and Stephen Parsons
 * Purpose:     GUI.java creates and shows the GUI application that is displayed to the user. It stores the data 
 *              from the players, provide text fields for user input and buttons that provide actions for the user. 
 *              It also displays the players details, along with the results from simulated games weekly and seasonly.
 * 
 * Revision:
 * Version Number(dates): 1.0(9/29/18) - Created the GUI design, added text fields,  -   Contributor: Sheldon Jeffers
 *                                          buttons, display field.
 * Version Number(dates): 1.1(10/06/18) - GUI functionality, Edit/Status buttons, action    -   Contributor: Sheldon Jeffers
 *                                          listeners for buttons. Added comments.
 * Version Number(dates): 1.1(10/06/18) - GUI functionality, Implemented file chooser,  -   Contributor: Stephen Parsons
 *                                          Added comments.
 * Version Number(dates): 1.2(10/10/18) - Removed unused lines, corrected spelling, reworked code   -   Contributor: Sheldon / Stephen
 */


import javax.swing.*;
import java.awt.*;              
import java.io.*;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class GUI extends JPanel {
  
  JTextField textField;
  JTextField textField2;
  JTextField textField3;
  JTextField textField4;
  JTextArea textArea;

  //max number of tennis Players allocated at current session
  public  static int j;
  
  //stores number of current week , during weekly match simulation
  public  static int week;

    @SuppressWarnings("static-access")
    public GUI() {
      
	//no more than 4096 Players for  tennis match simulation (1vs1game)
	Player[] Players = new Player[4096];	
	for(int i=0;i<4096;i++)
    	Players[i] = new Player();
        this.j=0;
        this.week=1;
        Game tennis_game= new Game();
  
        setLayout(new BorderLayout());
        //text field for entering number of Players 
        textField = new JTextField(30);
        //text field for entering tennis Player name
        textField2 = new JTextField(30);
        //text field for entering tennis Player rank
        textField3 = new JTextField(30);
        //text field for entering games per season
        textField4 = new JTextField(30);
        
        //button for adding manually Player details
        JButton button0 = new JButton();
        button0.setText("ADD");
        button0.setCursor(Cursor.getDefaultCursor());
        button0.setMargin(new Insets(1,1,1,1));
        
    /* ADD button listener-verifies that data entered by user are legit and if so input data inside the system */ 
	button0.addActionListener(
  	new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
	//Player number ( tennis match is a 1 vs 1 game , default value is 1)        
	int no=0;
	//Player name
        String name=null;
	//Player initial skill level rank
        int rank=0;
	//games played by the Player per season ( each Player will play with every Player in the roster)
        int games=0;
	boolean correct=false;
	/* input validation : non null values entered as Player name and remaining values are integer */
	try{   
		no=Integer.parseInt(textField.getText());
        	name=textField2.getText();
        	rank=Integer.parseInt(textField3.getText());
        	games=Integer.parseInt(textField4.getText());    
        	if (name != null && !name.isEmpty()){
		   correct=true;
	           System.out.println("Not empty");}
	
	          }catch (NumberFormatException ex) {
	           System.out.println("Input is not a valid integer");
	           correct=false;
	         }
	//successful input validation
        if (correct==true){textArea.setText("New Player added");
	//store data inside array
        Players[j].setNo(no);
        Players[j].setName(name);
        Players[j].setWins(0);
        Players[j].setLosses(0);
        Players[j].setRank(rank);
        Players[j].setGames(games);
	//keep array length
	j++;
	 }
	//input validation failed - inform the user so to enter valid values
        else {	
	textField.setText("Should be an integer value , please try again");
	textField2.setText("Should be a string value , please try again");
	textField3.setText("Should be an integer value , please try again");
	textField4.setText("Should be an integer value , please try again");
         }
	}
 	}
	);   
        
	//edit  button ,  for allowing manual data overriding
        JButton button01 = new JButton();
        button01.setText("EDIT");
        button01.setCursor(Cursor.getDefaultCursor());
        button01.setMargin(new Insets(1,1,1,1));
        /* EDIT button listener-
	modify  details of a specific Player
	*/ 
      	button01.addActionListener(
  	new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
        System.out.println("Edit Player details(records are update based on provided Player name)");
    
        int pos=-1;
        boolean cor=false;
        
	for(int i=0;i<j;i++){
        //locate specific tennis Player based on his/her name
	if( (textField2.getText()!=null)&&(Players[i].getName().equals(textField2.getText()) ) )
        	{pos=i;cor=true;break;}
         	
	 }
	//apply input validation , to see if values entered are valid so to replace existing ones
        if (cor==true){
		//number of Players
		int no1=0;
		//Player rank
        	float rank1=0;
		//Player games per season
        	int games1=0;
		boolean correct1;
		correct1=true;
  		/* input validation : non null values entered as Player name and remaining values are integer */
		try{   
			no1=Integer.parseInt(textField.getText());
        		rank1=Float.parseFloat(textField3.getText());
        		games1=Integer.parseInt(textField4.getText());    
	       	}catch (NumberFormatException ex) {
	           	System.out.println("Input is not a valid integer");
	           	correct1=false;	        
		  	}
	       //successful input validation - update specified Players record
               if (correct1==true){
		Players[pos].setNo(no1);
        	Players[pos].setRank(rank1);
        	Players[pos].setGames(games1); textArea.setText("Records updated");}
              } 
	   if (cor==false){ textArea.setText("Player does not exist. Records failed to be  updated .");}      
				}
  			}
					);

        //create button status button
        JButton button02 = new JButton();
        button02.setText("STATUS");
        button02.setCursor(Cursor.getDefaultCursor());
        button02.setMargin(new Insets(1,1,1,1));
	/*STATUS button listener -
	Present the status of operations to the user including the week the Players are on, current standings, and skill levels at request.
	User can choose to find details about a specific Player by typing his/her name or can leave an empty field for displaying
	status for all existing Players	
	 */
       	button02.addActionListener(
  	new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
	       
	
	/* Display Status For All Existing Tennis Players */
       
	//Sort Players array based on rankings
        float ia[];   
	ia = new float[4096]; 
	for(int i=0;i<j;i++){
    	ia[i]=Players[i].getRank();}
	
	//Sort in descending order(bubble sort) the Player rankings and store inside ia[] array 
        float temp = 0;  
         for(int i=0; i < j; i++){  
                 for(int k=1; k < (j-i); k++){  
                          if(ia[k-1] < ia[k]){  
                                 //swap elements  
                                 temp = ia[k-1];  
                                 ia[k-1] = ia[k];  
                                 ia[k] = temp;  
                         }  
                          
                 }  
         }  
  	String stat=".........WEEK "+Integer.toString(week)+"........\n";
	// Display the tennis Player details , sorted based on rankings
	//Two loops to check case where multiple tennis Player have the same rating ( but no need to display them multiple times)
	for (int ii=0; ii<j ; ii++){
	 for (int iii=0; iii<j ; iii++){
	    if ( (ia[ii]==Players[iii].getRank()) && (!stat.contains(Players[iii].getName())) )
		{stat=stat+"Player name: "+Players[iii].getName()+" , Rank: "+Float.toString(Players[iii].getRank())+ ", Wins: "+Integer.toString(Players[iii].getWins())+" , Losses: "+Integer.toString(Players[iii].getLosses())+"\n";}
					

	  } 
	 textArea.setText(stat);
	}	
	
	//Case when the user , search for status of a specific tennis Player
        String info=".........WEEK "+Integer.toString(week)+"........\n";
	for(int i=0;i<j;i++){
        //locate specific tennis Player based on his/her name
	if( (textField2.getText()!=null)&&(Players[i].getName().equals(textField2.getText()) ) ) {
	info=info+"Player name: "+Players[i].getName()+" , Rank: "+Float.toString(Players[i].getRank())+ ", Wins: "+Integer.toString(Players[i].getWins())+" , Losses: "+Integer.toString(Players[i].getLosses())+"\n";
	textArea.setText(info);
		}
       	 }
    					}
  			}
					);
	//Button for simulating one weeks matches
	JButton buttonA = new JButton();
        buttonA.setMargin(new Insets(5,5,5,5));
        buttonA.setEnabled(false);
        buttonA.setBorderPainted(false);
        JButton button1 = new JButton();
        button1.setText("ONE WEEK SIMULATION");
        button1.setCursor(Cursor.getDefaultCursor());
        button1.setMargin(new Insets(20,20,20,20));
       	/* Simulate one week matches and present the results to user*/
	button1.addActionListener(
  	new ActionListener() {
    	public void actionPerformed(ActionEvent e) {     
        System.out.println("Starting weekly simulation");
	String resu=".........WEEK "+Integer.toString(week)+"........\n";
	if ( week < j) {
	 for(int m=0; m< j-week; m++){
	   	 // Ra and Rb are current ELO ratings for the two opponents 
		float Ra = Players[m].getRank(), Rb = Players[m+week].getRank(); 
		// K , constant value for Elo formula calculation ( default value -> 30)	
		int K = 30; 
		//retrieve modified rankings 
		float new_rates[]=tennis_game.EloRating(Ra, Rb, K); 
		//System.out.print("\nNew skill for A:" +new_rates[0]+" New skill for B:"+new_rates[1]+" \n"); 
		
		//first Player won update wins
		if (new_rates[0] > Players[m].getRank() )
		{Players[m].setWins(Players[m].getWins()+1);
		resu=resu+"(Win) "+Players[m].getName()+" VS  "+Players[m+week].getName()+ " (Loss) \n";}		
		//case first Player lost , hence update losses
		else			
		{Players[m].setLosses(Players[m].getLosses()+1);
		resu=resu+"(Loss) "+Players[m].getName()+" VS  "+Players[m+week].getName()+ " (Win) \n";}
		//update first Player rank
		Players[m].setRank(new_rates[0]);

		//second Player won update wins
		if (new_rates[1] > Players[m+week].getRank() )
		Players[m+week].setWins(Players[m+week].getWins()+1);
		else	
		//second Player lost update losses		
		Players[m+week].setLosses(Players[m+week].getLosses()+1);
		//update second Player rank
		Players[m+week].setRank(new_rates[1]);

				}
	week++;
	textArea.setText(resu);
			}
	else
	   textArea.setText("Season came to an end ");
    					}
  			}
					);
	//Create full season button
        JButton button2 = new JButton();
        button2.setText("FULL SEASON SIMULATION");
        button2.setCursor(Cursor.getDefaultCursor());
        button2.setMargin(new Insets(20,20,20,20));
	/* Simulate full season matches and present the results to user*/
	button2.addActionListener(
  	new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
	String res="....\n"+".....SEASON CAME TO AN END - RESULTS....\n";
	if ( week < j) {
        System.out.println("Starting season simulation");
	for(int k=1; k<j; k++){
	 for(int m=0; m< j-k; m++){
	   	// Ra and Rb are current ELO ratings for the two opponents 
		float Ra = Players[m].getRank(), Rb = Players[m+k].getRank(); 
		// K , constant value for Elo formula calculation ( default value -> 30)	
		int K = 30; 
		//retrieve modified rankings 
		float new_rates[]=tennis_game.EloRating(Ra, Rb, K); 
		System.out.print("\nNew skill for A:" +new_rates[0]+" New skill for B:"+new_rates[1]+" \n"); 
		
		//first Player won update wins
		if (new_rates[0] > Players[m].getRank() ){
		Players[m].setWins(Players[m].getWins()+1);
		res=res+"(Win) "+Players[m].getName()+" VS  "+Players[m+k].getName()+ " (Loss) \n";}
		
		//case first Player lost , hence update losses
		else{
		Players[m].setLosses(Players[m].getLosses()+1);
		res=res+"(Loss) "+Players[m].getName()+" VS  "+Players[m+k].getName()+ " (Win) \n";}
		
		//update first Player rank
		Players[m].setRank(new_rates[0]);

		//second Player won update wins
		if (new_rates[1] > Players[m+k].getRank() )
		Players[m+k].setWins(Players[m+k].getWins()+1);
		else
		  
		//second Player lost update losses		
		Players[m+k].setLosses(Players[m+k].getLosses()+1);
		
		//update second Player rank
		Players[m+k].setRank(new_rates[1]);
				}
	     //simulate next week matches			
	     week++;}
	}

	//output full season results
	textArea.setText(res);
    					}
  			});

	//create open file button
        JButton button3 = new JButton();
        button3.setText("OPEN FILE");
        button3.setCursor(Cursor.getDefaultCursor());
        button3.setMargin(new Insets(1,1,1,1));
	/* User is able to import data from either a .txt or .csv file */
	button3.addActionListener(
  	new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
   
        JFileChooser fc;
        
	//Create a file chooser
    fc = new JFileChooser();
	int returnVal = fc.showOpenDialog(null);
	File file=null;
	
	//open the file
	if (returnVal == JFileChooser.APPROVE_OPTION) {
         file = fc.getSelectedFile();
         System.out.println("File to be open: " + file.getName() + ". \n" );}
	else {
        System.out.println("File opening cancelled\n");
        return;
          }
    //check file extension,  only  .txt and .csv are allowed
 	String extension = null;
    	String s = file.getName();
    	int i = s.lastIndexOf('.');
   	if (i > 0 &&  i < s.length() - 1) {
        	extension = s.substring(i+1).toLowerCase();
    		}
	//verify file type
    	String type = null; 
        if (extension.equals("txt")) {
            	type = "Text file";
        } else if (extension.equals("csv")){
            	type = "Comma separated values file";
        }
	else {  type = "Invalid";  }    		
        // whether extension is valid open the file
        if (extension.equals("txt")||extension.equals("csv") ) {
	       try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				
			 textArea.setText(""+type+"opened for reading");
		         String[] inp = line.toString().split(",");
		      	 int no3=0;
        		 String name3=null;
        		 float rank3=0;
        		 int games3=0;
        		 boolean correct;
        		 correct=false;
        		 
		// do some input validation , make sure that imported line contains legit data
		// if line data is not valid skip to next line
  		try{  	
		      	 no3=Integer.valueOf(inp[0]);
        		 name3=inp[1];
        		 rank3=Float.valueOf(inp[2]);
        		 games3=Integer.valueOf(inp[3]);  

        	  	 if (name3 != null && !name3.isEmpty()){
		    	   correct=true;
	                   System.out.println("Not empy");
        	      }
	
	          }catch (NumberFormatException ex) {
	           	 System.out.println("Input is not a valid integer");
	          	 correct=false;
	          }
	
  		if (correct==true){
  		  textArea.setText("Data imported from file");

  		  //update Players array with the imported data
  		  Players[j].setNo(no3);
          Players[j].setName(name3);
          Players[j].setWins(0);
          Players[j].setLosses(0);
          Players[j].setRank(rank3);
          Players[j].setGames(games3);
          j++;

  		}
         else {
           textArea.setText("Data incorrect, skip to next line");
         }
			}
			fileReader.close();

	        } catch (IOException ef) {
	          ef.printStackTrace();
	        }
        }
        else {  textArea.setText("Invalid FileType , please choose only .txt or .csv files");}
    	}
  			 });
        
	    //create labels for the GUI components
        JLabel textFieldLabel = new JLabel("Number of Player" + ": ");
        textFieldLabel.setLabelFor(textField);
        JLabel textFieldLabel2 = new JLabel("Name of Player" + ": ");
        textFieldLabel2.setLabelFor(textField2);
        JLabel textFieldLabel3 = new JLabel("Rank of Player" + ": ");
        textFieldLabel3.setLabelFor(textField3);
        JLabel textFieldLabel4 = new JLabel("Number of Games in a Season" + ": ");
        textFieldLabel4.setLabelFor(textField4);
        
        JLabel btnALabel = new JLabel("");
        btnALabel.setLabelFor(buttonA);
        JLabel btn0Label = new JLabel("Insert Player details" + ": ");
        btn0Label.setLabelFor(button0);
        JLabel btn01Label = new JLabel("Edit Player details" + ": ");
        btn01Label.setLabelFor(button01);
        JLabel btn02Label = new JLabel("Show status" + ": ");
        btn02Label.setLabelFor(button02);
        JLabel btn1Label = new JLabel("");
        btn1Label.setLabelFor(button1);      
        JLabel btn2Label = new JLabel("");
        btn2Label.setLabelFor(button2);
        JLabel btn3Label = new JLabel("Open File");
        btn3Label.setLabelFor(button3);
        
        //position the text controls and the labels.
        JPanel textControlsPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        textControlsPane.setLayout(gridbag);
        
        //create some array of labels , buttons , textfields
        JLabel[] labels = {textFieldLabel, textFieldLabel2, textFieldLabel3, textFieldLabel4,btn0Label,btn01Label,btn02Label ,btn3Label,btnALabel,btn1Label,btn2Label};
        JTextField[] textFields = {textField, textField2, textField3, textField4};
        JButton[] buttons= {button0, button01, button02,button3, buttonA,button1, button2 };
        
        //add GUI components ( e.g text fields , buttons)        
        addGUIComponents(buttons,labels, textFields, gridbag, textControlsPane);
          c.gridwidth = GridBagConstraints.REMAINDER;
          c.anchor = GridBagConstraints.WEST;
          c.weightx = 1.0;
          //configure the border
          textControlsPane.setBorder(
              BorderFactory.createCompoundBorder(
              BorderFactory.createTitledBorder("SIMULATION OPTIONS"),
              BorderFactory.createEmptyBorder(50,50,50,50)));

        //create a text area for displaying output back to the user
        textArea = new JTextArea("This is an editable JTextArea to OUTPUT MATCHES SIMULATION RESULTS "    );
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        //create a scroll pane
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(250, 250));
        areaScrollPane.setBorder(
            BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("SIMULATION OUTPUT"),
            BorderFactory.createEmptyBorder(5,5,5,5)),
                areaScrollPane.getBorder()));
        
        //put all the components together
        JPanel leftPane = new JPanel(new BorderLayout());
        leftPane.add(textControlsPane, 
        BorderLayout.PAGE_START);
        leftPane.add(areaScrollPane,
        BorderLayout.CENTER);
        add(leftPane, BorderLayout.CENTER);  

    }

    private void addGUIComponents(JButton[] buttons,JLabel[] labels,
                                  JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;

        //add the textfields along with their labels
        for (int i = 0; i < 4; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE; 
            c.fill = GridBagConstraints.NONE;      
            c.weightx = 0.0;                       
            container.add(labels[i], c);

            c.gridwidth = GridBagConstraints.REMAINDER;     
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
		
        //add the buttons along their labels
        for (int i = 0; i < 7; i++) {
          c.gridwidth = GridBagConstraints.RELATIVE; 
          c.fill = GridBagConstraints.NONE;      
          c.weightx = 0.0;                       
          container.add(labels[i+4], c);
          //add buttons//
          c.gridwidth = GridBagConstraints.REMAINDER;    
          c.fill = GridBagConstraints.HORIZONTAL;
          c.weightx = 2.0;
          container.add(buttons[i], c);
        }
    }

    /* create and show the GUI */
    private static void makeGUI() {
        JFrame frame = new JFrame("Team Season Predictor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GUI());
        //display the GUI
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        /* start the application */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
		 UIManager.put("swing.boldMetal", Boolean.FALSE);
		 makeGUI();
            }
        });
    }
}
