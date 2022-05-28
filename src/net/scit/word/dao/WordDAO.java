package net.scit.word.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.word.vo.Word;

public class WordDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	// 단어 추가
	public int appendWord(Word word) {
		SqlSession session = factory.openSession();

		WordMapper mapper = session.getMapper(WordMapper.class);

		int result = mapper.appendWord(word);

		session.commit();

		return result;
	}

	// 단어 목록
	public List<Word> listWord() {
		SqlSession session = factory.openSession();

		WordMapper mapper = session.getMapper(WordMapper.class);

		List<Word> list = mapper.listWord();

		return list;
	}

	// 단어 찾기
	public Word findByWord(String word) {
		SqlSession session = factory.openSession();

		WordMapper mapper = session.getMapper(WordMapper.class);

		Word w = mapper.findByWord(word);

		return w;
	}

	// 단어 삭제
	public int deleteWord(int seq) {
		SqlSession session = factory.openSession();

		WordMapper mapper = session.getMapper(WordMapper.class);

		int result = mapper.deleteWord(seq);

		session.commit();

		return result;
	}

	// 단어 수정
	public int updateWord(Word word) {
		SqlSession session = factory.openSession();

		WordMapper mapper = session.getMapper(WordMapper.class);

		int result = mapper.updateWord(word);

		session.commit();

		return result;
	}

	// 총 단어수
	public int totalWords() {
		SqlSession session = factory.openSession();

		WordMapper mapper = session.getMapper(WordMapper.class);

		int result = mapper.totalWords();

		return result;
	}
	public List<Word> unmemorizedWords(){
		SqlSession session = factory.openSession();

		WordMapper mapper = session.getMapper(WordMapper.class);
	
		List<Word> words = mapper.unmemorizedWords();
		
		return words;
		
	}
}
