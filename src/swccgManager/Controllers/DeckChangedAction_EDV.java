/**
 *
 */
package swccgManager.Controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import swccgManager.GUI.EditDeckView;
import swccgManager.Models.Deck;

/**
 * @author Mark Rustad
 * @version .01
 * @date Jul 27, 2014
 *
 * Triggered when the collection is changed. Will update the display as appropriate.
 */
public class DeckChangedAction_EDV extends AbstractAction{

	/**
	 *
	 */
	private static final long serialVersionUID = -401638151828131930L;
	private EditDeckView m_edv;



	public DeckChangedAction_EDV(EditDeckView displayUpdated)
	{
		m_edv = displayUpdated;
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		//update the model
		m_edv.updateStatsModel();

		//perform all actions required of changing the collection or the card
		new CardDeckChangedAction_EDV(m_edv);

		//System.out.println("DeckChangedAction_CV executed");//debugging
		//update the Deck Description Display
		Deck selectedDeck = m_edv.getSelectedDeck();
		//System.out.println("Deck Selected: " + selectedDeck.toString());
		m_edv.getDeckDisplay().setDeckDescriptionDisplay(selectedDeck.getDescription());
		m_edv.getDeckDisplay().updateCardCount();
	}

}
