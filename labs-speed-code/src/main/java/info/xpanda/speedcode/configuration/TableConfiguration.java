package info.xpanda.speedcode.configuration;

import java.util.HashMap;
import java.util.Map;

public class TableConfiguration {
	private String schema;
	private String tableName;
	private Map<String, String> customProperties;
	
	public TableConfiguration() {
		customProperties = new HashMap<String, String>();
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
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
