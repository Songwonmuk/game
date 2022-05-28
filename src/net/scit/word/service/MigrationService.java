package net.scit.word.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import net.scit.word.dao.WordDAO;
import net.scit.word.vo.Word;

public class MigrationService {

	WordDAO dao = new WordDAO();

	public void migration() throws Exception{
		
		File file = new File("D:/user/Desktop/word.txt");
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
			String input;
			while ((input = reader.readLine()) != null) {
				String[] inputs = input.split(" ");
				String word = inputs[0];
				String mean = inputs[1];

				Word findedWord = dao.findByWord(word);
				if (findedWord != null) {
					return;
				}

				dao.appendWord(new Word(word, mean));
			}
			reader.close();
		}
		}
}
