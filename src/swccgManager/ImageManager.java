/**
 * @author Mark Rustad
 * @version .01
 * @date May 3, 2014
 */
package swccgManager;

/**
 * @author Mark
 *
 */

import java.sql.*;
import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageManager {
	
	public void getImageFileLocation(Connection dbc)
	{
		//Get the list of cards to get the images
		ResultSet cardList = GenericSQLQueries.getCardsWithoutImages(dbc);
		
		int numSuccesses = 0;
		int numFailures = 0;
		try {
			//ResultSetMetaData cardListMd= cardList.getMetaData();
			//System.out.println("Current row: " + cardList.getRow());
			while (cardList.next())
			{
				//Retrieve information for file path
				String cardName = cardList.getString("cardName");
				String side = cardList.getString("Grouping");
				String expansion = cardList.getString("Expansion");
				int cardId = cardList.getInt("id");
				
				//Extract items in parentheses
				/*
				if (cardName.contains(")"))
				{
					int indexOfOpenParens = cardName.indexOf('(');
					int indexOfCloseParens = cardName.indexOf(')');
					
					String firstHalf = cardName.substring(0, indexOfOpenParens);
					String secondHalf = cardName.substring(indexOfCloseParens, cardName.length());
					
					cardName = firstHalf +secondHalf;
				}
				*/
				//Delete the second half of objectives/double cards
				 if (cardName.contains("/"))
				 {
					  int indexOfSlash = cardName.indexOf('/');
					  cardName = cardName.substring(0, indexOfSlash);
				 }
				 
				//Delete extra characters from each bit
				String cardName_p = removeNonLetterCharacters(cardName);
				String side_p = side.trim();
				String expansion_p = removeNonLetterCharacters(expansion);
				
				//Special changes for Virtual Sets
				if (expansion_p.contains("VIRTUAL"))
				{
					//Get the last character-which expansion set it is
					int setNumIndex = expansion_p.length() - 1;
					char virtualSetNum = expansion_p.charAt(setNumIndex);
					expansion_p = "Virtual" + virtualSetNum;
					
					//trim the set nums off the cardname
					
					//int trimmedCardName = cardName_p.length() -2;
					//cardName_p = cardName_p.substring(0, trimmedCardName);
				}
				
				if (expansion_p.contains("PREMIERE2PLAYER"))
				{
					//Get the last character-which expansion set it is
					int setNumIndex = expansion_p.length() - 1;
					expansion_p = "PremiereIntroductoryTwoPlayerGame";
					
					//trim the set nums off the cardname
					
					//int trimmedCardName = cardName_p.length() -2;
					//cardName_p = cardName_p.substring(0, trimmedCardName);
				}
				
				//Alter the name of the enhanced premiere
				if (expansion_p.contains("ENHANCEDPREMIEREPACK"))
				{
					//Get the last character-which expansion set it is
					int setNumIndex = expansion_p.length() - 1;
					expansion_p = "ENHANCEDPREMIERE";
				}
				//Special logic to trim defensive shields
				if(cardName_p.contains("DEFENSIVESHIELD"))
				{
					int lengthDefS = 15;
					int endLength = cardName_p.length() - lengthDefS;
					
					cardName_p = cardName_p.substring(0, endLength);
				}
				
				//Construct the file location
				String s = "/";
				String cardImageFolder = "starwars";
				
				String cardImagePath = s+ cardImageFolder + s + expansion_p + "-"+ side_p +s+ "large";
				
				String fileType = ".gif";
				String fileName = cardName_p + fileType;
				
				String totalPath = cardImagePath + s + fileName;
				
				//Does the file exist?
				try{
					Image img = ImageIO.read(getClass().getResource(totalPath));
					//System.out.println("Success, file exists: " + totalPath);
					addLargeImageFileLocation(dbc, cardId, totalPath);
					numSuccesses++;
				}
				catch(Exception e)
				{
					System.out.println(cardId + "|" + totalPath);
					//e.printStackTrace();
					numFailures++;
				}
			}
		} catch (SQLException e1) {
			System.out.println("Error with Card List Metadata");
			e1.printStackTrace();
		}
		
		System.out.println("Number of images successfully loaded: " + numSuccesses);
		System.out.println("Number of images failed to load: " + numFailures);
		try{
			cardList.close();
		}
		catch(SQLException e){
			System.out.println("Card List result set did not close");
			e.printStackTrace();
		}
		
	}
	
	private String removeNonLetterCharacters(String s)
	{
		//Remove whitespace and convert all uppercase
		String trimmedString = s.trim();
		String upperString = trimmedString.toUpperCase();
		StringBuilder workingString = new StringBuilder(upperString);
		
		
		//test each character, remove non letters
		int i = 0;
		while(i<workingString.length())
		{
			char testChar = workingString.charAt(i);
			int asciiTestChar = testChar;
			//get the grave-E first
			if (asciiTestChar == 201)
			{
				workingString.replace(i, i+1, "E");
				//System.out.println("Replace grave-E executed:" + workingString);//debugging
				asciiTestChar = 69;
			}
			//character and numbers are ok
			//System.out.println(workingString);//debugging
			boolean charIsLetter = asciiTestChar >= 65 && asciiTestChar <= 132;
			boolean charIsNumber = asciiTestChar >= 48 && asciiTestChar <= 57;
			if (!charIsLetter && !charIsNumber && asciiTestChar != '&')
			{
				workingString.deleteCharAt(i);
				//System.out.println("Char deleted at position: " + i);//debugging
			}
			else //only increment if we did not delete
			{
				i++;
			}
			
		}
		String parsedString = workingString.toString();
		
		return parsedString;
	}
	
	private void addLargeImageFileLocation(Connection connection, int cardId, String fileLocation)
	{
		try{
			//Write query
			PreparedStatement insertQuery = connection.prepareStatement(
				"INSERT INTO ImagePaths  "
				+ "(cardId, large) "
				+ "VALUES (?, ?)");
			
			//add values
			insertQuery.setInt(1, cardId);
			insertQuery.setString(2, fileLocation);
			//Execute query
			int rowsAffected = 0;
			rowsAffected = insertQuery.executeUpdate();
			//System.out.println(rowsAffected + " Rows Affected");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
