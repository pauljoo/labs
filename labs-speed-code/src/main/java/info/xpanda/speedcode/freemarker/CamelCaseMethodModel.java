package info.xpanda.speedcode.freemarker;

import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import info.xpanda.speedcode.utils.CamelCaseUtil;

public class CamelCaseMethodModel implements TemplateMethodModelEx{

	public Object exec(List arguments) throws TemplateModelException {
		if(arguments.size() != 1)  
        {  
            throw new TemplateModelException("Wrong argments !");  
        }  
		return CamelCaseUtil.toCamelCase(arguments.get(0).toString());
	}
}
