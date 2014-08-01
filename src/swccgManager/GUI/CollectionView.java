/**
 * 
 */
package swccgManager.GUI;

import java.awt.BorderLayout;

import javax.swing.*;

import swccgManager.Controllers.CardChangedAction_CV;
import swccgManager.Models.Card;
import swccgManager.Models.CardCollectionInfoModel;
import swccgManager.Models.CardCollectionStatsModel;
import swccgManager.Models.CardList;
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
	
	//list of the panels
	CollectionDisplay collectionDisplay;
	CardDisplay cardDisplay;
	CardListPanel listDisplay;

	public CollectionView()
	{

		//Set the layout
		setLayout(new BorderLayout());
		
		/**Trying to eliminate FullCardSet from use
		 * 
		SqlUtilities sqlUtil = new SqlUtilities();
		Connection swdb = sqlUtil.getDbConnection();
		FullCardSet test = new FullCardSet(swdb);
		sqlUtil.closeDB(swdb);**/
		
		//Test for db connection, works fine
		//GenericSQLQueries gsq = new GenericSQLQueries();
		//gsq.getCollectionList();
		
		//Add card selection list
		CardCollectionInfoModel model = new CardCollectionInfoModel();
		setCardList(model.getCardList());
		listDisplay = new CardListPanel(cardList);
		//add listener for changes
		CardChangedAction_CV cca = new CardChangedAction_CV(this);
		listDisplay.addCardChangedAction(cca);
		add(listDisplay, BorderLayout.WEST);
		
		//Add Collection Panel
		setCollectionList(model.getCollectionList());
		collectionDisplay = new CollectionDisplay(collectionList);
		add(collectionDisplay, BorderLayout.NORTH);
		
		//Add the basic card information
		Card selectedCard = listDisplay.getSelectedCard();
		cardDisplay = new CardDisplay(selectedCard);
		add(cardDisplay, BorderLayout.CENTER);
	}

	
	public Card getSelectedCard()
	{
		Card selectedCard = listDisplay.getSelectedCard();
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

}
