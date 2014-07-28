/**
 * 
 */
package swccgManager.GUI;

import java.awt.*;

import javax.swing.*;

import swccgManager.Models.Collection;
import swccgManager.Models.CollectionList;
import swccgManager.Models.CollectionListComboBoxModel;

/**Panel for viewing important information about a collection and selecting different collections
 * @author Mark Rustad
 * @version .01
 * @date May 20, 2014
 *
 */
public class CollectionDisplay extends JPanel{
	
	private static final long serialVersionUID = -2080540917727826932L;
	private JComboBox<Collection> collectionSelector;
	private CollectionList listModel;
	private CollectionListComboBoxModel comboListModel;
	
	//display fields
	private JTextArea description;
	
	public CollectionDisplay(CollectionList collectionListModel)
	{
		setLayout(new FlowLayout());
		
		addCollectionSelector(collectionListModel);
		addCollectionDescription();
	}
	
	/**
	 * adds the collection selector to the box
	 */
	private void addCollectionSelector(CollectionList collectionListModel)
	{
		//create the combo box
		listModel = collectionListModel;
		comboListModel = new CollectionListComboBoxModel(listModel);
		collectionSelector = new JComboBox<Collection>(comboListModel);
		add(collectionSelector);
	}
	
	private void addCollectionDescription()
	{
		description = new JTextArea(3, 20);
		description.setText("test description");
		description.setEditable(false);
		
		TitledBorderPanel descriptionPanel = new TitledBorderPanel("Description");
		add(descriptionPanel);
		
		descriptionPanel.add(description);
	}

}
