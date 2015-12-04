/**
 *
 */
package swccgManager.Controllers;


import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import swccgManager.Database.GenericSQLQueries;
import swccgManager.GUI.ExportDeckWindow ;

/**
 * This action exports a
 * @author Mark Rustad
 * @version .01
 * @date Jun 7, 2014
 *
 */
public class ImportDeckAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 7003398248883159423L;


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String deckName = "Test-Import";

		Path filePath = getImportFileLocation();
		GenericSQLQueries gsq = new GenericSQLQueries();
		gsq.importDeck(filePath, deckName);

	}

	private Path getImportFileLocation()
	{
		JFileChooser importFileChooser = new JFileChooser();
		importFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int result = importFileChooser.showOpenDialog(null);

		//close out if there is a cancel
		if(result == JFileChooser.APPROVE_OPTION)
		{
			return Paths.get(importFileChooser.getSelectedFile().getAbsolutePath());
		}
		else
		{
			return null;
		}
	}

}
