package net.archasmiel.bot.thread;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import net.archasmiel.processing.command.CommandFactory;

import java.util.List;

public class UpdateListThread extends Thread {

	private final TelegramBot bot;
	private final List<Update> updates;

	public UpdateListThread(TelegramBot bot, List<Update> updates) {
		this.bot = bot;
		this.updates = updates;
	}

	@Override
	public void run() {
		if (updates.isEmpty()) return;
		updates.forEach(update ->
			new UpdateThread(bot, update).start()
		);
	}

}
