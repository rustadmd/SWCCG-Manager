/**
 * 
 */
package swccgManager.Models;

import java.awt.*;
import java.sql.*;

import swccgManager.Database.GenericSQLQueries;
import swccgManager.Database.ImageManager;
import swccgManager.Database.SqlUtilities;

/**
 * Contains all of the generic attributes of a card. Can create a card based on the id.
 * Only attributes applicable to ALL cards are stored here.
 * 
 * @author mdrustad Rustad
 * @version .01
 * @date May 8, 2014
 *
 */
public class Card {
	
	private int m_cardId;
	private String m_cardName;
	private String m_side;
	private String m_cardType;
	private String m_subType;
	private String m_expansion;
	private String m_rarity;
	private String m_uniqueness;
	//private String m_frontSideImagePath;//currently unused, loads the image if there is none.
	private Image m_frontSideImage;
	
	/**
	 * Creates a card by pulling from the database all the important information
	 * @param swdb Connection to the db
	 * @param cardId ID number of the card to create
	 */
	public Card(int cardId)
	{	
		//Set all the information
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet cardInfo = gsq.getCardVitals(cardId);
		this.m_cardId = cardId;
		try {
			m_cardName = cardInfo.getString("cardName");
			m_side = cardInfo.getString("Grouping");
			m_cardType = cardInfo.getString("CardType");
			m_subType = cardInfo.getString("SubType");
			m_expansion = cardInfo.getString("Expansion");
			m_rarity = cardInfo.getString("Rarity");
			m_uniqueness = cardInfo.getString("Uniqueness");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		m_frontSideImage = null;//safety to ensure no image is there
		
		/*Unneeded at this time, get image will load the image if applicable
		//Set the image path, but don't actually load the image
		SqlUtilities su = new SqlUtilities();
		Connection swdb = su.getDbConnection();
		ImageManager im = new ImageManager(swdb);
		m_frontSideImagePath = im.getImagePath(cardId, 1);
		su.closeDB(swdb);*/
		System.out.println("Card created: " + m_cardId);
		
	}
	/**
	 * Creates a card, with full paramaters (this should aid in speed of retrieval, instead of calling a new query every time)
	 * @param cardId
	 * @param cardName
	 * @param side
	 * @param cardType
	 * @param subType
	 * @param expansion
	 * @param rarity
	 * @param uniqueness
	 * @param img Front side image of the card
	 */
	public Card (int cardId, String cardName, String side, String cardType, String subType, String expansion, String rarity, String uniqueness)
	{
		m_cardId = cardId;
		m_cardName = cardName;
		m_side = side;
		m_cardType = cardType;
		m_subType = subType;
		m_expansion = expansion;
		m_rarity = rarity;
		m_uniqueness = uniqueness;
	}
	/**
	 * Returns the full name as it would be printed on the card
	 */
	@Override
	public String toString()
	{
		String fullName = m_uniqueness + m_cardName;
		return fullName;
	}
	/**
	 * @return the cardId
	 */
	public int getCardId() {
		return m_cardId;
	}

	/**
	 * @return the cardName
	 */
	public String getCardName() {
		return m_cardName;
	}

	/**
	 * @return the side
	 */
	public String getSide() {
		return m_side;
	}

	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return m_cardType;
	}

	/**
	 * @return the subType
	 */
	public String getSubType() {
		return m_subType;
	}

	/**
	 * @return the expansion
	 */
	public String getExpansion() {
		return m_expansion;
	}

	/**
	 * @return the rarity
	 */
	public String getRarity() {
		return m_rarity;
	}

	/**
	 * @return the uniqueness
	 */
	public String getUniqueness() {
		return m_uniqueness;
	}

	/**
	 * @return the frontSideImage
	 */
	public Image getFrontSideImage() {
		
		//Load the card if it is not present
		if (m_frontSideImage == null)
		{
			SqlUtilities su = new SqlUtilities();
			Connection swdb = su.getDbConnection();
			ImageManager im = new ImageManager(swdb);
			m_frontSideImage = im.getCardImage(m_cardId, 1);
			su.closeDB(swdb);
		}
		//1 is always the front side
		return m_frontSideImage;
	}

	
	 

}
