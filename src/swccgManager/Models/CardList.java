/**
 * 
 */
package swccgManager.Models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

import swccgManager.Database.CardQueryCriteria;
import swccgManager.Database.GenericSQLQueries;


/**
 * @author Mark Rustad
 * @version .01
 * @param <E>
 * @date Jun 6, 2014
 *
 */
public class CardList extends AbstractListModel<Card> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8523269623472803052L;
	private List<Card> list = new ArrayList<Card>();
	private CardQueryCriteria m_criteria;
	
	public CardList()
	{
		this(new CardQueryCriteria());
	}
	
	public CardList(CardQueryCriteria cardCriteria)
	{
		m_criteria = cardCriteria;
		refreshList();
	}
	/**
	 * Refreshes the list based on new query criteria
	 * @param cardCriteria
	 */
	public void newCardCriteria(CardQueryCriteria cardCriteria)
	{
		m_criteria = cardCriteria;
		refreshList();
	}

	/**
	 * @return the m_criteria
	 */
	public CardQueryCriteria getCriteria() {
		return m_criteria;
	}

	@Override
	public Card getElementAt(int index) {
		Card elementAt = list.get(index);
		return elementAt;
	}

	@Override
	public int getSize() {
		return list.size();
	}
	
	public void refreshList()
	{
		fillListWithCards();
		fireListDataChanged();
	}
	/**
	 * Sets the list to the list of cards
	 * @param cardList List of cards you wish to have in the list
	 */
	public void setCardList(List<Card> cardList)
	{
		list.clear();
		list = cardList;
		fireListDataChanged();
	}
	
	/**
	 * Fills the table with cards
	 */
	private void fillListWithCards()
	{
		GenericSQLQueries gsq = new GenericSQLQueries();
		List<Card> newCardList = gsq.getCardList(m_criteria);
		if(newCardList.isEmpty())
		{
			System.out.println("Query Returned no results");
			newCardList.add(new Card(-1));//return an empty card
		}
		setCardList(newCardList);
	}
	
	private void fireListDataChanged() {
		fireContentsChanged(this, 0, Math.max(list.size() - 1, 0));
	}

}
