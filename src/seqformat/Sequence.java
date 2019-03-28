package seqformat;

import java.text.DecimalFormat;
import org.apache.commons.lang3.StringUtils;
public class Sequence{
    private String sequence = new String();
    
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
        int count1 = StringUtils.countMatches(sequence,upper);
        int count2 = StringUtils.countMatches(sequence,lower);
        int count = count1 + count2;
        Float percent = (float) count/sequence.length();
        percent *= 100;
        //System.out.println(upper + lower + percent.toString());
        DecimalFormat df = new DecimalFormat("##.#");
        String formatted = new String(df.format(percent));
        return formatted;
    }

    /**
     * Getter for total sequence length
     * 
     * @return length of the sequence as a String
     */
    public String getTotalSeqLen() {
    	Integer len = sequence.length();
    	return len.toString();
    }

    /**
     * Setter for setting the sequence string
     * 
     * @param seq - string to set the new sequence
     */
    public void setSeq(String seq){
        sequence = seq;
    }

    /**
     * Function for formatting the string into the line lengths
     * specified in the Chars per line field
     * 
     * @param lineLength - string containing charsPerLine content
     */
    public void formatLineLength(String lineLength) {
    	int intLength = 60;
    	try {
    		intLength = Integer.parseInt(lineLength);
    	} catch (NumberFormatException e) {
    		System.out.println("A number was not input.");
    	}
    	
    }

}