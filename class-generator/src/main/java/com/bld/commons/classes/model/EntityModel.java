/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.EntityModel.java 
 */
package com.bld.commons.classes.model;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class EntityModel.
 */
public class EntityModel {

	private static final String IMPORT = "import ";

	/** The name. */
	private String name;

	/** The package name. */
	private String packageName;

	/** The imp. */
	private String imp;

	/** The type id. */
	private String typeId;

	/**
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public String getClassName() {
		return this.packageName + "." + this.name;
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
	 * @param className the new name
	 */
	public void setName(String className) {
		this.name = className;
	}

	/**
	 * Gets the imp.
	 *
	 * @return the imp
	 */
	public String getImp() {
		return imp;
	}

	/**
	 * Sets the imp.
	 *
	 * @param imp the new imp
	 */
	public void setImp(String imp) {
		if (StringUtils.isNotBlank(imp) && imp.contains(IMPORT)) {
			imp=imp.replace(IMPORT, "");
		}
		this.imp = imp;
	}

	/**
	 * Gets the type id.
	 *
	 * @return the type id
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * Sets the type id.
	 *
	 * @param typeId the new type id
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	/**
	 * Gets the package name.
	 *
	 * @return the package name
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Sets the package name.
	 *
	 * @param packageName the new package name
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "EntityModel [className=" + name + ", imp=" + imp + ", typeId=" + typeId + "]";
	}

}
