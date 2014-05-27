/**
 * 
 */
package swccgManager.GUI;
import javax.swing.*;
import java.awt.*;
/**
 * @author Mark Rustad
 * @version .01
 * @date May 26, 2014
 *
 */
public class NewCollectionWindow extends JFrame{
	
	private static final long serialVersionUID = 5151115731903601102L;
	private JTextField name;
	private JTextArea description;
	
	public NewCollectionWindow()
	{
		super("Add New Collection");
		setLayout(new GridLayout());
		setVisible(true);
		
		//Add the various components
		addNameSection();
		addDescriptionSection();
		addSubmitButton();
		
		pack();
	}
	
	/**
	 * Adds the name field and label for input
	 */
	private void addNameSection()
	{
		JPanel namePanel = new JPanel();
		add(namePanel, BorderLayout.NORTH);
		namePanel.setLayout(new FlowLayout());
		
		//Add the name label
		String nameLabel_s = "Collection Name:";
		JLabel nameLabel = new JLabel(nameLabel_s);
		namePanel.add(nameLabel);
		
		//add the textfield
		name = new JTextField(20);
		namePanel.add(name);
	}
	
	/**
	 * Adds the description field and label for input
	 */
	private void addDescriptionSection()
	{
		JPanel descriptionPanel = new JPanel();
		add(descriptionPanel, BorderLayout.EAST);
		descriptionPanel.setLayout(new FlowLayout());
		
		//Add the name label
		String descriptionLabel_s = "Collection Description:";
		JLabel descriptionLabel = new JLabel(descriptionLabel_s);
		descriptionPanel.add(descriptionLabel);
		
		//add the textfield
		description = new JTextArea();
		descriptionPanel.add(description);
	}
	
	private void addSubmitButton()
	{
		JButton submitButton;
		submitButton = new JButton("Submit");
		add(submitButton, BorderLayout.SOUTH);
	}

}
