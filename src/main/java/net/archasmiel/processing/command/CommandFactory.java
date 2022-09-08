package net.archasmiel.processing.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import net.archasmiel.processing.command.advanced.StartCommand;
import net.archasmiel.processing.command.basic.Command;
import net.archasmiel.processing.command.error.InvalidCommand;
import net.archasmiel.processing.command.error.UnknownCommand;

import java.util.regex.Pattern;

public class CommandFactory {

	public static final CommandFactory INSTANCE = new CommandFactory();
	private static final String letters = "a-zA-Zа-яА-Я";

	public Command getCommand(TelegramBot bot, Update update) {
		if (!validCommand(update.message().text()))
			return new InvalidCommand(bot);

		if (StartCommand.equalsTo(update.message().text())) {
			return new StartCommand(bot);
		}

		return new UnknownCommand(bot);
	}

	public static CommandFactory getInstance() {
		return INSTANCE;
	}

	private boolean validCommand(String command) {
		return Pattern.compile("[^" + letters + " ][" + letters + "[^ ]]+").matcher(command).results().count() == 1;
	}

}
