<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${globalPackageName}.dao.${domainClassName}Mapper">
	<select id="query" parameterType="${globalPackageName}.entity.${domainClassName}Entity" resultType="${globalPackageName}.entity.${domainClassName}Entity">
        SELECT *
        FROM ${TABLE.tableName}
    </select>
    
    <insert id="add" parameterType="${globalPackageName}.entity.${domainClassName}Entity">
    	insert into
    	(
		<#list COLUMNS as column>
		<#if column_has_next>
		${column.columnName},
		</#if>
		<#if !column_has_next>
		${column.columnName}
		</#if>
		</#list>
    	)
    	values
    	(
    	<#list COLUMNS as column>
    	<#if column_has_next>
		${MYBATIS_PARAM_UTIL(CAMEL_UTIL(column.columnName))},
		</#if>
		<#if !column_has_next>
		${MYBATIS_PARAM_UTIL(CAMEL_UTIL(column.columnName))}
		</#if>
		</#list>
    	)
    </insert>
    <update id="update" parameterType="${globalPackageName}.entity.${domainClassName}Entity">
    	UPDATE ${TABLE.tableName}
    	SET
    	<#list COLUMNS as column>
			${column.columnName} = ${MYBATIS_PARAM_UTIL(CAMEL_UTIL(column.columnName))}
		</#list>
    	WHERE id = ${MYBATIS_PARAM_UTIL("id")}
    </update>
    <delete id="delete" parameterType="${globalPackageName}.entity.${domainClassName}Entity">
    	DELETE FROM ${TABLE.tableName}
    	WHERE id = ${MYBATIS_PARAM_UTIL("id")}
    </delete>
</mapper>