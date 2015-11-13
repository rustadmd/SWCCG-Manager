package swccgManager.GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class DeckCollectionView extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 6414873915778959632L;
	private DeckDisplay m_dd;
	private CollectionDisplay m_cd;

	public DeckCollectionView() {
		super();
		this.m_dd = new DeckDisplay();
		this.m_cd = new CollectionDisplay();

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(m_cd, gbc);

		gbc.gridx ++;
		this.add(m_dd, gbc);
	}

}
