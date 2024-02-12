/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.generator.validator.ValidatorBuildClass.java
 */
package bld.commons.classes.generator.validator;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bld.commons.classes.generator.config.ConfigurationClassGenerator;
import bld.commons.classes.generator.exception.YamlPropertyException;
import bld.commons.classes.model.ModelClasses;
import jakarta.validation.ConstraintViolation;

/**
 * The Class ValidatorBuildClass.
 */
public class ValidatorBuildClass {


	/** The Constant logger. */
	private final static Log logger = LogFactory.getLog(ValidatorBuildClass.class);

	
	/**
	 * Check validatr build class.
	 *
	 * @param container the container
	 * @throws YamlPropertyException the yaml property exception
	 */
	public static void checkValidatrBuildClass(ModelClasses container) throws YamlPropertyException {
		Set<ConstraintViolation<ModelClasses>> failedValidations = ConfigurationClassGenerator.VALIDATOR.validate(container);
		if (!failedValidations.isEmpty()) {
			for (ConstraintViolation<ModelClasses> failedValidation : failedValidations) {
				logger.error(failedValidation.getMessage());
				throw new YamlPropertyException(failedValidation.getMessage());
			}
		}
	}

}
