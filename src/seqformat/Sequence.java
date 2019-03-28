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
     * Get Total number of base pairs in the sequence
     * 
     * @return integer representing the total number of base-pairs
     */
    public int getTotalBP() {
    	return sequence.length();
    }
    
    /**
     * Setter for setting the sequence string and remove all whitespace and digits
     * 
     * @param seq - string to set the new sequence
     */
    public void setSeq(String seq){
        sequence = seq.replaceAll("\\s+", "").replaceAll("\\d+", "");
    }
}