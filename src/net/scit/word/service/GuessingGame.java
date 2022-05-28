package net.scit.word.service;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import net.scit.word.dao.WordDAO;
import net.scit.word.vo.Word;

// 단어 맞추기 게임
public class GuessingGame {
	int todayScore;	// 오늘 맞춘 개수
	Scanner scanner = new Scanner(System.in);		

	WordDAO dao = new WordDAO();

	// 단어 맞추기 게임
	// 5번에 거쳐 게임을 실시하여 맞은 경우 DB의 memorize컬럼을 1로 적용
	public void startGame() {
		String word;
		int rightMean =0;
		// Code Here
		
		System.out.println("\n<< 단어 맞추기 게임 >>");
		int cnt=1;
		List<Word> gameList = choiceWord();
		for(Word w : gameList) {			
			System.out.println(cnt+"번 : " + w.getMean());
			System.out.print("* 단어 ");
			word = scanner.nextLine();
			if(w.getWord().equals(word)) {
				System.out.println("**정답입니다");
				w.setMemorize(true);
				dao.updateWord(w);
//				myAnswer.add(w);
				rightMean++;
			}else {
				System.out.println("**오답입니다");
//				myAnswer.add(w);
			}		
			cnt++;
		}
		
		System.out.println("## 오늘 맞춘 개수 : "+rightMean);
		gameList.forEach(game->System.out.println(game));
	}

	// 게임결과와 점수 출력
//	private void result(List<Word> myAnswer) {
//
//		myAnswer.forEach(answer->System.out.println(answer));
//	}

	// 오늘의 단어를 랜덤하게 5개 선택하여 반환하는 메소드
	// 선택된 단어는 중복되지 않아야 하고, 외우지 않은 단어여야 한다.
	private List<Word> choiceWord() {
//		List<Word> list = dao.listWord();	// DB로 부터 가져온 데이터
//		List<Word> choiceWord = new ArrayList<Word>(); // 랜덤하게 선택된 
//		Set<Word> choice = new HashSet<>();	// 중복 제거를 위해 사용
//		// Code Here		
		List<Word> unmemorizedWords = dao.unmemorizedWords();
		
		Collections.shuffle(unmemorizedWords); // 리스트 랜덤하게 섞는다
		return unmemorizedWords.stream()
				.distinct()
				.limit(5)
				.collect(Collectors.toList());
		
	}
}
