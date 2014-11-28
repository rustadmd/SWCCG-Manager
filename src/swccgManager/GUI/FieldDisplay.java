/**
 * @author Mark Rustad
 * @version .01
 * @date Nov 1, 2014
 */
package swccgManager.GUI;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 * @author Mark
 * Basic Field/Value display component. Will increase the size of the default font for the title font
 * Can also be called using specific fonts
 */
public class FieldDisplay extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -952700717783957168L;
	private JLabel title, value;
	private Font titleFont, valueFont;
	
	//Derive default fonts from the default
	private static Font mainFont = UIManager.getDefaults().getFont("Label.font");
	private static final Font DEFAULT_TITLE_FONT = mainFont.deriveFont(Font.BOLD, mainFont.getSize() + 2);
	private static final Font DEFAULT_VALUE_FONT = mainFont;
	
	public FieldDisplay(String title, String value)
	{
		this(title, value, DEFAULT_TITLE_FONT, DEFAULT_VALUE_FONT);	
	}
	
	public FieldDisplay(String title, String value, Font titleFont, Font valueFont)
	{
		this.title = new JLabel(title + ":");
		setTitleFont(titleFont);
		
		this.value = new JLabel(value);
		setValueFont(valueFont);
		
		int verticalGap = 0;
		int horizontalGap = 3;
		setLayout(new BorderLayout(horizontalGap, verticalGap));
		add(this.title, BorderLayout.WEST);
		add(this.value, BorderLayout.EAST);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title.getText();
	}

	/**
	 * @return the titleFont
	 */
	public Font getTitleFont() {
		return titleFont;
	}

	/**
	 * @param titleFont the font to set use for the title
	 */
	public void setTitleFont(Font titleFont) {
		this.titleFont = titleFont;
		this.title.setFont(titleFont);
	}

	/**
	 * @return the valueFont
	 */
	public Font getValueFont() {
		return valueFont;
	}

	/**
	 * @param valueFont the valueFont to set
	 */
	public void setValueFont(Font valueFont) {
		this.valueFont = valueFont;
		this.value.setFont(valueFont);
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title.setText(title + ":");
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value.getText();
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value.setText(value);
	}
}
