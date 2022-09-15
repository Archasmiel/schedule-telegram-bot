package net.archasmiel.processing.signal.callback;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.processing.signal.basic.Signal;

public class UnknownCallback extends Signal {

	public UnknownCallback(TelegramBot bot) {
		super(bot);
	}

	@Override
	public void process(Update update) {
		CallbackQuery callback = update.callbackQuery();
		bot.execute(new SendMessage(callback.message().chat().id(), "Такий запит не підтримується."));
	}

}
