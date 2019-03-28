package seqformat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainWindowController implements Initializable {
	//FXML Variables-------------------------------------------------------------
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="mainText"
    private TextArea mainText; // Value injected by FXMLLoader
    
    @FXML // fx:id="upperLower"
    private Button upperLower; // Value injected by FXMLLoader
    
    @FXML // fx:id="spaceButton"
    private Button spaceButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="showNumButton"
    private Button showNumButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="saveButton"
    private Button saveButton; // Value injected by FXMLLoader

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
    
    
    
    //Object variables--------------------------------------------------------------
    
    //Declare the sequence object
    private Sequence sequence = new Sequence();
    //load the settings
    private Settings settings = new Settings();
    
    //Button Controls -----------------------------------------------------------------
    
    @FXML //method to change from uppercase to lowercase and vise versa
    void changeCase(Event event) {
    	if(settings.getUpperLower()) {
    		settings.setUpperLower(false);
    		formatter(settings.getUpperLower(), settings.getCharLength(),settings.getSpaces(),settings.getShowNums());
    	} else {
    		settings.setUpperLower(true);
    		formatter(settings.getUpperLower(), settings.getCharLength(),settings.getSpaces(),settings.getShowNums());
    	}
    }
    
    @FXML //controller for insert/remove spaces button
    void changeSpaceSetting(Event event) {
    	if(settings.getSpaces()) {
    		settings.setSpaces(false);
    		formatter(settings.getUpperLower(), settings.getCharLength(),settings.getSpaces(),settings.getShowNums());
    	} else {
    		settings.setSpaces(true);
    		formatter(settings.getUpperLower(), settings.getCharLength(),settings.getSpaces(),settings.getShowNums());
    	}
    }

    @FXML //controller for show/hide base numbers button
    void showHideNumbers(Event event) {
    	if(settings.getShowNums()) {
    		settings.setShowNums(false);
    		formatter(settings.getUpperLower(), settings.getCharLength(),settings.getSpaces(),settings.getShowNums());
    	} else {
    		settings.setShowNums(true);
    		formatter(settings.getUpperLower(), settings.getCharLength(),settings.getSpaces(),settings.getShowNums());
    	}
    }
    
    @FXML //controller for save button (open a new dialog window)
    void openSaveDialog(Event event) {
		Stage saveDialog = showSaveDialog(sequence,settings);
		saveDialog.show();
    }
    
    //Text Formatting--------------------------------------------------------------------------
    
    /**
     * Function for formatting the mainText area with current settings
     * 
     * @param upperLower - boolean indicating upper or lowercase
     * @param charLength - int indicating line length
     * @param spaces - boolean indicating spaces or no spaces
     * @param showNums - boolean indicating whether to show numbers at the beginning of lines
     */
    public void formatter(boolean upperLower, int charLength, boolean spaces, boolean showNums) {
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
    		for(int i = 0; i < sections.size(); i++) {
    			String workingStr = sections.get(i);
    			if(workingStr.length() > 10) {
	    			String withSpaces = workingStr.substring(0,10);
	    			for(int j=10; j<workingStr.length();j += 10) {
	    				if(j+10 < workingStr.length()) {
	    					withSpaces = (withSpaces + " " + workingStr.substring(j,j+10));
	    					//System.out.println(withSpaces);
	    				} else {
	    					withSpaces = (withSpaces + " " + workingStr.substring(j));
	    				}
	    			}
	    			sections.set(i, withSpaces);
	    			System.out.println(sections.get(i));
    			}
    		}
    	}
    	
    	//add numbers to the beginning if necessary
    	if(showNums) {
    		//make an array of leading numbers
    		String[] leadingNums = new String[sections.size()];
    		Integer counter = 1;
    		for (int i=0;i<sections.size();i++) {
    			leadingNums[i] = counter.toString();
    			counter += charLength;
    		}
    		int numLeadingSpaces = leadingNums[sections.size()-1].length();
    		String formattingStr = "%1$" + numLeadingSpaces + "s";
    		String[] formattedNums = new String[sections.size()];
    		for (int i = 0; i<formattedNums.length;i++) {
    			formattedNums[i] = String.format(formattingStr, leadingNums[i]);
    		}
    		//finally, add the numbers to the front of each section
    		for (int i=0;i<sections.size();i++) {
    			String before = sections.get(i);
    			sections.set(i, (formattedNums[i] + " " + before));
    		}
    	}
    	
    	//add newlines to the end of each section and combine them all
    	String finalStr = new String();
    	for(String i : sections) {
    		i += "\n";
    		finalStr += i;
    	}
    	
    	//print finalStr to main text window
    	mainText.setText(finalStr);
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
    	totSeqLen.setText("Total Sequence Length: " + sequence.getTotalBP() + "bp");
    }
    
    /**
     * custom stage for save dialog box
     * 
     * @param seq - sequence object to pass to dialog
     * @param set - settings object to pass to dialog
     * 
     * @return stage object for display
     * @throws IOException 
     */
    public Stage showSaveDialog(Sequence seq, Settings set) {
    	try {
	    	FXMLLoader loader = new FXMLLoader(
	    			getClass().getResource("SaveDialog.fxml")
	    	);
	    	Stage stage = new Stage();
	    	stage.setScene(
	    			new Scene((AnchorPane) loader.load())
	    	);
	    	SaveDialogController controller = loader.<SaveDialogController>getController();
	    	controller.initData(seq,set);
	    	//stage.show();
	    	return stage;
    	} catch(IOException e) {
	    	System.out.println("IO exception when trying to load save dialog");
	    	return null;
	    }
    }
    
    //Text input listeners
    @Override 
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	//listener for main text area
    	mainText.textProperty().addListener(new ChangeListener<String>() {
    			@Override
    			public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
    				sequence.setSeq(mainText.getText());
    				formatter(settings.getUpperLower(), settings.getCharLength(),settings.getSpaces(),settings.getShowNums());
    				setPercentandTotal();
    				System.out.println("sequence set.");
    			}
    	});
    	//listener for charlength text feild
    	charsPerLine.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
				settings.setCharLength(charsPerLine.getText());
				formatter(settings.getUpperLower(), settings.getCharLength(),settings.getSpaces(),settings.getShowNums());
				System.out.println("changed chars per line.");
			}
    	});
    }
    
}
