package com.seproject.healthqa.repositorys;

import com.seproject.healthqa.entitys.Qtype;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chaloemphisit
 */

@Repository
public interface QtypeRepository extends CrudRepository<Qtype, Integer>{

}
