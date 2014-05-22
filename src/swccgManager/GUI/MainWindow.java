/**
 * 
 */
package swccgManager.GUI;

import javax.swing.*;

import swccgManager.FullCardSet;

import java.awt.*;
import java.sql.Connection;
/**
 * @author Mark Rustad
 * @version .01
 * @date May 19, 2014
 *
 */
public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel activePanel;
	private JMenuBar mainMenu;
	
	public MainWindow(FullCardSet fcs)
	{
		
		super("SWCCG Manager");
		setLayout(new BorderLayout());
		setVisible(true);
		
		//Setup menus
		mainMenu = new JMenuBar();
		setJMenuBar(mainMenu);
		setupViewMenu();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pack();
	}
	
	private void setupViewMenu()
	{
		//Initialize menu
		
		//Setup view options menus
		JMenu viewMenu = new JMenu("View");
		
		//Collecton view, for viewing various collections of cards
		JMenuItem collectionView = new JMenuItem("Collection");
		viewMenu.add(collectionView);

		
		//Deck view, for viewing particular decks
		JMenuItem deckView = new JMenuItem("Deck");
		viewMenu.add(deckView);
		
		mainMenu.add(viewMenu);
	}
	
	private void setupCollectionMenu()
	{
		//Collection menu options
	}

}
