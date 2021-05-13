package skyglass.demo.model;

import java.io.Serializable;

public class GraphXml implements Serializable {

	private static final long serialVersionUID = 2289018306138983517L;

	private String id;

	private String bpmn20Xml;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBpmn20Xml() {
		return bpmn20Xml;
	}

	public void setBpmn20Xml(String bpmn20Xml) {
		this.bpmn20Xml = bpmn20Xml;
	}

}
