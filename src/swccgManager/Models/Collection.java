/**
 * 
 */
package swccgManager.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

import swccgManager.Database.GenericSQLQueries;
import swccgManager.Database.InsertQueries;

/**
 * @author Mark Rustad
 * @version .01
 * @date May 20, 2014
 *
 */
public class Collection {
	
	private String collectionName;
	private String collectionDescription;
	
	/**
	 * Creates a new collection
	 * @param name 
	 * @param description
	 */
	public Collection(String name, String description)
	{
		collectionName = name;
		collectionDescription = description;
		System.out.println("Collection Created: " + collectionName + collectionDescription);
	}
	
	public Collection (String name)
	{
		collectionName = name;
		
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet collectionInfo = gsq.getCollectionVitals(name);
		try {
			collectionDescription = collectionInfo.getString("CollectionDescription");
			collectionInfo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("Collection Created: " + collectionName + collectionDescription);//debugging
	}
	
	/**
	 * Allows the collection to be seen by its name
	 */
	@Override
	public String toString()
	{
		return collectionName;
	}

	/**
	 * @return the collectionName
	 */
	public String getCollectionName() {
		return collectionName;
	}

	/**
	 * @return the collectionDescription
	 */
	public String getCollectionDescription() {
		return collectionDescription;
	}
	
	public void saveCollection()
	{
		InsertQueries iq = new InsertQueries();
		iq.addCollection(collectionName, collectionDescription);
	}

}
