package org.dragon.yunpeng.form.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.dragon.yunpeng.form.dtos.ProgramDTO;
import org.dragon.yunpeng.form.services.IProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@CrossOrigin
public class ProgramController {

	@Autowired
	private IProgramService programService;

	@GetMapping("/list")
	public List<ProgramDTO> list() {
		return programService.getAll();
	}

	@PostMapping("/save")
	public String save(@RequestBody List<ProgramDTO> data) {
		programService.save(data);
		return "ok";
	}
}
