/**
 * @author Mark Rustad
 * @version .01
 * @date May 3, 2014
 */
package swccgManager;

/**
 * Generic Queries handles basic query writing and result sets.
 * The queries stored here are fixed, they take no arguments and simply return the results.
 * Used for basic and repeatable information
 * 
 * @author Mark
 *
 */

import java.sql.*;

public class GenericSQLQueries {
	/**
	 * Get the most important card vitals information:
	 * id, Name, Side(Grouping), Cardtype, Subtype, Expansion, and Rarity
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
					"SELECT	id, cardName, Grouping, CardType, SubType, Expansion, Rarity "
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
				+ "id, cardName, COUNT(ip.large) AS Num_Images, CardType, Expansion, Rarity, Destiny, ObjectiveFront, ObjectiveBack, "
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
	 * Generic query creator. Handles all the SQL transactions for the query and returns a ResultSet of that query
	 * @param swdb Connection to the SW db
	 * @param query SQL query for the desired results
	 * @return Results of the query
	 */
	private static ResultSet getQueryResults(Connection swdb, String query)
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
