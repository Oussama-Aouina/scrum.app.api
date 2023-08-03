package com.oussama.scrum.app.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "img")
    private String img;
    @Column(name = "post")
    private String post;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tasks")
    private List<TaskEntity> tasks;
    @Transient
    private String status;
    @Transient
    private LocalDate expectedEnd;

    public String getStatus() {
        int i = 0;
        for (TaskEntity e : tasks) {
            if (e.getfinished() == false) {
                i++;
            }
        }
        if (i == 0) {
            return "Free";
        } else if (tasks.size() <= 2) {
            return "Almost free";
        } else if (tasks.size() <= 4) {
            return "Busy";
        } else return "Overwhelmed";
    }

    public LocalDate getExpectedEnd() {

        if (!tasks.isEmpty()&&(status != "Free")) {
            LocalDate d = tasks.get(0).getEndDate();
            for (TaskEntity t : tasks) {
                if (t.getEndDate().isAfter(d)) {
                    d = t.getEndDate();
                }
            }
            return d;
        }
        return null;}
}
