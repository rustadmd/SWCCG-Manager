/**
 * 
 */
package swccgManager;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 * @author Mark Rustad
 * @version .01
 * @date May 19, 2014
 *
 */
public class Settings {
	
	private static String m_programPath;
	
	/**
	 * Creates initial settings, sets up file path and SQLlite connector
	 */
	public Settings()
	{
		m_programPath = setProgramPath();
		setupSqlLiteConnector();
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
		return programPath;
		//System.out.println(programPath);//debugging
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
		
		try {
			URL u = new URL(jarPrefix + m_programPath + connectorName + "!/");
			//System.out.println(u);
			URLClassLoader ucl = new URLClassLoader(new URL[] { u });
			Driver d = (Driver)Class.forName(driverName, true, ucl).newInstance();
			DriverManager.registerDriver(new DriverShim(d));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	

}
