package info.xpanda.speedcode.engine.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConnectionHelper {
	
	public static TableEntity getTable(String tableName) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/edu?user=root&password=root&serverTimezone=UTC");
		
		// 2. 下面就是获取表的信息。
		DatabaseMetaData m_DBMetaData = conn.getMetaData();
		/*
		 * 其中"%"就是表示*的意思，也就是任意所有的意思。其中m_TableName就是要获取的数据表的名字，如果想获取所有的表的名字，
		 * 就可以使用"%"来作为参数了。
		 */
		ResultSet tableRet = m_DBMetaData.getTables(null, "edu", tableName, new String[] { "TABLE" });
		
		// 3. 提取表的名字。
		TableEntity table = new TableEntity();
		while (tableRet.next())
			table.setTableName(tableRet.getString("TABLE_NAME"));
		conn.close();
		
		return table;
	}
	
	public static List<ColumnEntity> getColumns(String tableName) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/edu?user=root&password=root&serverTimezone=UTC");
		
		// 2. 下面就是获取表的信息。
		DatabaseMetaData m_DBMetaData = conn.getMetaData();
		/*
		 * 其中"%"就是表示*的意思，也就是任意所有的意思。其中m_TableName就是要获取的数据表的名字，如果想获取所有的表的名字，
		 * 就可以使用"%"来作为参数了。
		 */
		ResultSet tableRet = m_DBMetaData.getTables(null, "edu", tableName, new String[] { "TABLE" });
		
		// 3. 提取表的名字。
		while (tableRet.next())
			System.out.println(tableRet.getString("TABLE_NAME"));
		
		// 4. 提取表内的字段的名字和类型
		ResultSet colRet = m_DBMetaData.getColumns(null, "edu", tableName, "%");
		
		List<ColumnEntity> result = new ArrayList<ColumnEntity>();
		
		while (colRet.next()) {
			ColumnEntity entity = new ColumnEntity();
			entity.setColumnName(colRet.getString("COLUMN_NAME"));
			entity.setColumnType(colRet.getString("TYPE_NAME"));
			result.add(entity);
		}
		conn.close();
		
		return result;
	}
}
