/**
 * 
 */
package swccgManager;

import javax.swing.*;

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
	
	public MainWindow(FullCardSet fcs)
	{
		super("SWCCG Manager");
		//Set the layout
		setLayout(new BorderLayout());
		setVisible(true);
		
		//Add the card list
		SqlUtilities sqlUtil = new SqlUtilities();
		Connection swdb = sqlUtil.getDbConnection();
		FullCardSet test = new FullCardSet(swdb);
		sqlUtil.closeDB(swdb);
		
		Card[] cardList 
		= test.getFullCardSet();
		CardListPanel listDisplay = new CardListPanel(cardList);
		add(listDisplay, BorderLayout.WEST);
		pack();
	}

}
