package com.biz.names.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.biz.names.vo.FullNameVO;

//파일2개를 읽어 조합해야 하므로 기본적으로 2개의 파일을 읽어서 값들을 보관할 리스트 2개를 선언, 생성, 초기화하자
public class NameService {

	// 새로운 이름 만들어 저장할 fullNameList를 선언 및 초기화
	List<FullNameVO> fNameList;
	// 파일을 읽어 저장할 리스트2개
	List<String> firstList;
	List<String> sndList;

	// 파일 2개를 읽기 위해서 서비스에서 직접 파일 이름을 지정해도 되지만 메인에서 파일 이름을 매개변수로 전달하도록 디자인 한다
	String fstFile;
	String sndFile;
	String saveFile;

	public NameService(String fstFile, String sndFile) {
		// TODO Auto-generated constructor stub
		
		fNameList = new LinkedList();// fNameList = new ArrayList();
		firstList = new ArrayList();
		sndList = new ArrayList();

		this.fstFile = fstFile;
		this.sndFile = sndFile;

	}
	public void menu() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("이름생성개수");
		String strC = scan.nextLine();
		int intC = Integer.valueOf(strC);
		System.out.println("--------------");
		System.out.println("1.화면출력  2.파일저장  0.종료");
		System.out.println("--------------");
		
		while(true) {
			String strM = scan.nextLine();
			int intM = Integer.valueOf(strM);
			String filePath = "src/com/biz/names/";
			if(intM == 0) return ;
			if(intM == 1) viewFullName();
			if(intM == 2) {
				System.out.println("파일이름>>");
				String fileName = scan.nextLine();
				writeFullName(filePath + fileName + ".txt");
			}
		}
		
	}

	public void writeFullName(String saveFile) {
		this.saveFile = saveFile;
		PrintWriter pw;
		try {
			pw = new PrintWriter(saveFile);
			for (FullNameVO vo : fNameList) {
				pw.println(vo.getStr1stName() + vo.getStr2ndName());

			}
			pw.close();
			System.out.println("파일저장완료");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void viewFullName() {
		System.out.println("=====================");
		System.out.println("한국인이름들");
		System.out.println("=====================");
		for (FullNameVO vo : fNameList) {
			System.out.println(vo.getStr1stName() + vo.getStr2ndName());
		}
		System.out.println("---------------------");
	}

	// 100개의 새로운 이름을 만드는데 service에서 개수를 정하지 않고 main에서 몇개를 만들지 정해주도록 디자인
	public void makeFullName(int nameSize) {

		//fullnamelist를 비우고 이름 생성하기
		fNameList.clear();
		int fSize = firstList.size();
		int sSize = sndList.size();

		for (int i = 0; i < nameSize; i++) {
			int fstPos = (int) (Math.random() * fSize);
			int sndPos = (int) (Math.random() * sSize);

			String fName = firstList.get(fstPos);
			String sName = sndList.get(sndPos);

			System.out.println(fName + sName);

			// fNameList에 추가
			FullNameVO vo = new FullNameVO();
			vo.setStr1stName(fName);
			vo.setStr2ndName(sName);
			fNameList.add(vo);

		}
	}

	// 한국성씨.txt 파일에서 성씨리스트를 읽어서 fstList에 저장할 메소드를 만든다
	public void readFirstFile() {
		FileReader fr;
		BufferedReader buffer;

		try {
			fr = new FileReader(fstFile);
			buffer = new BufferedReader(fr);

			while (true) {
				String reader = buffer.readLine();
				if (reader == null)
					break;
				String[] names = reader.split(":");

				String fstName = names[1];
				//일부 특수문자는 단독으로 split안됨. ("(" "," "!")
				// 앞에 "\\"를 넣어준다
				String[] hans = fstName.split("\\(");
				firstList.add(hans[0]);
			}
			buffer.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 이름리스트.txt파일에서 이름을 읽어서 세컨리스트에 저장할 메소드를 만든다
	public void readSndFile() {
		FileReader fr;
		BufferedReader buffer;

		try {
			fr = new FileReader(sndFile);
			buffer = new BufferedReader(fr);

			while (true) {
				String reader = buffer.readLine();
				if (reader == null)
					break;
				sndList.add(reader);
			}
			buffer.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void scanType() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("1:화면에 보이기 2:파일에 저장하기 3:종료");
		String s = scanner.nextLine();
		int intS = Integer.valueOf(s);

		if (intS == 1) {
			viewFullName();
		}
		if (intS == 2) {
			writeFullName(saveFile);
			System.out.println("저장이 완료되었습니다");
		} else {
			System.out.println("종료되었습니다");
		}
	}

}