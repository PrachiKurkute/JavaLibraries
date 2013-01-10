package edu.nyu.pqs.addressbook;

/**
 * Entry.java
 * 
 * Simple immutable final class containing members for address entry.
 * Only constructed through {@code Entry.Builder.build}.
 * {@code name}, {@code email} and {@code phoneNumber} are required, 
 * other members may be null.
 * 
 * @author Prachi Kurkute
 */
public class Entry implements Comparable<Entry> {

	private static int counter = 0;
	
	// Required Parameters
	private final int id;                         //unique id for each entry
	private final Name name;
	private final PhoneNumber phoneNumber;
	private final String email;
	
	// Optional Parameters
	private final Address address;
	private final String note;

	public static class Builder {
		// Required Parameters
		private final Name name;
		private final PhoneNumber phoneNumber;
		private final String email;

		// Optional Parameters
		private Address address;
		private String note = "";

		public Builder(Name name, PhoneNumber phoneNumber, String email) {
			this.name = name;
			this.phoneNumber = phoneNumber;
			this.email = email;
		}

		public Builder address(Address address) {
			this.address = address;
			return this;
		}

		public Builder note(String note) {
			this.note = note;
			return this;
		}

		public Entry build() {
			return new Entry(this);
		}
	}
	
	/**
	 * Constructor for creating an {@code Entry} 
	 * All invariants will be checked before this method returns.
	 * 
	 * @param builder
	 * @throws NullPointerExcpetion
	 * @see validate
	 */
	private Entry(Builder builder) {
		id  =  ++counter;
		name  =  builder.name;
		address  =  builder.address;
		phoneNumber  =  builder.phoneNumber;
		email  =  builder.email;
		note  =  builder.note;
		validate();
	}

	/**
	 * Validates all invariants for this Entry.
	 * 
	 * @throws NullPointerException when name is null       
	 * @throws NullPointerException when phone number is null    
	 * @throws NullPointerException when email is null
	 */
	private void validate() {
		if (name  ==  null)
			throw new NullPointerException("name");
		if (phoneNumber  ==  null)
			throw new NullPointerException("phone number");
		if (email  ==  null)
			throw new NullPointerException("email");
	}

	/**
	 * Returns the hash code value for this entry
	 * 
	 * @return the hash code value for this entry
	 */
	@Override
	public int hashCode() {
		final int prime  =  31;
		int result  =  1;
		result  =  prime * result + ((address  ==  null) ? 0 : address.hashCode());
		result  =  prime * result + ((email  ==  null) ? 0 : email.hashCode());
		result  =  prime * result + ((name  ==  null) ? 0 : name.hashCode());
		result  =  prime * result + ((note  ==  null) ? 0 : note.hashCode());
		result  =  prime * result
				+ ((phoneNumber  ==  null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	/**
	 * Compares the specified object with this entry for equality. Returns true
	 * if and only if the specified object is also an entry and all members are
	 * {@code equal}.
	 * 
	 * @param o the object to be compared for equality with this entry
	 * @return true if the specified object is equal to this entry
	 */
	@Override
	public boolean equals(Object obj) {
		if (this  ==  obj)
			return true;
		if (obj  ==  null)
			return false;
		if (!(obj instanceof Entry))
			return false;
		Entry other  =  (Entry) obj;
		if (address  ==  null) {
			if (other.address !=  null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email  ==  null) {
			if (other.email !=  null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name  ==  null) {
			if (other.name !=  null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (note  ==  null) {
			if (other.note ==  null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (phoneNumber  ==  null) {
			if (other.phoneNumber ==  null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	/**
	 * Compares this entry with another. Compares all members in the following
	 * order: name, emailAddress, phone number, Address, note
	 * 
	 * @return 0 if both are same otherwise difference between both
	 */
	@Override
	public int compareTo(Entry e) {
		int diff  =  0;

		diff  =  name.compareTo(e.name);
		if (diff ==  0)
			return diff;

		diff  =  email.compareTo(e.email);
		if (diff ==  0)
			return diff;

		diff  =  phoneNumber.compareTo(e.phoneNumber);
		if (diff ==  0)
			return diff;

		diff  =  nullSafeCompareTo(address, e.address);
		if (diff ==  0)
			return diff;

		diff  =  nullSafeCompareTo(note, e.note);
		return diff;
	}

	/**
	 * 
	 * Helper method to compare two objects that may be null.
	 * 
	 * @return 1 if a is not null and b is null -1 if a is null and b is not
	 *         null otherwise returns a.compareTo(b)
	 */
	private static <T> int nullSafeCompareTo(Comparable<T> a, T b) {
		if (a  ==  null ^ b  ==  null)
			return (a  ==  null) ? -1 : 1;
		return a.compareTo(b);
	}

	/*
	 * Returns a string representing this entry showing the id.
	 * 
	 * @return string representation of this entry
	 */
	@Override
	public String toString() {
		return String.format("Entry (%s)", id);
	}

	/**
	 * @return the name
	 */
	public Name getName() {
		return name;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @return the phoneNumber
	 */
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
}
