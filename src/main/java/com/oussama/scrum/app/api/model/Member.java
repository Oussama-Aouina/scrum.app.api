package com.oussama.scrum.app.api.model;

import com.oussama.scrum.app.api.entity.TaskEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Member {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String img;
    private String post;
    private List<TaskEntity> tasks;
    private String status;
    private LocalDate expectedEnd;

}
