package org.dragon.yunpeng.form.services;

import java.util.List;

import org.dragon.yunpeng.form.dtos.ProgramDTO;

public interface IProgramService {

	void save(List<ProgramDTO> dtos);

	List<ProgramDTO> getAll();

}
