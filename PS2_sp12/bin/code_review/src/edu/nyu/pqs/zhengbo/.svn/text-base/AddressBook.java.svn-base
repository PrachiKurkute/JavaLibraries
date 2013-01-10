package edu.nyu.pqs.zhengbo;

import java.util.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * The AddressBook class represents an address book. It provides methods to add, modify, remove and
 * search entries. This class also allows user to save the entries of the address book to an XML
 * file and load an XML file to construct a new address book.
 * 
 * @author Zhengbo Liu
 */
public class AddressBook {

  private static final String addressBookName= "ADDRESSBOOK";
  private static final String entryName = "CONTACT";
  private static final String fileName = "contactList.xml";
  private List<Contact> contactList;

  /**
   * Constructs an empty address book.
   */
  public AddressBook() {
    contactList = new ArrayList<Contact>();
  }

  /**
   * Constructs an address book from an existing XML file.
   * 
   * @param file the XML file storing the information of an address book.
   */
  public AddressBook(File file) {
    contactList = new ArrayList<Contact>();
    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(file);
      doc.getDocumentElement().normalize();
      NodeList nodeList = doc.getElementsByTagName(entryName);

      ContactItem.ItemType [] itemTypes = ContactItem.ItemType.values();
      for (int i = 0; i < nodeList.getLength(); i++) {
         Node nNode = nodeList.item(i);
         if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            ContactItem [] contactItems = new ContactItem [itemTypes.length];
            for (int j = 0; j < itemTypes.length; j++) {
              NodeList nlList = eElement.getElementsByTagName(itemTypes[j].toString()).item(0).
                  getChildNodes();
              Node nValue = (Node) nlList.item(0);
              String nValueStr = (nValue == null) ? "" : nValue.getNodeValue();
              contactItems[j] = new ContactItem(itemTypes[j], nValueStr);
            }
            Contact contact = new Contact(contactItems);
            addContact(contact);
         }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Constructs a new address book with the same contact information as the specified AddressBook.
   * 
   * @param addressBook the address book whose contact information is to be placed in this address
   * book.
   */
  public AddressBook(AddressBook addressBook) {
    contactList = new ArrayList<Contact>(addressBook.getList());
  }

  @Override
  public String toString() {
    return String.format("Address Book (%d Entries)", contactList.size());
  }

  /**
   * Returns a collection view of the contact information contained in this address book.
   * 
   * @return a collection view of the contact information contained in this address book.
   */
  public List<Contact> getList() {
    return contactList;
  }

  /**
   * Saves the contact information to a default XML file which is defined by <I>fileName</I>. The
   * tag name of the root element of the XML file is defined by <I>addressBookName</I>, and the tag
   * name of each contact is defined by <I>entryName</I>. 
   */
  public void saveToFile() {
    File file = new File(fileName);
    saveToFile(file);
  }

  /**
   * Saves the contact information to an XML file specified by file. The tag name of the root
   * element of the XML file is defined by <I>addressBookName</I>, and the tag name of each contact
   * is defined by <I>entryName</I>.
   * 
   * @param file the file to save the contact information of this address book.
   */
  public void saveToFile(File file) {
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement(addressBookName);
      doc.appendChild(rootElement);      

      for (int i = 0; i < contactList.size(); i++) {
        Element contactElement = doc.createElement(entryName);
        rootElement.appendChild(contactElement);
        ContactItem [] contactItems = contactList.get(i).getContactItems();
        for (int j = 0; j < contactItems.length; j++) {
          Element item = doc.createElement(contactItems[j].getType().toString());
          item.appendChild(doc.createTextNode(contactItems[j].getValue()));
          contactElement.appendChild(item);
        }
        
      }

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(file);
      transformer.transform(source, result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Adds an entry to this address book.
   * 
   * @param contact the contact entry to be added.
   * @return true if the entry is added successfully.
   */
  public boolean addContact(Contact contact) {
    return contactList.add(contact);
  }

  /**
   * Modifies an item (specified by contactItem) of an entry specified by contact.  
   * @param contact contact specifies the entry to be modified.
   * @param contactItem contactItem specifies the item and value used to modify.
   * @return true if the entry is found and modified successfully.
   */
  public boolean modifyContact(Contact contact, ContactItem contactItem) {
    for (Contact entry : contactList) {
      if (entry.equals(contact)) {
        return entry.setItem(contactItem.getType(), contactItem.getValue());
      }
    }
    return false;
  }

  /**
   * Removes the contact entries specified by contacts.
   * 
   * @param contacts an array of Contact which specifies the entries to be removed.
   * @return the number of entries that is removed.
   */
  public int removeContact(Contact [] contacts) {
    int count = 0;
    for (int i = 0; i < contactList.size(); i++) {
      for (int j = 0; j < contacts.length; j++) {
        if (contactList.get(i).equals(contacts[j])) {
          contactList.remove(i--);
          count++;
          break;
        }
      }
    }
    return count;
  }

  /**
   * Returns a list which contains the entries that hold the information specified by contactItems.
   *  
   * @param contactItems an array of ContactItem that specifies the information to search for.
   * @return a list which contains the entries that hold the information specified by contactItems.
   */
  public List<Contact> search(ContactItem [] contactItems) {
    List<Contact> result = new ArrayList<Contact>();
    for (int i = 0; i < contactList.size(); i++) {
      boolean flag = true;
      for (int j = 0; j < contactItems.length; j++) {
        if (!contactList.get(i).getItemValue(contactItems[j].getType())
            .contains(contactItems[j].getValue())) {
          flag = false;
        }
      }
      if (flag) {
        result.add(contactList.get(i));
      }
    }
    return result;
  }
}
