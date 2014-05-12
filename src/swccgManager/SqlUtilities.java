/**
 * 
 */
package swccgManager;
import java.sql.*;
/**
 * @author Mark Rustad
 * @version .01
 * @date May 12, 2014
 *
 */
public class SqlUtilities {

	public int getNumRows(ResultSet results)
	{
		int rowCount = 0;
		try {
		//iterate through the rows
			while (results.next())
			{
				rowCount++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowCount;
	}
	
}
