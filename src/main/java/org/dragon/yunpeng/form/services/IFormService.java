package org.dragon.yunpeng.form.services;

import java.util.List;

import org.dragon.yunpeng.form.entities.Form;

public interface IFormService {

	List<String> readCodeFromFile();

	void exportData();

	List<Form> getAllForms();

	Form getForm(Long id);

	void deleteForm(Long id);

	Form saveForm(Form form);

}
