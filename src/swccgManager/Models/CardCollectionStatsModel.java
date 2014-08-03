/**
 * 
 */
package swccgManager.Models;

import java.sql.*;

import javax.swing.SpinnerNumberModel;

import swccgManager.Controllers.UpdateInventory;
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
	private SpinnerNumberModel m_inventory = new SpinnerNumberModel(0, -99, 99, 1);;
	private int m_desired, m_extra, m_rating;
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
		//System.out.println("CardId: " + cardID + " Collection Name: " + collectionName);//debugging
		
		GenericSQLQueries gsq = new GenericSQLQueries();
		
		try{
			ResultSet cardCollectionStats_rs = gsq.getCardCollectionStats(cardID, collectionName);
			m_sortLocation = cardCollectionStats_rs.getString("sortLocation");
			
			int inventory = cardCollectionStats_rs.getInt("inventory");
			//System.out.println(inventory);//debugging
			m_inventory.setValue(inventory);
			m_desired = cardCollectionStats_rs.getInt("desired");
			m_extra = cardCollectionStats_rs.getInt("extra");
			m_rating = cardCollectionStats_rs.getInt("rating");
			m_comment = cardCollectionStats_rs.getString("comment");
			setInDB(true);
			//System.out.println("Name: " + m_card + " Inventory: " + inventory);//debugging
		}
		catch(SQLException e)//probably means there is nothing in the database
		{
			setInDB(false);
			//set everything to blank, so it can be displayed
			m_sortLocation = "";
			m_comment = "";
			//m_inventory.setValue(0);
			m_desired  = 0;
			m_extra  = 0;
			m_rating  = 0;
		}
		
		//add change listeners
		UpdateInventory updateInv = new UpdateInventory(this);
		m_inventory.addChangeListener(updateInv);
		
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
	public SpinnerNumberModel getInventoryModel() {
		return m_inventory;
	}
	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(SpinnerNumberModel inventory) {
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


	/**
	 * @return the isInDB
	 */
	public boolean getIsInDB() {
		return isInDB;
	}


	/**
	 * @param isInDB the isInDB to set
	 */
	public void setInDB(boolean isInDB) {
		this.isInDB = isInDB;
	}

}
