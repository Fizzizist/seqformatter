/* I declare that the assignment and source code are in my own work in accordance with Seneca 
 * Academic Policy. No part of this assignment or source code have been copied manually or electronically 
 * from any our source (including web sites) or distributed to other students.
 * 
 * Name: Peter Vlasveld 
 * Student ID:  046 316 097 */
package seqformat;

import java.text.DecimalFormat;
import org.apache.commons.lang3.StringUtils;

/**
 * Sequence class for holding the nucleic acid sequence.
 * 
 * @author Peter Vlasveld
 *
 */
public class Sequence{
	//string sequence variable
    private String sequence = new String();
    
    //Constructors------------------------------------------------------------------
    
    /**
     * Constructor for declaring an empty sequence
     */
    public Sequence() {
    	
    }
    
    /**
     * Constructor for setting sequence
     * 
     * @param seq - holds the string of the sequence being analyzed
     */
    public Sequence(String seq){
        sequence = seq;
    }
    
    //Getters----------------------------------------------------------------------------
    
    /**
     * Getter to get the sequence string
     * 
     * @return the sequence string
     */
    public String getSeq(){
        return sequence;
    }

    /**
     * Getter for getting the percentage of a given character in the sequence
     * 
     * @param upper - upper case version of query letter
     * @param lower - lower case version of query letter
     * 
     * @return the value of the percentage as a float rounded to 1 decimal place
     *  as a String object
     */
    public String getPercentage(char upper, char lower){
    	if(sequence.length()==0) {
    		return "0";
    	}
    	//get the total matches
        int count1 = StringUtils.countMatches(sequence,upper);
        int count2 = StringUtils.countMatches(sequence,lower);
        int count = count1 + count2;
        //calculate the percent
        Float percent = (float) count/sequence.length();
        percent *= 100;
        //format it into a string to 1 decimal place
        DecimalFormat df = new DecimalFormat("##.#");
        String formatted = new String(df.format(percent));
        //return the string
        return formatted;
    }

    /**
     * Get Total number of base pairs in the sequence
     * 
     * @return integer representing the total number of base-pairs
     */
    public int getTotalBP() {
    	return sequence.length();
    }
    
    //Setters-----------------------------------------------------------------------------------
    
    /**
     * Setter for setting the sequence string and remove all whitespace and digits
     * 
     * @param seq - string to set the new sequence
     */
    public void setSeq(String seq){
        sequence = seq.replaceAll("[^ACGTUBDHVNWSMKRYacgtubdhvnwsmkry]+", "");
    }
}