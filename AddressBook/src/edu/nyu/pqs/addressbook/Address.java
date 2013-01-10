package edu.nyu.pqs.addressbook;

/**
 * Address.java
 * 
 * Simple immutable final class containing members for an address.
 * 
 * @author Prachi Kurkute
 */
public final class Address implements Comparable<Address> {
	
	private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String country;
    private final int zip;
    
    /**
	 * Basic constructor with all required fields
	 * 
	 * @throws NullPointerException if any fields are null
	 */
	public Address(String addressLine1,String addressLine2,String city,String state,String country,int zip) {
		this.addressLine1 = addressLine1;
	    this.addressLine2 = addressLine2;
	    this.city = city;
	    this.state = state;
	    this.country = country;
	    this.zip = zip;
	      
	    validateNonNullMember(this.addressLine1, "addressLine1");
		validateNonNullMember(this.addressLine1, "addressLine1");
		validateNonNullMember(this.city, "city");
		validateNonNullMember(this.state, "state");
		validateNonNullMember(this.country, "country");
		validateNonNullMember(this.zip, "zip");
	}
  
    /**
	 * Helper method to validate that a given object is non-null
	 * 
	 * @param member any object
	 * @param name name of the member object
	 * @throws NullPointerException when member is null
	 */
	private static void validateNonNullMember(Object member, String name) {
		if(member == null)
			throw new NullPointerException(name);
	}

    /**
	 * Returns the hash code value for this address.
	 * 
	 * @return the hash code value for this address.
	 */	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressLine1 == null) ? 0 : addressLine1.hashCode());
		result = prime * result
				+ ((addressLine2 == null) ? 0 : addressLine2.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + zip;
		return result;
	}

	/**
	 * Compares the specified object with this address for equality.
	 * Returns true if and only if the specified object is also an
	 * address and all members are {@code equal}.
	 * 
	 * @param o the object to be compared for equality with this address
	 * @return true if the specified object is equal to this address
	 */	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		if (addressLine1 == null) {
			if (other.addressLine1 != null)
				return false;
		} else if (!addressLine1.equals(other.addressLine1))
			return false;
		if (addressLine2 == null) {
			if (other.addressLine2 != null)
				return false;
		} else if (!addressLine2.equals(other.addressLine2))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zip != other.zip)
			return false;
		return true;
	}

	/**
	 * Compares this address to another based the following members in
	 * this order: country, state, city, zip, addressLine2, addressLine1
	 * 
	 * @return 0 if both are same otherwise difference between both
	 */	
	@Override 
	public int compareTo(Address a) {
		int diff = 0;
		
		diff = country.compareTo(a.country);
		if(diff != 0) return diff;
		
		diff = state.compareTo(a.state);
		if(diff != 0) return diff;		
		
		diff = city.compareTo(a.city);
		if(diff != 0) return diff;		
		
		diff = zip-a.zip;
		if(diff != 0) return diff;
	
		diff = addressLine2.compareTo(a.addressLine2);		
		if(diff != 0) return diff;
		
		diff = addressLine1.compareTo(a.addressLine1);		
		return diff;
	}

	/**
	 * Returns a string showing this address in the format
	 * addressLine1, addressLine1, city, state, Country, zip
	 * 
	 * @return a string representing this postal address
	 */
	@Override 
	public String toString(){
		return String.format("%s, %s, %s, %s, %s, zip: %s", 
				addressLine1, addressLine2, city, state, country, zip);
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the zip
	 */
	public int getZip() {
		return zip;
	}
}