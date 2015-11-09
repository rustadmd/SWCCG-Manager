/**
 *
 */
package swccgManager.GUI;

import java.awt.*;

import javax.swing.*;

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

	//display fields
	private JTextArea description_ta;

	public DeckDisplay(DefaultListModel<Deck> deckListModel)
	{
		setLayout(new FlowLayout());

		addDeckSelector(deckListModel);
		addDeckDescription();
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
	}

}
