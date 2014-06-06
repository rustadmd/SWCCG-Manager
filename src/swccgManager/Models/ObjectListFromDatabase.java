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
 * @author Mark Rustad
 * @version .01
 * @param <E>
 * @date Jun 6, 2014
 *
 */
public class ObjectListFromDatabase<E> extends AbstractListModel<E> {

	/**
	 * 
	 */
	public enum ObjectName{
		CARD, COLLECTION, DECK
	}
	private static final long serialVersionUID = -8523269623472803052L;
	private List<E> list = new ArrayList<E>();
	private String listTable, listColumn; //where in the database you can find the list of the items
	private ObjectName m_objectName;
	
	public ObjectListFromDatabase(ObjectName objectName)
	{
		m_objectName = objectName;
		refreshList();
	}

	@Override
	public E getElementAt(int index) {
		E elementAt = list.get(index);
		return elementAt;
	}

	@Override
	public int getSize() {
		return list.size();
	}
	
	public void refreshList()
	{
		//fill the list based on the object type
		switch(m_objectName)
		{
		case CARD: 
			fillListWithCards();
			break;
		case COLLECTION:
			break;
		case DECK:
			break;
		default:
			System.out.println("ObjectListFromDatabase constructor called using bad parameter.");
			break;
		}
	}
	
	/**
	 * Fills the table with cards
	 */
	private void fillListWithCards()
	{
		listTable = "SWD";
		listColumn = "id";
		
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet cardIdList = gsq.getList(listTable, listColumn);
		//iterate through the list and add them to the list
		try {
			while (cardIdList.next())
			{
				int cardID = cardIdList.getInt("id");
				Card newCard = new Card(cardID);
				//This next line could get dicey if not implemented correctly
				//Theoretically safe due to ObjectName enumeration
				list.add((E)newCard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void fillListWithCollections()
	{
		listTable = "CollectionList";
		listColumn = "CollectionName";
		
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet collectionList = gsq.getList(listTable, listColumn);
		//iterate through the list and add them to the list
		try {
			while (collectionList.next())
			{
				String collectionName = collectionList.getString(listColumn);
				String collectionDescription = collectionList.getString("CollectionDescription");
				Collection newCollection = new Collection(collectionName, collectionDescription);
				//This next line could get dicey if not implemented correctly
				//Theoretically safe due to ObjectName enumeration
				list.add((E)newCollection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
