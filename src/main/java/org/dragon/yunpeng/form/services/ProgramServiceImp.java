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

		Map<String, Program> programCache = new HashMap<>();

		for (ProgramDTO dto : dtos) {

			if (dto.getStatus() == null || dto.getStatus().equals("UNCHANGED")) {
				continue;
			}

			switch (dto.getStatus()) {

			case "NEW":
				handleNew(dto, programCache);
				break;

			case "MODIFIED":
				handleModified(dto, programCache);
				break;

			case "DELETED":
				handleDeleted(dto);
				break;
			}
		}

		// save all updated/created programs
		programRepository.saveAll(programCache.values());
	}

	private void handleNew(ProgramDTO dto, Map<String, Program> cache) {

		String key = dto.getName().trim().toLowerCase();

		Program program = cache.get(key);

		if (program == null) {

			program = programRepository.findByNameIgnoreCase(dto.getName()).orElse(null);

			if (program == null) {
				program = new Program();
			}

			program.setName(dto.getName());
			program.setObsolete(dto.isProgramObsolete());

			cache.put(key, program);
		}

		ProgramType type = new ProgramType();
		type.setType(dto.getType());
		type.setObsolete(dto.isTypeObsolete());

		program.addProgramType(type);
	}

	private void handleModified(ProgramDTO dto, Map<String, Program> cache) {

		String key = dto.getName().trim().toLowerCase();

		Program program = cache.get(key);

		if (program == null) {

			program = programRepository.findById((long) dto.getProgramId()).orElse(null);

			if (program == null)
				return;

			program.setName(dto.getName());
			program.setObsolete(dto.isProgramObsolete());

			cache.put(key, program);
		}

		for (ProgramType t : program.getProgramTypes()) {

			if (t.getId() != null && t.getId().intValue() == dto.getTypeId()) {

				t.setType(dto.getType());
				t.setObsolete(dto.isTypeObsolete());
				return;
			}
		}

		// if type not found → treat as new
		ProgramType newType = new ProgramType();
		newType.setType(dto.getType());
		newType.setObsolete(dto.isTypeObsolete());

		program.addProgramType(newType);
	}

	private void handleDeleted(ProgramDTO dto) {

		if (dto.getProgramId() == -1 || dto.getTypeId() == -1) {
			return; // not in DB
		}

		Program program = programRepository.findById((long) dto.getProgramId()).orElse(null);

		if (program == null)
			return;

		program.getProgramTypes().removeIf(t -> t.getId() != null && t.getId().intValue() == dto.getTypeId());

		if (program.getProgramTypes().isEmpty()) {
			programRepository.delete(program);
		} else {
			programRepository.save(program);
		}
	}

	// LOAD (DB → JSON)
	@Override
	public List<ProgramDTO> getAll() {

		List<ProgramDTO> result = new ArrayList<>();

		for (Program p : programRepository.findAll()) {

			for (ProgramType t : p.getProgramTypes()) {

				ProgramDTO dto = new ProgramDTO();

				dto.setProgramId(p.getId().intValue());
				dto.setName(p.getName());
				dto.setProgramObsolete(p.isObsolete());

				dto.setTypeId(t.getId().intValue());
				dto.setType(t.getType());
				dto.setTypeObsolete(t.isObsolete());

				result.add(dto);
			}
		}

		return result;
	}
}
