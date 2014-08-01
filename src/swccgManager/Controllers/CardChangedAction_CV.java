/**
 * 
 */
package swccgManager.Controllers;

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
public class CardChangedAction_CV implements ListSelectionListener{

	private CollectionView m_collectionView;
	
	public CardChangedAction_CV(CollectionView cv)
	{
		m_collectionView = cv;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Card selectedCard = m_collectionView.getSelectedCard();
		m_collectionView.updateCardDisplay(selectedCard);
		m_collectionView.repaint();
		
	}
	
	

}
