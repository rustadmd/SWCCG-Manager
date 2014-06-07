package swccgManager;

import swccgManager.GUI.MainWindow;
import swccgManager.Models.FullCardSet;


/**
 * @author Mark Rustad
 * @version .01
 * @date May 1, 2014
 */

public class Main {

	private static Settings s;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		initialSetup();
	}
	
	private static void initialSetup()
	{
		//Initiallize all settings
		s = new Settings();
		//FullCardSet fcs = s.fcs;
		
		
		//Setup intial GUI
		MainWindow mw = new MainWindow();
	}
	
	
}
