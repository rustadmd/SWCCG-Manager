package swccgManager.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import swccgManager.Controllers.UpdateDeckCollectionPanelAction;
import swccgManager.Database.GenericSQLQueries;
import swccgManager.Models.UnEditableTableModel;

public class DeckCollectionView extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 6414873915778959632L;
	private DeckDisplay m_dd;
	private CollectionDisplay m_cd;
	
	private JTable m_allMissingTable;
	private JTable m_colMissingTable;

	public DeckCollectionView() {
		super();
		this.m_dd = new DeckDisplay();
		

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(m_dd, gbc);
		
		
		setupAllMissingTable();
		setupColMissingTable();
		updateDisplay();
		
		UpdateDeckCollectionPanelAction action = new UpdateDeckCollectionPanelAction(this);
		m_cd.addCollectionSelectedAction(action);
		m_dd.addDeckSelectedAction(action);
	}
	
	private void fillMissingTable(HashMap<String, Integer> missingMap, JTable table)
	{
		UnEditableTableModel model = new UnEditableTableModel();
		table.setModel(model);
		model.addColumn("Name");
		model.addColumn("Needed");
		TableColumnModel columns = table.getColumnModel();
		TableColumn nameColumn = columns.getColumn(0);
		nameColumn.setMinWidth(150);
		
		TableColumn countColumn = columns.getColumn(1);
		countColumn.setPreferredWidth(20);
		
		for (Entry<String, Integer> entry : missingMap.entrySet()) {
		    Object[] row = {entry.getKey(), entry.getValue()};
			model.addRow(row);
		}
		
	}
	
	private void setupAllMissingTable()
	{
		TitledBorderPanel panel = new TitledBorderPanel("Cards Needed Using All Collections");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(panel, gbc);
		m_allMissingTable = new JTable();
		
		JScrollPane scrollpane = new JScrollPane(m_allMissingTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panel.add(scrollpane);
		
		Dimension d = new Dimension();
		d.height = 200;
		d.width = 300;
		m_allMissingTable.setPreferredScrollableViewportSize(d);
	}
	
	private void setupColMissingTable()
	{
		TitledBorderPanel panel = new TitledBorderPanel("Cards Needed Using One Collections");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		add(panel, gbc);
		panel.setLayout(new BorderLayout());
		
		this.m_cd = new CollectionDisplay();
		panel.add(m_cd, BorderLayout.NORTH);
		
		m_colMissingTable = new JTable();
		
		JScrollPane scrollpane = new JScrollPane(m_colMissingTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panel.add(scrollpane, BorderLayout.CENTER);
		
		Dimension d = new Dimension();
		d.height = 200;
		d.width = 300;
		m_colMissingTable.setPreferredScrollableViewportSize(d);
	}
	
	public void updateDisplay() 
	{
		m_dd.updateCardCount();
		
		String deckName = m_dd.getSelectedDeck().getName();
		HashMap<String, Integer> missingMap = new GenericSQLQueries().getNeededCards(deckName);
		fillMissingTable(missingMap, m_allMissingTable);
		
		String colName = m_cd.getSelectedCollection().getCollectionName();
		HashMap<String, Integer> colMissingMap = new GenericSQLQueries().getNeededCards(deckName, colName);
		fillMissingTable(colMissingMap, m_colMissingTable);
	}
	

}
