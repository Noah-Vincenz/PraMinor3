package NoahAbdullah;

import NoahAbdullah.controller.Controller;
import NoahAbdullah.model.Squad;
import NoahAbdullah.view.Fantasy;

public class Main {
	
	public static void main (String args[]) {
		
		Fantasy fantasy = new Fantasy();
		Squad squad = new Squad();
		Controller controller = new Controller(fantasy,squad);
	}
}
