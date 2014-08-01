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
	
	/**
	 * Establishes a list of cards
	 * @param cardList List of the cards to be displayed
	 */
	public CardListPanel(CardList cardList)
	{
		//Add the card selector
		
		cardSelector = new JList<Card>();
		cardSelector.setModel(cardList);
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(cardSelector);
		
		int numCardsListed = 15;
		cardSelector.setVisibleRowCount(numCardsListed);
		add(listScroller);
		
		//Set default to the first item on the list
		cardSelector.setSelectedIndex(0);
		
		//Add the listener action
		//cardSelector.addListSelectionListener();//End listener
	}
	
	public void addCardChangedAction(ListSelectionListener aa)
	{
		cardSelector.addListSelectionListener(aa);
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
