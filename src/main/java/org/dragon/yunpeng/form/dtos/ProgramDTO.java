package org.dragon.yunpeng.form.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProgramDTO {

	@JsonProperty("program_id")
    private int programId;

    @JsonProperty("type_id")
    private int typeId;

    private String name;
    private String type;

    @JsonProperty("program_obsolete")
    private boolean programObsolete;

    @JsonProperty("type_obsolete")
    private boolean typeObsolete;

    private String status;

    // Getters & Setters

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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

    public boolean isProgramObsolete() {
        return programObsolete;
    }

    public void setProgramObsolete(boolean programObsolete) {
        this.programObsolete = programObsolete;
    }

    public boolean isTypeObsolete() {
        return typeObsolete;
    }

    public void setTypeObsolete(boolean typeObsolete) {
        this.typeObsolete = typeObsolete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}