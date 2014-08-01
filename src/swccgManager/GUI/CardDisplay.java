/**
 * 
 */
package swccgManager.GUI;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import swccgManager.Models.Card;

/**
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
	
	public CardDisplay (Card card)
	{
		super("Card");
		m_card = card;
	}
	
	public void paintComponent(Graphics g)
	{
		drawCardImage(g);
	}
	
	public void refreshDisplay(Card newCard)
	{
		m_card = newCard;
		repaint();
	}
	/**
	 * Adds a card image to the display
	 * @param grph
	 */
	private void drawCardImage(Graphics grph) 
	{
		
		//top left corner of the image
		int verticalSpacing = 30;
		int horizontalSpacing = 30;
		
		//System.out.println(m_card);//debugging
		Image cardImage = m_card.getFrontSideImage(); 
		if(cardImage == null)
		{
			System.out.println("Image not loaded (drawCardImage())");
		}
		ImageIcon cardImageIcon = new ImageIcon(cardImage);
		frontSideImageHeight = cardImageIcon.getIconHeight();
		frontSideImageWidth = cardImageIcon.getIconWidth();
		
		grph.drawImage(cardImage, horizontalSpacing, verticalSpacing,frontSideImageWidth, frontSideImageHeight, this);
	}

}
