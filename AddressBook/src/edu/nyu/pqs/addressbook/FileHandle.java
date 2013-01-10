package edu.nyu.pqs.addressbook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * FileHandle Class used to read a file and write into the file
 * 
 * @author Prachi Kurkute
 * 
 */
public class FileHandle {
	
	private String file;
	private BufferedReader input = null;
	private Writer output = null;
	private String readLine = null;
	private List<String> fileEntryLine;
	
	/**
	 * Basic constructor with all required fields
	 * 
	 * @param fileName name of the file
	 */
	FileHandle(String fileName) {
		file = fileName;
		try {
			output = new BufferedWriter(new FileWriter(file));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * reads contact entries from a file
	 * 
	 * @return a list containing entries of the contacts in address book
	 */
	public List<String> readFromFile() {
		fileEntryLine=new ArrayList<String>();
		try {
			input = new BufferedReader(new FileReader(file));
			while (input.ready()) { 
				readLine = input.readLine(); 
				fileEntryLine.add(readLine); 
			}
			input.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return fileEntryLine;
	}
	
	/**
	 * writes content to the file
	 * 
	 * @param text to be written to the file
	 */
	public void writeToFile(String text) {
		try {
			//output = new BufferedWriter(new FileWriter(file));
			output.write(text);
			output.write(13);
			output.write(10);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens up the file
	 */
	public void openFile() {
		try {
			output = new BufferedWriter(new FileWriter(file));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * closes the file
	 */
	public void finishWrite() {
		try {
			output.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}