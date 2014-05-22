/**
 * 
 */
package swccgManager.GUI;

import javax.swing.*;

import swccgManager.FullCardSet;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
/**
 * @author Mark Rustad
 * @version .01
 * @date May 19, 2014
 *
 */
public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel m_activePanel;
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
		setupCollectionMenu();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Defaults to collection view -- allows you to see all cards and what you own
		setActivePanel(new CollectionView());
	}
	
	private void setupViewMenu()
	{
		//Initialize menu
		
		//Setup view options menus
		JMenu viewMenu = new JMenu("View");
		
		//Collecton view, for viewing various collections of cards
		JMenuItem collectionView = new JMenuItem("Collection");
		viewMenu.add(collectionView);
		
		//call the panel and controller for the panel
		ActionListener collectionView_al = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//System.out.println("View > Collection Selected");//testing
				setActivePanel(new CollectionView());
			}
		};
		collectionView.addActionListener(collectionView_al);
		

		
		//Deck view, for viewing particular decks
		JMenuItem deckView = new JMenuItem("Deck");
		viewMenu.add(deckView);
		
		mainMenu.add(viewMenu);
	}
	
	private void setupCollectionMenu()
	{
		//Collection menu options
		JMenu collectionMenu = new JMenu("Collection");
		
		//Collecton view, for viewing various collections of cards
		JMenuItem collectionView = new JMenuItem("New Collection");
		collectionMenu.add(collectionView);

		
		//Deck view, for viewing particular decks
		JMenuItem deckView = new JMenuItem("Export Collection");
		collectionMenu.add(deckView);
		
		mainMenu.add(collectionMenu);
	}
	
	private void setActivePanel(JPanel activePanel)
	{
		m_activePanel = activePanel;
		add(m_activePanel);
		pack();
	}

}