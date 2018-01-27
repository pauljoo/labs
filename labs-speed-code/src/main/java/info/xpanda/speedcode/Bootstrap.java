package info.xpanda.speedcode;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import info.xpanda.speedcode.configuration.TableConfiguration;
import info.xpanda.speedcode.configuration.TemplatesConfiguration;
import info.xpanda.speedcode.configuration.resolve.ConfigurationResolve;
import info.xpanda.speedcode.configuration.resolve.XMLConfigurationResolve;
import info.xpanda.speedcode.custom.CamelCaseMethodModel;
import info.xpanda.speedcode.custom.MyBatisMethodModel;
import info.xpanda.speedcode.engine.database.ColumnEntity;
import info.xpanda.speedcode.engine.database.ConnectionHelper;
import info.xpanda.speedcode.engine.database.TableEntity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import info.xpanda.speedcode.configuration.TemplateConfiguration;
import info.xpanda.speedcode.engine.ApplicationContext;

public class Bootstrap {
	public static void main(String[] args) throws Exception {
		//加载XML
		ConfigurationResolve resolve = new XMLConfigurationResolve("classpath:configuration.xml");
		ApplicationContext applicationContext = resolve.resolve();
		
		List<TableConfiguration> listTableConfiguratnion = applicationContext.getTablesConfiguration().getTables();
		
		//加载Freemarker
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        //设定去哪里读取相应的ftl模板文件
//		cfg.setDirectoryForTemplateLoading(new File(applicationContext.getTemplatesConfiguration().getDirectory()));
        cfg.setClassForTemplateLoading(Bootstrap.class,"/templates");
        
        TemplatesConfiguration templatesConfiguration = applicationContext.getTemplatesConfiguration();
        List<TemplateConfiguration> templateConfiguration = templatesConfiguration.getTemplates();
        
        CamelCaseMethodModel camel = new CamelCaseMethodModel();
        MyBatisMethodModel mybatis = new MyBatisMethodModel();
        for(TableConfiguration tableConfiguratnion : listTableConfiguratnion){
        	TableEntity table = ConnectionHelper.getTable(tableConfiguratnion.getTableName());
        	List<ColumnEntity> columns = ConnectionHelper.getColumns(tableConfiguratnion.getTableName());
        	for(TemplateConfiguration template : templateConfiguration){
        		//在模板文件目录中找到名称为name的文件
        		Template temp = cfg.getTemplate(template.getSource());
        		//加载数据库
        		Map<String, Object> dataModel = new HashMap<String, Object>();
        		dataModel.putAll(applicationContext.getCustomProperties());
        		dataModel.putAll(tableConfiguratnion.getCustomProperties());
        		dataModel.put("camel", camel);
        		dataModel.put("mybatis", mybatis);
        		dataModel.put("table", table);
        		dataModel.put("columns", columns);
        		dataModel.put("applicationContext", applicationContext);
        		
        		String target = template.getTarget();
        		String[] tags = StringUtils.substringsBetween(target, "{", "}");
        		if(tags != null && tags.length > 0){
        			Set<String> setTag = new HashSet<String>(Arrays.asList(tags));
        			for(String tag : setTag){
        				if(tableConfiguratnion.getCustomProperties().containsKey(tag)){
        					target = StringUtils.replace(target, "{" + tag + "}", tableConfiguratnion.getCustomProperties().get(tag));
        				}
        			}
        		}
        		
        		Writer out = new OutputStreamWriter(FileUtils.openOutputStream(new File(target)));
        		temp.process(dataModel, out);
        	}
        }
	}
}
