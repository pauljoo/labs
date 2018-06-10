package ${globalPackageName}.dao;

import org.apache.ibatis.annotations.Mapper;

import ${globalPackageName}.entity.${domainClassName}Entity;

@Mapper
public interface ${domainClassName}Dao {
	void query(${domainClassName}Entity entity);
	void add(${domainClassName}Entity entity);
	void update(${domainClassName}Entity entity);
	void delete(${domainClassName}Entity entity);
}