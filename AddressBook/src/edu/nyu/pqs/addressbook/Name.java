package edu.nyu.pqs.addressbook;

/**
 * Name.java
 * 
 * Simple immutable final class containing members for Name.
 * 
 * @author Prachi Kurkute
 * 
 */
public final class Name implements Comparable<Name> {
	
	private final String firstName;
	private final String middleName;
	private final String lastName;
  
	/**
	 * Constructor with required fields.
	 * @param firstName the First Name
	 * @param middleName the Middle Name
	 * @param lastName the Last Name
	 * @throws NullPointerException when firstName is null
	 */  
	public Name(String firstName, String middleName, String lastName){		
		this.firstName = firstName;
	    if(this.firstName == null)
	    	throw new NullPointerException("First Name");
	    this.middleName = middleName;
	    this.lastName = lastName;
	}
	
	/**
	 * Returns the hash code value for this Name.
	 * 
	 * @return the hash code value for this Name.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result	+ ((middleName == null) ? 0 : middleName.hashCode());
		return result;
	}

	/**
	 * Compares the specified object with this name for equality.
	 * Returns true if and only if the specified object is also a
	 * name and all members are {@code equal}.
	 * 
	 * @param o the object to be compared for equality with this name
	 * @return true if the specified object is equal to this name
	 */	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Name))
			return false;
		Name other = (Name) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		return true;
	}

	/**
	 * Compares this name to another.
	 * @return 0 if both are same otherwise difference between both
	 */
	@Override public int compareTo(Name name) {	
		int firstDiff = firstName.compareTo(name.firstName);
		if(firstDiff != 0)
			return firstDiff;
		
		int secondDiff = middleName.compareTo(name.middleName);
		if(secondDiff != 0)
			return secondDiff;
		
		return lastName.compareTo(name.lastName);
	}

	/**
	 * Returns a string representation of this name with the format
	 * fistName middleName lastName
	 * @return a string representing this name
	 */
	@Override public String toString(){
		return String.format("%s %s %s", firstName, middleName, lastName);
	}

	/**
	 * Returns immutable first name
	 * @return first name value
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns immutable middle name
	 * @return middle name value
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Returns immutable last name
	 * @return last name value
	 */
	public String getLastName() {
		return lastName;
	} 
}