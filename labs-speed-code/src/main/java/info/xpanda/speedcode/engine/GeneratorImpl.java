package info.xpanda.speedcode.engine;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import info.xpanda.speedcode.configuration.DataSourceConfiguration;
import info.xpanda.speedcode.configuration.TableConfiguration;
import info.xpanda.speedcode.configuration.TemplatesConfiguration;
import info.xpanda.speedcode.configuration.resolve.ConfigurationResolve;
import info.xpanda.speedcode.constants.CommonConstant;
import info.xpanda.speedcode.engine.database.ColumnEntity;
import info.xpanda.speedcode.engine.database.TableEntity;
import info.xpanda.speedcode.utils.ConnectionUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import info.xpanda.speedcode.configuration.TablesConfiguration;
import info.xpanda.speedcode.configuration.TemplateConfiguration;
import info.xpanda.speedcode.configuration.resolve.XMLConfigurationResolve;

public class GeneratorImpl implements Generator{
	private ApplicationContext applicationContext;
	
	@Override
	public void load(String xmlPath) throws Exception{
		//加载XML
		ConfigurationResolve resolve = new XMLConfigurationResolve(xmlPath);
		applicationContext = resolve.resolve();
	}

	@Override
	public void generator() throws Exception{
		//加载Freemarker
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        //设定去哪里读取相应的ftl模板文件
		cfg.setDirectoryForTemplateLoading(new File(applicationContext.getTemplatesConfiguration().getDirectory()));
		
		TablesConfiguration tablesConfiguratnion = applicationContext.getTablesConfiguration();
        TemplatesConfiguration templatesConfiguration = applicationContext.getTemplatesConfiguration();
        DataSourceConfiguration dataSourceConfiguration = applicationContext.getDataSourceConfiguration();
        
        ConnectionUtil connect = new ConnectionUtil();
		connect.connect(dataSourceConfiguration.getDriver(), dataSourceConfiguration.getUrl(),
				dataSourceConfiguration.getUserName(), dataSourceConfiguration.getPassword());
        
        List<TableConfiguration> listTableConfiguratnion = tablesConfiguratnion.getTables();
        List<TemplateConfiguration> templateConfiguration = templatesConfiguration.getTemplates();
        for(TableConfiguration tableConfiguratnion : listTableConfiguratnion){
        	TableEntity table = connect.getTable(tableConfiguratnion.getTableName());
        	List<ColumnEntity> columns = connect.getColumns(tableConfiguratnion.getTableName());
        	for(TemplateConfiguration template : templateConfiguration){
        		//在模板文件目录中找到名称为name的文件
        		Template temp = cfg.getTemplate(template.getSource());
        		//加载数据库
        		Map<String, Object> dataModel = new HashMap<String, Object>();
        		dataModel.putAll(applicationContext.getCustomProperties());
        		dataModel.putAll(tableConfiguratnion.getCustomProperties());
        		dataModel.put(CommonConstant.KEY_CAMEL_UTIL, CommonConstant.CAMEL_UTIL);
        		dataModel.put(CommonConstant.KEY_MYBATIS_PARAM_UTIL, CommonConstant.MYBATIS_PARAM_UTIL);
        		dataModel.put(CommonConstant.KEY_TABLE, table);
        		dataModel.put(CommonConstant.KEY_COLUMNS, columns);
        		
        		String target = template.getTarget();
        		String[] tags = StringUtils.substringsBetween(target, "${", "}");
        		if(tags != null && tags.length > 0){
        			Set<String> setTag = new HashSet<String>(Arrays.asList(tags));
        			for(String tag : setTag){
        				if(tableConfiguratnion.getCustomProperties().containsKey(tag)){
        					target = StringUtils.replace(target, "${" + tag + "}", tableConfiguratnion.getCustomProperties().get(tag));
        				}
        			}
        		}
        		
        		Writer out = new OutputStreamWriter(FileUtils.openOutputStream(new File(target)));
        		temp.process(dataModel, out);
        	}
        }
        
        connect.unconnect();
	}
}
