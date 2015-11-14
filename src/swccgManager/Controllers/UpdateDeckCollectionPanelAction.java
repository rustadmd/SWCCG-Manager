package swccgManager.Controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import swccgManager.GUI.DeckCollectionView;

public class UpdateDeckCollectionPanelAction extends AbstractAction implements ChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7864879793799492207L;
	private DeckCollectionView m_dcv;
	

	public UpdateDeckCollectionPanelAction(DeckCollectionView m_dcv) {
		super();
		this.m_dcv = m_dcv;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		m_dcv.updateDisplay();
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		m_dcv.updateDisplay();
	}

}
