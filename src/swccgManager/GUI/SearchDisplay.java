/**
 * 
 */
package swccgManager.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import swccgManager.Controllers.PerformSearch;
import swccgManager.Database.GenericSQLQueries;

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
	
	private PerformSearch performSearch;
	private CollectionDisplay collectionDisplay;
	
	//list options
	private String[] iconList = {"Astromech", "Creature", "Droid", "Episode 1", "Exterior", "Grabber", 
			"Independent", "Interior", "Mobile", "Permanent Weapon", 
			"Pilot", "Planet", "Republic", "Scomp Link", 
			"Selective", "Space", "Starship", "Trade Federation", "Underground",
			"Underwater", "Vehicle", "Warrior"};
	
	//options
	private JRadioButton all, light, dark;
	private JCheckBox trilogy, epiOne, virtual;
	private JComboBox<String> raritySelector, cardTypeSelector, cardSubTypeSelector
		, expansionSelector, iconSelector;
	private JTextField nameFilter = new JTextField();
	private JTextField loreFilter = new JTextField();
	private JTextField gameTextFilter = new JTextField();
	private JRadioButton colContains, colNotContain, colAny;
	
	//Panel groups
	private JPanel basicSearchPanel = new JPanel();
	private JPanel advSearchPanel = new JPanel();
	
	public SearchDisplay(CardListPanel cardListDisplay, CollectionDisplay cd)
	{
		super("Search");
		
		collectionDisplay = cd;
		performSearch = new PerformSearch(cardListDisplay, this, cd);
		setLayout(new BorderLayout());
		
		//add layouts
		int numRows = 6;
		basicSearchPanel.setLayout(new GridLayout(numRows, 1));
		addSideAndRarityPanel();
		addTypePanel();
		addExpansionPanel();
		addRealmPanel();
		add(basicSearchPanel, BorderLayout.CENTER);
		
		//setup advanced panel
		setupAdvPanel();
		add(advSearchPanel, BorderLayout.EAST);
	}
	
	public SearchDisplay(CardListPanel cardListDisplay)
	{
		this(cardListDisplay, null);
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
	
	public String getSelectedSubType()
	{
		String subType;
		if(cardSubTypeSelector.getSelectedItem() == "All")
		{
			subType = "%";
		}
		else
		{
			subType = (String) cardSubTypeSelector.getSelectedItem();
		}
		
		return subType;
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
		//System.out.println(expansion);//debugging
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
	
	public String getSelectedRarity()
	{
		String rarity;
		//System.out.println("Selected Rarity: " + raritySelector.getSelectedItem());
		if(raritySelector.getSelectedItem() == "All")
		{
			rarity = "%";
		}
		else
		{
			rarity = "%"+ (String) raritySelector.getSelectedItem() +"%";
		}
		//System.out.println(rarity);//debugging
		return rarity;
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
	
	public String getNameFilter()
	{
		String nameSql = "%" + nameFilter.getText() + "%";
		return nameSql;
	}
	
	public String getLoreFilter()
	{
		String loreSql = "%" + loreFilter.getText() + "%";
		return loreSql;
	}
	
	public String getGameTextFilter()
	{
		String gameTextSql = "%" + gameTextFilter.getText() + "%";
		return gameTextSql;
	}
	
	public String getSelectedIcon()
	{
		String iconSql;
		if(iconSelector.getSelectedItem() == "All")
		{
			iconSql = "%";
		}
		else
		{
			iconSql = "%" + (String) iconSelector.getSelectedItem()+ "%";
		}
		//System.out.println(iconSql);//debugging
		return iconSql;
	}
	
	/**
	 * Retrieves the selected collection contains label, i.e. "Any", "Contains", "Not Contains"
	 * Returns Null if the filter does not exist
	 * 
	 * @return Selected collection contains filter
	 */
	public String getCollectionContains()
	{
		if (colAny.isSelected()){
			return colAny.getText();
		}
		else if (colContains.isSelected()){
			return colContains.getText();
		}
		else if (colNotContain.isSelected()){
			return colNotContain.getText();
		}
		else {
			return null;
		}
		
	}
	/**
	 * Adds all of the search options to the advSearchPanel. Does Not add the advPanel to the display
	 */
	private void setupAdvPanel()
	{
		int numRows;
		//------Add Collection filtering if applicable----//
		if(collectionDisplay == null) {
			numRows = 5;
		}
		else {
			numRows = 6;
			TitledBorderPanel collectionPanel = new TitledBorderPanel("Collection");
			ButtonGroup collectionGroup = new ButtonGroup();
			
			colAny = new JRadioButton("Any");
			collectionGroup.add(colAny);
			colAny.setSelected(true);
			colAny.addActionListener(performSearch);
			collectionPanel.add(colAny);
			//System.out.println(colAny.getText());
			
			colContains = new JRadioButton("Contains");
			collectionGroup.add(colContains);
			colContains.addActionListener(performSearch);
			collectionPanel.add(colContains);
			
			colNotContain = new JRadioButton("Not Contains");
			collectionGroup.add(colNotContain);
			colNotContain.addActionListener(performSearch);
			collectionPanel.add(colNotContain);
			
			advSearchPanel.add(collectionPanel);
		}
		advSearchPanel.setLayout(new GridLayout(numRows, 1));
		
		//----Add Card Name Filter----//
		TitledBorderPanel namePanel = new TitledBorderPanel("Name Contains");
		namePanel.add(nameFilter);
		nameFilter.setColumns(10);
		advSearchPanel.add(namePanel);
		
		//----- Add Lore Filter-----//
		TitledBorderPanel lorePanel = new TitledBorderPanel("Lore Contains");
		lorePanel.add(loreFilter);
		loreFilter.setColumns(10);
		advSearchPanel.add(lorePanel);
		
		//----- Add GameText Filter-----//
		TitledBorderPanel gameTextPanel = new TitledBorderPanel("Game Text Contains");
		gameTextFilter.setColumns(15);
		gameTextPanel.add(gameTextFilter);
		advSearchPanel.add(gameTextPanel);
		
		//----- Add Icon Filter ------//
		TitledBorderPanel iconPanel = new TitledBorderPanel("Icons");
		iconSelector = new JComboBox<String>();
		iconSelector.addItem("All");
		for(String icon: iconList){
			iconSelector.addItem(icon);
		}
		iconSelector.addActionListener(performSearch);
		iconPanel.add(iconSelector);
		advSearchPanel.add(iconPanel);
		
		//-----add search Button -----//
		JPanel searchButtonPanel = new JPanel(new FlowLayout());
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(performSearch);
		searchButtonPanel.add(searchButton);
		advSearchPanel.add(searchButtonPanel);
		
	}
	
	private void addTypePanel()
	{
		
		//***Add the super type panel*****//
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
		
		//******add the sub-type panel*****//
		TitledBorderPanel cardSubTypePanel = new TitledBorderPanel("Subtype");
		
		cardSubTypeSelector = new JComboBox<String>();
		cardSubTypeSelector.addItem("All");
		
		//fill the remainder of the list from the DB
		ResultSet cardSubTypeList = gsq.getList("SWD", "Subtype");
		try {
			while(cardSubTypeList.next())
			{
				String typeName = cardSubTypeList.getString("Subtype");
				cardSubTypeSelector.addItem(typeName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cardSubTypeSelector.addActionListener(performSearch);
		cardSubTypePanel.add(cardSubTypeSelector);
		
		//****Add both selectors to panels to display****//
		//JPanel typePanel = new JPanel();
		//typePanel.setLayout(new FlowLayout());
		basicSearchPanel.add(cardTypePanel);
		basicSearchPanel.add(cardSubTypePanel);
		//basicSearchPanel.add(typePanel);
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
		basicSearchPanel.add(expansionPanel);
	}
	/**
	 * Adds a radio button selection to limit the card list
	 */
	private void addSideAndRarityPanel()
	{
		//***Create Side Selector****//
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
		
		
		//*****Create Rarity Selector******//
		TitledBorderPanel rarityPanel = new TitledBorderPanel("Rarity");
		raritySelector = new JComboBox<String>();
		raritySelector.addItem("All");
		raritySelector.addItem("Common");
		raritySelector.addItem("Uncommon");
		raritySelector.addItem("Rare");
		raritySelector.addItem("Premium");
		raritySelector.addItem("Fixed");
		raritySelector.addActionListener(performSearch);
		rarityPanel.add(raritySelector);
		
		
		//-----Add selectors to the display------//
		basicSearchPanel.add(sideSelection);
		basicSearchPanel.add(rarityPanel);
		
		/*
		 * JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.add(sideSelection);
		topPanel.add(rarityPanel);
		basicSearchPanel.add(topPanel);
		*/
	}
	
	private void addRealmPanel()
	{
		TitledBorderPanel realmPanel = new TitledBorderPanel("Realm");
		realmPanel.setLayout(new GridBagLayout());
		
		trilogy = new JCheckBox("Original Trilogy");
		trilogy.setSelected(true);
		trilogy.addActionListener(performSearch);
		GridBagConstraints trilCon = new GridBagConstraints();
		trilCon.gridwidth = 2;
		realmPanel.add(trilogy, trilCon);
		
		epiOne = new JCheckBox("Episode I");
		epiOne.setSelected(true);
		epiOne.addActionListener(performSearch);
		GridBagConstraints epiOneCon = new GridBagConstraints();
		epiOneCon.gridy = 1;
		realmPanel.add(epiOne, epiOneCon);
		
		virtual = new JCheckBox("Virtual");
		virtual.addActionListener(performSearch);
		GridBagConstraints virtCon = new GridBagConstraints();
		virtCon.gridy = 1;
		virtCon.gridx = 1;
		realmPanel.add(virtual, virtCon);
		
		basicSearchPanel.add(realmPanel);
	}
}
