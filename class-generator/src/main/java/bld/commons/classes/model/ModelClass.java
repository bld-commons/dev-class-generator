/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.ModelClass.java
 */
package bld.commons.classes.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import bld.commons.classes.attributes.ClassType;
import bld.commons.classes.generator.utils.ClassGeneratorUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * The Class ModelClass.
 */
public class ModelClass implements ModelComponentClass {

	/** The name. */
	@NotNull(message = "The \"name\" field can not be null to define the \"class\" entity")
	@JsonProperty("class")
	private String name;

	/** The type. */
	@NotNull
	private ClassType type;

	/** The package name. */
	@JsonProperty("package")
	@NotNull
	private String packageName;

	/** The extends class. */
	@JsonProperty("extends")
	@Valid
	private Set<ModelSuperClass> extendsClass;

	/** The implements class. */
	@JsonProperty("implements")
	@Valid
	private Set<ModelSuperClass> implementsClass;

	/** The annotations. */
	@Valid
	private Set<ModelAnnotation> annotations;

	/** The fields. */
	@Valid
	private LinkedHashSet<ModelField> fields;

	/** The imports. */
	private Set<String> imports;

	/** The generic types. */
	@JsonProperty("generic-types")
	private List<ModelGenericType> genericTypes;

	/** The lombok. */
	private boolean lombok;

	/** The abstract class. */
	@JsonProperty("abstract")
	private boolean abstractClass;

	/** The methods. */
	@Valid
	private LinkedHashSet<ModelMethod> methods;

	/** The costructors. */
	private LinkedHashSet<ModelConstructor> constructors;

	/** The enum values. */
	private LinkedHashSet<ModelEnumValue> enumValues;

	/**
	 * Instantiates a new model class.
	 */
	public ModelClass() {
		super();
		init();

	}

	/**
	 * Inits the.
	 */
	private void init() {
		this.annotations = new HashSet<>();
		this.fields = new LinkedHashSet<>();
		this.imports = new HashSet<>();
		this.genericTypes = new ArrayList<>();
		this.extendsClass = new HashSet<>();
		this.implementsClass = new HashSet<>();
		this.methods = new LinkedHashSet<>();
		this.lombok = false;
		this.abstractClass = false;
		this.type = ClassType.CLASS;
		this.constructors = new LinkedHashSet<>();
		this.enumValues = new LinkedHashSet<>();
	}

	/**
	 * Instantiates a new model class.
	 *
	 * @param name        the name
	 * @param type        the type
	 * @param packageName the package name
	 */
	public ModelClass(String name, ClassType type, String packageName) {
		super();
		this.name = name;
		this.type = type;
		this.packageName = packageName;
		init();
	}
	
	public void addInterface(ModelSuperClass... modelSuperClasses) {
		ClassGeneratorUtils.addElements(this.implementsClass, modelSuperClasses);
	}
	
	
	public void addExtendsClass(ModelSuperClass... modelSuperClasses) {
		ClassGeneratorUtils.addElements(this.extendsClass, modelSuperClasses);
	}
	
	public void addAnnotations(ModelAnnotation...annotations) {
		ClassGeneratorUtils.addElements(this.annotations, annotations);
	}
	
	public void addFields(ModelField...fields) {
		ClassGeneratorUtils.addElements(this.fields, fields);
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
	 * Gets the package name.
	 *
	 * @return the package name
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Sets the package name.
	 *
	 * @param packageName the new package name
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
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
	 * Gets the fields.
	 *
	 * @return the fields
	 */
	public LinkedHashSet<ModelField> getFields() {
		return fields;
	}

	/**
	 * Sets the fields.
	 *
	 * @param fields the new fields
	 */
	public void setFields(LinkedHashSet<ModelField> fields) {
		this.fields = fields;
	}

	/**
	 * Gets the imports.
	 *
	 * @return the imports
	 */
	public Set<String> getImports() {
		return imports;
	}

	/**
	 * Sets the imports.
	 *
	 * @param imports the new imports
	 */
	public void setImports(Set<String> imports) {
		this.imports = imports;
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
	 * @param genericType the new generic types
	 */
	public void setGenericTypes(List<ModelGenericType> genericType) {
		this.genericTypes = genericType;
	}

	/**
	 * Gets the extends class.
	 *
	 * @return the extends class
	 */
	public Set<ModelSuperClass> getExtendsClass() {
		return extendsClass;
	}

	/**
	 * Sets the extends class.
	 *
	 * @param extendsClass the new extends class
	 */
	public void setExtendsClass(Set<ModelSuperClass> extendsClass) {
		this.extendsClass = extendsClass;
	}

	/**
	 * Gets the implements class.
	 *
	 * @return the implements class
	 */
	public Set<ModelSuperClass> getImplementsClass() {
		return implementsClass;
	}

	/**
	 * Sets the implements class.
	 *
	 * @param implementsClass the new implements class
	 */
	public void setImplementsClass(Set<ModelSuperClass> implementsClass) {
		this.implementsClass = implementsClass;
	}

	/**
	 * Checks if is lombok.
	 *
	 * @return true, if is lombok
	 */
	public boolean isLombok() {
		return lombok;
	}

	/**
	 * Sets the lombok.
	 *
	 * @param lombok the new lombok
	 */
	public void setLombok(boolean lombok) {
		this.lombok = lombok;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public ClassType getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(ClassType type) {
		this.type = type;
	}

	/**
	 * Checks if is abstract class.
	 *
	 * @return true, if is abstract class
	 */
	public boolean isAbstractClass() {
		return abstractClass;
	}

	/**
	 * Sets the abstract class.
	 *
	 * @param abstractClass the new abstract class
	 */
	public void setAbstractClass(boolean abstractClass) {
		this.abstractClass = abstractClass;
	}

	/**
	 * Gets the methods.
	 *
	 * @return the methods
	 */
	public LinkedHashSet<ModelMethod> getMethods() {
		return methods;
	}

	/**
	 * Sets the methods.
	 *
	 * @param methods the new methods
	 */
	public void setMethods(LinkedHashSet<ModelMethod> methods) {
		this.methods = methods;
	}

	/**
	 * Gets the costructors.
	 *
	 * @return the costructors
	 */
	public LinkedHashSet<ModelConstructor> getConstructors() {
		return constructors;
	}

	/**
	 * Sets the costructors.
	 *
	 * @param constructors the new costructors
	 */
	public void setConstructors(LinkedHashSet<ModelConstructor> constructors) {
		this.constructors = constructors;
	}

	/**
	 * Gets the enum values.
	 *
	 * @return the enum values
	 */
	public LinkedHashSet<ModelEnumValue> getEnumValues() {
		return enumValues;
	}

	/**
	 * Sets the enum values.
	 *
	 * @param enumValues the new enum values
	 */
	public void setEnumValues(LinkedHashSet<ModelEnumValue> enumValues) {
		this.enumValues = enumValues;
	}
	
	public void addImport(String imp) {
		if(StringUtils.isNotBlank(imp))
			this.imports.add(imp);
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

		String extend = "";
		for (ModelSuperClass item : this.extendsClass)
			extend += "," + item.toString();
		if (StringUtils.isNotEmpty(extend))
			extend = " extends " + extend.substring(1);
		String implement = "";
		for (ModelSuperClass item : this.implementsClass)
			implement += "," + item.toString();
		if (StringUtils.isNotEmpty(implement))
			implement = " implements " + implement.substring(1);
		String abstractClass = "";
		if (this.abstractClass)
			abstractClass = "abstract";
		return "public " + abstractClass + " " + this.type.name().toLowerCase() + " " + this.name + genericType + ""
				+ extend + "" + implement;
	}

}
