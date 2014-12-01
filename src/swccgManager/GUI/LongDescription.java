/**
 * 
 */
package swccgManager.GUI;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Mark Rustad
 * @version .01
 * @date Nov 28, 2014
 *
 */
public class LongDescription extends TitledBorderPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1176845722693095375L;
	private JTextArea text;
	JScrollPane scroller;
	private final int DEFAULT_ROWS = 3;
	private final int DEFAULT_WIDTH = 20;
	
	public LongDescription(String title)
	{
		super(title);
		
		text = new JTextArea(DEFAULT_ROWS, DEFAULT_WIDTH);
		text.setBackground(this.getBackground());
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		
		scroller = new JScrollPane();
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.add(text);
		//text.getPreferredScrollableViewportSize();
		scroller.setViewportView(text);
		add(scroller);
	}

	public void setText(String t)
	{
		text.setText(t);
		text.setCaretPosition(0);
	}
	
	public void setWidth(int width)
	{
		text.setColumns(width);
	}
	
	public void setRows(int rows)
	{
		text.setRows(rows);
	}
}
