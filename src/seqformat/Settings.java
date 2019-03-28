package seqformat;

public class Settings {
	private int charLength = 60;
	private boolean upperLower = true; //upper = true, lower = false;
	private boolean showNums = false;
	private boolean spaces = false;
	public Settings() {
		
	}
	
	//Getters--------------------------------------------------------------
	
	/**
	 * Getter for charLength (characters per line) variable
	 * 
	 * @return integer representing how many characters per line the user ha specified
	 */
	public int getCharLength() {
		return charLength;
	}
	
	/**
	 * Getter for uppercase/lowercase variable
	 * 
	 * @return boolean representing whether the case should be upper or lower
	 */
	public boolean getUpperLower() {
		return upperLower;
	}
	
	/**
	 * Getter for the show/hide leading numbers variable
	 * 
	 * @return boolean representing whether to show or hide leading numbers 
	 */
	public boolean getShowNums() {
		return showNums;
	}
	
	/**
	 * Getter for the have/not have spaces variable
	 * 
	 * @return boolean representing whether to insert spaces every 10 characters
	 */
	public boolean getSpaces() {
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
	
	
}
