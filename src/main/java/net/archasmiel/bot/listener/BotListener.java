package net.archasmiel.bot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import net.archasmiel.bot.thread.UpdateListThread;
import net.archasmiel.processing.command.CommandFactory;

import java.util.List;

public class BotListener implements UpdatesListener {

	private final TelegramBot bot;

	public BotListener(TelegramBot bot) {
		this.bot = bot;
	}

	@Override
	public int process(List<Update> list) {
		new UpdateListThread(bot, list).start();
		return UpdatesListener.CONFIRMED_UPDATES_ALL;
	}

}
