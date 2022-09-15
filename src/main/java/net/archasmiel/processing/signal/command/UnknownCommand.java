package net.archasmiel.processing.signal.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.processing.signal.basic.Signal;

public class UnknownCommand extends Signal {

	public UnknownCommand(TelegramBot bot) {
		super(bot);
	}

	@Override
	public void process(Update update) {
		bot.execute(new SendMessage(update.message().chat().id(), "Така команда, на жаль не підтримується."));
	}

}
