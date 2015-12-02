/**
 *
 */
package swccgManager.GUI;
import javax.swing.*;

import swccgManager.Database.GenericSQLQueries;
import swccgManager.Controllers.ExportDeckAction;
import swccgManager.Models.Deck;
import swccgManager.Models.DeckListComboBoxModel;

import java.awt.*;


/**
 * @author Mark Rustad
 * @version .01
 * @date May 26, 2014
 *
 */
public class ExportDeckWindow extends JFrame{

	private static final long serialVersionUID = 5151115731903601102L;
	private JButton submitButton;
	private JComboBox<Deck> deckSelector;
	private DeckListComboBoxModel comboListModel;

	public ExportDeckWindow()
	{
		super("Export Deck");
		setLayout(new BorderLayout());
		setVisible(true);

		//Add the instructions label
		String nameLabel_s = "Select a Deck to Export";
		JLabel nameLabel = new JLabel(nameLabel_s);
		add(nameLabel, BorderLayout.NORTH);

		//setup collection selector
		DefaultListModel<Deck> listModel = new GenericSQLQueries().getDeckList();
		comboListModel = new DeckListComboBoxModel(listModel);
		deckSelector = new JComboBox<Deck>(comboListModel);
		add(deckSelector, BorderLayout.CENTER);

		addSubmitButton();

		pack();
		this.setResizable(false);
	}

	/**
	 * Gets the collection name as selected from the drop down menu
	 * @return Selected collection name
	 */
	public String getDeckName()
	{
		Deck selectedDeck = comboListModel.getSelectedItem();
		String name = selectedDeck.getName();

		return name;
	}

	private void addSubmitButton()
	{
		ExportDeckAction exportAction = new ExportDeckAction(this);
		submitButton = new JButton(exportAction);
		submitButton.setText("Export Collection");
		this.add(submitButton, BorderLayout.SOUTH);
	}


}
