/**
 *
 */
package swccgManager.GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import swccgManager.Controllers.CardChangedAction_CV;
import swccgManager.Models.CardList;

/**
 * @author Mark Rustad
 * @version .01
 * @date Sep 14, 2014
 *
 */
public class SearchAndListPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 4658457923114253028L;

	private CardListPanel listDisplay;
	private SearchDisplay searchPanel;
	//private CollectionDisplay collectionDisplay;

	public SearchAndListPanel(CollectionView cv, CollectionDisplay cd)
	{
		setLayout(new BorderLayout());

		//collectionDisplay = cd;

		//Add card selection list
		//CardCollectionInfoModel model = new CardCollectionInfoModel();
		CardList cardList = new CardList();
		listDisplay = new CardListPanel(cardList);
		//add listener for changes
		CardChangedAction_CV cca = new CardChangedAction_CV(cv);
		listDisplay.addCardChangedAction(cca);
		listDisplay.addListDataListener(cca);
		add(listDisplay, BorderLayout.SOUTH);

		//add the search panel
		searchPanel = new SearchDisplay(listDisplay, cd);
		add(searchPanel, BorderLayout.CENTER);
	}

	public SearchAndListPanel(EditDeckView dv, DeckDisplay dd) {
		setLayout(new BorderLayout());
		CardList cardList = new CardList();
		listDisplay = new CardListPanel(cardList);
		//add listener for changes
		//CardChangedAction_CV cca = new CardChangedAction_CV(cv);
		//listDisplay.addCardChangedAction(cca);
		//listDisplay.addListDataListener(cca);
		add(listDisplay, BorderLayout.SOUTH);

		//add the search panel
		searchPanel = new SearchDisplay(listDisplay, dd);
		add(searchPanel, BorderLayout.CENTER);

	}

	public SearchAndListPanel(CollectionView cv)
	{
		this(cv, null);
	}

	/**
	 * @return the cardList
	 */
	public CardListPanel getCardListPanel() {
		return listDisplay;
	}

	/**
	 * @return the searchPanel
	 */
	public SearchDisplay getSearchPanel() {
		return searchPanel;
	}




}
