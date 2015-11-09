/**
 *
 */
package swccgManager.GUI;
import javax.swing.*;

import swccgManager.Controllers.NewDeckAction;
import swccgManager.Models.CollectionList;

import java.awt.*;

/**
 * @author Mark Rustad
 * @version .01
 * @date May 26, 2014
 *
 */
public class NewDeckWindow extends JFrame{

	private static final long serialVersionUID = 5151115731903601102L;
	private JTextField name;
	private JTextArea description;
	private JButton submitButton;
	private CollectionList m_list;

	public NewDeckWindow()
	{
		this(null);
	}

	public NewDeckWindow(CollectionList list)
	{
		super("Add New Deck");
		setLayout(new BorderLayout());
		setVisible(true);

		m_list=list;

		//Add the various components
		addNameSection();
		addDescriptionSection();
		addSubmitButton();

		pack();
		this.setResizable(false);
	}
	/**
	 * Returns the name of the collection in a string format
	 */
	public String getName()
	{
		String name_s = name.getText();
		return name_s;
	}

	/**
	 * Returns the collection description as a string
	 * Uses the text area box
	 * @return User inputed description
	 */
	public String getDescription()
	{
		String descript = description.getText();
		return descript;
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
		String nameLabel_s = "Deck Name:";
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
		this.add(descriptionPanel, BorderLayout.CENTER);
		descriptionPanel.setLayout(new FlowLayout());

		//Add the name label
		String descriptionLabel_s = "Collection Description:";
		JLabel descriptionLabel = new JLabel(descriptionLabel_s);
		descriptionPanel.add(descriptionLabel);

		//add the textfield
		int descriptionHeight = 5;
		int descriptionWidth = 20;
		description = new JTextArea(descriptionHeight, descriptionWidth);
		descriptionPanel.add(description);
	}

	private void addSubmitButton()
	{
		NewDeckAction newDeck_a = new NewDeckAction(this, m_list);

		submitButton = new JButton(newDeck_a);
		submitButton.setText("Add Deck");
		this.add(submitButton, BorderLayout.SOUTH);
	}


}
