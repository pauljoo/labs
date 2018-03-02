;
var _${domainClassName}Function = new ${domainClassName}Function();
$(function(){
	$("#${domainClassName}_search").on("click", function(){
		_${domainClassName}Function.search();
	});
	$("#${domainClassName}_add").on("click", function(){
		_${domainClassName}Function.add();
	});
	$("#${domainClassName}_update").on("click", function(){
		_${domainClassName}Function.update();
	});
	$("#${domainClassName}_delete").on("click", function(){
		_${domainClassName}Function.delete();
	});
});

function ${domainClassName}Function(){
	var o${domainClassName}Function = new Object();
	o${domainClassName}Function.search = function(){
		alert("search");
	};
	o${domainClassName}Function.add = function(){
		alert("add");
	};
	o${domainClassName}Function.update = function(){
		alert("update");
	};
	o${domainClassName}Function.delete = function(){
		alert("delete");
	};
	return o${domainClassName}Function;
};