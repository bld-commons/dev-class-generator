/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.generator.config.ConfigurationClassGenerator.java
 */
package bld.commons.classes.generator.config;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

/**
 * The Class ConfigurationClassGenerator.
 */
public class ConfigurationClassGenerator {

	
	/** The Constant CONFIG_CLASS_GENERATOR. */
	public static final String CONFIG_CLASS_GENERATOR = "configClassGenerator";

	/**
	 * Config class generator.
	 *
	 * @param templateDirectory the template directory
	 * @return the configuration
	 * @throws Exception the exception
	 */
	public static Configuration configClassGenerator(String templateDirectory)throws Exception {
		Configuration configuration=new Configuration(Configuration.VERSION_2_3_30);
		configuration.setClassLoaderForTemplateLoading(ConfigurationClassGenerator.class.getClassLoader(), templateDirectory);
		//FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(sourceDirectory));
		//configuration.setTemplateLoader(fileTemplateLoader);
		configuration.setDefaultEncoding("UTF-8");
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		configuration.setLogTemplateExceptions(false);
		configuration.setWrapUncheckedExceptions(true);
		return configuration;
	}
	
	/** The Constant VALIDATOR. */
	public final static Validator VALIDATOR=getValidator();
	
	
	/**
	 * Gets the validator.
	 *
	 * @return the validator
	 */
	private static Validator getValidator() {
		ValidatorFactory valdiatorFactory = Validation.buildDefaultValidatorFactory(); 
		return  valdiatorFactory.getValidator();
	}
	
	
}