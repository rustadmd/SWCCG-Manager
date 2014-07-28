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
	private CollectionList m_collectionList;
	private CardCollectionStatsModel activeCardCollectionStatsModel;
	
	public CardCollectionInfoModel()
	{
		cardList = new CardList();
		m_collectionList = new CollectionList();
		
		//Display initial information
		Card firstCard = cardList.getElementAt(0);
		Collection firstCollection = m_collectionList.getElementAt(0);
		activeCardCollectionStatsModel = new CardCollectionStatsModel(firstCard, firstCollection);
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
		return m_collectionList;
	}

	/**
	 * @return the activeCard
	 */
	public Card getActiveCard() {
		return activeCardCollectionStatsModel.getCard();
	}

	/**
	 * @return the activeCardCollectionStatsModel
	 */
	public CardCollectionStatsModel getActiveCardCollectionStatsModel() {
		return activeCardCollectionStatsModel;
	}

	/**
	 * @param activeCardCollectionStatsModel the activeCardCollectionStatsModel to set
	 */
	public void setActiveCardCollectionStatsModel(Card card, Collection collection) {
		
		activeCardCollectionStatsModel = new CardCollectionStatsModel(card, collection);
	}

	/**
	 * @return the activeCollection
	 */
	public Collection getActiveCollection() {
		return activeCardCollectionStatsModel.getCollection();
	}

}
