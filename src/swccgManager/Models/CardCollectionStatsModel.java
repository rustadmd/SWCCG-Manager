/**
 * 
 */
package swccgManager.Models;

import java.sql.ResultSet;
import java.sql.*;

import swccgManager.Database.GenericSQLQueries;

/**
 * @author Mark Rustad
 * @version .01
 * @date Jul 26, 2014
 * 
 * This model contains all the information relating to a Card in a collection (how many, etc)
 *
 */
public class CardCollectionStatsModel {
	
	private Collection m_collection;
	private Card m_card;
	private String m_sortLocation;
	private int m_inventory, m_desired, m_extra, m_rating;
	private String m_comment;
	private boolean isInDB;//for when db needs to be updated
	
	public CardCollectionStatsModel(Card card, Collection collection)
	{
		//Set the card and collection
		m_card = card;
		m_collection = collection;
		
		//retrieve information from the database
		int cardID = m_card.getCardId();
		String collectionName = m_collection.getCollectionName();
		
		GenericSQLQueries gsq = new GenericSQLQueries();
		
		try{
			ResultSet cardCollectionStats_rs = gsq.getCardCollectionStats(cardID, collectionName);
			m_sortLocation = cardCollectionStats_rs.getString("sortLocation");
			m_inventory = cardCollectionStats_rs.getInt("inventory");
			m_desired = cardCollectionStats_rs.getInt("desired");
			m_extra = cardCollectionStats_rs.getInt("extra");
			m_rating = cardCollectionStats_rs.getInt("rating");
			m_comment = cardCollectionStats_rs.getString("comment");
			isInDB = true;
		}
		catch(SQLException e)//probably means there is nothing in the database
		{
			isInDB = false;
			//set everything to blank, so it can be displayed
			m_sortLocation = "";
			m_comment = "";
			m_inventory = 0;
			m_desired  = 0;
			m_extra  = 0;
			m_rating  = 0;
		}
		
	}
	
	
	/**
	 * @return the sortLocation
	 */
	public String getSortLocation() {
		return m_sortLocation;
	}
	/**
	 * @param sortLocation the sortLocation to set
	 */
	public void setSortLocation(String sortLocation) {
		this.m_sortLocation = sortLocation;
	}
	/**
	 * @return the inventory
	 */
	public int getInventory() {
		return m_inventory;
	}
	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(int inventory) {
		this.m_inventory = inventory;
	}
	/**
	 * @return the desired
	 */
	public int getDesired() {
		return m_desired;
	}
	/**
	 * @param desired the desired to set
	 */
	public void setDesired(int desired) {
		this.m_desired = desired;
	}
	/**
	 * @return the extra
	 */
	public int getExtra() {
		return m_extra;
	}
	/**
	 * @param extra the extra to set
	 */
	public void setExtra(int extra) {
		this.m_extra = extra;
	}
	/**
	 * @return the rating
	 */
	public int getRating() {
		return m_rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.m_rating = rating;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return m_comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.m_comment = comment;
	}
	/**
	 * @return the collection
	 */
	public Collection getCollection() {
		return m_collection;
	}
	/**
	 * @return the card
	 */
	public Card getCard() {
		return m_card;
	}

}
