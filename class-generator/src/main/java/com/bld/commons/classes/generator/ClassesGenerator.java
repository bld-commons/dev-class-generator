/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.generator.ClassesGenerator.java
 */
package com.bld.commons.classes.generator;

import java.util.Collection;

import javax.annotation.processing.ProcessingEnvironment;

import com.bld.commons.classes.model.ModelClasses;

/**
 * The Interface ClassesGenerator.
 */
public interface ClassesGenerator {

	/**
	 * Write class.
	 *
	 * @param modelClasses the model classes
	 * @param basedir      the basedir
	 * @throws Exception the exception
	 */
	public void writeClass(ModelClasses modelClasses,String basedir) throws Exception;
	
	/**
	 * Write classes.
	 *
	 * @param listModelClasses the list model classes
	 * @param basedir          the basedir
	 * @throws Exception the exception
	 */
	public void writeClasses(Collection<ModelClasses> listModelClasses,String basedir) throws Exception;

	
	/**
	 * Write class.
	 *
	 * @param modelClasses the model classes
	 * @param processingEnv the processing env
	 * @throws Exception the exception
	 */
	public void writeClass(ModelClasses modelClasses, ProcessingEnvironment processingEnv) throws Exception;


	/**
	 * Write classes.
	 *
	 * @param listModelClasses the list model classes
	 * @param processingEnv the processing env
	 * @throws Exception the exception
	 */
	public void writeClasses(Collection<ModelClasses> listModelClasses, ProcessingEnvironment processingEnv) throws Exception;

}