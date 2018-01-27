<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
<input id="${domainClassName}_search" type="button" value="查询"/>
<input id="${domainClassName}_add" type="button" value="新增"/>
<input id="${domainClassName}_update" type="button" value="修改"/>
<input id="${domainClassName}_delete" type="button" value="删除"/>
<table>   
    <thead>   
        <tr>   
            <#list COLUMNS as column>
				<th>${CAMEL_UTIL(column.columnName)}</th>
			</#list>
        </tr>   
    </thead>
    <tbody>
    </tbody>
</table>
<script type="text/javascript" src="/jquery-1.12.3.js"></script>
<script type="text/javascript" src="/js/${jsObjectName}.js"></script>
</body>
</html>