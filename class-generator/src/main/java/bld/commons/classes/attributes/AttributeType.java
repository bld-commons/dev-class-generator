/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.attributes.AttributeType.java
 */
package bld.commons.classes.attributes;

import java.util.Arrays;

/**
 * The Enum AttributeType.
 */
public enum AttributeType {

	/** The class. */
	CLASS("class"),
	
	/** The package. */
	PACKAGE("package-class"),
	
	/** The properties. */
	PROPERTIES("properties"),
	
	/** The property. */
	PROPERTY("property"),
	
	/** The name. */
	NAME("name"),
	
	/** The annotations. */
	ANNOTATIONS("annotations"),
	
	/** The annotation. */
	ANNOTATION("annotation"),
	
	/** The extends. */
	EXTENDS("extends"),
	
	/** The super class. */
	SUPER_CLASS("super-class"),
	
	/** The import. */
	IMPORT("import"),
	
	/** The imports. */
	IMPORTS("imports"),
	
	/** The generic types. */
	GENERIC_TYPES("generic-types"),
	
	/** The generic type. */
	GENERIC_TYPE("generic-type"),
	
	/** The field. */
	FIELD("field"),
	
	/** The fields. */
	FIELDS("fields"),
	
	/** The type. */
	TYPE("type"),
	
	/** The level. */
	LEVEL("level"),
	
	/** The value. */
	VALUE("value"), 
	
	/** The mark. */
	MARK("mark"),
	
	/** The final. */
	FINAL("final"),
	
	/** The static. */
	STATIC("static"), 
	
	/** The implements. */
	IMPLEMENTS("implements"),
	;
	
	
	/** The attribute. */
	private String attribute;

	
	/**
	 * Instantiates a new attribute type.
	 *
	 * @param attribute the attribute
	 */
	private AttributeType(String attribute) {
		this.attribute=attribute;
	}



	/**
	 * Gets the attribute.
	 *
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}
	
	
	/**
	 * From value.
	 *
	 * @param attribute the attribute
	 * @return the attribute type
	 */
	public static AttributeType fromValue(String attribute) {
		return Arrays.asList(AttributeType.values()).stream().filter(item-> item.getAttribute().equals(attribute)).findFirst().get();
	}
}
