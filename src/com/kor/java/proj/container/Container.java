package com.kor.java.proj.container;

import com.kor.java.proj.dao.ArticleDao;
import com.kor.java.proj.dao.MemberDao;
import com.kor.java.proj.service.ArticleService;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	public static ArticleService articleService;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		articleService = new ArticleService();
	}
}
