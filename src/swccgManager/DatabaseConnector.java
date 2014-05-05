package swccgManager;
/**
 * Creates a connection with the SWCCGdb.
 * @author Mark Rustad
 * @version .01
 * @date May 1, 2014
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;

public class DatabaseConnector {
	
	private Connection swdb;
	
	/**
	 * Establishes a connection with the database, keeps it open.
	 * You will need to close the database later.
	 */
	public DatabaseConnector()
	{
		try{
			//Find the current class path
			URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
			String programPathWithFile = location.toString();
			String programPath = programPathWithFile.substring(5);//delete "file:"
			//System.out.println(programPath);//debugging
			
			//Load the JDBC Connector
			String jarPrefix = "jar:file:";
			String connectorName = "sqlite-jdbc-3.7.2.jar";
			String driverName = "org.sqlite.JDBC";
			
			URL u = new URL(jarPrefix + programPath + connectorName + "!/");
			System.out.println(u);
			URLClassLoader ucl = new URLClassLoader(new URL[] { u });
			Driver d = (Driver)Class.forName(driverName, true, ucl).newInstance();
			DriverManager.registerDriver(new DriverShim(d));
			
			//Construct the file path from the current file location
			String filePrefix = "jdbc:sqlite:";
			String dbName = "testing3.s3db";
			String totalPath = filePrefix + programPath + dbName;

			//System.out.println(totalPath);//debugging
			
			
			swdb = DriverManager.getConnection(totalPath);
			System.out.println("Successfully connected to db.");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Could not find JDBC Driver");
		}
		catch(SQLException e)
		{
			System.out.println("Database Not found");
		} catch (MalformedURLException e) {
			System.out.println("Bad url");
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			System.out.println("InstantiationException");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("Illegal Access Exception");
			e.printStackTrace();
		}
		
	}
	/**
	 * closes the database. Must be run separately
	 */
	public void closeDB()
	{
		closeDB(swdb);
	}
	/**
	 * Closes an open connection. For closing a connection from getConnection().
	 * Available at any point in time
	 *
	 * @param dbConnnect a sql Connection that is open
	 */
	public static void closeDB (Connection dbConnect)
	{
		try{
			dbConnect.close();
			System.out.println("Connection successfully closed.");
		}
		
		catch (SQLException e)
		{
			System.out.println("No connection active.");
		}
	}
	/**
	 * Passes the connection
	 * 
	 * @return Passes connection to the SWCCG DB
	 */
	public Connection getConnection()
	{
		return swdb;
	}

}
