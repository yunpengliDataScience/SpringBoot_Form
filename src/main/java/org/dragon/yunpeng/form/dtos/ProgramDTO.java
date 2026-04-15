package org.dragon.yunpeng.form.dtos;

public class ProgramDTO {

	private int program_id;
	private int type_id;
	private String name;
	private String type;
	private boolean program_obsolete;
	private boolean type_obsolete;

	public int getProgram_id() {
		return program_id;
	}

	public void setProgram_id(int program_id) {
		this.program_id = program_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isProgram_obsolete() {
		return program_obsolete;
	}

	public void setProgram_obsolete(boolean program_obsolete) {
		this.program_obsolete = program_obsolete;
	}

	public boolean isType_obsolete() {
		return type_obsolete;
	}

	public void setType_obsolete(boolean type_obsolete) {
		this.type_obsolete = type_obsolete;
	}

	// getters and setters

}