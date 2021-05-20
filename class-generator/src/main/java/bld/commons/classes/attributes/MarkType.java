/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.attributes.MarkType.java
 */
package bld.commons.classes.attributes;

/**
 * The Enum MarkType.
 */
public enum MarkType {

	/** The none. */
	NONE(""),
	
	/** The single. */
	SINGLE("'"),
	
	/** The double. */
	DOUBLE("\"");
	
	/** The mark. */
	private String mark;
	
	/**
	 * Instantiates a new mark type.
	 *
	 * @param mark the mark
	 */
	private MarkType(String mark) {
		this.mark=mark;
	}

	/**
	 * Gets the mark.
	 *
	 * @return the mark
	 */
	public String getMark() {
		return mark;
	}

	
	
}
