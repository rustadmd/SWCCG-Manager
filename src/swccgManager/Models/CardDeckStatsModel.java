/**
 *
 */
package swccgManager.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SpinnerNumberModel;

import swccgManager.Controllers.UpdateDeckInventory;
import swccgManager.Database.GenericSQLQueries;

/**
 * @author mrustad Rustad
 * @version .01
 * @date Nov 9, 2015
 *
 */
public class CardDeckStatsModel {

	private Card m_card;
	private Deck m_deck;
	private SpinnerNumberModel m_inventory = new SpinnerNumberModel(0, 0, 99, 1);
	private boolean isInDB = false;

	public CardDeckStatsModel(Card card, Deck deck) {
		m_card = card;
		m_deck = deck;

		GenericSQLQueries gsq = new GenericSQLQueries();

		try{
			ResultSet cardDeckStats_rs = gsq.getCardDeckStats(m_card.getCardId(), m_deck.getName());
			int inventory = cardDeckStats_rs.getInt("inventory");
			//System.out.println(inventory);//debugging
			m_inventory.setValue(inventory);
			setInDB(true);
			//System.out.println("Name: " + m_card + " Inventory: " + inventory);//debugging
			cardDeckStats_rs.close();
		}
		catch(SQLException e)//probably means there is nothing in the database
		{
			setInDB(false);
			//set everything to blank, so it can be displayed
		}

		UpdateDeckInventory udi = new UpdateDeckInventory(this);
		m_inventory.addChangeListener(udi);
	}

	public Card getCard() {
		return m_card;
	}
	public void setCard(Card card) {
		this.m_card = card;
	}
	public Deck getDeck() {
		return m_deck;
	}
	public void setDeck(Deck deck) {
		this.m_deck = deck;
	}
	public int getInventory() {
		return (int)m_inventory.getValue();
	}

	public SpinnerNumberModel getInventoryModel() {
		return m_inventory;
	}

	/**
	 * @return the isInDB
	 */
	public boolean getIsInDB() {
		return isInDB;
	}


	/**
	 * @param isInDB the isInDB to set
	 */
	public void setInDB(boolean isInDB) {
		this.isInDB = isInDB;
	}
}