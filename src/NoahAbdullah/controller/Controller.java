package NoahAbdullah.controller;

import NoahAbdullah.view.Fantasy;
import NoahAbdullah.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

/**
 * @author Noah-Vincenz Noeh and Mohammad Abdullah
 * @version 1.0
 */
public class Controller {
	
	private Fantasy fantasy;
	private Squad squad;
	
	/**
	 * Controller class.
	 * Generates the model squad.
	 * Sets the listener for the JComboBox.
	 * @param f instance of fantasy class
	 * @param s instance of squad class
	 */
	public Controller(Fantasy f, Squad s){
		fantasy = f;
		squad = s;
		squad.generateSquad();
		fantasy.setComboBoxListener(new comboListener());		
	}
	
	/**
	 * This class implements an ActionListener for the JComboBox in the fantasy JFrame.
	 * Obtains the selected value in the JComboBox.
	 * Moves the "Select your Formation" entry in JComboBox to the bottom.
	 * Creates the formation according to the selection.
	 * sets Listeners to the remaining JComponents.
	 * @author Noah-Vincenz Noeh and Mohammad Abdullah
	 * @version 1.0
	 */
	public class comboListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = fantasy.getSelectedFormation();
			fantasy.moveInitial();
			fantasy.remove();
			createFormation(str);
			fantasy.setButtonListener(new buttonListener());
			fantasy.setTextListener(new textListener());
		}
		
		/**
		 * Method to create a formation that is selected in the JComboBox.
		 * Splits the formation into an array of 3 integers.
		 * Loops through all the players in the model package.
		 * Adds a player panel for every player in the squad to the Fantasy JFrame.
		 * Layouts the panels according to the selected formation.
		 * Adds the remaining players to the bench.
		 * @param s String selected in the JComboBox (formation)
		 */
		private void createFormation(String s) {
			if (s == fantasy.getStart()){ //if JComboBox selection is "Select your formation", return
				return;
			}
			else {
				String[] numberOfPlayers = s.split("-"); 
				int onPitchGoalie = 1; //there is always one Goalkeeper on the pitch
				int onPitchDefenders = Integer.parseInt(numberOfPlayers[0]); 
				int onPitchMidfielders = Integer.parseInt(numberOfPlayers[1]);
				int onPitchStrikers = Integer.parseInt(numberOfPlayers[2]);
				for (int j = 0; j < squad.getSquad().size(); j++){ 
					Player o = squad.searchByID(j);	 
					int tempID = o.getID();
					String tempName = o.getName();
					String tempPath = o.getImagePath();	
					if(o instanceof Goalkeeper){
						if (onPitchGoalie == 1){ //if no Goalkeeper on pitch, add one
							fantasy.addGoalie(tempID,tempName,tempPath);
							onPitchGoalie--;
						}else { //if Goalkeeper already on pitch, add the second to the bench
							fantasy.addBench(tempID,tempName,tempPath);
						}	
					}
					else if(o instanceof Defender){ 
						if (onPitchDefenders > 0 ){
							fantasy.addDefender(tempID, tempName, tempPath);
							onPitchDefenders--;
						}
						else if (onPitchDefenders == 0){ 
							fantasy.addBench(tempID,tempName,tempPath);
						}
						fantasy.update(tempID);
					}
					else if(o instanceof Midfielder){
						if (onPitchMidfielders !=0){
							fantasy.addMidfielder(tempID, tempName, tempPath);
							--onPitchMidfielders;	
						}
						else{
							fantasy.addBench(tempID, tempName, tempPath);
						}
					}
					else if(o instanceof Striker){
						if(onPitchStrikers > 0){
							fantasy.addStriker(tempID, tempName, tempPath);
							onPitchStrikers--;
						}else{
							fantasy.addBench(tempID, tempName, tempPath);
						}
					}
				}
			}			
		}			
	}

	/**
	 * This class implements an ActionListener for the JButton in the fantasy JFrame.
	 * Creates and displays a JFileChooser.
	 * Obtains the source of ActionEvent, casts it to JButton and gets the index of this source JButton from JButton ArrayList in the model package.
	 * Gets all corresponding JComponents at this index from all the ArrayLists.
	 * Gets the player at index(sourceID).
	 * @author Noah and Mohammad Abdullah
	 * @version 1.0
	 */
	public class buttonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser(); 
			fc.showOpenDialog(null);
			JButton source = (JButton) e.getSource(); 
			int sourceID = fantasy.getImagePrompts().indexOf(source); 
			JPanel sourcePanel = fantasy.getPanels().get(sourceID);  
			JTextField sourceTextField = fantasy.getPlayerTags().get(sourceID);
			Player sourcePlayer = squad.searchByID(sourceID);
			try{
				String imagePath = fc.getSelectedFile().getPath();
				File sourceImageFile = fc.getSelectedFile();
				ImageIcon image = new ImageIcon(imagePath);
				source.setText("");
				source.setIcon(image);			
				String fileName = sourceImageFile.getName();
				String lowercasePlayerNameToDisplay = fileName.replaceAll(".jpg", "");
				final String properNameToDisplay = lowercasePlayerNameToDisplay.substring(0,1).toUpperCase()+lowercasePlayerNameToDisplay.substring(1);
				sourceTextField.setText(properNameToDisplay);
				sourcePlayer.setName(properNameToDisplay);
				fantasy.update(sourceID);
			}
			catch(NullPointerException npe){
				return;
			}
			sourcePanel.repaint();
			}
	}
	
	/**
	 * This class implements an ActionListener for the JTextField in the fantasy JFrame.
	 * Obtains the source of ActionEvent, casts it JTextField and gets the index of this source JTextField from JTextField ArrayList in the model package.
	 * Gets the Player at index(sourceID).
	 * Obtains the text input in the JTextField in the Fantasy JFrame.
	 * Sets the text of the JTextField and the name of the Player in Focus to the text input in the JTextField.
	 * @author Noah-Vincenz Noeh and Mohammad Abdullah
	 * @version 1.0
	 */
	public class textListener implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField sourceField = (JTextField) e.getSource(); 
			int sourceID = fantasy.getPlayerTags().indexOf(sourceField);
			String textInField = sourceField.getText();
			Player sourcePlayer = squad.searchByID(sourceID);
			sourcePlayer.setName(textInField);
			System.out.println(textInField);
		}	
	}
}
	