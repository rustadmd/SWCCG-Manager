/**
 * @author Mark Rustad
 * @version .01
 * @date May 3, 2014
 */
package swccgManager.Database;

/**
 * @author Mark
 *
 */


import java.sql.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//Not currently used in active program
//import java.io.File;
//import java.net.URL;

public class ImageManager {
	
	Connection m_swdb;
	
	public ImageManager(Connection swdb)
	{
		m_swdb = swdb;
		
		//Check that each card has at least one image
		//testEachCardHasImage(swdb);		
		
		//Check that objective cards have 2 images
		//testObjectiveImages(swdb);
		
		//check that all image paths lead to an image
		//testImagePathsValid(swdb);
	}
	
	/**
	 * Returns a card image for a particular card
	 * @param cardId ID for the card
	 * @param side Which side of the card is to be retrieved: 1 = front, 2 = back
	 * @return Image of the card
	 */
	public Image getCardImage(int cardId, int side)
	{
		String imageLocation = getImagePath (cardId, side);
		//access the image and load it
		BufferedImage cardImage = null;
		try {
			//retrieve card
			cardImage = ImageIO.read(getClass().getResource(imageLocation));
			//System.out.println("ImageLocation within the getCardImage (image retrieved?): " + imageLocation);
		}
		
		catch (Exception e)
		{
			//File does not exist
			//int cardId = imagePaths.getInt("cardID");
			System.out.println("Image not found on path: " + cardId + "|" + imageLocation);
		}
		
		/***broken code
		BufferedImage cardImage = null;
		try {
			
			//cardImage = ImageIO.read(getClass().getResource(imageLocation));
			
			//java.net.URL fullImagePath = getClass().getResource(imageLocation);
			//String fullImagePath_s = fullImagePath.toString();
			//File imageFile = new File(fullImagePath_s);
			//
			//File imageFile = new File(imageLocation);
			
			cardImage = ImageIO.read(getClass().getResource(imageLocation));
			//if(imageFile.exists())
			{
				System.out.println("image exists: " + imageLocation);
			}
			//cardImage = ImageIO.read(imageFile);
		
		} catch (Exception e1) {
			e1.printStackTrace();
		}***/
		if(cardImage == null)
		{
			System.out.println("Image not loaded (ImageManager)");
		}
		
		return cardImage;
	}
	
	public String getImagePath(int cardId, int side)
	{
		//Retrieve card path from db
		String getCardImageQuery =
			"SELECT large "
			+ "FROM ImagePaths "
			+ "WHERE cardId = " + cardId + " AND side = " + side;
		SqlUtilities sqlUtil = new SqlUtilities();
		ResultSet imageLocation = sqlUtil.getQueryResults(m_swdb, getCardImageQuery);
		String imageLocation_s = "";
		
		
		try {
			imageLocation_s = imageLocation.getString("large");
			imageLocation.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(imageLocation_s);//debugging
		return imageLocation_s;
	}
	
	/****Obsolete code
	 * Not currently used during an active program
	 */
	
	/**
	 * Tests each card to make sure it has an image
	 * @param swdb
	 
	@SuppressWarnings("unused")
	private void testEachCardHasImage(Connection swdb)
	{
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet cardsWOImages = gsq.getCardsWithoutImages(swdb);
		
		int numCardsWOImages = 0;
		try {
			while (cardsWOImages.next())
			{
				//Give debugging info if there are no images
				String cardName = cardsWOImages.getString("cardName");
				String expansion = cardsWOImages.getString("Expansion");
				
				System.out.println("Objective Card Type image problem: " + cardName + " " + expansion);
				numCardsWOImages++;
			}
			cardsWOImages.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Display appropriate message
		if (numCardsWOImages == 0)
		{
			System.out.println("All cards have images.");
		}
		else
		{
			System.out.println("Number cards without images: " + numCardsWOImages);
		}
	}
	/**
	 * Tests each objective to guarantee there are exactly 2 images
	 * @param swdb
	 
	
	@SuppressWarnings("unused")
	private void testObjectiveImages(Connection swdb)
	{
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet objectivesNeedingImages = gsq.objectivesWithWrongNumberofImages(swdb);
		int numIncorrectObjectiveImages = 0;
		try {
			while (objectivesNeedingImages.next())
			{
				//Give debugging info if there are the wrong number of images
				String cardName = objectivesNeedingImages.getString("cardName");
				String expansion = objectivesNeedingImages.getString("Expansion");
				int numImages = objectivesNeedingImages.getInt("Num_Images");
				
				System.out.println("Objective Card Type image problem: " + cardName + " " + expansion + " " + numImages);
				numIncorrectObjectiveImages++;
			}
			objectivesNeedingImages.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Display appropriate message
		if (numIncorrectObjectiveImages == 0)
		{
			System.out.println("All Objectives have the correct number of images.");
		}
		else
		{
			System.out.println("Number of objectives with !2 Images: " + numIncorrectObjectiveImages);
		}
		
	}
	/**
	 * Tests each image path to make sure there is a file at the end
	 * @param swdb
	 */
	
	@SuppressWarnings("unused")
	public void testImagePathsValid()
	{
		GenericSQLQueries gsq = new GenericSQLQueries();
		ResultSet imagePaths = gsq.allLargeImagePaths(m_swdb);
		try{
			int numSuccesses = 0;
			int numFailures = 0;
			
			while(imagePaths.next())
			{
				
				String imagePath = imagePaths.getString("large");
				BufferedImage cardImage = null;
				//If you are able to create a file object, the file exists
				try {
					
					java.net.URL fullImagePath = getClass().getResource(imagePath);
					String fullImagePath_s = fullImagePath.toString();
					File imageFile = new File(fullImagePath_s);
					cardImage = ImageIO.read(getClass().getResource(imagePath));
					numSuccesses ++;
				}
				
				catch (Exception e)
				{
					//File does not exist
					numFailures++;
					int cardId = imagePaths.getInt("cardID");
					System.out.println("Image not found on path: " + cardId + "|" + imagePath);
				}
				
			}
			
			System.out.println("Number of images successfully found: " + numSuccesses);
			System.out.println("Number of images NOT found: " + numFailures);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
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
	
	/**
	 * Attempts to automatically add an image with a file path. Will print failed paths to the console
	 * Used only for initial loading of the database
	 * 
	 * @param swdb Connection to the db
	 * @param cardList list of cards that need images
	 
	@SuppressWarnings("unused")
	private void addImagePaths(Connection swdb, ResultSet cardList)
	{
		//Get the list of cards to get the images	
		int numSuccesses = 0;
		int numFailures = 0;
		try {
			//ResultSetMetaData cardListMd= cardList.getMetaData();
			//System.out.println("Current row: " + cardList.getRow());
			while (cardList.next())
			{
				//Retrieve information for file path
				String cardName = cardList.getString("ObjectiveBackName");
				String side = cardList.getString("Grouping");
				String expansion = cardList.getString("Expansion");
				int cardId = cardList.getInt("id");
				
				//Extract items in parentheses
				
				if (cardName.contains(")"))
				{
					int indexOfOpenParens = cardName.indexOf('(');
					int indexOfCloseParens = cardName.indexOf(')');
					
					String firstHalf = cardName.substring(0, indexOfOpenParens);
					String secondHalf = cardName.substring(indexOfCloseParens, cardName.length());
					
					cardName = firstHalf +secondHalf;
				}
				
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
					addLargeImageFileLocation(swdb, cardId, totalPath);
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
	
	/**
	 * Adds an image path for a card to the database
	 * @param connection Connection to the swdb
	 * @param cardId Card id of the card image
	 * @param fileLocation Location where the image file is, including name and type 
	 
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
			//int rowsAffected = 0;
			//rowsAffected = insertQuery.executeUpdate();
			//System.out.println(rowsAffected + " Rows Affected");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	***/
}
