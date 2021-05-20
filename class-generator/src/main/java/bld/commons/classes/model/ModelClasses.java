/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.ModelClasses.java
 */
package bld.commons.classes.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * The Class ModelClasses.
 */
public class ModelClasses implements ModelComponentClass{

	/** The classes. */
	@NotNull
	@Valid
	private List<ModelClass> classes;
	
	/**
	 * Instantiates a new model classes.
	 */
	public ModelClasses() {
		super();
		this.classes=new ArrayList<>();
	}

	/**
	 * Gets the classes.
	 *
	 * @return the classes
	 */
	public List<ModelClass> getClasses() {
		return classes;
	}

	/**
	 * Sets the classes.
	 *
	 * @param classes the new classes
	 */
	public void setClasses(List<ModelClass> classes) {
		this.classes = classes;
	}

	
	
	
	
}
