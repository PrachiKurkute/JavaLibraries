package edu.nyu.pqs.addressbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * AddressBook.java
 * 
 * Simple immutable final class containing members for address book.
 * It contains list of contact entries in the address book
 * 
 * @author Prachi Kurkute
 */
public class AddressBook implements Comparable<AddressBook> {
	
	private final String bookName;
    private List<Entry> list;              //handling list of entries in address book
    private List<String> fileEntryLine;    //handling list of entries in file
    private FileHandle file;
    private String entryLine;              //handling formatting of entry to store in a file
      
    /**
	 * Basic constructor with all required fields
	 */
    public AddressBook(String name) {
      bookName = name;
      list = new ArrayList<Entry>();
      fileEntryLine = new ArrayList<String>();
      file = new FileHandle(bookName);
    }
    
    /*
     * Adds entries in AddressBook. It first extracts entries from file 
     * and then write all these entries along with the new entry into the file.
     */
    public void add(Entry entry) {
      list.add(entry);
      
      entryLine = makeString(entry);
      
      file.openFile();
      fileEntryLine = file.readFromFile();
      Iterator<String> tempItr = fileEntryLine.iterator();
	  while(tempItr.hasNext()) {
		  file.writeToFile(tempItr.next());
	  }
	  
      file.writeToFile(entryLine);
      file.finishWrite();
    }	
    
    /*
     * Searches entries in AddressBook that matches with given name
     * 
     * @param name Name to be matched in Address Book
     * @return returns list containing entries matching with given name otherwise null 
     * @throws exception if parameter given is invalid
     */
    public List<Entry> searchByName(Name name) throws Exception {
    	List<Entry> searchedList=new ArrayList<Entry>();
    	Entry temp;
    	
    	if (!(name instanceof Name)) {
    		throw new Exception("Invalid parameter") ;
    	}
    	
    	Iterator<Entry> itr = list.iterator();
    	while(itr.hasNext()) {
    		temp = itr.next();
    		if(temp.getName().equals(name)) {
    			searchedList.add(temp);
    		}
    	}
    	
    	if(searchedList.isEmpty()) {
    		return null;
    	}
    	
		return searchedList;
    }
    
    /*
     * Searches entries in AddressBook that matches with given address
     * 
     * @param address Address to be matched in Address Book
     * @return returns list containing entries matching with given address otherwise null
     * @throws exception if parameter given is invalid
     */
    public List<Entry> searchByAddress(Address address) throws Exception {
    	List<Entry> searchedList=new ArrayList<Entry>();
    	Entry temp;
    	
    	if (!(address instanceof Address)) {
    		throw new Exception("Invalid parameter") ;
    	}
    	
    	Iterator<Entry> itr = list.iterator();
    	while(itr.hasNext()) {
    		temp = itr.next();
    		if(temp.getAddress().equals(address)) {
    			searchedList.add(temp);
    		}
    	}
    	
    	if(searchedList.isEmpty()) {
    		return null;
    	}
    	
		return searchedList;
    }
    
    /*
     * Searches entries in AddressBook that matches with given phone number
     * 
     * @param phoneNumber Phone Number to be matched in Address Book
     * @return returns list containing entries matching with given phone number otherwise null
     * @throws exception if parameter given is invalid
     */
    public List<Entry> searchByPhoneNumber(PhoneNumber phoneNumber) throws Exception {
    	List<Entry> searchedList=new ArrayList<Entry>();
    	Entry temp;
    	
    	if (!(phoneNumber instanceof PhoneNumber)) {
    		throw new Exception("Invalid parameter") ;
    	}
    	
    	Iterator<Entry> itr = list.iterator();
    	while(itr.hasNext()) {
    		temp = itr.next();
    		if(temp .getPhoneNumber().equals(phoneNumber)) {
    			searchedList.add(temp);
    		}
    	}
    	
    	if(searchedList.isEmpty()) {
    		return null;
    	}
    	
		return searchedList;
    }
    
    /*
     * Searches entries in AddressBook that matches with given email
     * 
     * @param email email id to be matched in Address Book
     * @return returns list containing entries matching with given email otherwise null
     * @throws exception if parameter given is invalid
     */
    public List<Entry> searchByPhoneNumber(String email) throws Exception {
    	List<Entry> searchedList=new ArrayList<Entry>();
    	Entry temp;
    	
    	if (!(email instanceof String)) {
    		throw new Exception("Invalid parameter") ;
    	}
    	 
    	Iterator<Entry> itr = list.iterator();
    	while(itr.hasNext()) {
    		temp = itr.next();
    		if(temp.getEmail().equals(email)) {
    			searchedList.add(temp);
    		}
    	}
    	
    	if(searchedList.isEmpty()) {
    		return null;
    	}
    	
		return searchedList;
    }
    
    /*
     * Searches entries in AddressBook that matches with given note
     * 
     * @param note note to be matched in Address Book
     * @return returns list containing entries matching with given note otherwise null
     * @throws exception if parameter given is invalid
     */
    public List<Entry> searchByNote(String note) throws Exception {
    	List<Entry> searchedList=new ArrayList<Entry>();
    	Entry temp;
    	
    	if (!(note instanceof String)) {
    		throw new Exception("Invalid parameter") ;
    	}
    	
    	Iterator<Entry> itr = list.iterator();
    	while(itr.hasNext()) {
    		temp = itr.next();
    		if(temp.getNote().equals(note)) {
    			searchedList.add(temp);
    		}
    	}
    	
    	if(searchedList.isEmpty()) {
    		return null;
    	}
    	
		return searchedList;
    }
    
    /*
     * Deletes all entries in AddressBook that matches with given name
     * and overwrites the file with the new list of entries.
     * 
     * @param name name entry to be deleted in Address Book
     */
    public void deleteByName(Name name) {
    	Entry temp;
    	
    	if (name == null) {
    		return;
    	}
    		
    	
    	Iterator<Entry> itr = list.iterator();
    	while(itr.hasNext()) {
    		temp=itr.next();
    		if(temp.getName().equals(name)) {
    			itr.remove();
    		}
    	}
    	
    	//writing updated list of entries to file
    	file.openFile();
    	itr = list.iterator();
  	    while(itr.hasNext()) {
  	    	temp=itr.next();
  	    	entryLine = makeString(temp);
  		    file.writeToFile(entryLine);
  	    }
        file.finishWrite();
    }
    
    /*
     * Deletes all entries in AddressBook that matches with given address
     * @param address address entry to be deleted in Address Book
     */
    public void deleteByAddress(Address address) {
    	Entry temp;
    	
    	if (address == null) {
    		return;
    	}
    	
    	Iterator<Entry> itr = list.iterator();
    	while(itr.hasNext()) {
    		temp=itr.next();
    		if(temp.getAddress().equals(address)) {
    			itr.remove();
    		}
    	}
    	
    	//writing updated list of entries to file
    	file.openFile();
    	itr = list.iterator();
  	    while(itr.hasNext()) {
  	    	temp=itr.next();
  	    	entryLine = makeString(temp);
  		    file.writeToFile(entryLine);
  	    }
        file.finishWrite();
    }
    
    /*
     * Deletes all entries in AddressBook that matches with given phone number
     * @param phone number phone number entry to be deleted in Address Book
     */
    public void deleteByPhoneNumber(PhoneNumber phoneNumber) {
    	Entry temp;
    	
    	if (phoneNumber == null) {
    		return;
    	}
    	
    	Iterator<Entry> itr = list.iterator();
    	while(itr.hasNext()) {
    		temp=itr.next();
    		if(temp.getPhoneNumber().equals(phoneNumber)) {
    			itr.remove();
    		}
    	}
    	
    	//writing updated list of entries to file
    	file.openFile();
    	itr = list.iterator();
  	    while(itr.hasNext()) {
  	    	temp=itr.next();
  	    	entryLine = makeString(temp);
  		    file.writeToFile(entryLine);
  	    }
        file.finishWrite();
    }
    
    /*
     * Deletes all entries in AddressBook that matches with given email id
     * @param email email id entry to be deleted in Address Book
     */
    public void deleteByEmail(String email) {
    	Entry temp;
    	
    	if (email == null) {
    		return;
    	}
    	
    	Iterator<Entry> itr = list.iterator();
    	while(itr.hasNext()) {
    		temp=itr.next();
    		if(temp.getEmail().equals(email)) {
    			itr.remove();
    		}
    	}
    	
    	//writing updated list of entries to file
    	file.openFile();
    	itr = list.iterator();
  	    while(itr.hasNext()) {
  	    	temp=itr.next();
  	    	entryLine = makeString(temp);
  		    file.writeToFile(entryLine);
  	    }
        file.finishWrite();
    }
    
    /*
     * Deletes all entries in AddressBook that matches with given note
     * @param note note entry to be deleted in Address Book
     */
    public void deleteByNote(String note) {
    	Entry temp;
    	
    	if (note == null) {
    		return;
    	}
    	
    	Iterator<Entry> itr = list.iterator();
    	while(itr.hasNext()) {
    		temp=itr.next();
    		if(temp.getNote().equals(note)) {
    			itr.remove();
    		}
    	}
    	
    	//writing updated list of entries to file
    	file.openFile();
    	itr = list.iterator();
  	    while(itr.hasNext()) {
  	    	temp=itr.next();
  	    	entryLine = makeString(temp);
  		    file.writeToFile(entryLine);
  	    }
        file.finishWrite();
    }
    
    /**
     * Helper function that makes the string in the format which is easier to store and retrieve
     * 
     * @return String in a format to store in the file
     */
    private static String makeString(Entry temp) {
    	
    	return temp.getName().getFirstName()
        +";"+temp.getName().getMiddleName()
        +";"+temp.getName().getLastName()
        +";"+temp.getAddress().getAddressLine1()
        +";"+temp.getAddress().getAddressLine2()
        +";"+temp.getAddress().getCity()
        +";"+temp.getAddress().getState()
        +";"+temp.getAddress().getCountry()
        +";"+temp.getAddress().getZip()
        +";"+temp.getPhoneNumber().getCountryCode()
        +";"+temp.getPhoneNumber().getAreaCode()
        +";"+temp.getPhoneNumber().getNumber()
        +";"+temp.getEmail()
        +";"+temp.getNote();
    }
    
    /**
	 * Returns the hash code value for this AddressBook.
	 * 
	 * @return the hash code value for this Addressbook.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bookName == null) ? 0 : bookName.hashCode());
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}

	/**
	 * Compares the specified object with this address book for equality.
	 * Returns true if and only if the specified object is also an
	 * address book and all fields are {@code equal}.
	 * 
	 * @param o the object to be compared for equality with this address book
	 * @return true if the specified object is equal to this address book
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AddressBook))
			return false;
		AddressBook other = (AddressBook) obj;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
    
	/**
	 * Returns a String representation of this address book
	 * 
	 * @return a string representing this address book
	 */
	@Override public String toString(){
		return String.format("Address Book (%s)", bookName);
	}
	
	/**
	 * Compares this address book to another using only the address book name.
	 *  
	 * @param a non-null account to be compared
	 * @return 0 if both are same otherwise difference between both
	 */
	@Override public int compareTo(AddressBook ab) {
		int diff = 0;
		
		diff = bookName.compareTo(ab.bookName);
		return diff;
	}

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}
}