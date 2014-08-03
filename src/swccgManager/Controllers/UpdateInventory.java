/**
 * 
 */
package swccgManager.Controllers;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import swccgManager.Database.GenericSQLQueries;
import swccgManager.Database.InsertQueries;
import swccgManager.Models.Card;
import swccgManager.Models.CardCollectionStatsModel;
import swccgManager.Models.Collection;

/**
 * @author Mark Rustad
 * @version .01
 * @date Aug 2, 2014
 *
 */
public class UpdateInventory implements ChangeListener{
	
	private CardCollectionStatsModel statsModel;

	public UpdateInventory(CardCollectionStatsModel model)
	{
		statsModel = model;
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		
		//retrieve valid information
		int newInventory = (int) statsModel.getInventoryModel().getValue();
		Card card = statsModel.getCard();
		Collection collection = statsModel.getCollection();
		boolean isInDb = statsModel.getIsInDB();
		
		//pick insert query or update based on whether it is in the db
		InsertQueries iq = new InsertQueries();
		if(isInDb)
		{
			iq.updateCardInventory(card, collection, newInventory);
		}
		else
		{
			iq.insertCardInventory(card, collection, newInventory);
			statsModel.setInDB(true);
		}
		
	}
	
	

}
