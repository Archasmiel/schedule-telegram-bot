package net.archasmiel.bot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import net.archasmiel.bot.thread.UpdateListThread;

import java.util.List;

public record BotListener(TelegramBot bot) implements UpdatesListener {

	@Override
	public int process(List<Update> list) {
		new UpdateListThread(bot, list).start();
		return UpdatesListener.CONFIRMED_UPDATES_ALL;
	}

}
