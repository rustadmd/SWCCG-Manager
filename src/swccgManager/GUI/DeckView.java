/**
 * 
 */
package swccgManager.GUI;

import javax.swing.JTabbedPane;

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
	}

}
