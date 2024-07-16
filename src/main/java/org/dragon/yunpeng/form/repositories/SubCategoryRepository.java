package org.dragon.yunpeng.form.repositories;

import org.dragon.yunpeng.form.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
	List<SubCategory> findByCategoryId(Long categoryId);
}
