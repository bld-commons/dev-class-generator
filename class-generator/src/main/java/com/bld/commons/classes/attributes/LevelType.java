/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.attributes.LevelType.java
 */
package com.bld.commons.classes.attributes;

import java.util.Arrays;

/**
 * The Enum LevelType.
 */
public enum LevelType{

	/** The public. */
	PUBLIC("public"),
	
	/** The private. */
	PRIVATE("private"),
	
	/** The protected. */
	PROTECTED("protected");

	/** The level. */
	private String level;

	/**
	 * Instantiates a new level type.
	 *
	 * @param level the level
	 */
	private LevelType(String level) {
		this.level=level;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * From value.
	 *
	 * @param level the level
	 * @return the level type
	 */
	public static LevelType fromValue(String level) {
		return Arrays.asList(LevelType.values()).stream().filter(item-> item.getLevel().equals(level)).findFirst().get();
	}
	
}
