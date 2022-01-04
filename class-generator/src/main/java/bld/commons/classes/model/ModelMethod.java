/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.ModelMethod.java
 */
package bld.commons.classes.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import bld.commons.classes.attributes.LevelType;
import bld.commons.classes.generator.annotation.FindImport;

/**
 * The Class ModelMethod.
 */
public class ModelMethod implements ModelComponentClass {

	/** The name. */
	@JsonProperty("method")
	@NotNull(message = "The \"field\" field can not be null to define the \"method\" entity")
	private String name;

	/** The type. */
	@FindImport
	@NotNull(message = "The \"type\" field can not be null to define the \"method\" entity")
	private String type;

	/** The level type. */
	@JsonProperty("level")
	private LevelType levelType;

	/** The static method. */
	@JsonProperty("static")
	private boolean staticMethod;

	/** The abstract method. */
	@JsonProperty("abstract")
	private boolean abstractMethod;

	/** The annotations. */
	private Set<ModelAnnotation> annotations;

	/** The generic types. */
	@JsonProperty("generic-types")
	@Valid
	private List<ModelGenericType> genericTypes;

	/** The parameters. */
	@Valid
	private LinkedHashSet<ModelParameter> parameters;

	/** The commands. */
	private List<String> commands;

	/**
	 * Instantiates a new model method.
	 */
	public ModelMethod() {
		super();
		init();
	}

	/**
	 * Inits the.
	 */
	private void init() {
		this.annotations = new HashSet<>();
		this.levelType = LevelType.PUBLIC;
		this.staticMethod = false;
		this.abstractMethod = false;
		this.commands = new ArrayList<>();
		this.genericTypes = new ArrayList<>();
		this.parameters = new LinkedHashSet<>();
	}

	/**
	 * Instantiates a new model method.
	 *
	 * @param name the name
	 * @param type the type
	 */
	public ModelMethod(String name, String type) {
		super();
		this.name = name;
		this.type = type;
		init();
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
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the annotations.
	 *
	 * @return the annotations
	 */
	public Set<ModelAnnotation> getAnnotations() {
		return annotations;
	}

	/**
	 * Sets the annotations.
	 *
	 * @param annotations the new annotations
	 */
	public void setAnnotations(Set<ModelAnnotation> annotations) {
		this.annotations = annotations;
	}

	/**
	 * Gets the level type.
	 *
	 * @return the level type
	 */
	public LevelType getLevelType() {
		return levelType;
	}

	/**
	 * Sets the level type.
	 *
	 * @param levelType the new level type
	 */
	public void setLevelType(LevelType levelType) {
		this.levelType = levelType;
	}

	/**
	 * Checks if is static method.
	 *
	 * @return true, if is static method
	 */
	public boolean isStaticMethod() {
		return staticMethod;
	}

	/**
	 * Sets the static method.
	 *
	 * @param staticMethod the new static method
	 */
	public void setStaticMethod(boolean staticMethod) {
		this.staticMethod = staticMethod;
	}

	/**
	 * Gets the commands.
	 *
	 * @return the commands
	 */
	public List<String> getCommands() {
		return commands;
	}

	/**
	 * Sets the commands.
	 *
	 * @param commands the new commands
	 */
	public void setCommands(List<String> commands) {
		this.commands = commands;
	}

	/**
	 * Sets the commands.
	 *
	 * @param commands the new commands
	 */
	public void setCommands(Set<String> commands) {
		if (CollectionUtils.isNotEmpty(commands))
			this.commands = new ArrayList<>(commands);
	}

	/**
	 * Gets the generic types.
	 *
	 * @return the generic types
	 */
	public List<ModelGenericType> getGenericTypes() {
		return genericTypes;
	}

	/**
	 * Sets the generic types.
	 *
	 * @param genericTypes the new generic types
	 */
	public void setGenericTypes(List<ModelGenericType> genericTypes) {
		this.genericTypes = genericTypes;
	}

	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public LinkedHashSet<ModelParameter> getParameters() {
		return parameters;
	}

	/**
	 * Sets the parameters.
	 *
	 * @param parameters the new parameters
	 */
	public void setParameters(LinkedHashSet<ModelParameter> parameters) {
		this.parameters = parameters;
	}

	/**
	 * Checks if is abstract method.
	 *
	 * @return true, if is abstract method
	 */
	public boolean isAbstractMethod() {
		return abstractMethod;
	}

	/**
	 * Sets the abstract method.
	 *
	 * @param abstractMethod the new abstract method
	 */
	public void setAbstractMethod(boolean abstractMethod) {
		this.abstractMethod = abstractMethod;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String methodGenericType = "";
		String genericType = "";
		for (ModelGenericType item : this.genericTypes) {
			if (StringUtils.isNotEmpty(item.getExtend()) && !item.getName().equals("?"))
				methodGenericType += "," + item.toString();
			genericType += "," + item.getName();
		}

		String parameter = "";

		for (ModelParameter item : parameters) {
			parameter += "," + item.toString();
			for (ModelGenericType gt : item.getGenericTypes())
				if (StringUtils.isNotEmpty(gt.getExtend()) && !gt.getName().equals("?"))
					methodGenericType += "," + gt.toString();
		}
		if (StringUtils.isNotEmpty(parameter))
			parameter = parameter.substring(1);
		if (StringUtils.isNotEmpty(methodGenericType))
			methodGenericType = "<" + methodGenericType.substring(1) + ">";
		if (StringUtils.isNotEmpty(genericType))
			genericType = "<" + genericType.substring(1) + ">";
		String command = "\n";
		for (String item : this.commands)
			command += item + "\n";
		return this.levelType.getLevel() + (this.abstractMethod ? " abstract" : "") + (this.staticMethod ? " static" : "") + " " + methodGenericType + " " + this.type + genericType + " " + this.name + "(" + parameter + ")"
				+ (this.abstractMethod ? ";" : " {" + command + "    }");
	}

}
