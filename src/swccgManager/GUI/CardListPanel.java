/**
 * 
 */
package swccgManager.GUI;

import javax.swing.*;
import javax.swing.event.*;

import swccgManager.Models.Card;

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
	private Card activeCard;
	
	/**
	 * Establishes a list of cards
	 * @param cardList List of the cards to be displayed
	 */
	public CardListPanel(Card[] cardList)
	{
		//Add the card selector
		
		cardSelector = new JList<Card>();
		cardSelector.setListData(cardList);
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(cardSelector);
		
		int numCardsListed = 15;
		cardSelector.setVisibleRowCount(numCardsListed);
		add(listScroller);
		
		//Add the listener action
		cardSelector.addListSelectionListener(new ListSelectionListener()
			{
				public void valueChanged(ListSelectionEvent e)
				{
					activeCard = cardSelector.getSelectedValue();
				}
			}
		);//End listener
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
	public Card getActiveCard()
	{
		return activeCard;
	}
	
	
	

}
