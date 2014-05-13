/**
 * 
 */
package swccgManager;

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
		numCards = 3512; //Number of cards
		cardSet = new Hashtable<Integer, Card>(numCards);
		int numSuccessfullyAddedCards = 0;
		
		ResultSet fullCardList = GenericSQLQueries.getCardVitals(swdb);
		 
		//create cards and add it to the hash table
		try {
			while(fullCardList.next())
			{
				int cardID = fullCardList.getInt("id");
				
				Card newCard = new Card (swdb, cardID);
				cardSet.put(cardID, newCard);
				numSuccessfullyAddedCards++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Created " + numSuccessfullyAddedCards + " cards.");
		
	}

}
