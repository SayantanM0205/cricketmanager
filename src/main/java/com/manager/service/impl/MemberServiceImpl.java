package com.manager.service.impl;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manager.entity.Member;
import com.manager.repository.MemberRepository;
import com.manager.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private MemberRepository memberRepository;
	private PasswordEncoder passwordEncoder;

	@Override
	public Member getMember(String memberId) {
		log.info("Searching member with id: {}",memberId);
		Member member = memberRepository.findById(memberId).orElseThrow(()-> new RuntimeException("member not found with Id: "+memberId));
		log.info("Member found: {}", member);
		return member;
	}

	@Override
	public String createMember(Member member) {
		String memberId = UUID.randomUUID().toString();
		String encodedPassword = passwordEncoder.encode(member.getMemberPassword());
		member.setMemberId(memberId);
		member.setMemberPassword(encodedPassword);
		Member memberCreated = memberRepository.save(member);
		log.info("Member created with id: {}", memberCreated);
		return member.getMemberId();
	}
	
	

}
