package org.dragon.yunpeng.form.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.dragon.yunpeng.form.dtos.ProgramDTO;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/programs")
public class ProgramController {

	private List<ProgramDTO> mockDB = new ArrayList<>();

	public ProgramController() {
		ProgramDTO p1 = new ProgramDTO();
		p1.setProgram_id(1);
		p1.setType_id(101);
		p1.setName("Program A");
		p1.setType("Type 1");

		ProgramDTO p2 = new ProgramDTO();
		p2.setProgram_id(2);
		p2.setType_id(102);
		p2.setName("Program B");
		p2.setType("Type 2");

		mockDB.add(p1);
		mockDB.add(p2);
	}

	/* GET */
	@GetMapping("/list")
	public List<ProgramDTO> getPrograms() {
		return mockDB;
	}

	/* POST */
	@PostMapping("/save")
	public String savePrograms(@RequestBody List<ProgramDTO> programs) {

		System.out.println("Received from frontend:");
		for (ProgramDTO p : programs) {
			System.out.println(p.getName() + " - " + p.getType());
		}

		// overwrite mock DB
		mockDB = programs;

		return "success";
	}
}
