/**
 * 
 */
package swccgManager.GUI;

import javax.swing.*;

import swccgManager.Collection;
import swccgManager.GenericSQLQueries;

/**
 * @author Mark Rustad
 * @version .01
 * @date May 20, 2014
 *
 */
public class CollectionDisplay extends JPanel{
	
	private JComboBox collectionSelector;
	private Collection activeCollection;
	
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
