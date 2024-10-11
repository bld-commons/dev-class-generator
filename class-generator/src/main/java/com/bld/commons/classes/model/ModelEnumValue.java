/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.ModelEnumValue.java
 * 
 */
package com.bld.commons.classes.model;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class ModelEnumValue.
 */
public class ModelEnumValue {

	/** The key. */
	private String key;
	
	/** The values. */
	private Object[] values;

	/**
	 * Instantiates a new model enum value.
	 */
	public ModelEnumValue() {
		super();
	}

	/**
	 * Instantiates a new model enum value.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public ModelEnumValue(String key, Object... value) {
		super();
		this.key = key;
		this.values = value;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public Object[] getValues() {
		return values;
	}

	/**
	 * Sets the values.
	 *
	 * @param values the new values
	 */
	public void setValues(Object... values) {
		this.values = values;
	}

	
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelEnumValue other = (ModelEnumValue) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String values="";
		for(Object value:this.values)
			values+=","+(value instanceof String ?"\""+value.toString()+"\"":value.toString());
		if(StringUtils.isNotEmpty(values))
			values="("+values.substring(1)+")";
		return this.key+values;
	}
	
	
	
	
}
