package com.kor.java.proj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kor.java.proj.controller.ArticleController;
import com.kor.java.proj.controller.Controller;
import com.kor.java.proj.controller.MemberController;
import com.kor.java.proj.dto.Article;
import com.kor.java.proj.dto.Member;
import com.kor.java.proj.util.Util;

public class App {
	private List<Article> articles;
	private List<Member> members;

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public void start() {
		System.out.println("== 프로그램 시작 ==");

		makeTestData();

		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc, members);
		ArticleController articleController = new ArticleController(sc, articles);

		while (true) {
			System.out.printf("명령어) ");
			String command = sc.nextLine();

			command = command.trim();

			if (command.length() == 0) {
				continue;
			}
			
			if (command.equals("system exit")) {
				break;
			}
			
			command.split(" ");
			String[] commandBits = command.split(" "); // article detail / member join
			
			if ( commandBits.length == 1) {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			
			String controllerName = commandBits[0]; // article / member
			String actionMethodName = commandBits[1]; // detail / join
			
			Controller controller = null;
			
			if ( controllerName.equals("article") ) {
				controller = articleController;
			} else if ( controllerName.equals("member") ) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
				continue;
			}
			
			controller.doAction(command, actionMethodName);
		}

		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}

	private void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		articles.add(new Article(1, Util.getNOtwDateStr(), "제목1", "내용1", 10));
		articles.add(new Article(2, Util.getNOtwDateStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNOtwDateStr(), "제목3", "내용3", 56));
	}
}
