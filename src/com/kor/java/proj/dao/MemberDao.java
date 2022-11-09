package com.kor.java.proj.dao;

import java.util.ArrayList;
import java.util.List;

import com.kor.java.proj.dto.Member;

public class MemberDao {
	public List<Member> members;
	
	public MemberDao() {
		members = new ArrayList<>();
	}
}
