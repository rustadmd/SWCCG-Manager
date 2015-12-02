/**
 *
 */
package swccgManager.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import swccgManager.Models.Card;
import swccgManager.Models.Collection;
import swccgManager.Models.Deck;



/**
 * @author mdrustad Rustad
 * @version .01
 * @date May 23, 2014
 *
 */
public class InsertQueries {

	SqlUtilities su;

	public InsertQueries()
	{
		su = new SqlUtilities();
	}

	public void deleteCollection(String collectionName)
	{
		Connection swdb = su.getDbConnection();
		try {
			PreparedStatement deleteCollectionCards;
			deleteCollectionCards = swdb.prepareStatement(
					"DELETE FROM Collection "
					+ "WHERE CollectionName =  ? ");
			deleteCollectionCards.setString(1, collectionName);
			deleteCollectionCards.executeUpdate();

			PreparedStatement deleteCollection;
			deleteCollection = swdb.prepareStatement(
					"DELETE FROM CollectionList "
					+ "WHERE CollectionName =  ? ");
			deleteCollection.setString(1, collectionName);
			deleteCollection.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		su.closeDB();
	}

	public void deleteDeck(String deckName)
	{
		Connection swdb = su.getDbConnection();
		try {
			PreparedStatement deleteDeckCards;
			deleteDeckCards = swdb.prepareStatement(
					"DELETE FROM Deck "
					+ "WHERE DeckName =  ? ");
			deleteDeckCards.setString(1, deckName);
			deleteDeckCards.executeUpdate();

			PreparedStatement deleteDeck;
			deleteDeck = swdb.prepareStatement(
					"DELETE FROM DeckList "
					+ "WHERE DeckName =  ? ");
			deleteDeck.setString(1, deckName);
			deleteDeck.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		su.closeDB();
	}

	public void insertCardDeckInventory(Card card, Deck deck, int newValue)
	{
		PreparedStatement insertInventory;
		Connection swdb = su.getDbConnection();
		try {
			insertInventory = swdb.prepareStatement(
					"INSERT INTO Deck (DeckName, ID, Inventory) "
					+ "VALUES( ?, ?, ? ) ");
			insertInventory.setString(1, deck.getName());
			insertInventory.setInt(2, card.getCardId());
			insertInventory.setInt(3, newValue);
			insertInventory.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		su.closeDB();
	}

	public void insertCardInventory(Card card, Collection collection, int newValue)
	{
		PreparedStatement insertInventory;
		Connection swdb = su.getDbConnection();
		try {
			insertInventory = swdb.prepareStatement(
					"INSERT INTO Collection (CollectionName, CardID, Inventory) "
					+ "VALUES( ?, ?, ? ) ");
			insertInventory.setString(1, collection.getCollectionName());
			insertInventory.setInt(2, card.getCardId());
			insertInventory.setInt(3, newValue);
			insertInventory.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		su.closeDB();
	}

	public void updateCardInventory(Card card, Collection collection, int newValue)
	{
		PreparedStatement updateInventory;
		Connection swdb = su.getDbConnection();
		try {
			updateInventory = swdb.prepareStatement(
					"UPDATE Collection SET Inventory = ? "
					+ " WHERE CollectionName = ? AND cardID = ? ");
			updateInventory.setInt(1, newValue);
			updateInventory.setString(2, collection.getCollectionName());
			updateInventory.setInt(3, card.getCardId());

			updateInventory.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		su.closeDB();
	}

	public void updateCardDeckInventory(Card card, Deck deck, int newValue)
	{
		PreparedStatement updateInventory;
		Connection swdb = su.getDbConnection();
		try {
			updateInventory = swdb.prepareStatement(
					"UPDATE Deck SET Inventory = ? "
					+ " WHERE DeckName = ? AND ID = ? ");
			updateInventory.setInt(1, newValue);
			updateInventory.setString(2, deck.getName());
			updateInventory.setInt(3, card.getCardId());

			updateInventory.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		su.closeDB();
	}
	public void addCollection(String name, String description)
	{
		PreparedStatement insertCollection;
		Connection swdb = su.getDbConnection();
		try {
			insertCollection = swdb.prepareStatement(
				"INSERT INTO CollectionList (CollectionName, CollectionDescription) "
				+ " VALUES (?, ?);");
			insertCollection.setString(1, name);
			insertCollection.setString(2, description);
			insertCollection.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		su.closeDB();

	}

	public void addDeck(String name, String description)
	{
		PreparedStatement insertDeck;
		Connection swdb = su.getDbConnection();
		try {
			insertDeck = swdb.prepareStatement(
				"INSERT INTO DeckList (DeckName, DeckDescription) "
				+ " VALUES (?, ?);");
			insertDeck.setString(1, name);
			insertDeck.setString(2, description);
			insertDeck.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		su.closeDB();

	}
}
