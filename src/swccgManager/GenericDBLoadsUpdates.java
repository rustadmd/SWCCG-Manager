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
		
		ImageManager im = new ImageManager();
		im.getImageFileLocation(db);
		
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