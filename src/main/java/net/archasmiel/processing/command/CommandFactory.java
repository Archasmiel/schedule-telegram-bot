package net.archasmiel.processing.command;

import com.pengrad.telegrambot.TelegramBot;
import net.archasmiel.processing.command.advanced.StartCommand;
import net.archasmiel.processing.command.basic.Command;
import net.archasmiel.processing.command.error.InvalidCommand;
import net.archasmiel.processing.command.error.UnknownCommand;
import org.apache.commons.math3.util.Pair;

import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CommandFactory {

	private static final CommandFactory INSTANCE = new CommandFactory();
	private static final String letters = "a-zA-Zа-яА-Я";

	public Command getCommand(TelegramBot bot, String command) {
		if (!validCommand(command))
			return new InvalidCommand(bot);

		Pair<String, String> commandData = parseCommand(command);
		if (StartCommand.equalsTo(commandData.getKey(), commandData.getValue())) {
			return new StartCommand(bot);
		}

		return new UnknownCommand(bot);
	}

	public static CommandFactory getInstance() {
		return INSTANCE;
	}

	private Pair<String, String> parseCommand(String command) {
		Optional<MatchResult> prefix = Pattern.compile("[^" + letters + " ]").matcher(command).results().findFirst();
		Optional<MatchResult> name = Pattern.compile("[" + letters + "]+").matcher(command).results().findFirst();

		if (prefix.isPresent() && name.isPresent()) {
			return new Pair<>(prefix.get().group(), name.get().group());
		}

		throw new IllegalStateException("Command parse error!");
	}

	private boolean validCommand(String command) {
		return Pattern.compile("[^" + letters + " ][" + letters + "[^ ]]+").matcher(command).results().count() == 1;
	}

}
