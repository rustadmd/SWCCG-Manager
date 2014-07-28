package swccgManager;

import java.sql.ResultSet;
import java.sql.SQLException;

import swccgManager.Database.GenericSQLQueries;
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
		
		
		//Setup intial GUI
		MainWindow mw = new MainWindow();
		
		//testing();
		
	}
	
	private static void testing()
	{
		//Card testCard = new Card(81);
		//System.out.println(testCard.getCardName() );
		
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet testResults = gsq.getCardCollectionStats(115, "Mark");
		ResultSet testCardResults = gsq.getCardVitals(81);
		try {
			//while(testResults.next())
			{
				String collectionName = testResults.getString(1);
				System.out.println("Collection Name: " + collectionName);
			}
			testResults.close();
			
			String cardName = testCardResults.getString("CardName");
			System.out.println("CardName: " + cardName);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
