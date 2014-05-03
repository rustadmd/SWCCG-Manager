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
		ResultSet cardList = GenericSQLQueries.getCardVitals(dbc);
		int numSuccesses = 0;
		int numFailures = 0;
		try {
			//ResultSetMetaData cardListMd= cardList.getMetaData();
		
			while (cardList.next())
			{
				//Retrieve information for file path
				String cardName = cardList.getString("cardName");
				String side = cardList.getString("Grouping");
				String expansion = cardList.getString("Expansion");
				
				//Delete extra characters from each bit
				String cardName_p = removeNonLetterCharacters(cardName);
				String side_p = side.trim();
				String expansion_p = removeNonLetterCharacters(expansion);
				
				//Construct the file location
				String s = "/";
				String cardImageFolder = "images" + s + "starwars";
				
				String cardImagePath = s+ cardImageFolder + s + expansion_p + "-"+ side_p +s+ "large";
				
				String fileType = ".gif";
				String fileName = cardName_p + fileType;
				
				String totalPath = cardImagePath + s + fileName;
				
				//Does the file exist?
				try{
					Image img = ImageIO.read(getClass().getResource(totalPath));
					System.out.println("Success, file exists: " + totalPath);
					numSuccesses++;
				}
				catch(Exception e)
				{
					System.out.println("Image NOT found: " + totalPath);
					e.printStackTrace();
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
			boolean charIsLetter = asciiTestChar >= 65 && asciiTestChar <= 132;
			boolean charIsNumber = asciiTestChar >= 48 && asciiTestChar <= 57;
			if (!charIsLetter && !charIsNumber)
			{
				workingString.deleteCharAt(i);
			}
			else //only increment if we did not delete
			{
				i++;
			}
			
		}
		String parsedString = workingString.toString();
		
		return parsedString;
	}

}
