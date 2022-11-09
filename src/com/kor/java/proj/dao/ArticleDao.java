package com.kor.java.proj.dao;

import java.util.ArrayList;
import java.util.List;

import com.kor.java.proj.dto.Article;

public class ArticleDao {
	public List<Article> articles;
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}
}
