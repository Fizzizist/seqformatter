package seqformat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainWindowController implements Initializable {
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    //Declare the sequence object
    private Sequence sequence = new Sequence();
    //boolean to hold upper and lowercase status
    //true = lower, false = upper
    private boolean upLow = false;
    
    
    @FXML // fx:id="mainText"
    private TextArea mainText; // Value injected by FXMLLoader

    @FXML // fx:id="upperLower"
    private Button upperLower; // Value injected by FXMLLoader

    @FXML // fx:id="charsPerLine"
    private TextField charsPerLine; // Value injected by FXMLLoader
    
    @FXML // fx:id="aPerc"
    private Text aPerc; // Value injected by FXMLLoader

    @FXML // fx:id="gPerc"
    private Text gPerc; // Value injected by FXMLLoader
    
    @FXML // fx:id="cPerc"
    private Text cPerc; // Value injected by FXMLLoader
    
    @FXML // fx:id="tPerc"
    private Text tPerc; // Value injected by FXMLLoader
    
    @FXML // fx:id="totSeqLen"
    private Text totSeqLen; // Value injected by FXMLLoader
    
    @FXML //method to change from uppercase to lowercase and vise versa
    void changeCase(Event event) {
    	if(upLow) {
    		mainText.setText(mainText.getText().toUpperCase());
    		upLow = false;
    	} else {
    		mainText.setText(mainText.getText().toLowerCase());
    		upLow = true;
    	}
    }
    
    /**
     * Function for formatting the mainText area with current settings
     * 
     * @param upperLower - boolean indicating upper or lowercase
     * @param charLength - int indicating line length
     * @param spaces - boolean indicating spaces or no spaces
     * @param showNums - boolean indicating whether to show numbers at the beginning of lines
     * 
     * @return - int indicating actual total length of sequence
     */
    public int formatter(boolean upperLower, int charLength, boolean spaces, boolean showNums) {
    	//get sequence string
    	String seq = sequence.getSeq();
    	//remove all whitespace characters from seq
    	seq = seq.replaceAll("\\s+", "");
    	//get real seq length
    	int realLength = seq.length();
    	//set seq to lower or uppercase
    	if(upperLower) {
    		seq = seq.toUpperCase();
    	} else {
    		seq = seq.toLowerCase();
    	}
    	//split seq into sections based on number of chars
    	List<String> sections = new ArrayList<>();
    	for(int i=0;i<realLength;i+=charLength) {
    		sections.add(seq.substring(i, Math.min(realLength, i + charLength)));
    	}
    	//add spaces if necessary
    	if(spaces) {
    		for(String i : sections) {
    			
    		}
    	}
    	
    	//return length
    	return realLength;
    }
    
    
    /**
     * Function to set all of the percent content values as well as the total
     * sequence length
     */
    private void setPercentandTotal() {
    	char[] letterArrayUpper = {'A','G','C','T'};
    	char[] letterArrayLower = {'a','g','c','t'};
    	Text[] percTextPointers = {aPerc,gPerc,cPerc,tPerc}; 
    	for(int i = 0; i < 4; i++) {
    		String fullText = "%" + letterArrayUpper[i] + ": " + 
    				sequence.getPercentage(letterArrayUpper[i], letterArrayLower[i]);
    		percTextPointers[i].setText(fullText);
    	}
    	totSeqLen.setText("Total Sequence Length: " + sequence.getTotalSeqLen() + "bp");
    }
    
    @Override 
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	//listener
    	mainText.textProperty().addListener(new ChangeListener<String>() {
    			@Override
    			public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
    				sequence.setSeq(mainText.getText());
    				sequence.formatLineLength(charsPerLine.getText());
    				setPercentandTotal();
    				System.out.println("sequence set.");
    			}
    	});
    }
    
}
