/**
 * 
 */
package swccgManager.Controllers.Shortcuts;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.SpinnerNumberModel;

import swccgManager.GUI.CollectionView;

/**
 * @author Mark Rustad
 * @version .01
 * @date Aug 3, 2014
 *
 */
public class DecrementInventory extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4955338249578082229L;
	private CollectionView m_cv;
	public DecrementInventory(CollectionView cv)
	{
		m_cv = cv;
	}


	@Override
	public void actionPerformed(ActionEvent ke) {
			SpinnerNumberModel invModel = m_cv.getStatsModel().getInventoryModel();
			invModel.setValue(invModel.getPreviousValue());
	}

}
