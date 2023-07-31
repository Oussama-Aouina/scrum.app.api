package com.oussama.scrum.app.api.repository;

import com.oussama.scrum.app.api.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<MemberEntity, Integer> {
}
