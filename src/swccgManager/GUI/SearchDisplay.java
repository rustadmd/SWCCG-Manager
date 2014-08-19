/**
 * 
 */
package swccgManager.GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import swccgManager.Controllers.PerformSearch;
import swccgManager.Database.GenericSQLQueries;
import swccgManager.Models.CardList;

/**
 * @author Mark Rustad
 * @version .01
 * @date Aug 3, 2014
 *
 */
public class SearchDisplay extends TitledBorderPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2839662535202758973L;
	
	private CardList listModel;
	private PerformSearch performSearch;
	
	//options
	private JRadioButton all, light, dark;
	private JCheckBox trilogy, epiOne, virtual;
	JComboBox<String> cardTypeSelector, expansionSelector;

	public SearchDisplay(CardList cardList)
	{
		super("Search");
		listModel = cardList;
		performSearch = new PerformSearch(listModel, this);
		
		
		//add layouts
		int numRows = 4;
		setLayout(new GridLayout(numRows, 1));
		addSidePanel();
		addTypePanel();
		addExpansionPanel();
		addRealmPanel();
		
	}
	
	/**
	 * Gets the selected card type in SQL formatting
	 * @return SQL formatted card type
	 */
	public String getSelectedCardType()
	{
		String cardType;
		if(cardTypeSelector.getSelectedItem() == "All")
		{
			cardType = "%";
		}
		else
		{
			cardType = (String) cardTypeSelector.getSelectedItem();
		}
		
		return cardType;
	}
	
	/**
	 * Gets the selected expansion in SQL formatting
	 * @return SQL formatted expansion
	 */
	public String getSelectedExpansion()
	{
		String expansion;
		if(expansionSelector.getSelectedItem() == "All")
		{
			expansion = "%";
		}
		else
		{
			expansion = (String) expansionSelector.getSelectedItem();
		}
		System.out.println(expansion);
		return expansion;
	}
	
	/**
	 * Gets the sql statement that will return the selected resutls
	 * @return SQL formatted string for WHERE clause
	 */
	public String getSelectedSide()
	{
		String sideSql = "%";
		if(all.isSelected())
		{
			sideSql = "%";
		}
		else if (light.isSelected())
		{
			sideSql = "Light";
		}
		else if (dark.isSelected())
		{
			sideSql = "Dark";
		}
		
		return sideSql;
	}
	
	public String getSelectedRealms()
	{
		String realmSql = "";
		if(trilogy.isSelected())
		{
			realmSql += "'A New Hope', 'Cloud City', 'Dagobah', 'Death Star II', 'Endor', 'Enhanced Cloud City', 'Enhanced Jabba''s Palace', 'Enhanced Premiere Pack', 'Hoth', 'Hoth 2 Player', 'Jabba''s Palace', 'Jabba''s Palace Sealed Deck', 'Premiere', 'Premiere 2 Player', 'Reflections II', 'Reflections III', 'Special Edition', 'Third Anthology', 'Jedi Pack', 'Official Tournament Sealed Deck', 'Rebel Leader Cards'";
		}
		if(epiOne.isSelected())
		{
			//add divider for continuing series
			if(trilogy.isSelected())
			{
				realmSql +=" , ";
			}
			realmSql += "'Coruscant', 'Tatooine', 'Theed Palace'";
		}
		if(virtual.isSelected())
		{
			if(trilogy.isSelected() || epiOne.isSelected())
			{
				realmSql +=" , ";
			}
			realmSql +="'Virtual Card Set #1', 'Virtual Card Set #2', 'Virtual Card Set #3', 'Virtual Card Set #4', 'Virtual Card Set #5', 'Virtual Card Set #6', 'Virtual Card Set #7'";
		}
		
		return realmSql;
	}
	
	
	private void addTypePanel()
	{
		TitledBorderPanel cardTypePanel = new TitledBorderPanel("Type");
		
		cardTypeSelector = new JComboBox<String>();
		cardTypeSelector.addItem("All");
		
		//fill the remainder of the list from the DB
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet cardTypeList = gsq.getList("SWD", "CardType");
		try {
			while(cardTypeList.next())
			{
				String typeName = cardTypeList.getString("CardType");
				cardTypeSelector.addItem(typeName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cardTypeSelector.addActionListener(performSearch);
		cardTypePanel.add(cardTypeSelector);
		add(cardTypePanel);
	}
	
	/**
	 * Adds a Dropdown menu to select the expansion
	 */
	private void addExpansionPanel()
	{
		TitledBorderPanel expansionPanel = new TitledBorderPanel("Expansion");
		
		expansionSelector = new JComboBox<String>();
		expansionSelector.addItem("All");
		
		//fill the remainder of the list from the DB
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet cardTypeList = gsq.getList("SWD", "Expansion");
		try {
			while(cardTypeList.next())
			{
				String typeName = cardTypeList.getString("Expansion");
				expansionSelector.addItem(typeName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		expansionPanel.add(expansionSelector);
		expansionSelector.addActionListener(performSearch);
		add(expansionPanel);
	}
	/**
	 * Adds a radio button selection to limit the card list
	 */
	private void addSidePanel()
	{
		TitledBorderPanel sideSelection = new TitledBorderPanel("Side");
		int numRows = 1, numCols = 3;
		sideSelection.setLayout(new GridLayout(numRows, numCols));
		ButtonGroup sideButtonGroup = new ButtonGroup();
		
		//create and add buttons
		all = new JRadioButton("All");
		all.setSelected(true);
		sideButtonGroup.add(all);
		all.addActionListener(performSearch);
		sideSelection.add(all);
		
		light = new JRadioButton("Light");
		sideButtonGroup.add(light);
		light.addActionListener(performSearch);
		sideSelection.add(light);
		
		dark = new JRadioButton("Dark");
		sideButtonGroup.add(dark);
		dark.addActionListener(performSearch);
		sideSelection.add(dark);
		
		add(sideSelection);
	}
	
	private void addRealmPanel()
	{
		TitledBorderPanel realmPanel = new TitledBorderPanel("Realm");
		realmPanel.setLayout(new FlowLayout());
		
		trilogy = new JCheckBox("Original Trilogy");
		trilogy.setSelected(true);
		trilogy.addActionListener(performSearch);
		realmPanel.add(trilogy);
		
		epiOne = new JCheckBox("Episode I");
		epiOne.setSelected(true);
		epiOne.addActionListener(performSearch);
		realmPanel.add(epiOne);
		
		virtual = new JCheckBox("Virtual");
		virtual.addActionListener(performSearch);
		realmPanel.add(virtual);
		
		add(realmPanel);
	}
}
