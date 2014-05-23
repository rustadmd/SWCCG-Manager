/**
 * 
 */
package swccgManager;

/**
 * @author mdrustad Rustad
 * @version .01
 * @date May 23, 2014
 *
 */
public class InsertQueries {
	
	SqlUtilities su;
	
	public InsertQueries()
	{
		su = new SqlUtilities();
	}
	
	public void addCollection(String name, String description)
	{
		String insertCollection = 
				"INSERT INTO CollectionList (CollectionName, CollectionDescription) "
				+ "VALUES ('" + name + "' , '" + description + "');";
		
		su.executeQuery(insertCollection);
		su.closeDB();

	}

}
