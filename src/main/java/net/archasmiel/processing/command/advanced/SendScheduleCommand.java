package net.archasmiel.processing.command.advanced;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.processing.command.basic.Command;

public class SendScheduleCommand extends Command {

	public static final String PREFIX = "/";
	public static final String NAME = "send_schedule";

	public SendScheduleCommand(TelegramBot bot) {
		super(bot);
	}

	public static boolean equalsTo(String prefix, String name) {
		return prefix.equals(PREFIX) && name.equals(NAME);
	}

	@Override
	public void process(Update update) {
		bot.execute(
			new SendMessage(update.message().chat().id(), "Оберіть потрібний варіант:")
		);
	}

}
