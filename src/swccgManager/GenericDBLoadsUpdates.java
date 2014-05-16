/**
 * @author Mark Rustad
 * @version .01
 * @date May 3, 2014
 */
package swccgManager;

/**
 * @author Mark
 *
 */

import java.sql.*;
public class GenericDBLoadsUpdates {
	
	Connection db;
	
	public GenericDBLoadsUpdates()
	{
		DatabaseConnector dbc = new DatabaseConnector();
		db = dbc.getConnection();
		
		//Test image loader
		
		ImageManager im = new ImageManager(db);
		GenericSQLQueries.getCardVitals(db);
		//Card cardTest = new Card(db, 0);
		FullCardSet fcs = new FullCardSet(db);
		
		//GenericSQLQueries.getCollectionList(db);
		
		dbc.closeDB();
		
		closeDBLoadsUpdates();
	}
	
	/**
	 * Closes the open connection
	 */
	public void closeDBLoadsUpdates()
	{
		DatabaseConnector.closeDB(db);
	}
	

}
