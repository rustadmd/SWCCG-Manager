/**
 *
 */
package swccgManager.Controllers;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import swccgManager.Database.InsertQueries;
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

	public UpdateDeckInventory(CardDeckStatsModel model)
	{
		statsModel = model;
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {

		//retrieve valid information
		int newInventory = (int) statsModel.getInventoryModel().getValue();
		Card card = statsModel.getCard();
		Deck deck = statsModel.getDeck();
		boolean isInDb = statsModel.getIsInDB();

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

	}



}
