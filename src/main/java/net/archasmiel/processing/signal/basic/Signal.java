package net.archasmiel.processing.signal.basic;

import com.pengrad.telegrambot.TelegramBot;

public abstract class Signal implements ISignal {

	protected final TelegramBot bot;

	protected Signal(TelegramBot bot) {
		this.bot = bot;
	}

}
