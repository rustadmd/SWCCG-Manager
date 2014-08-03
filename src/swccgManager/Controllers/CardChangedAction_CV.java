/**
 * 
 */
package swccgManager.Controllers;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import swccgManager.GUI.CollectionView;
import swccgManager.Models.Card;

/**
 * @author Mark Rustad
 * @version .01
 * @date Jul 31, 2014
 *
 */
public class CardChangedAction_CV implements ListSelectionListener, ListDataListener{

	private CollectionView m_collectionView;
	
	public CardChangedAction_CV(CollectionView cv)
	{
		m_collectionView = cv;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		updateDisplays();	
	}

	@Override
	public void contentsChanged(ListDataEvent arg0) {
		updateDisplays();
		m_collectionView.getListDisplay().setSelectedItem(0);
		m_collectionView.getCollectionDisplay().setSelectedCollection(0);
	}
	
	private void updateDisplays()
	{
		//update the model
		m_collectionView.updateStatsModel();
		
		//perform all actions required of changing the collection or the card
		new CardCollectionChangedAction_CV(m_collectionView);
		
		//update the card display
		Card selectedCard = m_collectionView.getSelectedCard();
		m_collectionView.updateCardDisplay(selectedCard);
	}

	@Override
	public void intervalAdded(ListDataEvent arg0) {
		// TODO Auto-generated method stub
		//should do nothing, will not add a single card
	}

	@Override
	public void intervalRemoved(ListDataEvent arg0) {
		// TODO Auto-generated method stub
		//should do nothing, will not remove single card
		
	}
	
	

}
