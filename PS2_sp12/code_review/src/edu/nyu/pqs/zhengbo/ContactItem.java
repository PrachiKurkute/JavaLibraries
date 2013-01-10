package edu.nyu.pqs.zhengbo;

import java.lang.NullPointerException;

/**
 * The ContactItem class represents an item in a contact entry.
 * 
 * @author Zhengbo Liu
 */
public class ContactItem {

  public enum ItemType {NAME, ADDRESS, PHONE, EMAIL, NOTE}

  private ItemType type;
  private String value;

  /**
   * Constructs a ContactItem with specified type and value.
   * 
   * @param type the type of the new ContactItem.
   * @param value the value of the new ContactItem.
   */
  public ContactItem(String type, String value) {
    this(ItemType.valueOf(type), value);
  }

  /**
   * Constructs a ContactItem with specified type and value.
   * 
   * @param type the type of the new ContactItem.
   * @param value the value of the new ContactItem.
   */
  public ContactItem(ItemType type, String value) {
    if (value == null || type == null ) {
      throw new NullPointerException();
    }
    this.type = type;
    this.value = value;
  }

  /**
   * Returns the type of this ContactItem.
   * 
   * @return the type of this ContactItem.
   */
  public ItemType getType() {
    return type;
  }

  /**
   * Returns the value of this ContactItem.
   * 
   * @return the value of this ContactItem.
   */
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return type.toString() + ": " + value;
  }
}
