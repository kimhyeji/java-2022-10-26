package com.kor.java.proj.container;

import com.kor.java.proj.dao.ArticleDao;
import com.kor.java.proj.dao.MemberDao;
import com.kor.java.proj.service.ArticleService;
import com.kor.java.proj.service.MemberService;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	public static MemberService memberService;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
		memberService = new MemberService();
	}
}
