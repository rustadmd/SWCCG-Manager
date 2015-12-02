/**
 *
 */
package swccgManager.GUI;

import javax.swing.*;

import swccgManager.Models.CollectionList;

import java.awt.*;
import java.awt.event.*;
/**
 * @author Mark Rustad
 * @version .01
 * @date May 19, 2014
 *
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JComponent m_activePanel;
	private JMenuBar mainMenu;

	public MainWindow()
	{

		super("SWCCG Manager");
		setLayout(new BorderLayout());
		setVisible(true);

		//Setup menus
		mainMenu = new JMenuBar();
		setJMenuBar(mainMenu);
		setupViewMenu();
		setupCollectionMenu();
		setupDeckMenu();
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		//this.setResizable(false);
		//Defaults to collection view -- allows you to see all cards and what you own
		setActivePanel(new DeckView());
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
		deckView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setActivePanel(new DeckView());
			}
		});

		mainMenu.add(viewMenu);
	}

	private void setupCollectionMenu()
	{
		//Collection menu options
		JMenu collectionMenu = new JMenu("Collection");

		//Collecton view, for viewing various collections of cards
		JMenuItem addCollection = new JMenuItem("New Collection");
		collectionMenu.add(addCollection);
		//Collection test = new Collection("Mark", "My Collection");
		//test.saveCollection();

		//Add the action action to add a new item
		ActionListener addCollection_al = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//System.out.println("View > Collection Selected");//testing

				//will need to see if there is a collection display
				//May need to add more if other panels have a collection display
				if(m_activePanel.getClass().getSimpleName().equals("CollectionView"))
				{
					CollectionView currentView = (CollectionView)m_activePanel;
					CollectionList collection = currentView.getCollectionList();

					@SuppressWarnings("unused")
					NewCollectionWindow ncw = new NewCollectionWindow(collection);
				}
				else
				{
					@SuppressWarnings("unused")
					NewCollectionWindow ncw = new NewCollectionWindow();
				}
			}
		};
		addCollection.addActionListener(addCollection_al);

		//Deck view, for viewing particular decks
		JMenuItem exportCollection = new JMenuItem("Export Collection");
		collectionMenu.add(exportCollection);

		ActionListener exportCollection_al = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				@SuppressWarnings("unused")
				ExportCollectionWindow ecw = new ExportCollectionWindow();
			}
		};
		exportCollection.addActionListener(exportCollection_al);
		mainMenu.add(collectionMenu);
	}

	private void setupDeckMenu()
	{
		//Collection menu options
		JMenu deckMenu = new JMenu("Deck");

		//Collecton view, for viewing various collections of cards
		JMenuItem addDeck = new JMenuItem("New Deck");
		deckMenu.add(addDeck);
		//Collection test = new Collection("Mark", "My Collection");
		//test.saveCollection();

		//Add the action action to add a new item
		ActionListener addDeck_al = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//will need to see if there is a collection display
				//May need to add more if other panels have a collection display
                @SuppressWarnings("unused")
				NewDeckWindow ncw = new NewDeckWindow();
			}
		};
		addDeck.addActionListener(addDeck_al);

		//Deck view, for viewing particular decks
		JMenuItem exportDeck = new JMenuItem("Export Deck");
		deckMenu.add(exportDeck);

		ActionListener exportDeck_al = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				@SuppressWarnings("unused")
				ExportDeckWindow ecw = new ExportDeckWindow();
			}
		};
		exportDeck.addActionListener(exportDeck_al);
		mainMenu.add(deckMenu);
	}
	private void setActivePanel(JComponent activePanel)
	{
		if(m_activePanel != null)
		{
			remove(m_activePanel);
		}
		m_activePanel = activePanel;
		add(m_activePanel);
		pack();

	}

}
