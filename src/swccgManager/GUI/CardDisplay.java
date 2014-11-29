/**
 * 
 */
package swccgManager.GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import swccgManager.Database.GenericSQLQueries;
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
	int frontSideImageHeight, frontSideImageWidth;
	JLabel cardImageLabel;
	FieldDisplay rarity;
	LongDescription information, isPulled;
	
	public CardDisplay (Card card)
	{
		super("Card");
		setLayout(new BorderLayout());
		
		//Setup card info panel
		TitledBorderPanel infoPanel = new TitledBorderPanel("Information");
		infoPanel.setLayout(new GridBagLayout());
		
		rarity = new FieldDisplay ("Rarity", card.getRarity());
		GridBagConstraints rarCon = new GridBagConstraints();
		rarCon.gridwidth = 2;
		infoPanel.add(rarity, rarCon);
		
		isPulled = new LongDescription("Pulled By");
		//isPulled.setWidth(15);
		GridBagConstraints pullCon = new GridBagConstraints();
		pullCon.gridy = 1;
		infoPanel.add(isPulled, pullCon);
		
		information = new LongDescription("Information");
		//information.setWidth(15);
		GridBagConstraints infoCon = new GridBagConstraints();
		infoCon.gridx = 1;
		infoCon.gridy = 1;
		infoPanel.add(information, infoCon);
		
		add(infoPanel, BorderLayout.SOUTH);
		
		//add card image padding
		cardImageLabel = new JLabel();
		int verticalSpacing = 10;
		int horizontalSpacing = 15;
		cardImageLabel.setBorder(new EmptyBorder(verticalSpacing, horizontalSpacing, verticalSpacing, horizontalSpacing));
		add(cardImageLabel, BorderLayout.WEST);
		
		refreshDisplay(card);
	}
 
	public void refreshDisplay(Card newCard)
	{
		m_card = newCard;
		drawCardImage();
		addCardInfo();
	}
	/**
	 * Adds a card image to the display
	 * @param grph
	 */
	private void drawCardImage() 
	{
		
		//top left corner of the image
				
		//System.out.println(m_card);//debugging
		Image cardImage = m_card.getFrontSideImage(); 
		
		double scaleFactor = .75;
		
		if(cardImage == null)
		{
			System.out.println("Image not loaded (drawCardImage())");
		}
		ImageIcon cardImageIcon = new ImageIcon(cardImage);
		frontSideImageHeight = cardImageIcon.getIconHeight();
		frontSideImageWidth = cardImageIcon.getIconWidth();
		int newWidth = (int) (frontSideImageWidth * scaleFactor);
		int newHeight = (int) (frontSideImageHeight * scaleFactor);
		Image resizedImage = cardImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		
		cardImageLabel.setIcon(resizedIcon);
		//grph.drawImage(cardImage, horizontalSpacing, verticalSpacing,frontSideImageWidth, frontSideImageHeight, this);
	}
	
	private void addCardInfo()
	{
		rarity.setValue(m_card.getRarity());
		GenericSQLQueries gsq = new GenericSQLQueries();
		information.setText(gsq.getCardField(m_card, "Information"));
		isPulled.setText(gsq.getCardField(m_card, "IsPulled"));
	}

}
