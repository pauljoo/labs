package ${globalPackageName}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${globalPackageName}.service.${domainClassName}Service;
import ${globalPackageName}.entity.${domainClassName}Entity;
import ${globalPackageName}.dao.${domainClassName}Dao;

@Service
public class ${domainClassName}ServiceImpl implements ${domainClassName}Service{

	@Autowired
	private ${domainClassName}Dao ${domainObjectName}Dao;
	
	@Override
	public void query(${domainClassName}Entity entity){
		${domainObjectName}Dao.query(entity);
	}
	@Override
	public void add(${domainClassName}Entity entity){
		${domainObjectName}Dao.add(entity);
	}
	@Override
	public void update(${domainClassName}Entity entity){
		${domainObjectName}Dao.update(entity);
	}
	@Override
	public void delete(${domainClassName}Entity entity){
		${domainObjectName}Dao.delete(entity);
	}
}