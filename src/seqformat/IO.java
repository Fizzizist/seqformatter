/* I declare that the assignment and source code are in my own work in accordance with Seneca 
 * Academic Policy. No part of this assignment or source code have been copied manually or electronically 
 * from any our source (including web sites) or distributed to other students.
 * 
 * Name: Peter Vlasveld 
 * Student ID:  046 316 097 */
package seqformat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Abstract class for holding IO methods
 * 
 * @author Peter Vlasveld
 */
public abstract class IO {
	
	//Text file methods-----------------------------------------------------------
	
	/**
	 * Method for writing text to a file
	 * 
	 * @param content - String of text to write
	 * @param file - filename specified by user
	 */
	public static void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Unable to save the specified file. Try again.");
        }
    }
	
	/**
	 * Method for reading in the text of a file
	 * 
	 * @param file - the file to be read
	 * 
	 * @return a string containing the file contents
	 */
	public static String readFileText(File file){
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
        
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
            }

        } catch (Exception e) {
            System.out.println("Problem reading specified file. Try again.");
        } 
        
        return stringBuffer.toString();
    }
	
	/**
	 * Method to read the lines of a file and return them as a list
	 * 
	 * @param file - the file to be read
	 * 
	 * @return a list of Strings which represent the lines of the file
	 */
	public static List<String> readFileLines(File file) {
        BufferedReader bufferedReader = null;
        List<String> lines = new ArrayList<String>();
        
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                lines.add(text);
            }

        } catch (Exception e) {
            System.out.println("Problem reading specified file. Try again.");
        } 
        
        return lines; 
	}
	
	//XML Methods-------------------------------------------------------------------
	
	/**
	 * Method to get the doc object of an xml file
	 * 
	 * @param filename - of the file being opened
	 * 
	 * @return the doc object
	 * 
	 * @throws exception if file isn't there which will trigger createConfig
	 */
	public static Document getDoc(String filename) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new File(filename));
		return doc;
	}
	
	/**
	 * Method to write changes to an xml file
	 * 
	 * @param doc - Document that has been changed upstream
	 * @param filename - of the file to write to
	 * 
	 * @throws Exception if the file doesn't open, to be handled upstream 
	 */
	public static void writeDoc(Document doc, String filename) throws Exception{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);
        System.out.println("XML file updated successfully");
	}
	
	/**
	 * Method to create a new config file if one doesn't exist yet
	 */
	public static void createConfig() {
		try {
			//create xml document
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			Element rootElement = doc.createElement("settings");
			doc.appendChild(rootElement);
			
			Element charLength = doc.createElement("charLength");
			charLength.appendChild(doc.createTextNode("60"));
			rootElement.appendChild(charLength);
			Element upLow = doc.createElement("upperLower");
			upLow.appendChild(doc.createTextNode("true"));
			rootElement.appendChild(upLow);
			Element showNums = doc.createElement("showNums");
			showNums.appendChild(doc.createTextNode("false"));
			rootElement.appendChild(showNums);
			Element spaces = doc.createElement("spaces");
			spaces.appendChild(doc.createTextNode("false"));
			rootElement.appendChild(spaces);
			
			
			//write the changes
			writeDoc(doc, "settings.xml");
			
		} catch(Exception e) {
			System.out.println("FATAL ERROR: Something went wrong creating the config file.");
			System.exit(0);
		}
	}
}
