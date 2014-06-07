/**
 * 
 */
package swccgManager.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

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
	
	public CardList()
	{
		refreshList();
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
		list.clear(); 
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
		String listTable = "SWD";
		String listColumn = "id";
		
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet cardIdList = gsq.getCardVitals();
		//iterate through the list and add them to the list
		try {
			while (cardIdList.next())
			{
				int cardID = cardIdList.getInt("id");
				Card newCard = new Card(cardID);
				//This next line could get dicey if not implemented correctly
				//Theoretically safe due to ObjectName enumeration
				list.add(newCard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void fireListDataChanged() {
		fireContentsChanged(this, 0, Math.max(list.size() - 1, 0));
	}

}
