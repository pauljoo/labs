package info.xpanda.speedcode.configuration.resolve;

import info.xpanda.speedcode.configuration.*;
import info.xpanda.speedcode.engine.ApplicationContext;
import org.apache.commons.digester3.Digester;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.File;
import java.net.URL;

public class XMLConfigurationResolve implements ConfigurationResolve{
	
	private String filePath;
	
	public XMLConfigurationResolve(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public ApplicationContext resolve() throws Exception{
		Digester digester = new Digester();
		URL url = new URL("file:///D:/work/git/speedcode/speedcode/src/main/resources/configuration.dtd");
		digester.register("-//Huiyao Jiang//DTD Configuration 0.1//EN", url);
		digester.setValidating(true);
		
		digester.addObjectCreate("configuration", ApplicationContext.class);
		digester.addObjectCreate("configuration/settings/setting", NameValuePair.class);
		digester.addSetProperties("configuration/settings/setting");
		digester.addSetNext("configuration/settings/setting", "addCustomProperty");
		
		
		digester.addObjectCreate("configuration/dataSource", DataSourceConfiguration.class);
		digester.addSetProperties("configuration/dataSource");
		digester.addSetNext("configuration/dataSource", "setDataSourceConfiguration");
		
		digester.addObjectCreate("configuration/tables", TablesConfiguration.class);
		digester.addSetProperties("configuration/tables");
		digester.addSetNext("configuration/tables", "setTablesConfiguration");
		
		digester.addObjectCreate("configuration/tables/table", TableConfiguration.class);
		digester.addSetProperties("configuration/tables/table");
		digester.addSetNext("configuration/tables/table", "addTable");
		
		digester.addObjectCreate("configuration/tables/table/settings/setting", NameValuePair.class); 
		digester.addSetProperties("configuration/tables/table/settings/setting");
		digester.addSetNext("configuration/tables/table/settings/setting", "addCustomProperty");

		digester.addObjectCreate("configuration/templates", TemplatesConfiguration.class);
		digester.addSetProperties("configuration/templates");
		digester.addSetNext("configuration/templates", "setTemplatesConfiguration");
		
		digester.addObjectCreate("configuration/templates/template", TemplateConfiguration.class);
		digester.addSetProperties("configuration/templates/template");
		digester.addSetNext("configuration/templates/template", "addTemplate");
		
		File file = new File(filePath);
		if(null != file && file.isFile()){
			try {
				digester.setErrorHandler(new ErrorHandler() {
					
					@Override
					public void warning(SAXParseException exception) throws SAXException {
						System.out.println(exception);
					}
					
					@Override
					public void fatalError(SAXParseException exception) throws SAXException {
						System.out.println(exception);
					}
					
					@Override
					public void error(SAXParseException exception) throws SAXException {
						System.out.println(exception);
					}
				});
				ApplicationContext vc = (ApplicationContext) digester.parse(file);
				return vc;
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
