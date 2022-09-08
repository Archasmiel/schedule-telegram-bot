package net.archasmiel.processing.callback.basic;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;

public abstract class Callback {

	protected final TelegramBot bot;

	protected Callback(TelegramBot bot) {
		this.bot = bot;
	}

	public abstract void process(CallbackQuery callback);

}
