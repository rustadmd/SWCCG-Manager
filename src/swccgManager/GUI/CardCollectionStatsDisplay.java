/**
 * 
 */
package swccgManager.GUI;

import swccgManager.GUI.IntFieldSpinnerDisplay.Type;
import swccgManager.Models.CardCollectionStatsModel;
import swccgManager.Models.InventorySpinnerModel;

/**
 * @author Mark Rustad
 * @version .01
 * @date Aug 1, 2014
 *
 */
public class CardCollectionStatsDisplay extends TitledBorderPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7127518044376721821L;
	private IntFieldSpinnerDisplay inventoryDisplay;

	public CardCollectionStatsDisplay(CardCollectionStatsModel statsModel)
	{
		super("Card Statistics");
		
		//add inventory spinner
		InventorySpinnerModel inventoryModel = statsModel.getInventoryModel();
		inventoryDisplay = new IntFieldSpinnerDisplay(Type.INVENTORY, inventoryModel);
		add(inventoryDisplay);
	}

}
