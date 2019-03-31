/* I declare that the assignment and source code are in my own work in accordance with Seneca 
 * Academic Policy. No part of this assignment or source code have been copied manually or electronically 
 * from any our source (including web sites) or distributed to other students.
 * 
 * Name: Peter Vlasveld 
 * Student ID:  046 316 097 */
package seqformat;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;

/**
 * Class for dealing with all of the settings that can be changed by the user and saved when they exit
 * 
 * @author Peter Vlasveld
 *
 */
public class Settings {
	//variables
	private Integer charLength;
	private Boolean upperLower = true; //upper = true, lower = false;
	private Boolean showNums = false;
	private Boolean spaces = false;
	
	/**
	 * Constructor that reads settings.xml file and sets all of the settings from that
	 * @throws ParserConfigurationException 
	 */
	public Settings() {
		//read the file
		try {
			Document doc = IO.getDoc("settings.xml");
			charLength = Integer.parseInt(doc.getElementsByTagName("charLength").item(0).getTextContent());
			upperLower = Boolean.parseBoolean(doc.getElementsByTagName("upperLower").item(0).getTextContent());
			showNums = Boolean.parseBoolean(doc.getElementsByTagName("showNums").item(0).getTextContent());
			spaces = Boolean.parseBoolean(doc.getElementsByTagName("spaces").item(0).getTextContent());
		} catch(Exception e) {
			IO.createConfig();
			restoreDefaults();
		}
	
	}
	
	//Getters--------------------------------------------------------------
	
	/**
	 * Getter for charLength (characters per line) variable
	 * 
	 * @return integer representing how many characters per line the user ha specified
	 */
	public Integer getCharLength() {
		return charLength;
	}
	
	/**
	 * Getter for uppercase/lowercase variable
	 * 
	 * @return boolean representing whether the case should be upper or lower
	 */
	public Boolean getUpperLower() {
		return upperLower;
	}
	
	/**
	 * Getter for the show/hide leading numbers variable
	 * 
	 * @return boolean representing whether to show or hide leading numbers 
	 */
	public Boolean getShowNums() {
		return showNums;
	}
	
	/**
	 * Getter for the have/not have spaces variable
	 * 
	 * @return boolean representing whether to insert spaces every 10 characters
	 */
	public Boolean getSpaces() {
		return spaces;
	}
	
	
	//Setters--------------------------------------------------------------------
	
	/**
	 * Setter for charLength (characters per line) variable
	 * 
	 * @param charLen - String representing how many characters per line the user ha specified
	 */
	public void setCharLength(String charLen) {
		try {
			int num = Integer.parseInt(charLen);
			if(num>0) {
				charLength = Integer.parseInt(charLen);
			} else {
				System.out.println("Number is not above 0");
			}
    	} catch (NumberFormatException e) {
    		System.out.println("A number was not input.");
    	}
	}
	
	/**
	 * Setter for uppercase/lowercase variable
	 * 
	 * @param upLow boolean representing whether the case should be upper or lower
	 */
	public void setUpperLower(boolean upLow) {
		upperLower = upLow;
	}
	
	/**
	 * Setter for the show/hide leading numbers variable
	 * 
	 * @param shoN boolean representing whether to show or hide leading numbers 
	 */
	public void setShowNums(boolean shoN) {
		showNums = shoN;
	}
	
	/**
	 * Setter for the have/not have spaces variable
	 * 
	 * @param spaces boolean representing whether to insert spaces every 10 characters
	 */
	public void setSpaces(boolean havSpace) {
		spaces = havSpace;
	}
	
	//Methods--------------------------------------------------------------------------------------
	
	/**
	 * Method for writing the current settings to the XML file
	 */
	public void writeSettings() {
		try {
			//get the xml file
			Document doc = IO.getDoc("settings.xml");
			
			//change the xml file
			doc.getElementsByTagName("charLength").item(0).setTextContent(charLength.toString());
			doc.getElementsByTagName("upperLower").item(0).setTextContent(upperLower.toString());
			doc.getElementsByTagName("showNums").item(0).setTextContent(showNums.toString());
			doc.getElementsByTagName("spaces").item(0).setTextContent(spaces.toString());
			
			//write the changes to the xml file
			IO.writeDoc(doc, "settings.xml");
		} catch (Exception e) {
			System.out.println("Problem opening or writing settings.xml file.");
			//System.out.println(e);
		}
	}
	
	/**
	 * Restore the default settings
	 */
	public void restoreDefaults() {
		charLength = 60;
		upperLower = true;
		showNums = false;
		spaces = false;
	}
}
