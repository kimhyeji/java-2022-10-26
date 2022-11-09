package com.kor.java.proj.controller;

import java.util.List;
import java.util.Scanner;

import com.kor.java.proj.container.Container;
import com.kor.java.proj.dto.Member;
import com.kor.java.proj.service.MemberService;
import com.kor.java.proj.util.Util;

public class MemberController extends Controller {
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private MemberService memberService;
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		
		memberService = Container.memberService;
	}
	
	public void doAction(String commnad, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;
		
		switch ( actionMethodName ) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default: 
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}
	
	private void doLogout() {		
		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}
	
	private void doLogin() {		
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();
		
		// 입력받은 아이디에 해당하는 회원이 존재하는지
		Member member = memberService.getMemberByLoginId(loginId);
		
		if ( member == null ) {
			System.out.println("해당회원은 존재하지 않습니다.");
			return;
		}
		
		if ( member.loginPw.equals(loginPw) == false ) {
			System.out.println("비밀번호가 맞지 않습니다.");
			return;
		}
		
		loginedMember = member;
		System.out.printf("로그인 되었습니다, %s님 환영합니다.\n", loginedMember.name);
	}

	private void doJoin() {
		int id = memberService.getNewId();
		String regDate = Util.getNOtwDateStr();
		
		String loginId = null;
					
		while ( true ) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();
			
			if ( memberService.isJoinableLoginId(loginId) == false ) {
				System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", loginId);
				continue;
			}
			
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;
		
		while ( true ) {					
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인 : ");
			loginPwConfirm = sc.nextLine();
			
			if ( loginPw.equals(loginPwConfirm) == false ) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			
			break;
		}
		
		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		memberService.join(member);

		System.out.printf("%d번 회원이 생성되었습니다. 환영합니다.\n", id);
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");

		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNOtwDateStr(), "admin", "admin", "관리자"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNOtwDateStr(), "user1", "user1", "홍길동"));
		Container.memberDao.add(new Member(Container.memberDao.getNewId(), Util.getNOtwDateStr(), "user2", "user2", "홍길순"));
	}
}
