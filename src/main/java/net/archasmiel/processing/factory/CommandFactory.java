package net.archasmiel.processing.factory;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import net.archasmiel.processing.signal.basic.Signal;
import net.archasmiel.processing.signal.command.StartCommand;
import net.archasmiel.processing.signal.command.InvalidCommand;
import net.archasmiel.processing.signal.command.UnknownCommand;

import java.util.List;
import java.util.regex.Pattern;

public class CommandFactory implements IFactory<Signal> {

	private static final IFactory<Signal> INSTANCE = new CommandFactory();
	private static final List<String> SPECTRE = List.of("m");
	private static final String LETTERS = "a-zA-Zа-яА-Я";

	public static IFactory<Signal> getFactory() {
		return INSTANCE;
	}

	private boolean validCommand(String command) {
		return Pattern.compile("[^" + LETTERS + " ][" + LETTERS + "[^ ]]+").matcher(command).results().count() == 1;
	}

	@Override
	public List<String> getSpectre() {
		return SPECTRE;
	}

	@Override
	public Signal fabricate(TelegramBot bot, Update data) {
		if (!validCommand(data.message().text()))
			return new InvalidCommand(bot);

		if (StartCommand.equalsTo(data.message().text())) {
			return new StartCommand(bot);
		}

		return new UnknownCommand(bot);
	}


}
