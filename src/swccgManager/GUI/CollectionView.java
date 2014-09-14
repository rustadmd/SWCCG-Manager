/**
 * 
 */
package swccgManager.GUI;

import java.awt.BorderLayout;

import javax.swing.*;

import swccgManager.Controllers.CollectionChangedAction_CV;
import swccgManager.Controllers.Shortcuts.DecrementInventory;
import swccgManager.Controllers.Shortcuts.IncrementInventory;
import swccgManager.Models.Card;
import swccgManager.Models.CardCollectionStatsModel;
import swccgManager.Models.CardList;
import swccgManager.Models.Collection;
import swccgManager.Models.CollectionList;
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
public class CollectionView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -192220976455137236L;
	
	//These are for menubar-level items. this way models can be called and act on the models
	private CardList cardList;
	private CollectionList collectionList;
	private CardCollectionStatsModel statsModel;
	
	//list of the panels
	CollectionDisplay collectionDisplay;
	SearchAndListPanel searchAndListPanel;
	CardDisplay cardDisplay;
	//CardListPanel listDisplay;
	CardCollectionStatsDisplay statsDisplay;

	public CollectionView()
	{

		//Set the layout
		setLayout(new BorderLayout());
		
		
		
		/**  replaced by search and List Panel
		searchAndListPanel.setLayout(new BorderLayout());
		
		//Add card selection list
		//CardCollectionInfoModel model = new CardCollectionInfoModel();
		cardList = new CardList();
		listDisplay = new CardListPanel(cardList);
		//add listener for changes
		CardChangedAction_CV cca = new CardChangedAction_CV(this);
		listDisplay.addCardChangedAction(cca);
		listDisplay.addListDataListener(cca);
		searchAndListPanel.add(listDisplay, BorderLayout.SOUTH);

		//add the search panel
		SearchDisplay searchPanel = new SearchDisplay(listDisplay);
		searchAndListPanel.add(searchPanel, BorderLayout.CENTER);
		*/
		
		
		//Add Collection Panel
		collectionList = new CollectionList();
		//setCollectionList(model.getCollectionList());
		collectionDisplay = new CollectionDisplay(collectionList);
		//add listener for changes
		CollectionChangedAction_CV colca = new CollectionChangedAction_CV(this);
		collectionDisplay.addCollectionSelectedAction(colca);
		
		add(collectionDisplay, BorderLayout.NORTH);
		
		searchAndListPanel = new SearchAndListPanel(this, collectionDisplay);
		add(searchAndListPanel, BorderLayout.WEST);
		
		//create the stats information
		Card selectedCard = getSelectedCard();
		Collection selectedCollection = getSelectedCollection();
		statsModel = new CardCollectionStatsModel(selectedCard, selectedCollection);
		
		//Add the basic card information
		cardDisplay = new CardDisplay(selectedCard);
		add(cardDisplay, BorderLayout.CENTER);
		
		//Add collection stats information
		statsDisplay = new CardCollectionStatsDisplay(statsModel);
		add(statsDisplay, BorderLayout.EAST);
		
		
		addKeyBindings();
		
	}
	
	/**
	 * method will create and update the stats model based on the current selections
	 * @param newCollecion
	 */
	public void updateStatsModel()
	{
		try{
		Collection collection = getSelectedCollection();
		Card card = getSelectedCard();
		statsModel = new CardCollectionStatsModel(card, collection);
		}
		//if the search returns no results
		catch (NullPointerException e)
		{
			
			System.out.println("Search parameters returned no results");
		}
	}
	
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
	
	public Collection getSelectedCollection()
	{
		Collection selectedCollection = collectionDisplay.getSelectedCollection();
		return selectedCollection;
	}
	
	public CollectionDisplay getCollectionDisplay()
	{
		return collectionDisplay;
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
	public CollectionList getCollectionList() {
		return collectionList;
	}

	/**
	 * @param collectionList the collectionList to set
	 */
	public void setCollectionList(CollectionList collectionList) {
		this.collectionList = collectionList;
	}
	
	public CardCollectionStatsModel getStatsModel()
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
	public CardCollectionStatsDisplay getStatsDisplay() {
		return statsDisplay;
	}

}
