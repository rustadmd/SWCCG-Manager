/**
 *
 */
package swccgManager.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import swccgManager.Models.Card;

/**
 * 	Displays all of the information pertaining directly to the Card.
 * @author Mark Rustad
 * @version .01
 * @date Jul 31, 2014
 *
 */
public class CardDisplay extends TitledBorderPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = -9161804104124972916L;
	Card m_card;
	int imageHeight, imageWidth;
	JLabel cardImageLabel, rearCardImageLabel;
	FieldDisplay rarity;
	LongDescription information, isPulled;

	public CardDisplay (Card card)
	{
		super("Card");
		setLayout(new BorderLayout());

		//Setup card info panel
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridBagLayout());

		rarity = new FieldDisplay ("Rarity", card.getRarity());
		GridBagConstraints rarCon = new GridBagConstraints();
		rarCon.gridwidth = 2;
		infoPanel.add(rarity, rarCon);

		isPulled = new LongDescription("Pulled/Cancelled By");
		//isPulled.setWidth(15);
		isPulled.setRows(6);
		GridBagConstraints pullCon = new GridBagConstraints();
		pullCon.gridy = 1;
		infoPanel.add(isPulled, pullCon);

		information = new LongDescription("Information");
		information.setWidth(22);
		information.setRows(6);
		GridBagConstraints infoCon = new GridBagConstraints();
		infoCon.gridx = 1;
		infoCon.gridy = 1;
		infoPanel.add(information, infoCon);

		add(infoPanel, BorderLayout.SOUTH);

		//add card image padding
		cardImageLabel = new JLabel();
		int verticalSpacing = 5;
		int horizontalSpacing = 5;
		cardImageLabel.setBorder(new EmptyBorder(verticalSpacing, horizontalSpacing, verticalSpacing, 0));
		//rear image
		rearCardImageLabel = new JLabel();
		rearCardImageLabel.setBorder(new EmptyBorder(verticalSpacing, 0, verticalSpacing, horizontalSpacing));

		JPanel images = new JPanel();
		images.setLayout(new FlowLayout());
		images.add(cardImageLabel);
		images.add(rearCardImageLabel);
		add(images, BorderLayout.CENTER);
		//this.setSize(600, 500);
		this.setPreferredSize(new Dimension(600, 540));
		refreshDisplay(card);
	}

	public void refreshDisplay(Card newCard)
	{
		m_card = newCard;
		refreshImages();
		addCardInfo();
	}
	/**
	 * Adds a card image to the display
	 * @param grph
	 */
	private void drawCardImage(Image i, JLabel label)
	{

		//top left corner of the image

		//System.out.println(m_card);//debugging
		//Image cardImage = m_card.getFrontSideImage();

		double scaleFactor = .75;

		if(i == null)
		{
			System.out.println("Image not loaded (drawCardImage())");
		}
		ImageIcon cardImageIcon = new ImageIcon(i);
		imageHeight = cardImageIcon.getIconHeight();
		imageWidth = cardImageIcon.getIconWidth();
		int newWidth = (int) (imageWidth * scaleFactor);
		int newHeight = (int) (imageHeight * scaleFactor);
		Image resizedImage = i.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);

		label.setIcon(resizedIcon);
		//System.out.println("drawCardImage() completed: " + m_card);
	}

	private void addCardInfo()
	{
		//System.out.println(m_card);
		rarity.setValue(m_card.getRarity());
		//GenericSQLQueries gsq = new GenericSQLQueries();
		String parRegex = "\\\\par(\\s)*";
		information.setText(m_card.getInformation().replaceAll(parRegex, "\n"));

		//combine isPulled and isCanceled text
		String pulledText = m_card.getIsPulled().replaceAll(parRegex, "\n");
		String cancelledText = m_card.getIsCancelledBy().replaceAll(parRegex, "\n");
		//System.out.printf("%s, %s", m_card.getRarity(), cancelledText);
		String combined = "";
		if(pulledText.length() > 0)
		{
			combined += "PULLED BY:\n" + pulledText;
			if(cancelledText.length() > 0)
			{
				combined += "\n\n";
			}
		}
		if(cancelledText.length() > 0)
		{
			combined += "CANCELLED BY: \n" + cancelledText;
		}
		isPulled.setText(combined);
	}

	private void refreshImages()
	{

		GridBagConstraints frontCon = new GridBagConstraints();
		frontCon.gridx = 0;

		drawCardImage(m_card.getFrontSideImage(), cardImageLabel);
		if(m_card != null && m_card.getCardType().equals("Objective"))
		{
			drawCardImage(m_card.getRearSideImage(), rearCardImageLabel);
			GridBagConstraints rearImage = new GridBagConstraints();
			rearImage.gridx = 1;

			//System.out.println("Rear Image added");
		}
		else
		{
			rearCardImageLabel.setIcon(null);
		}

	}

}
