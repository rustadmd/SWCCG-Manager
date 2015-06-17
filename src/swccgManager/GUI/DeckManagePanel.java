/**
 * 
 */
package swccgManager.GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import swccgManager.Database.GenericSQLQueries;
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
		add(addDeck_b, deck_gbc);
		
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
		
		updateDisplay();
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
