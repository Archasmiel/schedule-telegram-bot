package net.archasmiel.processing.signal.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.processing.signal.basic.Signal;

public class ScheduleCommand extends Signal {

	public static final String COMMAND = "/schedule";

	public ScheduleCommand(TelegramBot bot) {
		super(bot);
	}

	public static boolean equalsTo(String command) {
		return command.equals(COMMAND);
	}

	@Override
	public void process(Update update) {
		bot.execute(
			new SendMessage(update.message().chat().id(), "Оберіть потрібний варіант:")
		);
	}

}
