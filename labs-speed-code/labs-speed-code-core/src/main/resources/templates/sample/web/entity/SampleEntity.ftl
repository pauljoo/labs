package ${globalPackageName}.entity;

public class ${domainClassName}Entity{
<#list COLUMNS as column>
	private String ${CAMEL_UTIL(column.columnName)};
</#list>
}