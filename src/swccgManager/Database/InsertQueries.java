/**
 * 
 */
package swccgManager.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import swccgManager.Models.Card;
import swccgManager.Models.Collection;


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
					+ " WHERE COllectionName = ? AND cardID = ? ");
			updateInventory.setInt(1, newValue);
			updateInventory.setString(2, collection.getCollectionName());	
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

}
