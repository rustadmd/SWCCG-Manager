/**
 * 
 */
package swccgManager.GUI;

import java.awt.BorderLayout;
import javax.swing.*;

import swccgManager.Models.CardCollectionInfoModel;
import swccgManager.Models.CardList;
/**Main display for viewing a collection. This interface allows the user to browse
 * all cards, and see what cards they own. 
 * 
 * View class, controlled by 
 * 
 * @author Mark Rustad
 * @version .01
 * @date May 21, 2014
 *
 */
public class CollectionView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -192220976455137236L;

	public CollectionView()
	{

		//Set the layout
		setLayout(new BorderLayout());
		
		/**Trying to eliminate FullCardSet from use
		 * 
		SqlUtilities sqlUtil = new SqlUtilities();
		Connection swdb = sqlUtil.getDbConnection();
		FullCardSet test = new FullCardSet(swdb);
		sqlUtil.closeDB(swdb);**/
		
		//Test for db connection, works fine
		//GenericSQLQueries gsq = new GenericSQLQueries();
		//gsq.getCollectionList();
		
		//Set up models
		CardCollectionInfoModel model = new CardCollectionInfoModel();
		CardList cardList = model.getCardList();
		CardListPanel listDisplay = new CardListPanel(cardList);
		add(listDisplay, BorderLayout.WEST);
		
		//Add Collection Panel
		CollectionDisplay cd = new CollectionDisplay();
		add(cd, BorderLayout.NORTH);
	}

}
