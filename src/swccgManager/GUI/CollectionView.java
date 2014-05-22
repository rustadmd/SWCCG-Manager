/**
 * 
 */
package swccgManager.GUI;

import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.*;

import swccgManager.Card;
import swccgManager.FullCardSet;
import swccgManager.SqlUtilities;
/**
 * @author Mark Rustad
 * @version .01
 * @date May 21, 2014
 *
 */
public class CollectionView extends JPanel{
	
	public CollectionView()
	{

		//Set the layout
		setLayout(new BorderLayout());
		
		SqlUtilities sqlUtil = new SqlUtilities();
		Connection swdb = sqlUtil.getDbConnection();
		FullCardSet test = new FullCardSet(swdb);
		sqlUtil.closeDB(swdb);
		
		Card[] cardList 
		= test.getFullCardSet();
		CardListPanel listDisplay = new CardListPanel(cardList);
		add(listDisplay, BorderLayout.WEST);
		
		//Add Collection Panel
		CollectionDisplay cd = new CollectionDisplay();
		add(cd, BorderLayout.NORTH);
	}

}
