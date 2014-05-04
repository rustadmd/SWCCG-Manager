/**
 * @author Mark Rustad
 * @version .01
 * @date May 3, 2014
 */
package swccgManager;

/**
 * @author Mark
 *
 */

import java.sql.*;

public class GenericSQLQueries {
	
	public static ResultSet getCardVitals(Connection swdb)
	{
		ResultSet cardVitals = null;
		try{
			Statement statement = swdb.createStatement();
			//write query
			String cardVitalQuery = 
					"SELECT	id, cardName, Grouping, CardType, SubType, Expansion, Rarity "
					+ "FROM SWD "
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
	
	public static ResultSet getCollectionList(Connection swdb)
	{
		String collectionListQuery = "SELECT * FROM CollectionList";
		return getQueryResults(swdb, collectionListQuery);
	}
	
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
