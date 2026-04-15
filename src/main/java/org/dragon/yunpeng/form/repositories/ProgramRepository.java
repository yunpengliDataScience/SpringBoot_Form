package org.dragon.yunpeng.form.repositories;

import java.util.Optional;

import org.dragon.yunpeng.form.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {

	Optional<Program> findByNameIgnoreCase(String name);
}
