package net.scit.word.ui;

import java.util.List;
import java.util.Scanner;

import net.scit.word.dao.WordDAO;
import net.scit.word.service.GuessingGame;
import net.scit.word.vo.Word;

public class GuessingGameUI{
	Scanner scanner = new Scanner(System.in);
	WordDAO dao = new WordDAO();
	
	public GuessingGameUI() {
		String choice;
		while(true) {
			mainMenu();
			choice = scanner.nextLine();
			
			switch(choice) {
			case "1" : list();  	break;
			case "2" : add();   	break;
			case "3" : search();   	break;
			case "4" : update(); 	break;
			case "5" : delete(); 	break;
			case "6" : guessingGame(); break;
			case "0" : 
				System.out.println("** 프로그램을 종료합니다.");
				System.exit(0);
			default :
				System.out.println("err) 메뉴를 다시 선택해 주세요");
			}
			//scanner.nextLine(); // 버퍼 비우기
		}
	}
	
	// 메인 메뉴
	private void mainMenu() {
		System.out.println("===== [일본어 단어 외우기] =====");
		System.out.println("       1) 전체 목록 조회");
		System.out.println("       2) 단어 추가");
		System.out.println("       3) 단어 찾기");
		System.out.println("       4) 단어 수정");
		System.out.println("       5) 단어 삭제");
		System.out.println("       6) 단어 맞추기 게임");
		System.out.println("       0) 프로그램 종료");
		System.out.println("============================");
		System.out.print  ("          선택> ");
	}
	

	// 전체 단어 조회
	private void list() {
		System.out.println("\n<< 전체 단어 조회 >>");
		
		List<Word> listAll = dao.listWord();
		listAll.forEach(i->System.out.println(i));
		
		int total = dao.totalWords();
		System.out.println("현재 등록된 단어는 총 " + total + " 개입니다.");
		
	}

	// 새로운 단어 추가
	private void add() {
		System.out.println("\n<< 새로운 단어 등록 >>");
		
		System.out.print("> 단어 : ");
		String word = scanner.nextLine();
		if(word.trim().equals("")) {
			System.out.println("err) 단어를 입력해주세요");
			return;
		}
		
		System.out.print("> 뜻 : ");
		String mean = scanner.nextLine();

		if(mean.trim().equals("")) {//trim 좌우의 문자열 공백 제거
			System.out.println("err) 단어의 뜻을 입력해주세요");
			return;
		}
		
		Word addword = new Word(word, mean);
		dao.appendWord(addword);		
		
		System.out.println("** 새로운 단어가 등록되었습니다.\n");
	}

	// 단어 찾기
	private void search() {
		String word;
		System.out.println("\n<< 단어 찾기 >>");
		
		System.out.print("> 단어 : ");
		word = scanner.nextLine();
		if(word.trim().equals("")) {
			System.out.println("err) 단어를 입력해주세요");
			return;
		}		
	
		Word findWord = dao.findByWord(word);
		if(findWord == null) {
			System.out.println("** 단어가 없습니다.");
			return;
		}
		
		System.out.println(findWord);
	}
	
	// 단어 수정
	private void update() {
		System.out.println("\n<< 단어 수정 >>");
		System.out.println("> 수정할 단어");
		String word = scanner.nextLine();
		
		Word findedWord = dao.findByWord(word);
		
		if (findedWord == null) {
			System.out.println("찾으시는 단어를 찾을 수 없습니다.");
			return;
		}
		System.out.print("* 검색된 단어 : ");
		System.out.println(findedWord);
		
		System.out.print("> 단어 : ");
		String newWord = scanner.nextLine();
		System.out.print("> 뜻 : ");
		String mean = scanner.nextLine();
		
		findedWord.setWord(newWord);
		findedWord.setMean(mean);
		
		dao.updateWord(findedWord);
		
		System.out.println("** 단어가 수정되었습니다. ");
		
//		List<Word> listAll = dao.listWord(); //전체 리스트를 우선 가져온다.
//		for(Word findingWord : listAll) { // 뜻에 찾는 단어가 있는지 확인한다.
//			if(findingWord.getMean().equals(insertedword)) {
//				System.out.println(findingWord);
//				
//				System.out.print("> 단어 : ");
//				word = scanner.nextLine();
//				System.out.println("> 뜻 : ");
//				mean = scanner.nextLine();
//				
//				Word updatedWord = new Word(word,mean);
//				
//				dao.updateWord(updatedWord);
//				System.out.println("** 단어가 수정되었습니다.");
//				
//			}
//		}
		
	
	
	}
	
	// 단어 삭제
	private void delete() {
		System.out.println("\n<< 단어 삭제 >>");
		System.out.println("> 삭제할 단어");
		String word = scanner.nextLine();
		
		Word findedword = dao.findByWord(word);
		if(findedword == null) {
			System.out.println("입력하신 단어를 찾을 수 없습니다.");
			return;
		}		
		System.out.println(findedword);
		
		System.out.println("** 단어를 삭제하시겠습니까? (y/n) ");
		String answer = scanner.nextLine();

		if (answer.trim().equals("y")) {
			int result = dao.deleteWord(findedword.getSeq());
			System.out.println(result +"개 단어가 삭제되었습니다.");
			return;
		}
		System.out.println("** 삭제 작업이 취소되었습니다.");
				
	}
	// 단어 맞추기 게임
	private void guessingGame() {
		GuessingGame game = new GuessingGame();	
		game.startGame();
		
	}
}
