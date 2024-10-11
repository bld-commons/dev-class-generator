/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.generator.annotation.FindImport.java
 */
package com.bld.commons.classes.generator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface FindImport.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface FindImport {

}
