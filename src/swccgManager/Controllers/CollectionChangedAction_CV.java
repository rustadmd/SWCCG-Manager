/**
 * 
 */
package swccgManager.Controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import swccgManager.GUI.CollectionDisplay;
import swccgManager.GUI.CollectionView;
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
	private CollectionView m_collectionView;
	
	
	
	public CollectionChangedAction_CV(CollectionView displayUpdated)
	{
		m_collectionView = displayUpdated;
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		//update the model
		m_collectionView.updateStatsModel();
		
		//perform all actions required of changing the collection or the card
		new CardCollectionChangedAction_CV(m_collectionView);
		
		//System.out.println("CollectionChangedAction_CV executed");//debugging
		//update the Collection Description Display
		Collection selectedCollection = m_collectionView.getSelectedCollection();
		//System.out.println("Collection Selected: " + selectedCollection.toString());
		m_collectionView.getCollectionDisplay().setCollectionDescriptionDisplay(selectedCollection.getCollectionDescription());
	}

}
