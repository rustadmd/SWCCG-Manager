/**
 *
 */
package swccgManager.GUI;

import javax.swing.SpinnerNumberModel;

import swccgManager.GUI.IntFieldSpinnerDisplay.Type;
import swccgManager.Models.CardDeckStatsModel;

/**
 * @author Mark Rustad
 * @version .01
 * @date Aug 1, 2014
 *
 */
public class CardDeckStatsDisplay extends TitledBorderPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 7127518044376721821L;
	private IntFieldSpinnerDisplay inventoryDisplay;
	private IntFieldSpinnerDisplay desiredDisplay;
	private IntFieldSpinnerDisplay extraDisplay;

	public CardDeckStatsDisplay(CardDeckStatsModel statsModel)
	{
		super("Card Statistics");

		//add inventory spinner
		SpinnerNumberModel inventoryModel = statsModel.getInventoryModel();
		inventoryDisplay = new IntFieldSpinnerDisplay(Type.INVENTORY, inventoryModel);
		add(inventoryDisplay);
	}

	/**
	 * @return the inventoryDisplay
	 */
	public IntFieldSpinnerDisplay getInventoryDisplay() {
		return inventoryDisplay;
	}

	/**
	 * @return the desiredDisplay
	 */
	public IntFieldSpinnerDisplay getDesiredDisplay() {
		return desiredDisplay;
	}

	/**
	 * @return the extraDisplay
	 */
	public IntFieldSpinnerDisplay getExtraDisplay() {
		return extraDisplay;
	}

}
