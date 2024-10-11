/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.plugin.jpa.service.property.OutputDirectoryType.java
 */
package com.bld.commons.classes.type;

/**
 * The Enum OutputDirectoryType.
 */
public enum OutputDirectoryType {

	/** The src. */
	src_main_java("/src/main/java"),
	
	/** The target. */
	target_generated_source_classes("/target/generated-sources/classes"),
	
	/** The target generated source annotations. */
	target_generated_source_annotations("/target/generated-sources/annotations"),
	;
	
	
	/** The value. */
	private String value;
	

	/**
	 * Instantiates a new output directory type.
	 *
	 * @param value the value
	 */
	private OutputDirectoryType(String value) {
		this.value=value;
	}


	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	
	
}
