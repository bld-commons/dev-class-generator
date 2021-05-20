/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.yaml.converter.ConvertYaml.java
 */
package bld.commons.yaml.converter;

import java.util.List;

import bld.commons.classes.model.ModelClasses;

/**
 * The Interface ConvertYaml.
 */
public interface ConvertYaml {

	/**
	 * Gets the list classes.
	 *
	 * @param path the path
	 * @return the list classes
	 * @throws Exception the exception
	 */
	public List<ModelClasses> getListClasses(String path) throws Exception;

}
