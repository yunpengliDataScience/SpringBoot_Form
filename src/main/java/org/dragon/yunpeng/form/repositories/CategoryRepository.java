package org.dragon.yunpeng.form.repositories;

import org.dragon.yunpeng.form.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
