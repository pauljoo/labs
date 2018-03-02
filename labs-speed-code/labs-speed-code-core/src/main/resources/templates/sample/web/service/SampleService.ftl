package ${globalPackageName}.service;

import ${globalPackageName}.entity.${domainClassName}Entity;

public interface ${domainClassName}Service {
	void query(${domainClassName}Entity entity);
	void add(${domainClassName}Entity entity);
	void update(${domainClassName}Entity entity);
	void delete(${domainClassName}Entity entity);
}