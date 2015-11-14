/**
 *
 */
package swccgManager.Controllers;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import swccgManager.Database.InsertQueries;
import swccgManager.GUI.DeckDisplay;
import swccgManager.Models.Card;
import swccgManager.Models.CardDeckStatsModel;
import swccgManager.Models.Deck;

/**
 * @author Mark Rustad
 * @version .01
 * @date Aug 2, 2014
 *
 */
public class UpdateDeckInventory implements ChangeListener{

	private CardDeckStatsModel statsModel;
	private DeckDisplay m_deckDisplay;

	public UpdateDeckInventory(CardDeckStatsModel model)
	{
		this(model, null);
	}
	
	public UpdateDeckInventory(CardDeckStatsModel model, DeckDisplay dd) {
		// TODO Auto-generated constructor stub
		statsModel = model;
		m_deckDisplay = dd;
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {

		//retrieve valid information
		int newInventory = (int) statsModel.getInventoryModel().getValue();
		Card card = statsModel.getCard();
		Deck deck = statsModel.getDeck();
		boolean isInDb = statsModel.getIsInDB();
		//System.out.println("UpdateDeckInventory action triggered()");
		//pick insert query or update based on whether it is in the db
		InsertQueries iq = new InsertQueries();
		if(isInDb)
		{
			iq.updateCardDeckInventory(card, deck, newInventory);
		}
		else
		{
			iq.insertCardDeckInventory(card, deck, newInventory);
			statsModel.setInDB(true);
		}
		
		if (m_deckDisplay != null) {
			m_deckDisplay.updateCardCount();
		}

	}



}
