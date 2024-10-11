/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.yaml.converter.impl.ConvertYamlImpl.java
 */
package com.bld.commons.yaml.converter.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bld.commons.classes.generator.utils.ClassGeneratorUtils;
import com.bld.commons.classes.generator.validator.ValidatorBuildClass;
import com.bld.commons.classes.model.ModelClasses;
import com.bld.commons.yaml.converter.ConvertYaml;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * The Class ConvertYamlImpl.
 */
public class ConvertYamlImpl implements ConvertYaml {

	/** The Constant logger. */
	private final static Log logger = LogFactory.getLog(ConvertYamlImpl.class);

	/**
	 * Gets the list classes.
	 *
	 * @param path the path
	 * @return the list classes
	 * @throws Exception the exception
	 */
	@Override
	public List<ModelClasses> getListClasses(String path) throws Exception {
		logger.info("Start Convert");
		List<ModelClasses> containers=new ArrayList<>();
		if(Files.exists(Paths.get(path))) {
			ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
			List<File> files = ClassGeneratorUtils.getFiles(path,"yaml");
			files.addAll(ClassGeneratorUtils.getFiles(path,"yml"));
			for(File file:files) {
				Reader reader = new InputStreamReader(new FileInputStream(file));
				ModelClasses container = objectMapper.readValue(reader, ModelClasses.class);

				logger.info("End convert");
				logger.info("Start validator");
				ValidatorBuildClass.checkValidatrBuildClass(container);
				containers.add(container);
				logger.info("End validator");
			}

		}
		return containers;
	}

	

	

}
