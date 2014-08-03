/**
 * 
 */
package swccgManager.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * @author Mark Rustad
 * @version .01
 * @date Aug 1, 2014
 *
 */
public class IntFieldSpinnerDisplay extends TitledBorderPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3193176338693004788L;

	public static enum Type {INVENTORY, DESIRED, EXTRA}
	private SpinnerNumberModel m_model;
	private JSpinner m_spinner;
	
	public IntFieldSpinnerDisplay(Type type, SpinnerNumberModel model)
	{
		super();
		m_spinner = new JSpinner();
		//add the correct title and labels
		String title;
		JLabel upLabel = new JLabel();
		JLabel downLabel = new JLabel();
		switch (type)
		{
			case INVENTORY : 
				title = "Inventory"; upLabel.setText("F1"); downLabel.setText("F2");break;
			case DESIRED : title = "Desired"; break;
			case EXTRA : title = "Extra"; break;
			default: title = "ERROR IN IntFieldSpinnerDisplay";
		}
		setTitle(title);
		
		//set Layout
		setLayout(new BorderLayout());
		
		//add spinner
		setModel(model);
		add(m_spinner, BorderLayout.WEST);
		
		//add the Labels
		JPanel labels = new JPanel();
		labels.setLayout(new GridLayout(2,1));
		labels.add(upLabel);
		labels.add(downLabel);
		add(labels, BorderLayout.EAST);
	}
	
	public void setModel(SpinnerNumberModel newModel)
	{
		m_model = newModel;
		m_spinner.setModel(m_model);
	}
	
	public SpinnerNumberModel getModel()
	{
		return m_model;
	}

}
