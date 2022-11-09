package com.kor.java.proj.service;

import java.util.List;

import com.kor.java.proj.container.Container;
import com.kor.java.proj.dao.ArticleDao;
import com.kor.java.proj.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService() {
		this.articleDao = Container.articleDao;
	}
	
	public List<Article> getForPrintArticles() {
		return articleDao.getArticles();
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);
	}

	public void add(Article article) {
		articleDao.add(article);
	}

	public int getNewId() {
		return articleDao.getNewId();
	}
}
