/**
 * 
 */
package swccgManager.Controllers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import swccgManager.Database.CardQueryCriteria;
import swccgManager.Database.CardQueryCriteria.Attribute;
import swccgManager.GUI.CardListPanel;
import swccgManager.GUI.CollectionDisplay;
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
	private CardListPanel m_cardListDisplay;
	private CollectionDisplay m_collectionDisplay;
	
	public PerformSearch(CardListPanel cardListPanel, SearchDisplay searchDisplay, CollectionDisplay cd)
	{
		m_cardListDisplay = cardListPanel;
		m_cardList = cardListPanel.getCardList();
		m_searchDisplay = searchDisplay;
		m_collectionDisplay = cd;
	}
	
	public PerformSearch(CardListPanel cardListPanel, SearchDisplay searchDisplay)
	{
		this(cardListPanel, searchDisplay, null);
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
		
		//add the rarity
		String raritySql = m_searchDisplay.getSelectedRarity();
		criteria.setCriteria(Attribute.RARITY, raritySql);
		
		//update cardType
		String typeSql = m_searchDisplay.getSelectedCardType();
		criteria.setCriteria(Attribute.TYPE, typeSql);
		
		//Update Subtype
		String subtypeSql = m_searchDisplay.getSelectedSubType();
		criteria.setCriteria(Attribute.SUBTYPE, subtypeSql);
		
		//get Expansion
		String expansionSql = m_searchDisplay.getSelectedExpansion();
		criteria.setCriteria(Attribute.EXPANSION, expansionSql);
		
		//update Card realms
		String realmSql = m_searchDisplay.getSelectedRealms();
		criteria.setCriteria(Attribute.REALM, realmSql);
		
		//Add name filter
		String nameSql = m_searchDisplay.getNameFilter();
		criteria.setCriteria(Attribute.NAME, nameSql);
		
		//Add lore filter
		String loreSql = m_searchDisplay.getLoreFilter();
		criteria.setCriteria(Attribute.LORE, loreSql);
		
		//add Game Text filter
		String gameTextSql = m_searchDisplay.getGameTextFilter();
		criteria.setCriteria(Attribute.GAMETEXT, gameTextSql);
		
		//add Icon filter
		String iconSql = m_searchDisplay.getSelectedIcon();
		criteria.setCriteria(Attribute.ICON, iconSql);
		
		//Add collection contains filter
		//String any = "Any"; //unneeded, should result in null
		String contains = "Contains"; String notContains = "Not Contains";
		String whereClause = "";
		String collectionName = null;
		String containsFilter = m_searchDisplay.getCollectionContains();
		if (containsFilter == contains){
			whereClause = " AND Collection.Inventory > 0 ";
			collectionName = m_collectionDisplay.getSelectedCollection().getCollectionName();
		}
		else if (containsFilter == notContains) {
			whereClause = " AND (Collection.Inventory <= 0 OR Collection.Inventory LIKE '' OR Collection.Inventory IS NULL ) ";
			collectionName = m_collectionDisplay.getSelectedCollection().getCollectionName();
		}
		criteria.setCriteria(Attribute.COL_CONTAINS, whereClause);
		
		criteria.setCriteria(Attribute.COL_NAME, collectionName);
		//System.out.println(collectionName);//debugging
		
		m_cardListDisplay.setSelectedItem(0);//reset selected Item to so there are no out of bounds errors
		//update list with new criteria
		m_cardList.newCardCriteria(criteria);
		m_cardListDisplay.updateCardCount();

	}

}
