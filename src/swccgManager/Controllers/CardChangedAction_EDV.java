/**
 *
 */
package swccgManager.Controllers;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import swccgManager.GUI.EditDeckView;
import swccgManager.Models.Card;

/**
 * @author Mark Rustad
 * @version .01
 * @date Jul 31, 2014
 *
 */
public class CardChangedAction_EDV implements ListSelectionListener, ListDataListener{

	private EditDeckView m_deckView;

	public CardChangedAction_EDV(EditDeckView dv)
	{
		m_deckView = dv;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		updateDisplays();
	}

	@Override
	public void contentsChanged(ListDataEvent arg0) {
		updateDisplays();
		m_deckView.getListDisplay().setSelectedItem(0);//prevent error, reset
		m_deckView.getDeckDisplay().setSelectedDeck(0);
	}

	private void updateDisplays()
	{
		//update the model
		m_deckView.updateStatsModel();

		new CardDeckChangedAction_EDV(m_deckView);
		//update the card display
		Card selectedCard = m_deckView.getSelectedCard();
		m_deckView.updateCardDisplay(selectedCard);
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
