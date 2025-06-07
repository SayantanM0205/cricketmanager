package com.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manager.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
	Optional<Member> findByMemberEmail(String memberEmail);

}
