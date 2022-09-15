package net.archasmiel.processing.signal.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.processing.signal.basic.Signal;

public class InvalidCommand extends Signal {

	public InvalidCommand(TelegramBot bot) {
		super(bot);
	}

	@Override
	public void process(Update update) {
		bot.execute(new SendMessage(update.message().chat().id(), "Не зрозумів Вашу команду."));
	}

}
