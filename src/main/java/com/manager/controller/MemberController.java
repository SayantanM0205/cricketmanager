package com.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.entity.Member;
import com.manager.service.MemberService;

@RestController
//@RequestMapping("/api/v1")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
//	@GetMapping("/member/{memberId}")
//	public ResponseEntity<Member> getMember(@PathVariable String memberId){
//		return ResponseEntity.status(HttpStatus.OK).body(this.memberService.getMember(memberId));
//	}
	
	@PostMapping("/register")
	public ResponseEntity<String> createMember(@RequestBody Member member){
		if(member != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.memberService.createMember(member));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
