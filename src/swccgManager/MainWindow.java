/**
 * 
 */
package swccgManager;

import javax.swing.*;
import java.awt.*;
/**
 * @author Mark Rustad
 * @version .01
 * @date May 19, 2014
 *
 */
public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public MainWindow()
	{
		super("SWCCG Manager");
		//Set the layout
		setLayout(new BorderLayout());
		
		//Add the card list
		
		pack();
	}

}
