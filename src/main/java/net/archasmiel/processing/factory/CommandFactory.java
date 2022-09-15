package net.archasmiel.processing.factory;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import net.archasmiel.processing.signal.basic.Signal;
import net.archasmiel.processing.signal.command.ScheduleCommand;
import net.archasmiel.processing.signal.command.StartCommand;
import net.archasmiel.processing.signal.command.UnknownCommand;

import java.util.List;

public class CommandFactory implements IFactory<Signal> {

	private static final IFactory<Signal> INSTANCE = new CommandFactory();
	private static final List<String> SPECTRE = List.of("m");

	public static IFactory<Signal> getFactory() {
		return INSTANCE;
	}

	@Override
	public List<String> getSpectre() {
		return SPECTRE;
	}

	@Override
	public Signal fabricate(TelegramBot bot, Update data) {
		if (StartCommand.equalsTo(data.message().text())) {
			return new StartCommand(bot);
		}
		if (ScheduleCommand.equalsTo(data.message().text())) {
			return new ScheduleCommand(bot);
		}

		return new UnknownCommand(bot);
	}
}
