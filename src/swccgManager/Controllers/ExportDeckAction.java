/**
 *
 */
package swccgManager.Controllers;


import java.awt.event.ActionEvent;
import java.io.File;

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
public class ExportDeckAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 7003398248883159423L;

	private ExportDeckWindow m_edw;

	public ExportDeckAction(ExportDeckWindow edw)
	{
		m_edw = edw;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String deckName = m_edw.getDeckName();

		String filePath = getExportFileLocation();
		writeDeckExportFile(filePath, deckName);

		m_edw.setVisible(false);

	}

	private void writeDeckExportFile(String fileLocation, String deckName)
	{
		File exportFile = new File(fileLocation);
		GenericSQLQueries gsq = new GenericSQLQueries();
		gsq.writeDeckExportFile(exportFile, deckName);
	}

	private String getExportFileLocation()
	{
		String filePath = "";
		JFileChooser saveFileLocation = new JFileChooser();
		saveFileLocation.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int result = saveFileLocation.showSaveDialog(m_edw);

		//close out if there is a cancel
		if(result == JFileChooser.CANCEL_OPTION)
		{
			m_edw.setVisible(false);
		}
		else
		{
			filePath = saveFileLocation.getSelectedFile().getPath();
		}
		//System.out.println(filePath);//Debugging
		return filePath;
	}

}
