package com.oussama.scrum.app.api.service;

import com.oussama.scrum.app.api.entity.MemberEntity;
import com.oussama.scrum.app.api.entity.TaskEntity;
import com.oussama.scrum.app.api.model.Member;
import com.oussama.scrum.app.api.repository.MemberRepo;
import com.oussama.scrum.app.api.repository.TasksRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private final MemberRepo repo;
    @Autowired
    private TasksRepo taskrepo;

    public MemberService(MemberRepo repo) {
        this.repo = repo;
    }

    public Member createMember(Member member) {
        MemberEntity memberEntity = new MemberEntity();
        BeanUtils.copyProperties(member, memberEntity);
        member.setStatus(memberEntity.getStatus());
        member.setExpectedEnd(memberEntity.getExpectedEnd());
        System.out.println(memberEntity);
        repo.saveAndFlush(memberEntity);
        return member;
    }

    public List<Member> getAllMembers() {
        List<Member> allMembers = new ArrayList<>();
        List<MemberEntity> memberEntities = this.repo.findAll();
        for (MemberEntity e : memberEntities) {
            Member emp = new Member();
            BeanUtils.copyProperties(e, emp);
            allMembers.add(emp);
        }
        return allMembers;
    }


    public boolean deleteMember(int id) {
        try {
            MemberEntity e = this.repo.findById(id).get();
            e.setTasks(null);
            this.repo.delete(e);
            return true;
        } catch (DataIntegrityViolationException e) {
        }
    return false;
    }

    public Member getMember(int id) {
        Member emp = new Member();
        MemberEntity e = this.repo.getById(id);
        BeanUtils.copyProperties(e, emp);
        return emp;
    }

    public Member updateMember(int id, Member member) {
        MemberEntity e = this.repo.findById(id).get();
        e.setFirstName(member.getFirstName());
        e.setLastName(member.getLastName());
        e.setEmail(member.getEmail());
        this.repo.saveAndFlush(e);
        return member;
    }

    public boolean deleteTask(int id) {
        try {
            TaskEntity a = this.taskrepo.findById(id).get();
            this.taskrepo.delete(a);
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public TaskEntity getTask(int id) {
        return this.taskrepo.findById(id).get();
    }

    public TaskEntity updateTask(int id, TaskEntity task) {
        TaskEntity t = this.taskrepo.findById(id).
                orElseThrow(
                        () -> new EntityNotFoundException("not found task with id = " + id)
                );
        BeanUtils.copyProperties(task, t);
        this.taskrepo.saveAndFlush(t);
        return t;
    }
    public TaskEntity addTask(int idMember, TaskEntity t) {
        MemberEntity e = this.repo.findById(idMember).get();
        e.getTasks().add(t);
        this.repo.saveAndFlush(e);
        return t;

    }
}

