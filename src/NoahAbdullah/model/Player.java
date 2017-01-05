package NoahAbdullah.model;

/**
 * Player class.
 * Sets the unique ID, a name, role and image path.
 * @author Noah-Vincenz Noeh and Mohammad Abdullah
 * @version 1.0
 */
public class Player {

	private int playerID; 
	private String playerName;
	private String imagePath;
	
	/**
	 * Constructor for the Player class.
	 * @param integer playerID
	 */
	public Player(int id){
		playerName = this.getClassName(); //default playerName is its type
		playerID = id;
		imagePath = "None"; //default image path is set to this
	}

	/**
	 * method to set the image path of a player.
	 * @param imagePath
	 */
	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}
	
	/**
	 * Getter method for this Player's imagePath.
	 * @return String imagePath
	 */
	public String getImagePath(){
		return imagePath;
	}
	
	/**
	 * Setter method for the Player's name.
	 * @param name 
	 */
	public void setName(String name){
		this.playerName = name;
	}

	/**
	 * Getter method for the Player's name.
	 * @return String Player's name
	 */
	public String getName(){
		return playerName;
	}
	
	/**
	 * Method at override to return String representation of the Player's name.
	 */
	@Override
	public String toString(){
		return playerName;
	}
	
	/**
	 * method to get this player's class name to determind the role of this.Player.
	 * @return String name of the class
	 */
	public String getClassName() {
		String classNameExt = getClass().toString();
	  	String className = classNameExt.split("\\.")[2]; //remove package extension
	  	return className;
	}

	/**
	 * method to get this player's unique ID.
	 * @return integer playerID
	 */
	public int getID() {
		return playerID;
	}	
}
