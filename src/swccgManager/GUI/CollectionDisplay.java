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
	private JTextArea description_ta;

	public CollectionDisplay(CollectionList collectionListModel)
	{
		setLayout(new FlowLayout());

		if (collectionListModel == null) {
			collectionListModel = new CollectionList();
		}
		addCollectionSelector(collectionListModel);
		addCollectionDescription();
	}

	public CollectionDisplay() {
		this(null);
	}

	public void setSelectedCollection(int index)
	{
		collectionSelector.setSelectedIndex(index);
	}

	public void addCollectionSelectedAction(Action a)
	{
		collectionSelector.addActionListener(a);
	}

	public Collection getSelectedCollection()
	{
		Collection selectedCollection = (Collection) collectionSelector.getSelectedItem();
		return selectedCollection;
	}

	public void setCollectionDescriptionDisplay(String description)
	{
		//System.out.println("Description: " + description);
		description_ta.setText(description);
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
		//set default
		setSelectedCollection(0);
		add(collectionSelector);
	}

	private void addCollectionDescription()
	{
		description_ta = new JTextArea(3, 20);
		Collection initialCollection = getSelectedCollection();
		String initialDescription = initialCollection.getCollectionDescription();
		setCollectionDescriptionDisplay(initialDescription);
		description_ta.setEditable(false);

		TitledBorderPanel descriptionPanel = new TitledBorderPanel("Description");
		description_ta.setBackground(descriptionPanel.getBackground());
		add(descriptionPanel);

		descriptionPanel.add(description_ta);
	}

}
