package org.dragon.yunpeng.form.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dragon.yunpeng.form.dtos.ProgramDTO;
import org.dragon.yunpeng.form.entities.Program;
import org.dragon.yunpeng.form.entities.ProgramType;
import org.dragon.yunpeng.form.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "programService")
public class ProgramServiceImp implements IProgramService {

	@Autowired
	private ProgramRepository programRepository;

	// SAVE (JSON → DB)
	@Override
	public void save(List<ProgramDTO> dtos) {

		programRepository.deleteAll(); // simple demo (overwrite)

		Map<Integer, Program> map = new HashMap<>();

		for (ProgramDTO dto : dtos) {

			Program p = map.get(dto.getProgram_id());

			if (p == null) {
				p = new Program();
				p.setName(dto.getName());
				p.setObsolete(dto.isProgram_obsolete());
				map.put(dto.getProgram_id(), p);
			}

			ProgramType t = new ProgramType();
			t.setType(dto.getType());
			t.setObsolete(dto.isType_obsolete());

			p.addProgramType(t);
		}

		programRepository.saveAll(map.values());
	}

	// LOAD (DB → JSON)
	@Override
	public List<ProgramDTO> getAll() {

		List<ProgramDTO> result = new ArrayList<>();

		for (Program p : programRepository.findAll()) {

			for (ProgramType t : p.getProgramTypes()) {

				ProgramDTO dto = new ProgramDTO();

				dto.setProgram_id(p.getId().intValue());
				dto.setName(p.getName());
				dto.setProgram_obsolete(p.isObsolete());

				dto.setType_id(t.getId().intValue());
				dto.setType(t.getType());
				dto.setType_obsolete(t.isObsolete());

				result.add(dto);
			}
		}

		return result;
	}
}
