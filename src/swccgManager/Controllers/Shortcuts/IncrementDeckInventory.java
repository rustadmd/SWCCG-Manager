/**
 *
 */
package swccgManager.Controllers.Shortcuts;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.SpinnerNumberModel;

import swccgManager.GUI.EditDeckView;

/**
 * @author Mark Rustad
 * @version .01
 * @date Aug 3, 2014
 *
 */
public class IncrementDeckInventory extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -4955338249578082229L;
	private EditDeckView m_edv;
	public IncrementDeckInventory(EditDeckView edv)
	{
		m_edv = edv;
	}


	@Override
	public void actionPerformed(ActionEvent ke) {
			SpinnerNumberModel invModel = m_edv.getStatsModel().getInventoryModel();
			invModel.setValue(invModel.getNextValue());
	}

}
