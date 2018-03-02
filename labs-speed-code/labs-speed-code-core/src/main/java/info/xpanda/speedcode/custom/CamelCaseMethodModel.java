package info.xpanda.speedcode.custom;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

public class CamelCaseMethodModel implements TemplateMethodModelEx{

	public Object exec(List arguments) throws TemplateModelException {
		if(arguments.size() != 1)  
        {  
            throw new TemplateModelException("Wrong argments !");  
        }  
		return CamelCaseUtils.toCamelCase(arguments.get(0).toString());
	}
}
