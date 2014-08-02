/**
 * 
 */
package swccgManager.GUI;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * @author Mark Rustad
 * @version .02
 * @date Jul 27, 2014
 *
 * Changelog:
 * 		2014-08-02: Added functionality to change title. Default constructor added.
 */
public class TitledBorderPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6375520442784055949L;
	
	TitledBorder titledBorder;
	
	public TitledBorderPanel(String title)
	{
		super();
		
		//create basic outline
		Border loweredetched;
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		
		//add the title
		
		titledBorder = BorderFactory.createTitledBorder(loweredetched, title);
		//titledBorder.setTitleJustification(TitledBorder.RIGHT);
		setBorder(titledBorder);
	}
	
	public TitledBorderPanel()
	{
		this("Default");
	}
	
	public void setTitle(String newTitle)
	{
		titledBorder.setTitle(newTitle);
	}

}
