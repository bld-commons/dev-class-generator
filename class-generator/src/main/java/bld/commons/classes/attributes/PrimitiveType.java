/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.commons.classes.attributes.PrimitiveType.java
 */
package bld.commons.classes.attributes;

/**
 * The Enum PrimitiveType.
 */
public enum PrimitiveType {

	
	/** The int. */
	INT(int.class, Integer.class,Integer[].class),
	
	/** The byte. */
	BYTE(byte.class, Byte.class,Byte[].class),
	
	/** The char. */
	CHAR(char.class, Character.class,Character[].class),
	
	/** The boolean. */
	BOOLEAN(boolean.class, Boolean.class,Boolean[].class),
	
	/** The double. */
	DOUBLE(double.class, Double.class,Double[].class),
	
	/** The float. */
	FLOAT(float.class, Float.class,Float[].class),
	
	/** The long. */
	LONG(long.class, Long.class,Long[].class),
	
	/** The short. */
	SHORT(short.class, Short.class,Short[].class),
	
	/** The void. */
	VOID(void.class, Void.class,Void[].class);
	
	
	

	/** The primitive class. */
	private Class<?> primitiveClass;
	
	/** The object class. */
	private Class<?> objectClass;
	
	/** The array class. */
	private Class<?> arrayClass;
	
	
	/**
	 * Instantiates a new primitive type.
	 *
	 * @param primitive the primitive
	 * @param object the object
	 * @param arrayClass the array class
	 */
	private PrimitiveType(Class<?> primitive, Class<?> object,Class<?> arrayClass) {
		this.objectClass=object;
		this.primitiveClass=primitive;
		this.arrayClass=arrayClass;
	}


	/**
	 * Gets the primitive class.
	 *
	 * @return the primitive class
	 */
	public Class<?> getPrimitiveClass() {
		return primitiveClass;
	}


	/**
	 * Gets the object class.
	 *
	 * @return the object class
	 */
	public Class<?> getObjectClass() {
		return objectClass;
	}
	
	
	/**
	 * Gets the array class.
	 *
	 * @return the array class
	 */
	public Class<?> getArrayClass() {
		return arrayClass;
	}


	/**
	 * Contains.
	 *
	 * @param className the class name
	 * @return true, if successful
	 */
	public static PrimitiveType getPrimitiveType(String className) {
		
		for (PrimitiveType primitiveType : PrimitiveType.values())
			if (primitiveType.name().toLowerCase().equals(className))
				return primitiveType;
		return null;
	} 
	
	/**
	 * Gets the class.
	 *
	 * @param className the class name
	 * @return the class
	 */
	public static Class<?> getClass(String className){
		PrimitiveType primitiveType=getPrimitiveType(className.replace("[]","").trim());
		if(primitiveType!=null)
			return className.contains("[]")?primitiveType.getArrayClass():primitiveType.getObjectClass();
		return null;
	}
	
	
}
