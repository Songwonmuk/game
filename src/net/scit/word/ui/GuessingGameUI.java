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
				System.out.println("** ���α׷��� �����մϴ�.");
				System.exit(0);
			default :
				System.out.println("err) �޴��� �ٽ� ������ �ּ���");
			}
			//scanner.nextLine(); // ���� ����
		}
	}
	
	// ���� �޴�
	private void mainMenu() {
		System.out.println("===== [�Ϻ��� �ܾ� �ܿ��] =====");
		System.out.println("       1) ��ü ��� ��ȸ");
		System.out.println("       2) �ܾ� �߰�");
		System.out.println("       3) �ܾ� ã��");
		System.out.println("       4) �ܾ� ����");
		System.out.println("       5) �ܾ� ����");
		System.out.println("       6) �ܾ� ���߱� ����");
		System.out.println("       0) ���α׷� ����");
		System.out.println("============================");
		System.out.print  ("          ����> ");
	}
	

	// ��ü �ܾ� ��ȸ
	private void list() {
		System.out.println("\n<< ��ü �ܾ� ��ȸ >>");
		
		List<Word> listAll = dao.listWord();
		listAll.forEach(i->System.out.println(i));
		
		int total = dao.totalWords();
		System.out.println("���� ��ϵ� �ܾ�� �� " + total + " ���Դϴ�.");
		
	}

	// ���ο� �ܾ� �߰�
	private void add() {
		System.out.println("\n<< ���ο� �ܾ� ��� >>");
		
		System.out.print("> �ܾ� : ");
		String word = scanner.nextLine();
		if(word.trim().equals("")) {
			System.out.println("err) �ܾ �Է����ּ���");
			return;
		}
		
		System.out.print("> �� : ");
		String mean = scanner.nextLine();

		if(mean.trim().equals("")) {//trim �¿��� ���ڿ� ���� ����
			System.out.println("err) �ܾ��� ���� �Է����ּ���");
			return;
		}
		
		Word addword = new Word(word, mean);
		dao.appendWord(addword);		
		
		System.out.println("** ���ο� �ܾ ��ϵǾ����ϴ�.\n");
	}

	// �ܾ� ã��
	private void search() {
		String word;
		System.out.println("\n<< �ܾ� ã�� >>");
		
		System.out.print("> �ܾ� : ");
		word = scanner.nextLine();
		if(word.trim().equals("")) {
			System.out.println("err) �ܾ �Է����ּ���");
			return;
		}		
	
		Word findWord = dao.findByWord(word);
		if(findWord == null) {
			System.out.println("** �ܾ �����ϴ�.");
			return;
		}
		
		System.out.println(findWord);
	}
	
	// �ܾ� ����
	private void update() {
		System.out.println("\n<< �ܾ� ���� >>");
		System.out.println("> ������ �ܾ�");
		String word = scanner.nextLine();
		
		Word findedWord = dao.findByWord(word);
		
		if (findedWord == null) {
			System.out.println("ã���ô� �ܾ ã�� �� �����ϴ�.");
			return;
		}
		System.out.print("* �˻��� �ܾ� : ");
		System.out.println(findedWord);
		
		System.out.print("> �ܾ� : ");
		String newWord = scanner.nextLine();
		System.out.print("> �� : ");
		String mean = scanner.nextLine();
		
		findedWord.setWord(newWord);
		findedWord.setMean(mean);
		
		dao.updateWord(findedWord);
		
		System.out.println("** �ܾ �����Ǿ����ϴ�. ");
		
//		List<Word> listAll = dao.listWord(); //��ü ����Ʈ�� �켱 �����´�.
//		for(Word findingWord : listAll) { // �濡 ã�� �ܾ �ִ��� Ȯ���Ѵ�.
//			if(findingWord.getMean().equals(insertedword)) {
//				System.out.println(findingWord);
//				
//				System.out.print("> �ܾ� : ");
//				word = scanner.nextLine();
//				System.out.println("> �� : ");
//				mean = scanner.nextLine();
//				
//				Word updatedWord = new Word(word,mean);
//				
//				dao.updateWord(updatedWord);
//				System.out.println("** �ܾ �����Ǿ����ϴ�.");
//				
//			}
//		}
		
	
	
	}
	
	// �ܾ� ����
	private void delete() {
		System.out.println("\n<< �ܾ� ���� >>");
		System.out.println("> ������ �ܾ�");
		String word = scanner.nextLine();
		
		Word findedword = dao.findByWord(word);
		if(findedword == null) {
			System.out.println("�Է��Ͻ� �ܾ ã�� �� �����ϴ�.");
			return;
		}		
		System.out.println(findedword);
		
		System.out.println("** �ܾ �����Ͻðڽ��ϱ�? (y/n) ");
		String answer = scanner.nextLine();

		if (answer.trim().equals("y")) {
			int result = dao.deleteWord(findedword.getSeq());
			System.out.println(result +"�� �ܾ �����Ǿ����ϴ�.");
			return;
		}
		System.out.println("** ���� �۾��� ��ҵǾ����ϴ�.");
				
	}
	// �ܾ� ���߱� ����
	private void guessingGame() {
		GuessingGame game = new GuessingGame();	
		game.startGame();
		
	}
}
