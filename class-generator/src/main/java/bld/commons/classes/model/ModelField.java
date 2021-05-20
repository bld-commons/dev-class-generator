/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.ModelField.java
 */
package bld.commons.classes.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import bld.commons.classes.attributes.LevelType;
import bld.commons.classes.generator.annotation.FindImport;

/**
 * The Class ModelField.
 */
public class ModelField implements ModelComponentClass{

	/** The name. */
	@JsonProperty("field")
	@NotNull(message="The \"field\" field can not be null to define the \"field\" entity")
	private String name;
	
	/** The type. */
	@FindImport
	@NotNull(message="The \"type\" field can not be null to define the \"field\" entity")
	private String type;
	
	/** The generic types. */
	@JsonProperty("generic-types")
	@Valid
	private List<ModelGenericType> genericTypes;
	
	/** The level type. */
	@JsonProperty("level")
	private LevelType levelType; 
	
	/** The field static. */
	@JsonProperty("static")
	private boolean  fieldStatic;
	
	/** The field final. */
	@JsonProperty("final")
	private boolean fieldFinal;
	
	/** The annotations. */
	@Valid
	private Set<ModelAnnotation> annotations;
	
	/** The value. */
	private Object value;
	
	/** The getter setter. */
	private boolean getter;
	
	/** The setter. */
	private boolean setter;
	
	/** The show quotes. */
	private boolean showQuotes;

	/**
	 * Instantiates a new model field.
	 */
	public ModelField() {
		super();
		init();
	}



	/**
	 * Inits the.
	 */
	private void init() {
		this.annotations=new HashSet<>();
		this.levelType=LevelType.PRIVATE;
		this.fieldFinal=false;
		this.fieldStatic=false;
		this.getter=true;
		this.setter=true;
		this.genericTypes=new ArrayList<>();
		this.showQuotes=true;
	}
	
	

	/**
	 * Instantiates a new model field.
	 *
	 * @param name the name
	 * @param type the type
	 */
	public ModelField(String name,String type) {
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
	 * Checks if is field static.
	 *
	 * @return true, if is field static
	 */
	public boolean isFieldStatic() {
		return fieldStatic;
	}

	/**
	 * Sets the field static.
	 *
	 * @param fieldStatic the new field static
	 */
	public void setFieldStatic(boolean fieldStatic) {
		this.fieldStatic = fieldStatic;
	}

	/**
	 * Checks if is field final.
	 *
	 * @return true, if is field final
	 */
	public boolean isFieldFinal() {
		return fieldFinal;
	}

	/**
	 * Sets the field final.
	 *
	 * @param fieldFinal the new field final
	 */
	public void setFieldFinal(boolean fieldFinal) {
		this.fieldFinal = fieldFinal;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
	/**
	 * Checks if is getter.
	 *
	 * @return true, if is getter
	 */
	public boolean isGetter() {
		return getter;
	}



	/**
	 * Sets the getter.
	 *
	 * @param getter the new getter
	 */
	public void setGetter(boolean getter) {
		this.getter = getter;
	}



	/**
	 * Checks if is setter.
	 *
	 * @return true, if is setter
	 */
	public boolean isSetter() {
		return setter;
	}



	/**
	 * Sets the setter.
	 *
	 * @param setter the new setter
	 */
	public void setSetter(boolean setter) {
		this.setter = setter;
	}

	/**
	 * Sets the getter setter.
	 *
	 * @param getterSetter the new getter setter
	 */
	public void setGetterSetter(boolean getterSetter) {
		this.setSetter(getterSetter);
		this.setGetter(getterSetter);
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
	 * Checks if is show quotes.
	 *
	 * @return true, if is show quotes
	 */
	public boolean isShowQuotes() {
		return showQuotes;
	}

	/**
	 * Sets the show quotes.
	 *
	 * @param showQuotes the new show quotes
	 */
	public void setShowQuotes(boolean showQuotes) {
		this.showQuotes = showQuotes;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		String genericType = "";
		for (ModelGenericType item : genericTypes)
				genericType += "," + item.toString();
		if (StringUtils.isNotEmpty(genericType))
			genericType = "<" + genericType.substring(1) + ">";
		String value="";
		if(this.value!=null)
			value="="+(this.type.equalsIgnoreCase("String") && this.showQuotes?"\""+this.value.toString()+"\"":this.value);
		return levelType.getLevel()+(this.fieldFinal?" final":"")+(this.fieldStatic?" static":"")+" " + type+genericType + " " + name+value+";";
	}
	
	
	
	
}
