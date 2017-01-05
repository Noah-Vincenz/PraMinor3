package NoahAbdullah.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Fantasy class for the view package.
 * @author Noah-Vincenz Noeh and Mohammad Abdullah
 * @version 1.0
 */
public class Fantasy extends JFrame {
	
	private JComboBox<String> jcb;
	private JTextField playerTag;
	private JButton imagePrompt;
	
	private JPanel header;
	private JPanel goal;
	private JPanel defence;
	private JPanel midfield;
	private JPanel attack;
	private JPanel bench;
	private JPanel placeholder;
	
	private String start;
	private String selectedFormation;
	
	private ArrayList<String> formations = new ArrayList<String>();
	private ArrayList<JPanel> panels = new ArrayList<JPanel>();
	private ArrayList<JButton> imagePrompts = new ArrayList<JButton>();
	private ArrayList<JTextField> playerTags = new ArrayList<JTextField>();
	
	/**
	 * Constructor for the Fantasy class.
	 * Every instance of JFrame is named to "Fantasy Football".
	 * Calls the method to create the required JComponents.
	 * Creates an array of the optional formations.
	 * Adds every Object from formations array to JComboBox.
	 */
	public Fantasy() {
		super("Fantasy Football"); 
		createWidgets(); 
		String[] formationArray = {start,"4-4-2", "4-3-3", "3-5-2", "5-3-2", "3-4-3", "4-5-1"};
		for(String s : formationArray) { 
			formations.add(s);
			jcb.addItem(s);
		}
	}	
	
	/**
	 * Method to create all the required JComponents.
	 * Creates JComboBox for formations.
	 * Creates appropriate panels for every area on the pitch i.e Goal, Defence, Midfield, Attack.
	 * Adds created JPanels to the JFrame window.
	 */
	public void createWidgets() {	
		this.start = "Select your Formation"; //default value of JComboBox set to "start" field
		this.jcb = new JComboBox<String>();		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		header = new JPanel (new FlowLayout());
		add(header);
		header.add(jcb);
		goal = new JPanel (new FlowLayout());
		add(goal);
		defence = new JPanel (new FlowLayout()); 
		add(defence);
		midfield = new JPanel (new FlowLayout());
		add(midfield);	
		attack = new JPanel (new FlowLayout());
		add(attack);	
		bench = new JPanel (new FlowLayout());
		add(bench);
		pack();
	    setSize(700,700);
	    setVisible(true);		
	}	
	
	/**
	 * Method to add a placeholder JPanel to the Goalkeeper JPanel in the JFrame.
	 */
	public void addGoalie(int i, String name, String path){
		goal.add(playerPanel(i,name,path));
	}
	
	/**
	 * Method to add a placeholder JPanel to the Defence JPanel in the JFrame.
	 */
	public void addDefender(int i, String name, String path){
		defence.add(playerPanel(i,name,path));
	}

	/**
	 * Method to add a placeholder JPanel to the Midfield JPanel in the JFrame.
	 */
	public void addMidfielder(int i, String name, String path){
		midfield.add(playerPanel(i,name,path));
	}

	/**
	 * Method to add a placeholder JPanel to the Attack JPanel in the JFrame.
	 */
	public void addStriker(int i, String name, String path){
		attack.add(playerPanel(i,name,path));
	}
	
	/**
	 * Method to add a placeholder JPanel to the Bench JPanel in the window.
	 */
	public void addBench(int i, String name, String path){
		bench.add(playerPanel(i,name,path));
	}
	
	/**
	 * Method to create a JPanel for a Player.
	 * Creates a new JPanel and adds to the ArrayList of JPanels.
	 * Creates a new JButton (imagePrompt), adds to the ArrayList of JButtons and adds to the playerPanel @NORTH.
	 * Creates a new JTextField (playerTag), adds to the ArrayList of JTextFields and adds to the playerPanel @SOUTH.
	 * @param i player's ID
	 * @param name player's name
	 * @param path player's imagePath
	 * @return JPanel with JButton @centre and JTextfield @bottom
	 */
	public JPanel playerPanel(int i, String name, String path){
			placeholder = new JPanel(new BorderLayout());
			playerTag = new JTextField (name);
			imagePrompt = new JButton();
			placeholder.add(imagePrompt, BorderLayout.NORTH);
			placeholder.add(playerTag, BorderLayout.SOUTH);
			imagePrompt.setText("+");
			placeholder.setName(Integer.toString(i)); //Sets the playerID as the name of the placeholder JPanel 
			imagePrompt.setName(Integer.toString(i));  // Sets the playerID as the name of the imagePrompt JButton 
			playerTag.setName(Integer.toString(i)); // Sets the playerID as the name of the playerTag JTextField 
			panels.add(placeholder);
			imagePrompts.add(imagePrompt);
			playerTags.add(playerTag);
			return placeholder;
	}
	
	/**
	 * Method to update a specific player panel.
	 * Uses the integer passed as a parameter as index and obtains the JPanel from the ArrayList of player JPanels.
	 * @param id of the JPanel in focus
	 */
	public void update(int i){
		panels.get(i).repaint();
		panels.get(i).revalidate();
	}
	
	/**
	 * Method to remove all player panels before another formation is shown.
	 */
	public void remove(){
		goal.removeAll();
		defence.removeAll();
		midfield.removeAll();
		attack.removeAll();
		bench.removeAll();
		repaint();
	}

	/**
	 * Setter method, to set ActionListener for the JCombobox.
	 * @param e ActionListener
	 */
	public void setComboBoxListener(ActionListener e){
		jcb.addActionListener(e);
	}
	
	/**
	 * Setter method, to set ActionListener for every JButton in imagePrompts ArrayList.
	 * @param e ActionListener
	 */
	public void setButtonListener(ActionListener e){
		for(JButton ip : imagePrompts){
			ip.addActionListener(e);
		}
	}
	
	/**
	 * Setter method, to set ActionListener for every JTextField in playerTags ArrayList.
	 * @param e ActionListener
	 */
	public void setTextListener(ActionListener e){
		for (JTextField tf : playerTags ){
			tf.addActionListener(e);
		}
	}
	
	/**
	 * Getter method, for the currently selected value in the JComboBox.
	 * @return selected formation
	 */
	public String getSelectedFormation() {
		selectedFormation = (String) jcb.getSelectedItem();
		return selectedFormation;	
	}

	/**
	 * Getter method, for the default value of the JComboBox ("Select your formation").
	 * @return "start"
	 */
	public String getStart() {
		return start;
	}
	
	/**
	 * Getter method for the ArrayList of all the Player JPanels.
	 * @return ArrayList of Player JPanels
	 */
	public ArrayList<JPanel> getPanels(){
		return panels;
	}
	
	/**
	 * Getter method for the ArrayList of all the imagePrompt JButtons.
	 * @return ArrayList of imagePrompt JButtons
	 */
	public ArrayList<JButton> getImagePrompts(){
		return imagePrompts;
	}
	
	/**
	 * Getter method for the ArrayList of all the playerTags JTextfields.
	 * @return ArrayList of playerTags JTextfields
	 */
	public ArrayList<JTextField> getPlayerTags(){
		return playerTags;
	}
	
	/**
	 * Method to move the default value ("Choose your formation") to the bottom of the JComboBox.
	 */
	public void moveInitial(){
		jcb.removeItem(start);
		jcb.addItem(start);
	}	
}