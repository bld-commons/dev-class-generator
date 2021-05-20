package ${modelClass.packageName};

<#list modelClass.imports as importClass>
<#if importClass??>
import ${importClass};
</#if>
</#list>

<#list modelClass.annotations as annotation>
${annotation.toString()}
</#list>
${modelClass.toString()}{
	<#assign count = 0>
	<#list modelClass.enumValues as enumValue>
	<#assign count = count + 1>
	${enumValue.toString()}<#if count==modelClass.enumValues?size >;<#else>,</#if>
	
	</#list>
	<#list modelClass.constructors as constructor>
	${constructor.toString()}
	
	</#list>
	<#list modelClass.fields as field>
	<#list field.annotations as annotationField>
	<#if annotationField??>
	${annotationField.toString()}
	</#if>	
	</#list>
    ${field.toString()}
    
	</#list>
	<#list modelClass.fields as field>
	<#if field.getter == true >
    public ${field.type} get${field.name?cap_first}(){
        return ${field.name};
    }
    
    </#if>
    <#if field.setter == true >
    public void set${field.name?cap_first}(${field.type} ${field.name}){
        this.${field.name} = ${field.name};
    }
    
    </#if>
    </#list>
    <#list modelClass.methods as method>
    <#list method.annotations as annotationMethod>
	${annotationMethod.toString()}
	</#list>	
    ${method.toString()}
    
    </#list>

}