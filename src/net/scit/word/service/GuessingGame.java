package net.scit.word.service;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import net.scit.word.dao.WordDAO;
import net.scit.word.vo.Word;

// �ܾ� ���߱� ����
public class GuessingGame {
	int todayScore;	// ���� ���� ����
	Scanner scanner = new Scanner(System.in);		

	WordDAO dao = new WordDAO();

	// �ܾ� ���߱� ����
	// 5���� ���� ������ �ǽ��Ͽ� ���� ��� DB�� memorize�÷��� 1�� ����
	public void startGame() {
		String word;
		int rightMean =0;
		// Code Here
		
		System.out.println("\n<< �ܾ� ���߱� ���� >>");
		int cnt=1;
		List<Word> gameList = choiceWord();
		for(Word w : gameList) {			
			System.out.println(cnt+"�� : " + w.getMean());
			System.out.print("* �ܾ� ");
			word = scanner.nextLine();
			if(w.getWord().equals(word)) {
				System.out.println("**�����Դϴ�");
				w.setMemorize(true);
				dao.updateWord(w);
//				myAnswer.add(w);
				rightMean++;
			}else {
				System.out.println("**�����Դϴ�");
//				myAnswer.add(w);
			}		
			cnt++;
		}
		
		System.out.println("## ���� ���� ���� : "+rightMean);
		gameList.forEach(game->System.out.println(game));
	}

	// ���Ӱ���� ���� ���
//	private void result(List<Word> myAnswer) {
//
//		myAnswer.forEach(answer->System.out.println(answer));
//	}

	// ������ �ܾ �����ϰ� 5�� �����Ͽ� ��ȯ�ϴ� �޼ҵ�
	// ���õ� �ܾ�� �ߺ����� �ʾƾ� �ϰ�, �ܿ��� ���� �ܾ�� �Ѵ�.
	private List<Word> choiceWord() {
//		List<Word> list = dao.listWord();	// DB�� ���� ������ ������
//		List<Word> choiceWord = new ArrayList<Word>(); // �����ϰ� ���õ� 
//		Set<Word> choice = new HashSet<>();	// �ߺ� ���Ÿ� ���� ���
//		// Code Here		
		List<Word> unmemorizedWords = dao.unmemorizedWords();
		
		Collections.shuffle(unmemorizedWords); // ����Ʈ �����ϰ� ���´�
		return unmemorizedWords.stream()
				.distinct()
				.limit(5)
				.collect(Collectors.toList());
		
	}
}
