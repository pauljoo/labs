package info.xpanda.speedcode.configuration;

import java.util.ArrayList;
import java.util.List;

public class TablesConfiguration {
	private List<TableConfiguration> tables;

	public TablesConfiguration() {
		tables = new ArrayList<>();
	}
	public List<TableConfiguration> getTables() {
		return tables;
	}

	public void setTables(List<TableConfiguration> tables) {
		this.tables = tables;
	}
	
	public void addTable(TableConfiguration table){
		tables.add(table);
	}
}
