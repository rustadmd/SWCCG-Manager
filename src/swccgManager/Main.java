package swccgManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import swccgManager.Database.GenericSQLQueries;
import swccgManager.Database.ImageManager;
import swccgManager.Database.SqlUtilities;
import swccgManager.GUI.MainWindow;
import swccgManager.Models.Card;


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
		//testing();
		//Setup intial GUI
		MainWindow mw = new MainWindow();
		
		
		
	}
	
	private static void testing()
	{
		SqlUtilities su = new SqlUtilities();
		Connection swdb = su.getDbConnection();
		ImageManager im = new ImageManager(swdb);
		im.testImagePathsValid();
		
	}
}
