/**
 * 
 */
package swccgManager.Models;

/**
 * @author Mark Rustad
 * @version .01
 * @date Jun 7, 2014
 *
 */
public class CardCollectionInfoModel {
	
	private CardList cardList;
	private CollectionList collectionList;
	private Card activeCard;
	private Collection activeCollection;
	
	public CardCollectionInfoModel()
	{
		cardList = new CardList();
		collectionList = new CollectionList();
	}

	/**
	 * @return the cardList
	 */
	public CardList getCardList() {
		return cardList;
	}

	/**
	 * @return the collectionList
	 */
	public CollectionList getCollectionList() {
		return collectionList;
	}

	/**
	 * @return the activeCard
	 */
	public Card getActiveCard() {
		return activeCard;
	}

	/**
	 * @return the activeCollection
	 */
	public Collection getActiveCollection() {
		return activeCollection;
	}

}
