package com.biz.names.exec;

import java.util.Scanner;

import com.biz.names.service.NameService;

public class NameExec01 {

	public static void main(String[] args) {
		String fstFile = "src/com/biz/names/성씨.txt";
		String sndFile = "src/com/biz/names/이름.txt";
		String saveFile = "src/com/biz/names/fullNameList.txt";
		NameService ns = new NameService(fstFile, sndFile);
		ns.readFirstFile();
		ns.readSndFile();
		ns.viewFullName();

		Scanner scanner = new Scanner(System.in);
		System.out.println("-------------------");
		System.out.println("1.화면출력 2.파일저장 3.종료");
		String s = scanner.nextLine();
		int intS = Integer.valueOf(s);
		if (intS == 1) {
			ns.makeFullName(10);
		}
		if (intS == 2) {
			ns.makeFullName(10);
			ns.writeFullName(saveFile);
			System.out.println("파일에 저장되었습니다.");
		} else {
			System.out.println("종료되었습니다.");
		}
	}

}