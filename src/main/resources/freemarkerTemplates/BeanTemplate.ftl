

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import java.io.Serializable;
import java.util.Date;

/**
 * ${class.comment}
 */
public class ${class.classname} implements Serializable{

    <#list class.properties as property>
    /**
     * ${property.comment}
     */
    ${property.propertyScope} ${property.type} ${property.propertyName};

    </#list>
    <#list class.properties as property>
    /**
     * ${property.comment} 取值方法get
     * @return ${property.propertyName}
     */
    public ${property.type} get${property.propertyName?cap_first}() {
        return ${property.propertyName};
    }

    /**
     * ${property.comment} 賦值方法set
     * @param ${property.propertyName}
     */
    public void set${property.propertyName?cap_first}(${property.type} ${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    }

    </#list>
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}