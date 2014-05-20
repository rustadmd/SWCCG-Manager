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

	private SqlUtilities sqlUtil;
	
	public GenericDBLoadsUpdates()
	{
		sqlUtil = new SqlUtilities();
		//Test image loader
		
		Connection swdb = sqlUtil.getDbConnection();
		//Card cardTest = new Card(db, 0);
		FullCardSet fcs = new FullCardSet(swdb);
		
		//GenericSQLQueries.getCollectionList(db);
		
		sqlUtil.closeDB(swdb);
	}
	
}
