package ${globalPackageName}.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ${globalPackageName}.service.${domainClassName}Service;
import ${globalPackageName}.entity.${domainClassName}Entity;

@Controller
@RequestMapping("/${domainObjectName}")
public class ${domainClassName}Controller {
	@Autowired
	private ${domainClassName}Service ${domainObjectName}Service;
	
	@RequestMapping("/query.html")
	public String query(${domainClassName}Entity param){
		${domainObjectName}Service.query(param);
		return "/user/add";
	}
	@RequestMapping("/add.html")
	public String add(${domainClassName}Entity param){
		${domainObjectName}Service.add(param);
		return "/user/add";
	}
	@RequestMapping("/update.html")
	public String update(${domainClassName}Entity param){
		${domainObjectName}Service.update(param);
		return "/user/add";
	}
	@RequestMapping("/delete.html")
	public String delete(${domainClassName}Entity param){
		${domainObjectName}Service.delete(param);
		return "/user/add";
	}
}
