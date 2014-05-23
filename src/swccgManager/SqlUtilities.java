/**
 * 
 */
package swccgManager;
import java.sql.*;
/**
 * @author Mark Rustad
 * @version .01
 * @date May 12, 2014
 *
 */
public class SqlUtilities {

	private Connection m_swdb;
	
	/**
	 * Sets up an initial connection to be used with SqlUtilites
	 */
	public SqlUtilities()
	{
		m_swdb = getDbConnection();
	}
	
	/**
	 * Establishes a connection with the database
	 * @return Connection to the Star Wars Database
	 */
	public Connection getDbConnection()
	{
		Connection db = null;
		
		try{
			String programPath = Settings.getProgramPath();
			//Construct the file path from the current file location
			String filePrefix = "jdbc:sqlite:";
			String dbName = "testing3.s3db";
			String totalPath = filePrefix + programPath + dbName;

			//System.out.println(totalPath);//debugging
			
			
			db = DriverManager.getConnection(totalPath);
			System.out.println("Successfully connected to db.");

		}

		catch(SQLException e)
		{
			System.out.println("Database Not found");
		}
		return db;
	}
	
	/**
	 * Closes an open connection. For closing a connection from getConnection().
	 * Available at any point in time
	 *
	 * @param dbConnnect a sql Connection that is open
	 */
	public void closeDB (Connection dbConnect)
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
	 * Closes an open connection. For closing a connection from getConnection().
	 * Available at any point in time
	 *
	 */
	public void closeDB ()
	{
		closeDB(m_swdb);
	}
	/**
	 * Gets the number of rows in the ResultSet
	 * @param results ResultSet to count the rows
	 * @return Number of records
	 */
	public int getNumRows(ResultSet results)
	{
		int rowCount = 0;
		try {
		//iterate through the rows
			while (results.next())
			{
				rowCount++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowCount;
	}
	
	/**
	 * Generic query creator. Handles all the SQL transactions for the query and returns a ResultSet of that query
	 * @param swdb Connection to the SW db
	 * @param query SQL query for the desired results
	 * @return Results of the query
	 */
	public ResultSet getQueryResults(Connection swdb, String query)
	{
		ResultSet queryResults = null;
		try{
			Statement statement = swdb.createStatement();
			//write query
			queryResults = statement.executeQuery(query);
			//System.out.println("Current row: " + queryResults.getRow());//debugging
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		//return results
		return queryResults;
	}
	

	/**
	 * Generic query creator. Handles all the SQL transactions for the query and returns a ResultSet of that query
	 * @param query SQL query for the desired results
	 * @return Results of the query
	 */
	public ResultSet getQueryResults(String query)
	{
		ResultSet queryResults = null;
		try{
			Statement statement = m_swdb.createStatement();
			//write query
			queryResults = statement.executeQuery(query);
			//System.out.println("Current row: " + queryResults.getRow());//debugging
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		//return results
		return queryResults;
	}
	
	/**
	 * Executes a query. Use for UPDATE, INSERT, etc queries that return no results.
	 * @param query Query to execute
	 */
	public void executeQuery(String query)
	{
		try{
			Statement statement = m_swdb.createStatement();
			//write query
			statement.executeQuery(query);
			//System.out.println("Current row: " + queryResults.getRow());//debugging
		}
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}
	
}
