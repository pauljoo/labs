package info.xpanda.speedcode.engine;

import java.util.HashMap;
import java.util.Map;

import info.xpanda.speedcode.configuration.DataSourceConfiguration;
import info.xpanda.speedcode.configuration.NameValuePair;
import info.xpanda.speedcode.configuration.TablesConfiguration;
import info.xpanda.speedcode.configuration.TemplatesConfiguration;

public class ApplicationContext {
	private DataSourceConfiguration dataSourceConfiguration;
	private TablesConfiguration tablesConfiguration;
	private TemplatesConfiguration templatesConfiguration;
	private Map<String, String> customProperties;
	
	public ApplicationContext() {
		customProperties = new HashMap<String, String>();
	}
	
	public DataSourceConfiguration getDataSourceConfiguration() {
		return dataSourceConfiguration;
	}
	public void setDataSourceConfiguration(DataSourceConfiguration dataSourceConfiguration) {
		this.dataSourceConfiguration = dataSourceConfiguration;
	}
	
	public TablesConfiguration getTablesConfiguration() {
		return tablesConfiguration;
	}

	public void setTablesConfiguration(TablesConfiguration tablesConfiguration) {
		this.tablesConfiguration = tablesConfiguration;
	}

	public TemplatesConfiguration getTemplatesConfiguration() {
		return templatesConfiguration;
	}
	public void setTemplatesConfiguration(TemplatesConfiguration templatesConfiguration) {
		this.templatesConfiguration = templatesConfiguration;
	}
	public Map<String, String> getCustomProperties() {
		return customProperties;
	}
	public void setCustomProperties(Map<String, String> customProperties) {
		this.customProperties = customProperties;
	}
	
	public void addCustomProperty(NameValuePair property){
		customProperties.put(property.getName(), property.getValue());
	}
}
