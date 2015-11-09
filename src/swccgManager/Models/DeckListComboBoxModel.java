/**
 *
 */
package swccgManager.Models;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import swccgManager.Database.GenericSQLQueries;
/**
 * Heavily influenced by https://opensource.link-intersystems.com/svn/open-source/examples/tags
 * 		/mvc-with-java-swing-1/src/main/java/com/link_intersystems/publications/blog/mvc
 * 		/model/list/ListComboBoxModel.java
 * @author Mark Rustad
 * @version .01
 * @param <E>
 * @param <E>
 * @date Jun 6, 2014
 *
 */
public class DeckListComboBoxModel extends DefaultListModel<Deck> implements
ComboBoxModel<Deck>  {

	/**
	 *
	 */
	private static final long serialVersionUID = 5664563549961843244L;
	private DefaultListModel<Deck> list;
	private ListSelectionModel listSelectionModel;

	@SuppressWarnings("static-access")
	public DeckListComboBoxModel(DefaultListModel<Deck> listModel)
	{
		//initialize basics
		list = listModel;
		listSelectionModel = new DefaultListSelectionModel();
		listSelectionModel.setSelectionMode(listSelectionModel.SINGLE_SELECTION);
		listSelectionModel.setSelectionInterval(0, 0);

		//These classes are listeners for data changes
		@SuppressWarnings("unused")
		ListSelectionUpdater listSelectionUpdater = new ListSelectionUpdater();
		@SuppressWarnings("unused")
		ListModelUpdater listModelUpdater = new ListModelUpdater();
		//Add the contents
		//updateList();
	}


	/**
	 * Will update the list of decks from the database
	 */
	public void updateList()
	{
		list.clear();
		list = new GenericSQLQueries().getDeckList();
		fireContentsChanged(this, 0, getSize() - 1);
	}
	/**
	 * The following are generic methods, not difference from usual behavior
	 */



	@Override
	public Deck getElementAt(int index) {
		Deck objectAtIndex = list.getElementAt(index);
		return objectAtIndex;
	}

	@Override
	public int getSize() {
		int numObjects = list.getSize();
		return numObjects;
	}

	@Override
	public Deck getSelectedItem() {
		int minSelectionIndex = listSelectionModel.getMinSelectionIndex();
		if (minSelectionIndex < 0) {
			return null;
		}
		Deck elementAt = getElementAt(minSelectionIndex);
		return elementAt;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		int index = -1;
		for (int i = 0; i < getSize(); i++) {
			Object elementAt = list.getElementAt(i);
			if (elementAt != null && elementAt.equals(anItem)) {
				index = i;
				break;
			}
		}
		listSelectionModel.setSelectionInterval(index, index);
	}

	/**
	 * Copied from https://opensource.link-intersystems.com/svn/open-source/examples/tags/mvc-with-java-swing-1/src/main/java/com/link_intersystems/publications/blog/mvc/model/list/ListComboBoxModel.java
	 * @author Mark Rustad
	 * @version .01
	 * @date Jun 7, 2014
	 *
	 */
	private class ListSelectionUpdater implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			fireContentsChanged(this, 0, getSize() - 1);
		}

	}

	private class ListModelUpdater implements ListDataListener {

		@Override
		public void intervalAdded(ListDataEvent e) {
			fireIntervalAdded(this, e.getIndex0(), e.getIndex1());

		}
		@Override
		public void intervalRemoved(ListDataEvent e) {
			fireIntervalRemoved(this, e.getIndex0(), e.getIndex1());
		}

		@Override
		public void contentsChanged(ListDataEvent e) {
			fireContentsChanged(this, e.getIndex0(), e.getIndex1());

		}

	}
}
