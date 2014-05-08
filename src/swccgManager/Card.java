/**
 * 
 */
package swccgManager;

import java.awt.*;
import java.sql.*;

/**
 * @author mdrustad Rustad
 * @version .01
 * @date May 8, 2014
 *
 */
public class Card {
	
	private int cardId;
	private String cardName;
	private String side;
	private String cardType;
	private String subType;
	private String expansion;
	private String rarity;
	private String uniqueness;
	private Image frontSideImage;
	
	public Card(Connection swdb, int cardId)
	{
		//Set all the information
		ResultSet cardInfo = GenericSQLQueries.getCardVitals(swdb, cardId);
		this.cardId = cardId;
		try {
			cardName = cardInfo.getString("cardName");
			side = cardInfo.getString("Grouping");
			cardType = cardInfo.getString("CardType");
			subType = cardInfo.getString("SubType");
			expansion = cardInfo.getString("Expansion");
			rarity = cardInfo.getString("Rarity");
			uniqueness = cardInfo.getString("Uniqueness");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ImageManager im = new ImageManager(swdb);
		frontSideImage = im.getCardImage(cardId, 1);//1 is always the front side
	}
	
	/**
	 * @return the cardId
	 */
	public int getCardId() {
		return cardId;
	}

	/**
	 * @return the cardName
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * @return the side
	 */
	public String getSide() {
		return side;
	}

	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @return the subType
	 */
	public String getSubType() {
		return subType;
	}

	/**
	 * @return the expansion
	 */
	public String getExpansion() {
		return expansion;
	}

	/**
	 * @return the rarity
	 */
	public String getRarity() {
		return rarity;
	}

	/**
	 * @return the uniqueness
	 */
	public String getUniqueness() {
		return uniqueness;
	}

	/**
	 * @return the frontSideImage
	 */
	public Image getFrontSideImage() {
		return frontSideImage;
	}

	
	 

}
