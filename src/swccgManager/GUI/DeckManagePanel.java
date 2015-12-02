/**
 *
 */
package swccgManager.GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import swccgManager.Database.GenericSQLQueries;
import swccgManager.Database.InsertQueries;
import swccgManager.Models.Deck;

/**
 * @author Mark Rustad
 * @version .01
 * @date Jun 17, 2015
 *
 */
public class DeckManagePanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 4887938579791066056L;
	JList<Deck> deckSelector = null;
	FieldDisplay name, createDate, modifyDate;
	LongDescription description, strategy, matchups, notes;

	public DeckManagePanel()
	{
		setLayout(new GridBagLayout());
		//managePanel.add(new JLabel("Test"));

		//add list
		GenericSQLQueries gsq = new GenericSQLQueries();
		DefaultListModel<Deck> deckList = gsq.getDeckList();
		deckSelector = new JList<Deck>();
		deckSelector.setModel(deckList);
		deckSelector.setSelectedIndex(0);
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(deckSelector);
		listScroller.setPreferredSize(new Dimension(300, 200));
		listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		deckSelector.setVisibleRowCount(30);
		deckSelector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints deck_gbc = new GridBagConstraints();
		deck_gbc.gridx = 0; deck_gbc.gridy = 1;
		add(deckSelector, deck_gbc);

		//add title
		deck_gbc.gridy = 0;
		add(new JLabel("Deck List"), deck_gbc);

		//add new deck button
		deck_gbc.gridy = 2;
		JButton addDeck_b = new JButton("Add New Deck");
		//Add the action action to add a new item
		ActionListener addDeck_al = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//System.out.println("View > Collection Selected");//testing
                @SuppressWarnings("unused")
                NewDeckWindow ncw = new NewDeckWindow();
			}
		};
		addDeck_b.addActionListener(addDeck_al);
		add(addDeck_b, deck_gbc);

		//add new deck button
		deck_gbc.gridy++;
		JButton deleteDeck_b = new JButton("Delete Deck");
		//Add the action action to add a new item
		ActionListener deleteDeck_al = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String deckName = deckSelector.getSelectedValue().getName();
				new InsertQueries().deleteDeck(deckName);
				deckSelector.setModel(new GenericSQLQueries().getDeckList());
				deckSelector.setSelectedIndex(0);
				updateDisplay();
			}
		};
		deleteDeck_b.addActionListener(deleteDeck_al);
		add(deleteDeck_b, deck_gbc);

		deck_gbc.gridy = 0;
		deck_gbc.gridx = 1;
		name = new FieldDisplay("Deck Name");
		add(name, deck_gbc);

		int rows = 8;
		int width = 35;

		deck_gbc.gridy++;
		description = new LongDescription("Description", rows, width);
		description.setEditable(true);
		add(description, deck_gbc);

		deck_gbc.gridy++;
		strategy = new LongDescription("Strategy", rows, width);
		strategy.setEditable(true);
		add(strategy, deck_gbc);

		deck_gbc.gridy++;
		matchups = new LongDescription("Matchups", rows, width);
		matchups.setEditable(true);
		add(matchups, deck_gbc);

		deck_gbc.gridy++;
		notes = new LongDescription("Notes", rows, width);
		notes.setEditable(true);
		add(notes, deck_gbc);

		deck_gbc.gridy++;
		JButton save_b = new JButton("Save");
		add(save_b, deck_gbc);

		if (deckSelector.getSelectedValue() != null) {
			updateDisplay();
		}
	}

	public void updateDisplay() {
		Deck curDeck = deckSelector.getSelectedValue();
		name.setValue(curDeck.getName());
		description.setText(curDeck.getDescription());
		strategy.setText(curDeck.getStrategy());
		matchups.setText(curDeck.getMatchups());
		notes.setText(curDeck.getNotes());
	}

}
