/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.model.ModelProperty.java
 */
package com.bld.commons.classes.model;

import java.util.ArrayList;
import java.util.List;

import com.bld.commons.classes.attributes.MarkType;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class ModelProperty.
 */
public class ModelProperty implements ModelComponentClass{

	/** The name. */
	@JsonProperty("property")
	private String name;
	
	/** The mark. */
	private MarkType mark;
	
	/** The value. */
	private Object value;
	
	/** The annotations. */
	private List<ModelAnnotation> annotations;

	/**
	 * Instantiates a new model property.
	 */
	public ModelProperty() {
		super();
		this.setMark(MarkType.NONE);
		this.name="value";
		this.annotations=new ArrayList<>();
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
	 * Gets the annotations.
	 *
	 * @return the annotations
	 */
	public List<ModelAnnotation> getAnnotations() {
		return annotations;
	}

	/**
	 * Sets the annotations.
	 *
	 * @param annotations the new annotations
	 */
	public void setAnnotations(List<ModelAnnotation> annotations) {
		this.annotations = annotations;
	}

	/**
	 * Gets the mark.
	 *
	 * @return the mark
	 */
	public MarkType getMark() {
		return mark;
	}

	/**
	 * Sets the mark.
	 *
	 * @param mark the new mark
	 */
	public void setMark(MarkType mark) {
		this.mark = mark;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		String mark=this.mark.getMark();
		String value="";
		List<Object>list=new ArrayList<>();
		if(this.value!=null) {
			if(this.value instanceof List) 
				list=(List<Object>)this.value;
			else
				list.add(this.value);
		}else {
			list=(List<Object>)(List<?>)this.annotations;
		}
		
		for(Object val:list) 
			value+=","+mark+val.toString()+mark;
		value=value.substring(1);
		if(list.size()>1)
			value="{"+value+"}";
		return " "+name + "="+value  ;
	}

	
	
}
