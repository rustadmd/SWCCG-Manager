/**
 * 
 */
package swccgManager.Controllers;

import javax.swing.SpinnerNumberModel;

import swccgManager.GUI.CollectionView;
import swccgManager.GUI.IntFieldSpinnerDisplay;
import swccgManager.Models.CardCollectionStatsModel;


/**
 * @author Mark Rustad
 * @version .01
 * @date Aug 2, 2014
 *
 */
public class CardCollectionChangedAction_CV {
	
	public CardCollectionChangedAction_CV(CollectionView cv)
	{
		//****update the inventory model****//
		CardCollectionStatsModel ccsm = cv.getStatsModel();
		SpinnerNumberModel invModel = ccsm.getInventoryModel();
		IntFieldSpinnerDisplay spinner = cv.getStatsDisplay().getInventoryDisplay();
		spinner.setModel(invModel);
	}

}
