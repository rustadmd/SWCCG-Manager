/**
 * 
 */
package swccgManager.Controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import swccgManager.Database.CardQueryCriteria;
import swccgManager.Database.CardQueryCriteria.Attribute;
import swccgManager.GUI.SearchDisplay;
import swccgManager.Models.CardList;

/**
 * @author Mark Rustad
 * @version .01
 * @date Aug 3, 2014
 *
 */
public class PerformSearch extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardList m_cardList;
	private SearchDisplay m_searchDisplay;
	
	public PerformSearch(CardList cardList, SearchDisplay searchDisplay)
	{
		m_cardList = cardList;
		m_searchDisplay = searchDisplay;
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		CardQueryCriteria criteria = m_cardList.getCriteria();
		
		//update side
		String sideSql = m_searchDisplay.getSelectedSide();
		criteria.setCriteria(Attribute.SIDE, sideSql);
		
		//update cardType
		String typeSql = m_searchDisplay.getSelectedCardType();
		criteria.setCriteria(Attribute.TYPE, typeSql);
		
		//get Expansion
		String expansionSql = m_searchDisplay.getSelectedExpansion();
		criteria.setCriteria(Attribute.EXPANSION, expansionSql);
		
		//update Card realms
		String realmSql = m_searchDisplay.getSelectedRealms();
		criteria.setCriteria(Attribute.REALM, realmSql);
		
		//update list with new criteria
		m_cardList.newCardCriteria(criteria);

	}

}
