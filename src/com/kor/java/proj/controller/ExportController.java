package com.kor.java.proj.controller;

import java.util.Scanner;

import com.kor.java.proj.container.Container;
import com.kor.java.proj.service.ExportService;
import com.kor.java.proj.service.MemberService;

public class ExportController extends Controller{
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private ExportService exportService;
	
	public ExportController(Scanner sc) {
		this.sc = sc;
		exportService = Container.exportService;
	}

	public void doAction(String commnad, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;
		
		switch ( actionMethodName ) {
		case "html":
			doHtml();
			break;
		default: 
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}

	private void doHtml() {
		System.out.println("html 생성을 시작합니다.");
		exportService.makeHtml();
	}

	public void makeTestData() {
		// TODO Auto-generated method stub
		
	}
}
