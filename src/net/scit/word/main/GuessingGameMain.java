package net.scit.word.main;

import net.scit.word.service.MigrationService;
import net.scit.word.ui.GuessingGameUI;

public class GuessingGameMain {
	
	public static void main(String[] args) throws Exception {
		MigrationService migrationService = new MigrationService();
		migrationService.migration();
		new GuessingGameUI();
	}
}