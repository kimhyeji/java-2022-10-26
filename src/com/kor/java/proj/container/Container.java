package com.kor.java.proj.container;

import com.kor.java.proj.dao.ArticleDao;
import com.kor.java.proj.dao.MemberDao;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}
}
