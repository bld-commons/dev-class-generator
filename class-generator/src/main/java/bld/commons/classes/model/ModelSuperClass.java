/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.ModelSuperClass.java
 */
package bld.commons.classes.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import bld.commons.classes.generator.annotation.FindImport;
import bld.commons.classes.generator.utils.ClassGeneratorUtils;
import jakarta.validation.constraints.NotNull;

/**
 * The Class ModelSuperClass.
 */
public class ModelSuperClass implements ModelComponentClass{

	/** The name. */
	@JsonProperty("super-class")
	@FindImport
	@NotNull
	private String name;

	/** The generic types. */
	@JsonProperty("generic-types")
	private List<ModelGenericType> genericTypes;

	/**
	 * Instantiates a new model super class.
	 */
	public ModelSuperClass() {
		super();
		this.genericTypes = new ArrayList<>();
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
	 * Gets the generic types.
	 *
	 * @return the generic types
	 */
	public List<ModelGenericType> getGenericTypes() {
		return genericTypes;
	}

	/**
	 * Sets the generic types.
	 *
	 * @param genericTypes the new generic types
	 */
	public void setGenericTypes(List<ModelGenericType> genericTypes) {
		this.genericTypes = genericTypes;
	}
	
	public void addGenericTypes(ModelGenericType... genericTypes) {
		ClassGeneratorUtils.addElements(this.genericTypes, genericTypes);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String genericType = "";
		for (ModelGenericType item : genericTypes)
				genericType += "," + item.getName();
		if(StringUtils.isNotEmpty(genericType))
			genericType="<"+genericType.substring(1)+">";
		return name + "" + genericType;
	}

}
