/**
 * 
 */
package swccgManager.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import swccgManager.Database.GenericSQLQueries;

/**
 * Retrieves a list of Collections from the database. This list can then be added to whatever
 * 
 * Currently no filtering options.
 * 
 * @author Mark Rustad
 * @version .01
 * @date Jun 6, 2014
 *
 */
public class CollectionList extends AbstractListModel<Collection> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8523269623472803052L;
	private List<Collection> list = new ArrayList<Collection>();
	
	public CollectionList()
	{
		refreshList();
	}

	@Override
	public Collection getElementAt(int index) {
		Collection elementAt = list.get(index);
		return elementAt;
	}

	@Override
	public int getSize() {
		return list.size();
	}
	
	/**
	 * Will reload the list from the database
	 */
	public void refreshList()
	{
		list.clear(); 
		fillListWithCollections();
		fireListDataChanged();
	}
	
	/**
	 * Fills the list from the database
	 */
	private void fillListWithCollections()
	{
		String listTable = "CollectionList";
		String listColumn = "CollectionName";
		
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet collectionList = gsq.getList(listTable, listColumn);
		if(collectionList.equals(null))
		{
			System.out.println("CollectionList Result set is empty");
		}
		//iterate through the list and add them to the list
		try {
			while (collectionList.next())
			{
				String collectionName = collectionList.getString(listColumn);
				//System.out.println(collectionName);//Debugging
				Collection newCollection = new Collection(collectionName);
				list.add(newCollection);
				System.out.println("Collection Added: " + newCollection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	private void fireListDataChanged() {
		fireContentsChanged(this, 0, Math.max(list.size() - 1, 0));
	}

}
