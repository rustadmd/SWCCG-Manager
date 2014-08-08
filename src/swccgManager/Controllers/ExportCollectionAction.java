/**
 * 
 */
package swccgManager.Controllers;


import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import swccgManager.Database.GenericSQLQueries;
import swccgManager.GUI.ExportCollectionWindow;

/**
 * This action exports a 
 * @author Mark Rustad
 * @version .01
 * @date Jun 7, 2014
 *
 */
public class ExportCollectionAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7003398248883159423L;

	private ExportCollectionWindow m_ecw;
	
	public ExportCollectionAction(ExportCollectionWindow ecw)
	{
		m_ecw = ecw;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String collectionName = m_ecw.getCollectionName();
		
		String filePath = getExportFileLocation();
		writeCollectionExportFile(filePath, collectionName);
		
		m_ecw.setVisible(false);

	}
	
	private void writeCollectionExportFile(String fileLocation, String collectionName)
	{
		File exportFile = new File(fileLocation);
		GenericSQLQueries gsq = new GenericSQLQueries();
		gsq.writeCollectionExportFile(exportFile, collectionName);
	}
	
	private String getExportFileLocation()
	{
		String filePath = "";
		JFileChooser saveFileLocation = new JFileChooser();
		saveFileLocation.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int result = saveFileLocation.showSaveDialog(m_ecw);
		
		//close out if there is a cancel
		if(result == JFileChooser.CANCEL_OPTION)
		{
			m_ecw.setVisible(false);
		}
		else
		{
			filePath = saveFileLocation.getSelectedFile().getPath();
		}
		//System.out.println(filePath);//Debugging
		return filePath;
	}

}
