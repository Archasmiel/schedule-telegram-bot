package net.archasmiel.processing.command.error;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.processing.command.basic.Command;

public class InvalidCommand extends Command {

	public InvalidCommand(TelegramBot bot) {
		super(bot);
	}

	@Override
	public void process(Update update) {
		bot.execute(new SendMessage(update.message().chat().id(), "Не зрозумів Вашу команду."));
	}

}
