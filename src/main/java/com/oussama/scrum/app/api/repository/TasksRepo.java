package com.oussama.scrum.app.api.repository;

import com.oussama.scrum.app.api.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepo extends JpaRepository<TaskEntity,Integer>{
}
