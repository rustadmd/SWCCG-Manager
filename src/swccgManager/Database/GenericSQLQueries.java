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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

import javax.swing.DefaultListModel;

import swccgManager.Database.CardQueryCriteria.Attribute;
import swccgManager.Models.Card;
import swccgManager.Models.Collection;
import swccgManager.Models.Deck;

public class GenericSQLQueries {
	private SqlUtilities sqlUtil;

	public GenericSQLQueries()
	{
		sqlUtil = new SqlUtilities();
	}


	public void writeCollectionExportFile(File exportFile, String collectionName)
	{
		Connection swdb = sqlUtil.getDbConnection();
		try {
			//Establish print writer
			PrintWriter pw = new PrintWriter(exportFile);
			//build query
			PreparedStatement exportQuery = swdb.prepareStatement(
					"SELECT cardId, SortLocation, Inventory, "
					+ "Desired, Extra, Rating, Comment "
					+ "FROM Collection "
					+ "WHERE CollectionName = ? ");
			exportQuery.setString(1, collectionName);
			ResultSet exportResults = exportQuery.executeQuery();

			while (exportResults.next())
			{
				//get information from query
				int cardId = exportResults.getInt("cardId");
				String sortLocation = exportResults.getString("SortLocation");
				int inventory = exportResults.getInt("Inventory");
				int desired = exportResults.getInt("Desired");
				int extra = exportResults.getInt("Extra");
				int rating = exportResults.getInt("Rating");
				String comment = exportResults.getString("Comment");

				//write the output file
				String sep = "|";
				String cardEntry = cardId + sep + sortLocation + sep + inventory
						+ sep + desired + sep + extra + sep + rating + sep + comment;
				String fullLine = String.format("%s\n", cardEntry);
				//System.out.println(fullLine);//testing

				pw.write(fullLine);

			}
			pw.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the information concerning a card in a particular collection
	 * @param cardID
	 * @param collectionName
	 * @return
	 */
	public ResultSet getCardCollectionStats(int cardID, String collectionName)
	{
		Connection swdb = sqlUtil.getDbConnection();
		PreparedStatement cardCollectionStatsQuery;
		ResultSet cardCollectionStatsResults = null;
		try {
			cardCollectionStatsQuery = swdb.prepareStatement(
					"SELECT collectionName, cardID, sortLocation, inventory, desired, extra, rating, comment"
							+ " FROM Collection "
							+ "WHERE collectionName =  ? AND CardID = ?"
					);
			cardCollectionStatsQuery.setString(1, collectionName);
			cardCollectionStatsQuery.setInt(2, cardID);
			cardCollectionStatsResults = cardCollectionStatsQuery.executeQuery();
			//System.out.println("Collection Name from GSQ: " + cardCollectionStatsResults.getString(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//sqlUtil.closeDB(swdb);
		return cardCollectionStatsResults;
	}

	public ResultSet getCardDeckStats(int cardID, String deckName)
	{
		Connection swdb = sqlUtil.getDbConnection();
		PreparedStatement cardDeckStatsQuery;
		ResultSet cardDeckStatsResults = null;
		try {
			cardDeckStatsQuery = swdb.prepareStatement(
					"SELECT DeckName, ID, inventory"
							+ " FROM Deck "
							+ "WHERE deckName =  ? AND ID = ?"
					);
			cardDeckStatsQuery.setString(1, deckName);
			cardDeckStatsQuery.setInt(2, cardID);
			cardDeckStatsResults = cardDeckStatsQuery.executeQuery();
			//System.out.println("Deck Name from GSQ: " + cardDeckStatsResults.getString(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//sqlUtil.closeDB(swdb);
		return cardDeckStatsResults;
	}

	public int getDeckSize(String deckName)
	{
		Connection swdb = sqlUtil.getDbConnection();
		PreparedStatement deckCountQuery;
		ResultSet deckCountResults = null;
		int count = 0;
		try {
			deckCountQuery = swdb.prepareStatement(
					"SELECT Deck.deckName, SUM(Deck.inventory) as Count "
					+ "FROM Deck "
					+ "WHERE Deck.deckName = ? "
					+ "GROUP BY Deck.deckName"
					);
			deckCountQuery.setString(1, deckName);
			deckCountResults = deckCountQuery.executeQuery();
			//System.out.println("Deck Name from GSQ: " + cardDeckStatsResults.getString(1));
			count = deckCountResults.getInt("Count");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		sqlUtil.closeDB(swdb);
		return count;
	}
	public HashMap<String, Integer> getNeededCards(String deckName){
		return getNeededCards(deckName, null);
	}

	public HashMap<String, Integer> getNeededCards(String deckName, String collectionName) {
		Connection swdb = sqlUtil.getDbConnection();
		PreparedStatement deckCountQuery;
		ResultSet deckCountResults = null;
		String collectionTable = "";
		if (collectionName != null) {
			collectionTable = String.format(
					("(SELECT * FROM Collection "
					+ "WHERE Collection.CollectionName = '%s')"), collectionName);
		}
		else {
			collectionTable = "Collection";
		}
		HashMap<String, Integer> missingMap = new HashMap<String, Integer>();
		try {
			deckCountQuery = swdb.prepareStatement(
					"SELECT SWD.cardName as cardName, SUM(DISTINCT Deck.inventory) - Total(c.inventory) as Count "
					+ "FROM Deck "
					+ "LEFT JOIN SWD on Deck.ID = SWD.id "
					+ "LEFT JOIN " + collectionTable + " as c on Deck.id = c.cardId "
					+ "WHERE Deck.deckName = ? "
					+ "GROUP BY Deck.deckName, SWD.CardName "
					+ "HAVING Count > 0 or c.CollectionName IS NULL "
					+ "ORDER BY SWD.cardName"
					);
			deckCountQuery.setString(1, deckName);
			deckCountResults = deckCountQuery.executeQuery();
			//System.out.println("Deck Name from GSQ: " + cardDeckStatsResults.getString(1));
			while(deckCountResults.next())
			{
				missingMap.put(deckCountResults.getString("cardName"),
						(int)deckCountResults.getFloat("Count"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		sqlUtil.closeDB(swdb);
		return missingMap;
	}
	/**
	 * Tests the database to see if there is an entry in the system already
	 * helps test against duplicated entries
	 * @param column Column (usually a primary key) where the entry exists
	 * @param table The table the entry can be found on
	 * @param entry The exact entry
	 * @return True if there is an entry
	 */
	public boolean entryExists(String column, String table, String entry)
	{
		Connection swdb = sqlUtil.getDbConnection();

		int numRows = -1;
		try {
			PreparedStatement entryExistsQuery = swdb.prepareStatement(
					"SELECT DISTINCT " + column + " "
					+ "FROM " + table + " "
					+ "WHERE " + column + " = ? ");

			entryExistsQuery.setString(1, entry);

			ResultSet list = entryExistsQuery.executeQuery();
			numRows = sqlUtil.getNumRows(list);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("Number of rows: " + numRows);//debugging

		if (numRows != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
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
				+ " WHERE " + column + " != '' "//eliminate blanks
				+ "ORDER BY " + column;
		//System.out.println(getListQuery);//debugging
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
			String realmSql = cardCriteria.getCriteria(Attribute.REALM);

			//Collection filtering
			String collectionJoin;
			String collectionName = cardCriteria.getCriteria(Attribute.COL_NAME);
			if(collectionName == null){
				collectionJoin = "";
			}
			else {
				collectionJoin = " LEFT JOIN Collection ON SWD.id = Collection.cardId AND Collection.CollectionName = '" + collectionName + "' ";
			}
			String collectionWhere = cardCriteria.getCriteria(Attribute.COL_CONTAINS);
			if (collectionWhere == null){
				collectionWhere = "";
			}
			//System.out.println(collectionWhere);

			String deckJoin;
			String deckName = cardCriteria.getCriteria(Attribute.DECK_NAME);
			if (deckName == null) {
				deckJoin = "";
			}
			else {
				deckJoin = " LEFT JOIN Deck ON SWD.id = Deck.ID AND Deck.DeckName = '" + deckName + "' ";
			}
			String deckWhere = cardCriteria.getCriteria(Attribute.DECK_CONTAINS);
			if (deckWhere == null) {
				deckWhere = "";
			}

			cardList = swdb.prepareStatement(
					 "SELECT SWD.id, SWD.cardName, Grouping, SWD.CardType, SWD.SubType, SWD.Expansion, "
					 + "SWD.Rarity, SWD.Uniqueness, Information, IsPulled, IsCanceledBy "
					+ "FROM SWD "
					+ collectionJoin
					+ deckJoin
					+ " WHERE SWD.cardName LIKE ? "
					+ "	AND Grouping LIKE ? "
					+ " AND SWD.CardType LIKE ? "
					+ " AND SWD.Subtype LIKE ? "
					+ " AND SWD.Expansion LIKE ? "
					+ " AND SWD.Rarity LIKE ? "
					+ " AND Characteristics LIKE ? "
					+ " AND Lore LIKE ? "
					+ " AND Gametext LIKE ? "
					+ " AND Icons LIKE ? "
					+ " AND SWD.Expansion IN ( " + realmSql + " )"
					+ collectionWhere//for realm
					+ deckWhere
					+ "ORDER BY SWD.cardName "
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
			cardList.setString(10, cardCriteria.getCriteria(Attribute.ICON));
			//System.out.println(cardList.toString());//debugging DOESN'T PRINT SQL
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
				String info = cardListResultSet.getString("Information");
				String isPulled = cardListResultSet.getString("IsPulled");
				String isCancelled = cardListResultSet.getString("IsCanceledBy");
				Card newCard = new Card (cardID, cardName, side, cardType, subType, expansion,
						rarity, uniqueness, info, isPulled, isCancelled);
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
	 * Retrieves a list of all decks
	 * @return
	 */
	public DefaultListModel<Deck> getDeckList()
	{
		Connection swdb = sqlUtil.getDbConnection();
		ResultSet deckListRS = null;
		PreparedStatement deckList_sql = null;

		DefaultListModel<Deck> deckList = new DefaultListModel<Deck>();

		try{
			deckList_sql = swdb.prepareStatement(
					"SELECT DeckName, DeckDescription, DeckStrategy, "
					+ "DeckMatchups, DeckNotes, CreateDate, ModifyDate "
					+ "FROM DeckList "
					);
			deckListRS = deckList_sql.executeQuery();

			while(deckListRS.next())
			{
				deckList.addElement(
					new Deck(deckListRS.getString("DeckName"),
							deckListRS.getString("DeckDescription"),
							deckListRS.getString("DeckStrategy"),
							deckListRS.getString("DeckMatchups"),
							deckListRS.getString("DeckNotes"),
							deckListRS.getDate("CreateDate"),
							deckListRS.getDate("ModifyDate"))
					);
				//System.out.println("Deck Added");
			}
		}
		catch (SQLException e){
			System.out.println("Error retrieving list of decks");
			e.printStackTrace();
		}
		sqlUtil.closeDB(swdb);

		return deckList;
	}

	public String getCardField(Card c, String field)
	{
		String cardFieldQuery =
				"SELECT " + field
				+ " FROM SWD WHERE id = " + c.getCardId();

		ResultSet cardField = sqlUtil.getQueryResults(cardFieldQuery);
		String fieldVal = "ERROR";
		try {
			cardField.next();
			fieldVal = cardField.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("Card field information: " + fieldVal);
		return parseText(fieldVal);
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
				"SELECT	id, cardName, Grouping, CardType, SubType, Expansion, Rarity, Uniqueness, "
				+ " Information, IsPulled, IsCanceledBy "
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
							+ "WHERE CollectionName = ? "
					);
		collectionVitalsQuery.setString(1, collectionName);
		collectionInfo = collectionVitalsQuery.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//sqlUtil.closeDB(swdb);
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
	 */
	public ResultSet allLargeImagePaths(Connection swdb)
	{
		String allImagePathsQuery =
				"SELECT * "
				+ "FROM ImagePaths";

		ResultSet imagePaths = sqlUtil.getQueryResults(swdb, allImagePathsQuery);
		return imagePaths;
	}

	private String parseText(String s)
	{
		String parsedString = s.replace("\\par ", "\n");
		return parsedString;
	}
	//****/
}
