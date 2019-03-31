/* I declare that the assignment and source code are in my own work in accordance with Seneca 
 * Academic Policy. No part of this assignment or source code have been copied manually or electronically 
 * from any our source (including web sites) or distributed to other students.
 * 
 * Name: Peter Vlasveld 
 * Student ID:  046 316 097 */
package seqformat;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * Main class for loading the primary stage
 * 
 * @author Peter Vlasveld
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			AnchorPane root = (AnchorPane) fxmlloader.load();
			Scene scene = new Scene(root,700,600);
			
			primaryStage.setTitle("Sequence Formatter");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
