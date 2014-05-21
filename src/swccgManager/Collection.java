/**
 * 
 */
package swccgManager;

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

}
