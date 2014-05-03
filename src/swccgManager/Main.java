package swccgManager;
/**
 * @author Mark Rustad
 * @version .01
 * @date May 1, 2014
 */

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseConnector dbc = new DatabaseConnector();
		GenericDBLoadsUpdates test = new GenericDBLoadsUpdates();
		dbc.closeDB();

	}

}
