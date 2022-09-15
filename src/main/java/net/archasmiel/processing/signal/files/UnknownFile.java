package net.archasmiel.processing.signal.files;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.processing.signal.basic.Signal;

public class UnknownFile extends Signal {

	public UnknownFile(TelegramBot bot) {
		super(bot);
	}

	@Override
	public void process(Update update) {
		bot.execute(new SendMessage(update.message().chat().id(), "Такий тип файлу не підтримується."));
	}

}
