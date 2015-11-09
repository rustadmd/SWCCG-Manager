/**
 *
 */
package swccgManager.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import swccgManager.Database.GenericSQLQueries;
import swccgManager.GUI.SearchAndListPanel;
import swccgManager.Models.Card;
import swccgManager.Models.CardDeckStatsModel;
import swccgManager.Models.CardList;
import swccgManager.Models.Deck;


/**Main display for viewing a collection. This interface allows the user to browse
 * all cards, and see what cards they own.
 *
 * View class, controlled by
 *
 * @author Mark Rustad
 * @version .01
 * @date May 21, 2014
 *
 */
public class EditDeckView extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = -192220976455137236L;

	//These are for menubar-level items. this way models can be called and act on the models
	private CardList cardList;
	private DefaultListModel<Deck> m_deckList;
	private CardDeckStatsModel statsModel;

	//list of the panels
	DeckDisplay m_deckDisplay;
	SearchAndListPanel searchAndListPanel;
	CardDisplay cardDisplay;
	//CardListPanel listDisplay;
	CardDeckStatsDisplay statsDisplay;

	public EditDeckView()
	{

		//Set the layout
		setLayout(new GridBagLayout());
		int fullGbHeight = 3;

		//Add Deck Panel
		m_deckList = new GenericSQLQueries().getDeckList();
		//setCollectionList(model.getCollectionList());
		m_deckDisplay = new DeckDisplay(m_deckList);
		//add listener for changes
		//CollectionChangedAction_CV colca = new CollectionChangedAction_CV(this);
		//m_deckDisplay.addCollectionSelectedAction(colca);

		GridBagConstraints colDisplay = new GridBagConstraints();
		colDisplay.gridwidth = 2;
		colDisplay.gridx = 1;
		add(m_deckDisplay, colDisplay);

		searchAndListPanel = new SearchAndListPanel(this, m_deckDisplay);
		GridBagConstraints searchDisp = new GridBagConstraints();
		searchDisp.gridheight = fullGbHeight;
		searchDisp.fill = GridBagConstraints.VERTICAL;
		searchDisp.gridx = 0;
		add(searchAndListPanel, searchDisp);

		//create the stats information
		Card selectedCard = getSelectedCard();
		Deck selectedDeck = getSelectedDeck();
		statsModel = new CardDeckStatsModel(selectedCard, selectedDeck);

		//Add the basic card information
		cardDisplay = new CardDisplay(selectedCard);
		GridBagConstraints cardDisp = new GridBagConstraints();
		cardDisp.gridx = 1;
		cardDisp.gridy = 1;
		add(cardDisplay, cardDisp);

		//Add collection stats information
		statsDisplay = new CardDeckStatsDisplay(statsModel);
		GridBagConstraints statDisp = new GridBagConstraints();
		statDisp.gridx = 2;
		statDisp.gridy = 1;
		add(statsDisplay, statDisp);


		//addKeyBindings();

	}

	/**
	 * method will create and update the stats model based on the current selections
	 * @param newCollecion
	 */
	public void updateStatsModel()
	{
		try{
		Deck selectedDeck = getSelectedDeck();
		Card card = getSelectedCard();
		statsModel = new CardDeckStatsModel(card, selectedDeck);
		}
		//if the search returns no results
		catch (NullPointerException e)
		{

			System.out.println("Search parameters returned no results");
		}
	}
/**
	private void addKeyBindings()
	{
		//add inventory incrementer
		IncrementInventory invInc = new IncrementInventory(this);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), "pressedF1");
		getActionMap().put("pressedF1", invInc);

		DecrementInventory invDec = new DecrementInventory(this);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), "pressedF2");
		getActionMap().put("pressedF2", invDec);
	}
*/
	public Deck getSelectedDeck()
	{
		Deck selectedDeck = m_deckDisplay.getSelectedDeck();
		return selectedDeck;
	}

	public DeckDisplay getDeckDisplay()
	{
		return m_deckDisplay;
	}

	public Card getSelectedCard()
	{
		Card selectedCard = searchAndListPanel.getCardListPanel().getSelectedCard();
		return selectedCard;
	}
	/**
	 * Feeds a new card to the card display. Simply an access issue
	 * @param newCard
	 */
	public void updateCardDisplay(Card newCard)
	{
		cardDisplay.refreshDisplay(newCard);
	}
	/**
	 * @return the cardList
	 */
	public CardList getCardList() {
		return cardList;
	}

	/**
	 * @param cardList the cardList to set
	 */
	public void setCardList(CardList cardList) {
		this.cardList = cardList;
	}

	/**
	 * @return the collectionList
	 */
	public DefaultListModel<Deck> getDeckList() {
		return m_deckList;
	}

	/**
	 * @param deckList the collectionList to set
	 */
	public void setDeckList(DefaultListModel<Deck> deckList) {
		this.m_deckList = deckList;
	}

	public CardDeckStatsModel getStatsModel()
	{
		return statsModel;
	}

	/**
	 * @return the cardDisplay
	 */
	public CardDisplay getCardDisplay() {
		return cardDisplay;
	}

	/**
	 * @return the listDisplay
	 */
	public CardListPanel getListDisplay() {
		return searchAndListPanel.getCardListPanel();
	}

	/**
	 * @return the statsDisplay
	 */
	public CardDeckStatsDisplay getStatsDisplay() {
		return statsDisplay;
	}

}
