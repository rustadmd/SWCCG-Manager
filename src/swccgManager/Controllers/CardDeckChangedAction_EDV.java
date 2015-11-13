/**
 *
 */
package swccgManager.Controllers;

import javax.swing.SpinnerNumberModel;

import swccgManager.GUI.EditDeckView;
import swccgManager.GUI.IntFieldSpinnerDisplay;
import swccgManager.Models.CardDeckStatsModel;


/**
 * @author Mark Rustad
 * @version .01
 * @date Aug 2, 2014
 *
 */
public class CardDeckChangedAction_EDV {

	public CardDeckChangedAction_EDV(EditDeckView edv)
	{
		//****update the inventory model****//
		//perform all actions required of changing the deck or the card
		CardDeckStatsModel ccsm = edv.getStatsModel();
		SpinnerNumberModel invModel = ccsm.getInventoryModel();
		IntFieldSpinnerDisplay spinner = edv.getStatsDisplay().getInventoryDisplay();
		spinner.setModel(invModel);
	}

}
