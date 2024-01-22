package org.dragon.yunpeng.form.entities;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "formList")
public class FormList implements Serializable {

	private List<Form> forms;

	public FormList() {
		// forms = new ArrayList<Form>();
	}

	public FormList(List<Form> forms) {
		this.forms = forms;
	}

	@XmlElement(name = "form")
	public List<Form> getForms() {
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

}
