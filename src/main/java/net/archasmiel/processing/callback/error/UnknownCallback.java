package net.archasmiel.processing.callback.error;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.processing.callback.basic.Callback;

public class UnknownCallback extends Callback {

	public UnknownCallback(TelegramBot bot) {
		super(bot);
	}

	@Override
	public void process(CallbackQuery callback) {
		bot.execute(new SendMessage(callback.message().chat().id(), "Такий запит не підтримується."));
	}

}
