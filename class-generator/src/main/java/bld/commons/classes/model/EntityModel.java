package bld.commons.classes.model;

public class EntityModel {

	private String name;
	
	private String packageName;
	
	private String imp;
	
	private String typeId;

	public String getClassName() {
		return this.packageName+"."+this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String className) {
		this.name = className;
	}

	
	public String getImp() {
		return imp;
	}

	public void setImp(String imp) {
		this.imp = imp;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String toString() {
		return "EntityModel [className=" + name + ", imp=" + imp + ", typeId=" + typeId + "]";
	}
	
	
	
}
