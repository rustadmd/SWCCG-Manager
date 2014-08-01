/**
 * 
 */
package swccgManager.GUI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
	int frontSideImageHeight, frontSideImageWidth;
	JLabel cardImageLabel;
	
	public CardDisplay (Card card)
	{
		super("Card");
		setLayout(new BorderLayout());
		
		//add card image padding
		cardImageLabel = new JLabel();
		int verticalSpacing = 10;
		int horizontalSpacing = 15;
		cardImageLabel.setBorder(new EmptyBorder(verticalSpacing, horizontalSpacing, verticalSpacing, horizontalSpacing));
		add(cardImageLabel, BorderLayout.WEST);
		
		refreshDisplay(card);
	}
	/*
	public void paintComponent(Graphics g)
	{
		drawCardImage();
	}
	*/
	public void refreshDisplay(Card newCard)
	{
		m_card = newCard;
		drawCardImage();
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

}
