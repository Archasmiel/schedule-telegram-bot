package net.archasmiel.processing.command.basic;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;

public abstract class Command {

	protected final TelegramBot bot;

	protected Command(TelegramBot bot) {
		this.bot = bot;
	}

	public abstract void process(Update update);

}
