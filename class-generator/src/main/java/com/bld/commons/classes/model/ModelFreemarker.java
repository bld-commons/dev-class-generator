/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.ModelFreemarker.java
 */
package com.bld.commons.classes.model;

/**
 * The Class ModelFreemarker.
 */
public class ModelFreemarker {

	
	/** The model class. */
	private ModelClass modelClass;
	
	

	/**
	 * Instantiates a new model freemarker.
	 *
	 * @param modelClass the model class
	 */
	public ModelFreemarker(ModelClass modelClass) {
		super();
		this.modelClass = modelClass;
	}

	/**
	 * Gets the model class.
	 *
	 * @return the model class
	 */
	public ModelClass getModelClass() {
		return modelClass;
	}

	/**
	 * Sets the model class.
	 *
	 * @param modelClass the new model class
	 */
	public void setModelClass(ModelClass modelClass) {
		this.modelClass = modelClass;
	}
	
	
	
}
