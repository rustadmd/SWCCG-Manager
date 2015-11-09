/**
 *
 */
package swccgManager.Models;

import javax.swing.SpinnerNumberModel;

/**
 * @author mrustad Rustad
 * @version .01
 * @date Nov 9, 2015
 *
 */
public class CardDeckStatsModel {

	private Card m_card;
	private Deck m_deck;
	private SpinnerNumberModel m_inventory = new SpinnerNumberModel(0, -99, 99, 1);

	public CardDeckStatsModel(Card card, Deck deck) {
		m_card = card;
		m_deck = deck;
	}

	public Card getM_card() {
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


}