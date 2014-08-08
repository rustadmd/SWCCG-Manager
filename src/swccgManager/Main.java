package swccgManager;

import java.sql.Connection;

import swccgManager.Database.ImageManager;
import swccgManager.Database.SqlUtilities;
import swccgManager.GUI.MainWindow;


/**
 * @author Mark Rustad
 * @version .01
 * @date May 1, 2014
 */

public class Main {

	@SuppressWarnings("unused")
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
		//testing();
		//Setup intial GUI
		@SuppressWarnings("unused")
		MainWindow mw = new MainWindow();
		
		
		
	}
	
	@SuppressWarnings("unused")
	private static void testing()
	{
		SqlUtilities su = new SqlUtilities();
		Connection swdb = su.getDbConnection();
		ImageManager im = new ImageManager(swdb);
		im.testImagePathsValid();
		
	}
}
