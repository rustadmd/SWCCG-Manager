/**
 * 
 */
package swccgManager.GUI;
import javax.swing.*;

import swccgManager.Controllers.ExportCollectionAction;
import swccgManager.Models.Collection;
import swccgManager.Models.CollectionList;
import swccgManager.Models.CollectionListComboBoxModel;

import java.awt.*;


/**
 * @author Mark Rustad
 * @version .01
 * @date May 26, 2014
 *
 */
public class ExportCollectionWindow extends JFrame{
	
	private static final long serialVersionUID = 5151115731903601102L;
	private JButton submitButton;
	private JComboBox<Collection> collectionSelector;
	private CollectionList listModel;
	private CollectionListComboBoxModel comboListModel;
	
	public ExportCollectionWindow()
	{
		super("Export Collection");
		setLayout(new BorderLayout());
		setVisible(true);
		
		//Add the instructions label
		String nameLabel_s = "Select a collection to Export";
		JLabel nameLabel = new JLabel(nameLabel_s);
		add(nameLabel, BorderLayout.NORTH);
		
		//setup collection selector
		listModel = new CollectionList();
		comboListModel = new CollectionListComboBoxModel(listModel);
		collectionSelector = new JComboBox<Collection>(comboListModel);
		add(collectionSelector, BorderLayout.CENTER);
		
		addSubmitButton();
		
		pack();
		this.setResizable(false);
	}
	
	/**
	 * Gets the collection name as selected from the drop down menu
	 * @return Selected collection name 
	 */
	public String getCollectionName()
	{
		Collection exportCollection = comboListModel.getSelectedItem();
		String name = exportCollection.getCollectionName();
		
		return name;
	}

	private void addSubmitButton()
	{
		ExportCollectionAction exportAction = new ExportCollectionAction(this);
		submitButton = new JButton(exportAction);
		submitButton.setText("Export Collection");
		this.add(submitButton, BorderLayout.SOUTH);
	}
	

}
