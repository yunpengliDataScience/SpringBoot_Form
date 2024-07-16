package org.dragon.yunpeng.form.repositories;

import org.dragon.yunpeng.form.entities.Form;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends CrudRepository<Form, Long> {

}
