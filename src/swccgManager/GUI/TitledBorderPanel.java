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
 * @version .01
 * @date Jul 27, 2014
 *
 */
public class TitledBorderPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6375520442784055949L;

	public TitledBorderPanel(String title)
	{
		super();
		
		//create basic outline
		Border loweredetched;
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		
		//add the title
		TitledBorder titledBorder;
		titledBorder = BorderFactory.createTitledBorder(loweredetched, title);
		//titledBorder.setTitleJustification(TitledBorder.RIGHT);
		setBorder(titledBorder);
	}

}
