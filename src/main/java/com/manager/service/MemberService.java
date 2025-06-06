package com.manager.service;

import com.manager.entity.Member;

public interface MemberService {

	Member getMember(String memberId);
	
	String createMember(Member member);
}
