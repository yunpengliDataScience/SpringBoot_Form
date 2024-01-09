package org.dragon.yunpeng.form.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FORM")
public class Form {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	
	private String field1;

	private String field2;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getField1() {
		return field1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	@Override
	public String toString() {
		return "Form{" + "id=" + id + ", field1=" + field1 + ", field2=" + field2 + '}';
	}
}
