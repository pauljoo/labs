package info.xpanda.speedcode.configuration;

import java.util.ArrayList;
import java.util.List;

public class TemplatesConfiguration {
	private String directory;
	private List<TemplateConfiguration> templates;
	
	public TemplatesConfiguration() {
		templates = new ArrayList<TemplateConfiguration>();
	}

	
	public String getDirectory() {
		return directory;
	}


	public void setDirectory(String directory) {
		this.directory = directory;
	}


	public List<TemplateConfiguration> getTemplates() {
		return templates;
	}

	public void setTemplates(List<TemplateConfiguration> templates) {
		this.templates = templates;
	}
	
	public void addTemplate(TemplateConfiguration template){
		templates.add(template);
	}
}
