/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.ModelParameter.java
 */
package com.bld.commons.classes.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.bld.commons.classes.generator.annotation.FindImport;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * The Class ModelParameter.
 */
public class ModelParameter implements ModelComponentClass {

	/** The name. */
	@JsonProperty("parameter")
	@NotNull(message = "The \"parameter\" field can not be null to define the \"parameter\" entity")
	private String name;

	/** The type. */
	@FindImport
	@NotNull(message = "The \"type\" field can not be null to define the \"parameter\" entity")
	private String type;

	/** The parameter final. */
	@JsonProperty("final")
	private boolean parameterFinal;

	/** The annotations. */
	@Valid
	private Set<ModelAnnotation> annotations;

	/** The generic types. */
	@JsonProperty("generic-types")
	@Valid
	private List<ModelGenericType> genericTypes;

	/**
	 * Instantiates a new model parameter.
	 *
	 * @param name the name
	 * @param type the type
	 */
	public ModelParameter(String name, String type) {
		super();
		this.name = name;
		this.type = type;
		this.annotations = new HashSet<>();
		this.parameterFinal = false;
		this.genericTypes = new ArrayList<>();
	}

	/**
	 * Instantiates a new model parameter.
	 */
	public ModelParameter() {
		super();
		this.annotations = new HashSet<>();
		this.parameterFinal = false;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the annotations.
	 *
	 * @return the annotations
	 */
	public Set<ModelAnnotation> getAnnotations() {
		return annotations;
	}

	/**
	 * Sets the annotations.
	 *
	 * @param annotations the new annotations
	 */
	public void setAnnotations(Set<ModelAnnotation> annotations) {
		this.annotations = annotations;
	}

	/**
	 * Checks if is parameter final.
	 *
	 * @return true, if is parameter final
	 */
	public boolean isParameterFinal() {
		return parameterFinal;
	}

	/**
	 * Sets the parameter final.
	 *
	 * @param parameterFinal the new parameter final
	 */
	public void setParameterFinal(boolean parameterFinal) {
		this.parameterFinal = parameterFinal;
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

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String annotation = "";
		for (ModelAnnotation item : this.annotations)
			annotation += item.toString() + " ";
		String genericType = "";
		for (ModelGenericType item : genericTypes)
			genericType += "," + (item.getName().equals("?") ? item.toString() : item.getName());
		if (StringUtils.isNotEmpty(genericType))
			genericType = "<" + genericType.substring(1) + ">";
		return annotation + (this.parameterFinal ? " final" : "") + " " + type + genericType + " " + name;
	}

}
