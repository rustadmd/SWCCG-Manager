/**
 *
 */
package swccgManager.GUI;

import java.awt.*;

import javax.swing.*;

import swccgManager.Database.GenericSQLQueries;
import swccgManager.Models.Deck;
import swccgManager.Models.DeckListComboBoxModel;

/**Panel for viewing important information about a deck and selecting different decks
 * @author Mark Rustad
 * @version .01
 * @date May 20, 2014
 *
 */
public class DeckDisplay extends JPanel{

	private static final long serialVersionUID = -2080540917727826932L;
	private JComboBox<Deck> deckSelector;
	private DefaultListModel<Deck> listModel;
	private DeckListComboBoxModel comboListModel;
	private FieldDisplay cardCountDisplay;

	//display fields
	private JTextArea description_ta;

	public DeckDisplay(DefaultListModel<Deck> deckListModel)
	{
		setLayout(new FlowLayout());

		if (deckListModel == null) {
			deckListModel = new GenericSQLQueries().getDeckList();
		}
		addDeckSelector(deckListModel);
		addDeckDescription();
	}

	public DeckDisplay(){
		this(null);
	}

	public void setSelectedDeck(int index)
	{
		deckSelector.setSelectedIndex(index);
	}

	public void addDeckSelectedAction(Action a)
	{
		deckSelector.addActionListener(a);
	}

	public Deck getSelectedDeck()
	{
		Deck selectedDeck = (Deck) deckSelector.getSelectedItem();
		return selectedDeck;
	}

	public void setDeckDescriptionDisplay(String description)
	{
		//System.out.println("Description: " + description);
		description_ta.setText(description);
	}
	/**
	 * adds the deck selector to the box
	 */
	private void addDeckSelector(DefaultListModel<Deck> deckListModel)
	{
		//create the combo box
		listModel = deckListModel;
		comboListModel = new DeckListComboBoxModel(listModel);
		if (comboListModel.getSize() == 0) {
			new NewDeckWindow();
		}
		deckSelector = new JComboBox<Deck>(comboListModel);
		//set default
		setSelectedDeck(0);
		add(deckSelector);
	}

	private void addDeckDescription()
	{
		description_ta = new JTextArea(3, 20);
		Deck initialDeck = getSelectedDeck();
		String initialDescription = initialDeck.getDescription();
		setDeckDescriptionDisplay(initialDescription);
		description_ta.setEditable(false);

		TitledBorderPanel descriptionPanel = new TitledBorderPanel("Description");
		description_ta.setBackground(descriptionPanel.getBackground());
		add(descriptionPanel);

		descriptionPanel.add(description_ta);
		
		//Add card count display
		cardCountDisplay = new FieldDisplay("Card Count");
		updateCardCount();
		this.add(cardCountDisplay);
	}
	
	public void updateCardCount() {
		getSelectedDeck().updateTotalCards();
		String count_s = Integer.toString(getSelectedDeck().getTotalCards());
		cardCountDisplay.setValue(count_s);
	}

}
