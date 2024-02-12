/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.ModelGenericType.java
 */
package bld.commons.classes.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import bld.commons.classes.generator.annotation.FindImport;
import jakarta.validation.constraints.NotNull;


/**
 * The Class ModelGenericType.
 */
public class ModelGenericType implements ModelComponentClass{

	/** The name. */
	@JsonProperty("generic-type")
	@NotNull
	private String name;
	
	/** The extend. */
	@FindImport
	@JsonProperty("extends")
	private String extend;
	
	
	

	/**
	 * Instantiates a new model generic type.
	 */
	public ModelGenericType() {
		super();
	}

	
	
	
	/**
	 * Instantiates a new model generic type.
	 *
	 * @param name the name
	 */
	public ModelGenericType(@NotNull String name) {
		super();
		this.name = name;
	}


	/**
	 * Instantiates a new model generic type.
	 *
	 * @param name   the name
	 * @param extend the extend
	 */
	public ModelGenericType(@NotNull String name, String extend) {
		super();
		this.name = name;
		this.extend = extend;
	}




	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the extend.
	 *
	 * @return the extend
	 */
	public String getExtend() {
		return extend;
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
		result = prime * result + ((extend == null) ? 0 : extend.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ModelGenericType other = (ModelGenericType) obj;
		if (extend == null) {
			if (other.extend != null)
				return false;
		} else if (!extend.equals(other.extend))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Sets the extend.
	 *
	 * @param extend the new extend
	 */
	public void setExtend(String extend) {
		this.extend = extend;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String extend="";
		if(StringUtils.isNotEmpty(this.extend))
			extend=" extends "+this.extend;
		return  this.name + extend;
	}
	
	
	
}
