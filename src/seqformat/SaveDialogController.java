package seqformat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SaveDialogController {
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="saveOK"
    private Button saveOK; // Value injected by FXMLLoader

    @FXML // fx:id="saveName"
    private TextField saveName; // Value injected by FXMLLoader

    @FXML // fx:id="saveCancel"
    private Button saveCancel; // Value injected by FXMLLoader
    
    private Sequence sequence;
    private Settings settings;
    
    /**
     * Custom method to take in sequence and settings objects form main window
     * 
     * @param seq - passed in sequence object
     * @param set - passed in settings object
     */
    void initData(Sequence seq, Settings set) {
    	sequence = seq;
    	settings = set;
    }
    
    @FXML
    void saveSettingsSequence(Event event) {
    	
    }

    @FXML
    void closeSaveDialog(Event event) {
    	Stage stage = (Stage) saveCancel.getScene().getWindow();
    	stage.close();
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
