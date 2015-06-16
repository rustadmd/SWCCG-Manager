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
 * ChangeLog:
 * 		-2014-07-30: Changed from Image to ImageIcon
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
	
	private String m_isPulled, m_isCancelledBy, m_information;
	//private String m_frontSideImagePath;//currently unused, loads the image if there is none.
	private Image m_frontSideImage, m_rearSideImage;
	
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
			m_isPulled = cardInfo.getString("IsPulled");
			m_isCancelledBy = cardInfo.getString("IsCanceledBy");
			m_information = cardInfo.getString("Information");
			System.out.println("Card created: " + m_cardId);
		} catch (SQLException e) {
			//handle an empty card
			m_cardName = "No Results Returned";
			m_uniqueness = "";
			System.out.printf("Card could not be found: %d\n", m_cardId);
			//e.printStackTrace();
		}
		m_frontSideImage = null;//safety to ensure no image is there
		
		System.out.printf("%s, %s, %s\n", m_isPulled, m_isCancelledBy, m_isPulled);
		
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
	public Card (int cardId, String cardName, String side, String cardType, String subType, 
			String expansion, String rarity, String uniqueness, String info, String isPulled, String isCancelled)
	{
		m_cardId = cardId;
		m_cardName = cardName;
		m_side = side;
		m_cardType = cardType;
		m_subType = subType;
		m_expansion = expansion;
		m_rarity = rarity;
		m_uniqueness = uniqueness;
		m_information = info;
		m_isPulled = isPulled;
		m_isCancelledBy = isCancelled;
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
	
	public String getInformation() {
		return m_information;
	}
	
	public String getIsPulled() {
		return m_isPulled;
	}
	
	public String getIsCancelledBy() {
		return m_isCancelledBy;
	}

	/**
	 * @return the frontSideImage
	 */
	public Image getFrontSideImage() {
		
		//m_frontSideImage = null;
		//Load the card if it is not present
		if (m_frontSideImage == null)
		{
			//System.out.println("Front Side Image is null");
			
			SqlUtilities su = new SqlUtilities();
			Connection swdb = su.getDbConnection();
			ImageManager im = new ImageManager(swdb);
			Image image = im.getCardImage(m_cardId, 1);//1 is always the front side
			//m_frontSideImage.setImage(image);
			m_frontSideImage = image;
			
			su.closeDB(swdb);
		}
		
		return m_frontSideImage;
	}

	public Image getRearSideImage() {
		
		//m_frontSideImage = null;
		//Load the card if it is not present
		if (m_rearSideImage == null)
		{
			//System.out.println("Front Side Image is null");
			
			SqlUtilities su = new SqlUtilities();
			Connection swdb = su.getDbConnection();
			ImageManager im = new ImageManager(swdb);
			Image image = im.getCardImage(m_cardId, 2);//1 is always the front side
			//m_frontSideImage.setImage(image);
			m_rearSideImage = image;
			
			su.closeDB(swdb);
		}
		
		return m_rearSideImage;
	}
	 

}
