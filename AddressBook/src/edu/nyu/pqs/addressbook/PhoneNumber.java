package edu.nyu.pqs.addressbook;

/**
 * PhoneNumber.java
 * 
 * Simple immutable final class representing phone number in format
 * country code - area code - number
 * 
 * @author Prachi Kurkute
 */
public final class PhoneNumber implements Comparable<PhoneNumber> {
	
	private final short countryCode;
	private final short areaCode;
	private final short number;
	
	/**
	 * Class constructor that creates a PhoneNumber from the individual parts.
	 * 
	 * @param areaCode the First three digits
	 * @param prefix the middle three digits
	 * @param lineNumber The last four digits
	 * @throws IllegalArgumentException if any fields are invalid
	 */ 
	public PhoneNumber(int countryCode,int areaCode,int number) {
		validatePhoneNumber(this.countryCode, 0, 999, "countryCode");
		validatePhoneNumber(this.areaCode, 0, 999, "areaCode");
		validatePhoneNumber(this.number, 0, 9999, "number");
		  
	    this.countryCode = (short)countryCode;
	    this.areaCode = (short)areaCode;
	    this.number = (short)number;
	}
  
	/**
	 * Helper method to validate that a given object is valid phone number
	 * @param arg argument to be ranged checked
	 * @param min minimum valid value 
	 * @param max maximum valid value
	 * @param name name of the variable to be shown in the exception
	 * @throws IllegalArgumentException when member is invalid
	 */
	private static void validatePhoneNumber(int arg, int min, int max, String name) {
		if (arg < min || arg > max) 
			throw new IllegalArgumentException(name + ": " + arg);
	}

	/**
	 * Returns the hash code value for this PhoneNumber.
	 * 
	 * @return the hash code value for this PhoneNumber.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + areaCode;
		result = prime * result + countryCode;
		result = prime * result + number;
		return result;
	}

	/**
	 * Compares the specified object with this PhoneNumber for equality.
	 * Returns true if and only if the specified object is also a 
	 * PhoneNumber and has the same field values.
	 * 
	 * @param o the object being compared for equality with this PhoneNumber
	 * @return true if the specified object is equal to this PhoneNumber
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PhoneNumber))
			return false;
		PhoneNumber other = (PhoneNumber) obj;
		if (areaCode != other.areaCode)
			return false;
		if (countryCode != other.countryCode)
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	/**
	 * Compares this PhoneNumber to another PhoneNumber.
	 * Each field is compared in this order:
	 * countryCode, areaCode, number
	 * 
	 * @return < 0 if this PhoneNumber is less than the other
	 * > 0 if it is greater and 0 otherwise
	 */
	@Override 
	public int compareTo(PhoneNumber pn) {
		int countryCodeDiff = countryCode - pn.countryCode;
		if(countryCodeDiff != 0)
			return countryCodeDiff;
		
		int areaCodeDiff = areaCode - pn.areaCode;
		if(areaCodeDiff != 0)
			return areaCodeDiff;
		
		return number - pn.number;
	}

	/**
	 * Returns a string representation of this phone number in the format
	 * countryCode-areaCode-number
	 * @return string representing this phone number
	 */
	@Override 
	public String toString() {
		return String.format("%d-%d-%d",countryCode, areaCode, number);
	}

	/**
	 * Returns immutable country code
	 * @return countryCode value
	 */
	public int getCountryCode() {
		return countryCode;
	}

	/**
	 * Returns immutable area code
	 * @return areaCode value
	 */
	public int getAreaCode() {
		return areaCode;
	}

	/**
	 * Returns immutable number
	 * @return number value
	 */
	public int getNumber() {
		return number;
	}  
}
