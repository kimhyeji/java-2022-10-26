package com.kor.java.proj.service;

import com.kor.java.proj.container.Container;
import com.kor.java.proj.dao.MemberDao;
import com.kor.java.proj.dto.Member;

public class MemberService {
private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = Container.memberDao;
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public boolean isJoinableLoginId(String loginId) {
		return memberDao.isJoinableLoginId(loginId);
	}

	public void join(Member member) {
		memberDao.add(member);
	}

	public int getNewId() {
		return memberDao.getLastId();
	}

	public String getMemberNameById(int memberId) {
		return memberDao.getMemberNameById(memberId);
	}
}
