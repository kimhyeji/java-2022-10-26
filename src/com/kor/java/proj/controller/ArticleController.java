package com.kor.java.proj.controller;

import java.util.List;
import java.util.Scanner;

import com.kor.java.proj.container.Container;
import com.kor.java.proj.dto.Article;
import com.kor.java.proj.dto.Member;
import com.kor.java.proj.service.ArticleService;
import com.kor.java.proj.service.MemberService;
import com.kor.java.proj.util.Util;

public class ArticleController extends Controller {
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private ArticleService articleService;
	private MemberService memberService;
	
	public ArticleController(Scanner sc) {
		this.sc = sc;
		
		articleService = Container.articleService;
		memberService = Container.memberService;
	}
	
	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;
		
		switch ( actionMethodName ) {
		case "write" :
			doWrite();
			break;
		case "list" :
			showList();
			break;
		case "detail" :
			showDetail();
			break;
		case "modify" :
			doModify();
			break;
		case "delete" :
			doDelete();
			break;
		default: 
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}
	
	private void doWrite() {
		int id = articleService.getNewId();
		String regDate = Util.getNOtwDateStr();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, loginedMember.id, title, body);
		articleService.add(article);

		System.out.printf("%d번 글이 생성되었습니다.\n", id);
	}

	private void showList() {
		List<Article> forPrintArticles = articleService.getForPrintArticles();
		
		if (forPrintArticles.size() == 0) {
			System.out.println("게시물이 없습니다.");
			return;
		}

		System.out.println("번호  |  작성자 | 조회  | 제목");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			Article article = forPrintArticles.get(i);
			
			String writerName = memberService.getMemberNameById(i);

			System.out.printf("%4d | %4s | %4d | %s\n", article.id, writerName, article.hit, article.title);
		}
	}

	private void showDetail() {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]); // "1" -> 1

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		foundArticle.increaseHit();

		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("날짜 : %s\n", foundArticle.regDate);
		System.out.printf("작성자 : %s\n", foundArticle.memberId);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("조회 : %d\n", foundArticle.hit);
	}
	
	

	private void doModify() {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]); // "1" -> 1

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		if (foundArticle.memberId != loginedMember.id) {
			System.out.printf("권한이 없습니다.\n");
			return;
		}

		System.out.printf("제목 : ");
		String title = sc.nextLine();

		System.out.printf("내용 : ");
		String body = sc.nextLine();

		foundArticle.title = title;
		foundArticle.body = body;
		System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
	}

	private void doDelete() {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]); // "1" -> 1

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		if (foundArticle.memberId != loginedMember.id) {
			System.out.printf("권한이 없습니다.\n");
			return;
		}

		articleService.remove(foundArticle);
		System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
	}
	
	public void makeTestData() {
		System.out.println("테스트를 위한 게시물 데이터를 생성합니다.");

		Container.articleDao.add(new Article(Container.articleDao.getNewId(), Util.getNOtwDateStr(), 1, "제목1", "내용1", 10));
		Container.articleDao.add(new Article(Container.articleDao.getNewId(), Util.getNOtwDateStr(), 2, "제목2", "내용2", 22));
		Container.articleDao.add(new Article(Container.articleDao.getNewId(), Util.getNOtwDateStr(), 3, "제목3", "내용3", 56));
	}

}
