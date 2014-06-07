/**
 * @author Mark Rustad
 * @version .01
 * @date May 3, 2014
 */
package swccgManager.Database;

/**
 * Generic Queries handles basic query writing and result sets.
 * The queries stored here are information finding queries and simply return the results.
 * Used for basic and repeatable information
 * 
 * @author Mark
 *
 */

import java.sql.*;
import java.util.*;

import swccgManager.Database.CardQueryCriteria.Attribute;
import swccgManager.Models.Card;
import swccgManager.Models.Collection;

public class GenericSQLQueries {
	private SqlUtilities sqlUtil;
	
	public GenericSQLQueries()
	{
		sqlUtil = new SqlUtilities();
	}
	
	/**
	 * Returns a list of values from any column in the database
	 * Uses SELECT DISTINCT to gather a list of unique values
	 * 
	 * @param table Table the column can be found
	 * @param column Field to search
	 * @return All unique fields in that column.
	 */
	public ResultSet getList(String table, String column)
	{
		ResultSet list = null;
		
		//Write sql using preparedStatement
		String getListQuery = "SELECT DISTINCT " + column + " " 
				+ "FROM " + table + " "
				+ "ORDER BY " + column;
		
		list = sqlUtil.getQueryResults(getListQuery);
		
		//Get results and return them
		return list;
	}
	
	/**
	 * Get the most important card vitals information:
	 * id, Name, Side(Grouping), Cardtype, Subtype, Expansion, and Rarity, Uniqueness
	 * 
	 * @param swdb Connection to the DB
	 * @return List all cards and their "vital" information
	 */
	public List<Card> getCardList(CardQueryCriteria cardCriteria)
	{
		Connection swdb = sqlUtil.getDbConnection();
		ResultSet cardListResultSet = null;
		PreparedStatement cardList = null;
		List<Card> list = new ArrayList<Card>();
		try{
			 cardList = swdb.prepareStatement(
					 "SELECT id, cardName, Grouping, CardType, SubType, Expansion, Rarity, Uniqueness "
					+ "FROM SWD "
					+ "WHERE cardName LIKE ? "
					+ "	AND Grouping LIKE ? "
					+ " AND CardType LIKE ? "
					+ " AND Subtype LIKE ? "
					+ " AND Expansion LIKE ? "
					+ " AND Rarity LIKE ? "
					+ " AND Characteristics LIKE ? "
					+ " AND Lore LIKE ? "
					+ " AND Gametext LIKE ? "
					+ "ORDER BY cardName "
					);
			
			//Add all the sorting criteria
			cardList.setString(1, cardCriteria.getCriteria(Attribute.NAME));
			cardList.setString(2, cardCriteria.getCriteria(Attribute.SIDE));
			cardList.setString(3, cardCriteria.getCriteria(Attribute.TYPE));
			cardList.setString(4, cardCriteria.getCriteria(Attribute.SUBTYPE));
			cardList.setString(5, cardCriteria.getCriteria(Attribute.EXPANSION));
			cardList.setString(6, cardCriteria.getCriteria(Attribute.RARITY));
			cardList.setString(7, cardCriteria.getCriteria(Attribute.CHARACTERISTICS));
			cardList.setString(8, cardCriteria.getCriteria(Attribute.LORE));
			cardList.setString(9, cardCriteria.getCriteria(Attribute.GAMETEXT));
			//System.out.println(cardList.toString());//debugging
			cardListResultSet = cardList.executeQuery();
			
			//Add cards to the list to return
			while (cardListResultSet.next())
			{
				//It is quickest use a query and fill a list from the query
				//creating a new connection for each card takes too long
				int cardID = cardListResultSet.getInt("id");
				String cardName = cardListResultSet.getString("cardName");
				String side = cardListResultSet.getString("Grouping");
				String cardType = cardListResultSet.getString("CardType");
				String subType = cardListResultSet.getString("SubType");
				String expansion = cardListResultSet.getString("Expansion");
				String rarity = cardListResultSet.getString("Rarity");
				String uniqueness = cardListResultSet.getString("Uniqueness");
				Card newCard = new Card (cardID, cardName, side, cardType, subType, expansion, rarity, uniqueness);
				list.add(newCard);
			}
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println(cardList.toString());
		}
		sqlUtil.closeDB(swdb);
		//return results
		return list;
	}
	
	
	/**
	 * Retrieves important card information from the database for a single card
	 * @param swdb Connection to the database
	 * @param cardId id of the card
	 * @return ResultSet containing all the global important card information
	 */
	public ResultSet getCardVitals(int cardId)
	{
		Connection swdb = sqlUtil.getDbConnection();
		String cardVitalsQuery = 
				"SELECT	id, cardName, Grouping, CardType, SubType, Expansion, Rarity, Uniqueness "
					+ "FROM SWD "
					+ "WHERE id = " + cardId;
	
		ResultSet cardInfo = sqlUtil.getQueryResults(swdb, cardVitalsQuery);
		//System.out.println("one card getCardVitals() Executed.");
		sqlUtil.closeDB(swdb);
		return cardInfo;
	}
	
	/**
	 * Gets all the important information for a particular collection
	 * @param collectionName
	 * @return
	 */
	public ResultSet getCollectionVitals(String collectionName)
	{
		Connection swdb = sqlUtil.getDbConnection();
		PreparedStatement collectionVitalsQuery;
		ResultSet collectionInfo = null;
		try {
			collectionVitalsQuery = swdb.prepareStatement(
					"SELECT CollectionName, CollectionDescription FROM CollectionList "
							+ "WHERE CollectionName = ?"
					);
		collectionVitalsQuery.setString(1, collectionName);		
		collectionInfo = collectionVitalsQuery.executeQuery();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sqlUtil.closeDB(swdb);
		return collectionInfo;
		
	}
	
	/**
	 * Provides a simple list of every Collection in the db by name and description
	 * @param swdb
	 * @return List of the collections
	 */
	public Collection[] getCollectionList()
	{
		
		SqlUtilities su = new SqlUtilities();
		Connection swdb = su.getDbConnection();
		String collectionListQuery = "SELECT * FROM CollectionList Order By CollectionName";
		ResultSet collectionList = sqlUtil.getQueryResults(swdb, collectionListQuery);
		
		//Create an arraylist to turn into a collection array
		ArrayList<Collection> collectionList_al = new ArrayList<Collection>();
		try {
			while (collectionList.next())
			{
				String name = collectionList.getString("CollectionName");
				String description = collectionList.getString("CollectionDescription");
				Collection newCollection = new Collection(name, description);
				collectionList_al.add(newCollection);
			}
			collectionList.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		int numCollections = collectionList_al.size();
		Collection[] collectionList_ar = new Collection[numCollections];
		collectionList_al.toArray(collectionList_ar);
		
		su.closeDB(swdb); 
		return collectionList_ar;
				
	}
	
	/****Obsolete code
	 *  testing code that does not need to be used in an active program
	 */
	
	/**
	 * Gets a query that checks for cards that don't have a matched image
	 * @param swdb Connection to the SW database
	 * @return ResultSet list of cards that don't have matched images
	 
	public ResultSet getCardsWithoutImages (Connection swdb)
	{
		String cardsWOImages = "SELECT "
				+ "id, cardName, Grouping, CardType, SubType, Expansion, Rarity "
				+ "FROM SWD s "
				+ "WHERE NOT EXISTS "
				+ "	(SELECT * "
				+ "FROM ImagePaths ip "
				+ "WHERE ip.cardID = s.id);";
		ResultSet cardList = sqlUtil.getQueryResults(swdb, cardsWOImages);
		return cardList;
		
	}
	
	/**
	 * Looks for Objective card types where the there are not 2 images
	 * Test needed, as Objectives have both a front and a back image
	 * 
	 * @param swdb Connection to the SW database
	 * @return ResultSet list of the Objective cards with the wrong number of images
	 
	public  ResultSet objectivesWithWrongNumberofImages(Connection swdb)
	{
		String objectiveImageIssueList =
				"SELECT "
				+ "id, cardName, Grouping, COUNT(ip.large) AS Num_Images, CardType, Expansion, Rarity, Destiny, ObjectiveFront, ObjectiveBack, "
				+ "ObjectiveFrontName, ObjectiveBackName, Gametext, Icons, Episode1 "
				+"FROM SWD s "
				+"JOIN ImagePaths ip ON s.id = ip.cardID "
				+"WHERE cardType = 'Objective' "
				+"GROUP BY id, cardName,CardType, Expansion, Rarity, Destiny, ObjectiveFront, ObjectiveBack, "
				+" ObjectiveFrontName, ObjectiveBackName, Gametext, Icons, Episode1 "
				+"HAVING Num_Images != 2";
		ResultSet cardList = sqlUtil.getQueryResults(swdb, objectiveImageIssueList);
		return cardList;
	}
	
	/**
	 * Gets a list of all the image paths
	 * @param swdb connection to the db
	 * @return List of all the image paths, matched to the cardId
	 
	public ResultSet allLargeImagePaths(Connection swdb)
	{
		String allImagePathsQuery = 
				"SELECT * "
				+ "FROM ImagePaths";
		
		ResultSet imagePaths = sqlUtil.getQueryResults(swdb, allImagePathsQuery);
		return imagePaths;
	}
	
	****/
}
