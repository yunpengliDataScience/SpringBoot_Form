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

	        String programName = dto.getName().trim().toLowerCase();

	        Program program = programCache.get(programName);

	        // 🔹 Step 1: resolve Program
	        if (program == null) {

	            if (dto.getProgram_id() != -1) {
	                // try find by ID
	                program = programRepository.findById((long) dto.getProgram_id()).orElse(null);
	            }

	            // if not found, try by name
	            if (program == null) {
	                program = programRepository.findByNameIgnoreCase(dto.getName()).orElse(null);
	            }

	            // still not found → create
	            if (program == null) {
	                program = new Program();
	            }

	            program.setName(dto.getName());
	            program.setObsolete(dto.isProgram_obsolete());

	            programCache.put(programName, program);
	        }

	        // 🔹 Step 2: handle ProgramType
	        ProgramType type = null;

	        if (dto.getType_id() != -1) {
	            // find existing type inside program
	            for (ProgramType t : program.getProgramTypes()) {
	                if (t.getId() != null && t.getId().intValue() == dto.getType_id()) {
	                    type = t;
	                    break;
	                }
	            }
	        }

	        // if not found → create new
	        if (type == null) {
	            type = new ProgramType();
	            program.addProgramType(type);
	        }

	        type.setType(dto.getType());
	        type.setObsolete(dto.isType_obsolete());
	    }

	    // 🔹 Step 3: save all programs (cascade saves types)
	    programRepository.saveAll(programCache.values());
	}
	
	/*
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
	*/
	
	
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
