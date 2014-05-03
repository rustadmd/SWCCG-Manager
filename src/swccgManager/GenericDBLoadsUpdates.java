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
		dbc.closeDB();
	}
	
	/**
	 * Closes the open connection
	 */
	public void closeDB()
	{
		DatabaseConnector.closeDB(db);
	}

}
