/**
 * 
 */
package swccgManager;

import java.awt.*;
import java.sql.*;
import java.util.*;

/**
 * Manages all Star Wars cards. Maintains a full list of the cards
 * @author Mark Rustad
 * @version .01
 * @date May 12, 2014
 *
 */

public class FullCardSet {
	private int numCards;
	private Hashtable<Integer, Card> cardSet;
	
	public FullCardSet(Connection swdb)
	{
		numCards = 3503; //Number of cards
		cardSet = new Hashtable<Integer, Card>(numCards);
		int numSuccessfullyAddedCards = 0;
		
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet fullCardList = gsq.getCardVitals(swdb);
		
		//create cards and add it to the hash table
		try {
			while(fullCardList.next())
			{
				//Retrieve card info from the row
				int cardID = fullCardList.getInt("id");
				String cardName = fullCardList.getString("cardName");
				String side = fullCardList.getString("Grouping");
				String cardType = fullCardList.getString("CardType");
				String subType = fullCardList.getString("SubType");
				String expansion = fullCardList.getString("Expansion");
				String rarity = fullCardList.getString("Rarity");
				String uniqueness = fullCardList.getString("Uniqueness");
				/*Temporarily disabled for speed
				ImageManager im = new ImageManager(swdb);
				Image img = im.getCardImage(cardID, 1);//1 is always the front side
				*/
				Image img = null; //for speed
				Card newCard = new Card (cardID, cardName, side, cardType, subType, expansion, rarity, uniqueness, img);
				cardSet.put(cardID, newCard);
				numSuccessfullyAddedCards++;
				System.out.println("Created " + numSuccessfullyAddedCards + " cards, Card ID: " + cardID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Created " + numSuccessfullyAddedCards + " cards out of exepected " + numCards + " number of cards");
		
	}
	
	/**
	 * Returns a single card from the card set
	 * @param cardId unique identifier for the cards
	 * @return One starwars card owning the valid index
	 */
	public Card retrieveCard(int cardId)
	{
		Card card = cardSet.get(cardId);
		return card;
	}
	/**
	 * Takes an array of Card ID's and returns the list of cards
	 * @param cardIdList List of the card ID's
	 * @return Returns the matching set of cards
	 */
	public Card[] retrieveCardSet(int[] cardIdList)
	{
		//Create card array
		int numCards = cardIdList.length;
		Card[] cardList = new Card[numCards];
		
		//fill card array
		for(int cardIndex : cardIdList)
		{
			Card newCard = retrieveCard(cardIndex);
			cardList[cardIndex] = newCard;
		}
		
		//return it
		return cardList;
	}
	
	/**
	 * Returns the entire Card set as an array
	 * @return Card list representation of the full card list hashtable
	 */
	public Card[] getFullCardSet()
	{
		Card[] fullCardList;
		fullCardList = new Card[getNumCards()];
		
		//Copy the table
		int i = 0;
		for (Card card : cardSet.values())
		{
			fullCardList[i] = card;
			i++;
		}
		
		return fullCardList;
	}
	
	/**
	 * @return the numCards
	 */
	public int getNumCards() {
		return cardSet.size();
	}

}
