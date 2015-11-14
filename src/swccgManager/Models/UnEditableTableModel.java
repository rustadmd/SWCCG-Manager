/**
 * 
 */
package swccgManager.Models;

import javax.swing.table.DefaultTableModel;

/**
 * @author Mark
 *
 */
public class UnEditableTableModel extends DefaultTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3057906787199980452L;

	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}

}
