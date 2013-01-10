package edu.nyu.pqs.zhengbo;

import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;

/**
 * The Contact class represents a contact entry in an address book.
 * 
 * @author Zhengbo Liu
 */
public class Contact {

  private ContactItem [] contactItems;

  /**
   * Constructs a Contact from contactItems.
   * 
   * @param contactItems an array of ContactItem which contains the information to construct
   * Contact.
   * @throws IllegalArgumentException if contactItems doesn't have enough items for Contact, or if
   * the value is an empty string when the type is NAME.
   */
  public Contact(ContactItem [] contactItems) {
    ContactItem.ItemType [] itemTypes = ContactItem.ItemType.values();
    if (contactItems.length != itemTypes.length) {
      throw new IllegalArgumentException();
    }
    this.contactItems = new ContactItem[itemTypes.length];
    for (int i = 0; i < itemTypes.length; i++) {
      if (contactItems[i].getValue() == null) {
        throw new IllegalArgumentException();
      }
      if (contactItems[i].getType() == ContactItem.ItemType.NAME
          && contactItems[i].getValue().equals("")) {
        throw new IllegalArgumentException();
      }
      this.contactItems[i] = contactItems[i];
    }
  }

  /**
   * Returns the value of the item specified by itemType.
   * 
   * @param itemType itemType specifies the type of the item to get.
   * @return the value of the item specified by itemType.
   */
  public String getItemValue(String itemType) {
    ContactItem.ItemType type = ContactItem.ItemType.valueOf(itemType);
    return getItemValue(type);
  }

  /**
   * Returns the value of the item specified by itemType.
   * 
   * @param itemType itemType specifies the type of the item to get.
   * @return the value of the item specified by itemType.
   * @throws NullPointerException if itemType is null.
   */
  public String getItemValue(ContactItem.ItemType itemType) {
    if (itemType == null) {
      throw new NullPointerException();
    }
    for (ContactItem item : contactItems) {
      if (item.getType() == itemType) {
        return item.getValue();
      }
    }
    return null;
  }

  /**
   * Replaces the item specified by itemType with a new item with specified value.
   * 
   * @param itemType itemType specifies the type of the item to replace.
   * @param value the value of the new item.
   */
  public void setItem(String itemType, String value) {
    ContactItem.ItemType type = ContactItem.ItemType.valueOf(itemType);
    setItem(type, value);
  }

  /**
   * Replaces the item specified by itemType with a new item with specified value.
   * 
   * @param itemType itemType specifies the type of the item to replace.
   * @param value the value of the new item.
   * @return true if the item is replaced successfully.
   * @throws NullPointerException if value or itemType is null.
   */
  public boolean setItem(ContactItem.ItemType itemType, String value) {
    if (value == null || itemType == null) {
      throw new NullPointerException();
    }
    for (int i = 0; i < contactItems.length; i++) {
      if (contactItems[i].getType() == itemType) {
        contactItems[i] = new ContactItem(itemType, value);
        return true;
      } 
    }
    return false;
  }

  /**
   * Returns an array consists of the items of this Contact.
   * 
   * @return an array consists of the items of this Contact.
   */
  public ContactItem [] getContactItems() {
    return contactItems;
  }

  @Override
  public String toString() {
    String name = getItemValue(ContactItem.ItemType.NAME);
    String phone = getItemValue(ContactItem.ItemType.PHONE);
    String email = getItemValue(ContactItem.ItemType.EMAIL);
    return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
  }
}
