<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${class.packagename}.dao.${class.classname}Mapper" >

  <resultMap id="BaseResultMap" type="${class.classname}" >
<#list class.properties as property>
    <result column="${property.columnName}" property="${property.propertyName}" javaType="${property.type}" />
</#list>
  </resultMap>

  <sql id="Base_Column_List" >
<#list class.properties as property>
  ${property.columnName} <#if list_has_next>,</#if>
</#list>
  </sql>

  <sql id="Is_Delete">
    is_delete = 0
  </sql>

  <insert id="insert" parameterType="${class.packagename}.model.${class.classname}" useGeneratedKeys="true" keyProperty="id" keyColumn="id">
    insert into ${class.tableName} (
      <#list class.properties as property>
        ${property.columnName} <#if list_has_next>,</#if>
      </#list>
    )values (
    <#list class.properties as property>
      ${"#"}{${property.propertyName},javaType=${property.type}} <#if list_has_next>,</#if>
    </#list>
    )
  </insert>

  <select id="select" resultMap="BaseResultMap" >
    select
      <include refid="Base_Column_List" />
    from ${class.tableName}
    <where>
      <include refid="Is_Delete" />
    </where>
  </select>

  <update id="updateByPrimaryKey" parameterType="${class.packagename}.model.${class.classname}">
    update
      ${class.tableName}
    <set >
<#list class.properties as property>
         <if test=" ${property.propertyName} != null" >
           ${property.columnName} = ${"#"}{${property.propertyName},javaType=${property.type}},
         </if>
</#list>
    </set>
    <where>
      id = ${"#"}{id}
    </where>
  </update>

</mapper>