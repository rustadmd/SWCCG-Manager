/**
 * 
 */
package swccgManager.Models;

import javax.swing.SpinnerNumberModel;

/**
 * @author Mark Rustad
 * @version .01
 * @date Aug 2, 2014
 *
 */
public class InventorySpinnerModel extends SpinnerNumberModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3412361240245376159L;

	public InventorySpinnerModel(int initialValue)
	{
		super(initialValue, -99, 99, 1);
	}
	
	public InventorySpinnerModel()
	{
		this(0);
	}

}
