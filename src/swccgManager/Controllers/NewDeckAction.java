/**
 *
 */
package swccgManager.Controllers;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import swccgManager.Database.GenericSQLQueries;
import swccgManager.Database.InsertQueries;
import swccgManager.GUI.NewDeckWindow;
import swccgManager.Models.CollectionList;

/**
 * This action adds new collections into the system, the updates the display as appropriate
 * @author Mark Rustad
 * @version .01
 * @date Jun 7, 2014
 *
 */
public class NewDeckAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 7003398248883159423L;

	private NewDeckWindow m_ndw;
	private CollectionList m_list;

	public NewDeckAction(NewDeckWindow ndw, CollectionList list)
	{
		m_ndw = ndw;
		m_list = list;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String name = m_ndw.getName();
		String description = m_ndw.getDescription();

		GenericSQLQueries gsq = new GenericSQLQueries();
		boolean alreadyExists = gsq.entryExists("DeckName", "DeckList", name);

		//You must adjust for blank names
		if(name.equals(null) || name.equals(""))
		{
			JOptionPane.showMessageDialog(m_ndw, "You must enter a name.");
		}
		else if (alreadyExists)
		{
			JOptionPane.showMessageDialog(m_ndw, "This deck already exists.");
		}

		else{
			InsertQueries iq = new InsertQueries();
			iq.addDeck(name, description);
			m_ndw.setVisible(false);
		}

		//if there is a list, notify it to update
		if(m_list != null)
		{
			m_list.refreshList();
		}

	}

}
