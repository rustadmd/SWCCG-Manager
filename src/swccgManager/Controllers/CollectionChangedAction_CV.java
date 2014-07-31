/**
 * 
 */
package swccgManager.Controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import swccgManager.GUI.CollectionDisplay;
import swccgManager.Models.Collection;

/**
 * @author Mark Rustad
 * @version .01
 * @date Jul 27, 2014
 * 
 * Triggered when the collection is changed. Will update the display as appropriate.
 */
public class CollectionChangedAction_CV extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -401638151828131930L;
	private CollectionDisplay m_collectionDisplay;
	
	
	
	public CollectionChangedAction_CV(CollectionDisplay displayUpdated)
	{
		m_collectionDisplay = displayUpdated;
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		//System.out.println("CollectionChangedAction_CV executed");//debugging
		//update the Collection Description Display
		Collection selectedCollection = m_collectionDisplay.getSelectedCollection();
		//System.out.println("Collection Selected: " + selectedCollection.toString());
		m_collectionDisplay.setCollectionDescriptionDisplay(selectedCollection.getCollectionDescription());
	}

}
