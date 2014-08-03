/**
 * 
 */
package swccgManager.GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import swccgManager.Controllers.PerformSearch;
import swccgManager.Database.CardQueryCriteria;
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
	
	private CardQueryCriteria cardQueryCriteria;
	private CardList listModel;
	private PerformSearch performSearch;
	
	//options
	private JRadioButton all, light, dark;

	public SearchDisplay(CardList cardList)
	{
		super("Search");
		listModel = cardList;
		cardQueryCriteria = cardList.getCriteria();
		performSearch = new PerformSearch(listModel, this);
		
		//add layouts
		setLayout(new FlowLayout());
		addSidePanel();
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
}
