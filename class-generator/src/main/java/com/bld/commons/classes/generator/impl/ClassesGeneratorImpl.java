/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.generator.impl.ClassesGeneratorImpl.java
 */
package com.bld.commons.classes.generator.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.JavaFileObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bld.commons.classes.attributes.StandardImportType;
import com.bld.commons.classes.generator.ClassesGenerator;
import com.bld.commons.classes.generator.annotation.FindImport;
import com.bld.commons.classes.model.ModelAnnotation;
import com.bld.commons.classes.model.ModelClass;
import com.bld.commons.classes.model.ModelClasses;
import com.bld.commons.classes.model.ModelComponentClass;
import com.bld.commons.classes.model.ModelFreemarker;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

/**
 * The Class ClassesGeneratorImpl.
 */
@SuppressWarnings("unchecked")
public class ClassesGeneratorImpl implements ClassesGenerator {

	/** The Constant ANNOTATION. */
	private static final String ANNOTATION = "Annotation";

	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(ClassesGeneratorImpl.class);

	/** The template. */
	private Template template = null;

	/**
	 * Instantiates a new classes generator impl.
	 *
	 * @param configuration the configuration
	 * @throws TemplateNotFoundException the template not found exception
	 * @throws MalformedTemplateNameException the malformed template name exception
	 * @throws ParseException the parse exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ClassesGeneratorImpl(Configuration configuration) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
		super();
		this.template = configuration.getTemplate("typological.ftl");
	}

	/**
	 * Write class.
	 *
	 * @param modelClasses the model classes
	 * @param basedir      the basedir
	 * @throws Exception the exception
	 */
	@Override
	public void writeClass(ModelClasses modelClasses, String basedir) throws Exception {
		for (ModelClass modelClass : modelClasses.getClasses()) {
			removeImport(modelClass);

			ModelFreemarker modelFreemarker = new ModelFreemarker(modelClass);
			String outputDirectory = basedir + "/" + modelClass.getPackageName().replace(".", "/");
			File directory = new File(outputDirectory);
			if (!directory.exists()) {
				directory.mkdirs();
				logger.info("mkdir -p " + outputDirectory);
			}

			File javaSourceFile = new File(directory, modelClass.getName() + ".java");
			boolean check = true;
			try {
				if (!javaSourceFile.exists()) {
					logger.info("Class generation: " + javaSourceFile.getAbsolutePath());

					Writer javaSourceFileWriter = new FileWriter(javaSourceFile);
					template.process(modelFreemarker, javaSourceFileWriter);
				}
			} catch (Exception e) {
				check = false;
				throw e;
			} finally {
				if (!check) {
					javaSourceFile.delete();
				}
			}
		}

	}

	/**
	 * Write classes.
	 *
	 * @param listModelClasses the list model classes
	 * @param basedir          the basedir
	 * @throws Exception the exception
	 */
	@Override
	public void writeClasses(Collection<ModelClasses> listModelClasses, String basedir) throws Exception {
		for (ModelClasses modelClasses : listModelClasses)
			writeClass(modelClasses, basedir);

	}

	/**
	 * Find all import.
	 *
	 * @param modelComponentClass the model component class
	 * @param imports             the imports
	 * @throws Exception the exception
	 */
	private void findAllImport(ModelComponentClass modelComponentClass, Set<String> imports) throws Exception {
		for (Field field : modelComponentClass.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(FindImport.class)) {
				try {
					String type = BeanUtils.getProperty(modelComponentClass, field.getName());
					if (modelComponentClass instanceof ModelAnnotation) {
						type = ANNOTATION + (type.startsWith("@") ? type.substring(1) : type);
					}

					if (StandardImportType.contains(type))
						imports.add(StandardImportType.valueOf(type).getStandardImport());
				} catch (Exception e) {
				}
				continue;
			}
			Object value = PropertyUtils.getProperty(modelComponentClass, field.getName());
			if (value instanceof ModelComponentClass)
				findAllImport((ModelComponentClass) value, imports);
			else if (value instanceof Collection) {
				try {
					Collection<? extends ModelComponentClass> list = (Collection<? extends ModelComponentClass>) value;
					for (ModelComponentClass item : list)
						findAllImport(item, imports);
				} catch (Exception e) {
				}
			}

		}

	}

	/**
	 * Write classes.
	 *
	 * @param listModelClasses the list model classes
	 * @param processingEnv the processing env
	 * @throws Exception the exception
	 */
	@Override
	public void writeClasses(Collection<ModelClasses> listModelClasses, ProcessingEnvironment processingEnv) throws Exception {
		for (ModelClasses modelClasses : listModelClasses)
			writeClass(modelClasses, processingEnv);
	}

	/**
	 * Write class.
	 *
	 * @param modelClasses the model classes
	 * @param processingEnv the processing env
	 * @throws Exception the exception
	 */
	@Override
	public void writeClass(ModelClasses modelClasses, ProcessingEnvironment processingEnv) throws Exception {
		for (ModelClass modelClass : modelClasses.getClasses()) {
			removeImport(modelClass);

			System.out.println("Class generated: " + modelClass.getPackageName() + "." + modelClass.getName());
			ModelFreemarker modelFreemarker = new ModelFreemarker(modelClass);
			JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(modelClass.getPackageName() + "." + modelClass.getName());
			Writer javaSourceFileWriter = null;
			try {

				javaSourceFileWriter = builderFile.openWriter();
				template.process(modelFreemarker, javaSourceFileWriter);

			} catch (Exception e) {
				throw e;
			} finally {
				if (javaSourceFileWriter != null)
					javaSourceFileWriter.close();
			}
		}

	}

	/**
	 * Removes the import.
	 *
	 * @param modelClass the model class
	 * @throws Exception the exception
	 */
	private void removeImport(ModelClass modelClass) throws Exception {
		Set<String> imports = new HashSet<>();
		findAllImport(modelClass, imports);
		modelClass.getImports().addAll(imports);
		imports = new HashSet<>();
		for (String importClass : modelClass.getImports())
			if (importClass.substring(0, importClass.lastIndexOf(".")).equals(modelClass.getPackageName()))
				imports.add(importClass);

		modelClass.getImports().removeAll(imports);
	}

}
