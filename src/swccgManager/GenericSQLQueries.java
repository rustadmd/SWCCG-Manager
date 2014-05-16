/**
 * @author Mark Rustad
 * @version .01
 * @date May 3, 2014
 */
package swccgManager;

/**
 * Generic Queries handles basic query writing and result sets.
 * The queries stored here are information finding queries and simply return the results.
 * Used for basic and repeatable information
 * 
 * @author Mark
 *
 */

import java.sql.*;

public class GenericSQLQueries {
	/**
	 * Get the most important card vitals information:
	 * id, Name, Side(Grouping), Cardtype, Subtype, Expansion, and Rarity, Uniqueness
	 * 
	 * @param swdb Connection to the DB
	 * @return List all cards and their "vital" information
	 */
	public static ResultSet getCardVitals(Connection swdb)
	{
		ResultSet cardVitals = null;
		try{
			Statement statement = swdb.createStatement();
			//write query
			String cardVitalQuery = 
					"SELECT	id, cardName, Grouping, CardType, SubType, Expansion, Rarity, Uniqueness "
					+ "FROM SWD "
					+ "ORDER BY Expansion, Grouping"
					//+ "WHERE id < 100"//Current limit, only want 20 at first
					;
			cardVitals = statement.executeQuery(cardVitalQuery);
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		//return results
		return cardVitals;
	}
	
	
	/**
	 * Retrieves important card information from the database for a single card
	 * @param swdb Connection to the database
	 * @param cardId id of the card
	 * @return ResultSet containing all the global important card information
	 */
	public static ResultSet getCardVitals(Connection swdb, int cardId)
	{

		String cardVitalsQuery = 
				"SELECT	id, cardName, Grouping, CardType, SubType, Expansion, Rarity, Uniqueness "
					+ "FROM SWD "
					+ "WHERE id = " + cardId;
	
		ResultSet cardInfo = getQueryResults(swdb, cardVitalsQuery);
		//System.out.println("one card getCardVitals() Executed.");
		return cardInfo;
	}
	/**
	 * Provides a simple list of every Collection in the db by name and description
	 * @param swdb
	 * @return List of the collections
	 */
	public static ResultSet getCollectionList(Connection swdb)
	{
		String collectionListQuery = "SELECT * FROM CollectionList";
		return getQueryResults(swdb, collectionListQuery);
	}
	/**
	 * Gets a query that checks for cards that don't have a matched image
	 * @param swdb Connection to the SW database
	 * @return ResultSet list of cards that don't have matched images
	 */
	public static ResultSet getCardsWithoutImages (Connection swdb)
	{
		String cardsWOImages = "SELECT "
				+ "id, cardName, Grouping, CardType, SubType, Expansion, Rarity "
				+ "FROM SWD s "
				+ "WHERE NOT EXISTS "
				+ "	(SELECT * "
				+ "FROM ImagePaths ip "
				+ "WHERE ip.cardID = s.id);";
		ResultSet cardList = getQueryResults(swdb, cardsWOImages);
		return cardList;
		
	}
	
	/**
	 * Looks for Objective card types where the there are not 2 images
	 * Test needed, as Objectives have both a front and a back image
	 * 
	 * @param swdb Connection to the SW database
	 * @return ResultSet list of the Objective cards with the wrong number of images
	 */
	public static ResultSet objectivesWithWrongNumberofImages(Connection swdb)
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
		ResultSet cardList = getQueryResults(swdb, objectiveImageIssueList);
		return cardList;
	}
	
	/**
	 * Gets a list of all the image paths
	 * @param swdb connection to the db
	 * @return List of all the image paths, matched to the cardId
	 */
	public static ResultSet allLargeImagePaths(Connection swdb)
	{
		String allImagePathsQuery = 
				"SELECT * "
				+ "FROM ImagePaths";
		
		ResultSet imagePaths = getQueryResults(swdb, allImagePathsQuery);
		return imagePaths;
	}
	
	/**
	 * Generic query creator. Handles all the SQL transactions for the query and returns a ResultSet of that query
	 * @param swdb Connection to the SW db
	 * @param query SQL query for the desired results
	 * @return Results of the query
	 */
	public static ResultSet getQueryResults(Connection swdb, String query)
	{
		ResultSet queryResults = null;
		try{
			Statement statement = swdb.createStatement();
			//write query
			queryResults = statement.executeQuery(query);
			//System.out.println("Current row: " + queryResults.getRow());//debugging
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		//return results
		return queryResults;
	}		
	
	
}
