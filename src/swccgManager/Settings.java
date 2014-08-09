/**
 * 
 */
package swccgManager;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.sql.DriverManager;

import swccgManager.Database.DriverShim;

/**
 * @author Mark Rustad
 * @version .01
 * @date May 19, 2014
 *
 */
public class Settings {
	
	private static String m_programPath;
	//public static FullCardSet fcs; //there is only one master list of cards
	
	/**
	 * Creates initial settings, sets up file path and SQLlite connector
	 */
	public Settings()
	{
		m_programPath = setProgramPath();
		setupSqlLiteConnector();
		
		//Create full card list
		/**This is now obsolete, using individual card lists pulled from db
		SqlUtilities sqlUtil = new SqlUtilities();
		Connection swdb = sqlUtil.getDbConnection();
		@SuppressWarnings("unused")
		FullCardSet fcs = new FullCardSet(swdb);
		sqlUtil.closeDB(swdb);
		**/
	}
	
	/**
	 * Gets the current program file location
	 * @return
	 */
	public static String getProgramPath()
	{
		return m_programPath;
	}
	
	/**
	 * Sets the program file location
	 * @return
	 */
	private String setProgramPath()
	{
		//Find the current class path
		URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
		String programPathWithFile = location.toString();
		String programPath = programPathWithFile.substring(5);//delete "file:"
		System.out.println(programPath);//debugging
		return programPath;
		
	}
	
	/**
	 * Sets up the Sqllite db connector
	 */
	private void setupSqlLiteConnector()
	{
		
		//Load the JDBC Connector
		String jarPrefix = "jar:file:";
		String connectorName = "sqlite-jdbc-3.7.2.jar";
		String driverName = "org.sqlite.JDBC";
		String resourceLocation = "resources/Database/";
		URL u = null;
		
		try {
			u = new URL(jarPrefix + m_programPath + resourceLocation + connectorName + "!/");
			System.out.println(u);
			URLClassLoader ucl = new URLClassLoader(new URL[] { u });
			Driver d = (Driver)Class.forName(driverName, true, ucl).newInstance();
			DriverManager.registerDriver(new DriverShim(d));
		} catch (Exception e) {
			System.out.println(u);
			e.printStackTrace();
		} 
		
	}
	

}
