
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import java.io.Serializable;
import java.util.Date;

/**
 * ${class.comment}实体
 */
@Data
@ApiModel(value = "${class.comment}", description = "")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ${class.classname} extends BaseModel implements Serializable{

    <#list class.properties as property>
    @ApiModelProperty("${property.comment}")
    ${property.propertyScope} ${property.type} ${property.propertyName};

    </#list>

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}