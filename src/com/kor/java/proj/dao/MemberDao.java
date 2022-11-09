package com.kor.java.proj.dao;

import java.util.ArrayList;
import java.util.List;

import com.kor.java.proj.dto.Article;
import com.kor.java.proj.dto.Member;

public class MemberDao extends Dao {
	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}
	
	public void add(Member member) {
		members.add(member);
		lastId = member.id;
	}

	
}
