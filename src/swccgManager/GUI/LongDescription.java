/**
 * 
 */
package swccgManager.GUI;

import java.awt.Color;

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
	private final static int DEFAULT_ROWS = 3;
	private final static int DEFAULT_WIDTH = 20;
	
	public LongDescription(String title)
	{
		this(title, DEFAULT_ROWS, DEFAULT_WIDTH);
	}
	
	public LongDescription(String title, int rows, int width)
	{
		super(title);
		
		text = new JTextArea(rows, width);
		text.setBackground(this.getBackground());
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
		
		scroller = new JScrollPane();
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.add(text);
		//text.getPreferredScrollableViewportSize();
		scroller.setViewportView(text);
		add(scroller);
	}
	
	public void setEditable(boolean e)
	{
		text.setEditable(e);
		if (e) { text.setBackground(Color.WHITE); }
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
