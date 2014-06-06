/**
 * 
 */
package swccgManager.GUI;

import javax.swing.*;

import swccgManager.Database.GenericSQLQueries;
import swccgManager.Models.Collection;

/**Panel for viewing important information about a collection and selecting different collections
 * @author Mark Rustad
 * @version .01
 * @date May 20, 2014
 *
 */
public class CollectionDisplay extends JPanel{
	
	private static final long serialVersionUID = -2080540917727826932L;
	private JComboBox<Collection> collectionSelector;
	
	public CollectionDisplay()
	{
		addCollectionSelector();
	}
	
	/**
	 * adds the collection selector to the box
	 */
	private void addCollectionSelector()
	{
		//get the list
		GenericSQLQueries gsq = new GenericSQLQueries();
		Collection[] collectionList = gsq.getCollectionList();
		
		//create the combo box
		collectionSelector = new JComboBox<Collection>(collectionList);
		add(collectionSelector);
	}

}
