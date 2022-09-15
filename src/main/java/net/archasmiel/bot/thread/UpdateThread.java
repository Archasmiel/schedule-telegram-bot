package net.archasmiel.bot.thread;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import net.archasmiel.processing.GeneralManufacture;

public class UpdateThread extends Thread {

	private final TelegramBot bot;
	private final Update update;

	public UpdateThread(TelegramBot bot, Update update) {
		this.bot = bot;
		this.update = update;
	}

	@Override
	public void run() {
		GeneralManufacture.INSTANCE
			.manufacture(update)
			.fabricate(bot, update)
			.process(update);
	}

}
