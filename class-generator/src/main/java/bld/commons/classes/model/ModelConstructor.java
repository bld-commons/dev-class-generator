/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.ModelConstructor.java
 * 
 */
package bld.commons.classes.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import bld.commons.classes.attributes.LevelType;
import bld.commons.classes.generator.annotation.FindImport;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * The Class ModelCostructor.
 */
public class ModelConstructor {

	/** The type. */
	@FindImport
	@NotNull(message = "The \"type\" field can not be null to define the \"method\" entity")
	private String type;

	/** The level type. */
	@JsonProperty("level")
	private LevelType levelType;

	/** The parameters. */
	@Valid
	private LinkedHashSet<ModelParameter> parameters;

	/** The commands. */
	private List<String> commands;

	/**
	 * Instantiates a new model method.
	 */
	public ModelConstructor() {
		super();
		init();
	}

	/**
	 * Inits the.
	 */
	private void init() {
		this.levelType = LevelType.PUBLIC;
		this.commands = new ArrayList<>();
		this.parameters = new LinkedHashSet<>();
	}

	/**
	 * Instantiates a new model method.
	 *
	 * @param type the type
	 */
	public ModelConstructor(String type) {
		super();
		this.type = type;
		init();
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String methodGenericType = "";

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
		String command = "\n";
		for (String item : this.commands)
			command += item + "\n";
		return this.levelType.getLevel() + " " + this.type + " (" + parameter + ")" + "{" + command + "    }";
	}

}
