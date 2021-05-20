/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.ModelAnnotation.java
 */
package bld.commons.classes.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import bld.commons.classes.generator.annotation.FindImport;

/**
 * The Class ModelAnnotation.
 */
public class ModelAnnotation implements ModelComponentClass{

	/** The name. */
	@JsonProperty("annotation")
	@FindImport
	@NotNull
	private String name;
	
	/** The properties. */
	@Valid
	private List<ModelProperty>properties;
	
	/**
	 * Instantiates a new model annotation.
	 */
	public ModelAnnotation() {
		super();
		properties=new ArrayList<>();
	}
	
	

	/**
	 * Instantiates a new model annotation.
	 *
	 * @param name the name
	 */
	public ModelAnnotation(String name) {
		super();
		this.name = name;
		properties=new ArrayList<>();
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
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public List<ModelProperty> getProperties() {
		return properties;
	}

	/**
	 * Sets the properties.
	 *
	 * @param properties the new properties
	 */
	public void setProperties(List<ModelProperty> properties) {
		this.properties = properties;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((properties == null) ? 0 : properties.hashCode());
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
		ModelAnnotation other = (ModelAnnotation) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (properties == null) {
			if (other.properties != null)
				return false;
		} else if (!properties.equals(other.properties))
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
		String properties="";
		for(ModelProperty property:this.properties) 
			properties+=","+property.toString();
		if(StringUtils.isNotEmpty(properties)) 
			properties="("+properties.substring(1).trim()+")";
		if(!this.name.startsWith("@"))
			this.name="@"+this.name;
		return this.name+properties;
	}

		
	
	
	
}
