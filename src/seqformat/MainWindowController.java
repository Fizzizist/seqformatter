/* I declare that the assignment and source code are in my own work in accordance with Seneca 
 * Academic Policy. No part of this assignment or source code have been copied manually or electronically 
 * from any our source (including web sites) or distributed to other students.
 * 
 * Name: Peter Vlasveld 
 * Student ID:  046 316 097 */
package seqformat;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.layout.Region;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Controller class for MainWindow.fxml
 * 
 * @author Peter Vlasveld
 *
 */
public class MainWindowController implements Initializable {
	//FXML Variables-------------------------------------------------------------
	
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    //Text Fields and Areas------------------------------------------
    
    @FXML // fx:id="charsPerLine"
    private TextField charsPerLine; // Value injected by FXMLLoader
    
    @FXML // fx:id="mainText"
    private TextArea mainText; // Value injected by FXMLLoader
    
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
    
    //Buttons--------------------------------------------------------
    
    @FXML // fx:id="upperLower"
    private Button upperLower; // Value injected by FXMLLoader
    
    @FXML // fx:id="spaceButton"
    private Button spaceButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="showNumButton"
    private Button showNumButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="saveButton"
    private Button saveButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="loadButton"
    private Button loadButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="restoreButton"
    private Button restoreButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="loadFastaButton"
    private Button loadFastaButton; // Value injected by FXMLLoader
    
    //Menu Items-------------------------------------------------------------
    
    @FXML // fx:id="closeMenuItem"
    private MenuItem closeMenuItem; // Value injected by FXMLLoader
    
    @FXML // fx:id="aboutMenuItem"
    private MenuItem aboutMenuItem; // Value injected by FXMLLoader
    
    @FXML // fx:id="restartItem"
    private MenuItem restartItem; // Value injected by FXMLLoader
    
    //other variables--------------------------------------------------------------
    
    //Declare the sequence object
    private Sequence sequence = new Sequence();
    //load empty settings
    private Settings settings;
    
    
    //Menu Bar Controls--------------------------------------------------------------
    
    @FXML //File > Close menu item controller
    void shutdownProgram(ActionEvent event) {
    	Stage stage = (Stage) saveButton.getScene().getWindow();
    	stage.close();
    }
    
    @FXML //Alert window for 'About' dialog
    void openAboutDialog(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("About Sequence Formatter");
		alert.setContentText("Sequence Formatter is a small Java Application written by Peter Vlasveld. "
				+ "This app allows the user to enter or upload a DNA sequence in either raw text or FASTA format "
				+ "and allows them to manipulate the text and format it however they would like. "
				+ "The text can then be saved as a text file, or copied right out of the window. "
				+ "The user can also edit the sequence as they format it.\n\n"
				+ "To begin, simply paste a DNA sequence into the main window, "
				+ "or press one of the 'load' buttons to load a sequence in.\n\n"
				+ "Happy formatting!");
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.showAndWait();
    }
    
    @FXML //Restart menu item to clear the mainText window
    void clearSequence(ActionEvent event) {
    	sequence.setSeq("");
    	formatter(settings.getUpperLower(), settings.getCharLength(),settings.getSpaces(),settings.getShowNums());
    }
    
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
    
    @FXML //controller for Restore Defaults button
    void restoreDefaults(Event event) {
    	charsPerLine.setText("60");
    	settings.restoreDefaults();
    	formatter(settings.getUpperLower(), settings.getCharLength(),settings.getSpaces(),settings.getShowNums());
    }
    
    @FXML //controller for save button
    void openSaveDialog(Event event) {
    	FileChooser fc = new FileChooser();
    	Stage stage = (Stage) saveButton.getScene().getWindow();
    	File file = fc.showSaveDialog(stage);
		
    	if(file!=null) {
    		IO.saveTextToFile(mainText.getText(), file);
    	}
    }
    
    @FXML //loading raw sequence with no header
    void loadSequence(Event event) {
    	FileChooser fc = new FileChooser();
    	Stage stage = (Stage) saveButton.getScene().getWindow();
    	File file = fc.showOpenDialog(stage);
    	
    	if(file!=null) {
    		mainText.setText(IO.readFileText(file));
    	}
    }
    
    //loading FASTA sequence with fasta header
    @FXML //method cuts off the header and inserts raw sequence into mainText
    void loadFastaFile(Event event) {
    	FileChooser fc = new FileChooser();
    	Stage stage = (Stage) saveButton.getScene().getWindow();
    	File file = fc.showOpenDialog(stage);
    	
    	if(file!=null) {
    		List<String> fastaText = IO.readFileLines(file);
    		for(Iterator<String> iter=fastaText.listIterator(); iter.hasNext();) {
    			String str = iter.next();
    			if(str.length()>1 && str.substring(0, 1).equals(">")) {
    				iter.remove();
    			}
    		}
    		mainText.setText(String.join("",fastaText));
    	}
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
    	
    	//remove all whitespace characters and other letters from seq
    	seq = seq.replaceAll("[^ACGTUBDHVNWSMKRYacgtubdhvnwsmkry]+", "");
    	
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
    
    //Text input listeners and other initialization activities-----------------------------------
    
    @Override //initialization override method for MainWindow Activities
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	//load settings into settings variable
    	settings = new Settings();
    	
    	//Shutdown hook for writing the settings upon shutdown
    	Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                settings.writeSettings();
            }
        }));
    	
    	//initially set charlength upon opening
    	charsPerLine.setText(settings.getCharLength().toString());
    	
    	//listener for main text area
    	//Whenever main text area is editted it is reformatted and the sequence is updated
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
