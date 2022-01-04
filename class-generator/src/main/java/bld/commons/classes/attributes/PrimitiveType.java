package bld.commons.classes.attributes;

public enum PrimitiveType {

	
	INT(int.class, Integer.class,Integer[].class),
	BYTE(byte.class, Byte.class,Byte[].class),
	CHAR(char.class, Character.class,Character[].class),
	BOOLEAN(boolean.class, Boolean.class,Boolean[].class),
	DOUBLE(double.class, Double.class,Double[].class),
	FLOAT(float.class, Float.class,Float[].class),
	LONG(long.class, Long.class,Long[].class),
	SHORT(short.class, Short.class,Short[].class),
	VOID(void.class, Void.class,Void[].class);
	
	
	

	private Class<?> primitiveClass;
	
	private Class<?> objectClass;
	
	private Class<?> arrayClass;
	
	
	private PrimitiveType(Class<?> primitive, Class<?> object,Class<?> arrayClass) {
		this.objectClass=object;
		this.primitiveClass=primitive;
		this.arrayClass=arrayClass;
	}


	public Class<?> getPrimitiveClass() {
		return primitiveClass;
	}


	public Class<?> getObjectClass() {
		return objectClass;
	}
	
	
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
	
	public static Class<?> getClass(String className){
		PrimitiveType primitiveType=getPrimitiveType(className.replace("[]","").trim());
		if(primitiveType!=null)
			return className.contains("[]")?primitiveType.getArrayClass():primitiveType.getObjectClass();
		return null;
	}
	
	
}
