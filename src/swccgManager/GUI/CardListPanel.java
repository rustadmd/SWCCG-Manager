/**
 * 
 */
package swccgManager.GUI;

import javax.swing.*;
import javax.swing.event.*;

import swccgManager.Models.Card;
import swccgManager.Models.CardList;

/**
 * @author Mark Rustad
 * @version .01
 * @date May 19, 2014
 *
 */
public class CardListPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<Card> cardSelector;
	private CardList m_cardList;
	
	/**
	 * Establishes a list of cards
	 * @param cardList List of the cards to be displayed
	 */
	public CardListPanel(CardList cardList)
	{
		//Add the card selector
		m_cardList = cardList;
		cardSelector = new JList<Card>();
		cardSelector.setModel(m_cardList);
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(cardSelector);
		
		int numCardsListed = 15;
		cardSelector.setVisibleRowCount(numCardsListed);
		add(listScroller);
		
		//Set default to the first item on the list
		setSelectedItem(0);
		
		//Add the listener action
		//cardSelector.addListSelectionListener();//End listener
	}
	
	public CardList getCardList()
	{
		return m_cardList;
	}
	
	public void setSelectedItem(int index)
	{
		cardSelector.setSelectedIndex(index);
	}
	
	public void addCardChangedAction(ListSelectionListener aa)
	{
		cardSelector.addListSelectionListener(aa);
		
	}
	
	public void addListDataListener(ListDataListener listener)
	{
		m_cardList.addListDataListener(listener);
	}
	
	/**
	 * Sets a new list of cards. For when parameters change 
	 * @param cardList New list of cards
	 */
	public void refreshCardList(Card[] cardList)
	{
		cardSelector.setListData(cardList);
	}
	
	/**
	 * Gets the selected card from the list
	 * @return Selected card
	 */
	public Card getSelectedCard()
	{
		Card selectedCard = cardSelector.getSelectedValue();
		return selectedCard;
	}
	
	
	

}
