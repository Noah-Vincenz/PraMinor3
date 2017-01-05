package NoahAbdullah.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Squad class for the model package.
 * @author Noah-Vincenz Noeh and Mohammad Abdullah
 * @version 1.0
 */
public class Squad {
	
	private List<Player> squadList;
	
	/**
	 * Constructor for the Squad class.
	 * Creates an ArrayList of Players
	 */
	public Squad() {
		this.squadList = new ArrayList<Player>();
	}
	
	/**
	 * Method to search for a player by ID passed as a parameter.
	 * Loops through the Squad ArrayList.
	 * if ID matches, sets foundPlayer to the obtained player.
	 * @param id integer playerID
	 * @return Player stored in local field variable foundPlayer
	 */
	public Player searchByID(int id){
		Player foundPlayer = null;
		for(Player P : squadList) { 
			if(P.getID() == id) { 
				foundPlayer = P;
			}
		}
		return foundPlayer; 
	}
	
	/**
	 * Method to add the Player passed as a parameter, to the Squad ArrayList.
	 * @param P Player
	 */
	public void addToSquad(Player P) {
		squadList.add(P);	
	}
	
	/**
	 * Getter method for the Squad ArrayList.
	 * @return ArrayList of every player in the squad
	 */
	public List<Player> getSquad() {
		return squadList;
	}
	
	/**
	 * Method to generate a squad consisting of 2 Goalkeepers, 5 Defenders, 5 Midfielders and 3 Strikers.
	 */
	public void generateSquad(){
		int positionID = 0;
		while(positionID<2) {
			Goalkeeper g = new Goalkeeper(positionID); 
			addToSquad(g);
			positionID++;
		}
		while (positionID<7) {
			Defender d = new Defender(positionID); 
			addToSquad(d);
			positionID++;
		}
		while (positionID<12) {
			Midfielder m = new Midfielder(positionID); 
			addToSquad(m);
			positionID++;
		}
		while(positionID<15) {
			Striker s = new Striker(positionID); 
			addToSquad(s);
			positionID++;
		}
	}
}
