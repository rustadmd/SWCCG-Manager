/**
 *
 */
package swccgManager.GUI;

import javax.swing.JTabbedPane;

import swccgManager.Controllers.UpdateDeckCollectionPanelAction;

/**
 * @author Mark Rustad
 * @version .01
 * @date Jun 16, 2015
 *
 */
public class DeckView extends JTabbedPane {

	/**
	 *
	 */
	private static final long serialVersionUID = 968252659758256252L;

	public DeckView()
	{
		setupManageTab();
	}

	public void setupManageTab(){
		DeckManagePanel managePanel = new DeckManagePanel();

		//Add relevant information
		this.addTab("Manage Decks", managePanel);
		this.addTab("Add/Remove Cards", new EditDeckView());
		DeckCollectionView dcv = new DeckCollectionView();
		this.addTab("Collection Analysis", dcv);
		this.addChangeListener(new UpdateDeckCollectionPanelAction(dcv));
	}

}
