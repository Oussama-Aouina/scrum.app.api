package com.oussama.scrum.app.api.service;

import com.oussama.scrum.app.api.entity.TaskEntity;
import com.oussama.scrum.app.api.repository.TasksRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TasksRepo repo;
    public boolean deleteTask(int id){
        try {
            TaskEntity a = this.repo.findById(id).get();
            this.repo.delete(a);
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public TaskEntity getTask(int id) {
        return this.repo.findById(id).get();
    }
}
