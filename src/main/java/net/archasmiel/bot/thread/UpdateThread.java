package net.archasmiel.bot.thread;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.bot.Bot;
import net.archasmiel.parser.XLSXParser;
import net.archasmiel.processing.callback.CallbackFactory;
import net.archasmiel.processing.command.CommandFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateThread extends Thread {

	private final TelegramBot bot;
	private final Update update;

	private static final List<String> SCHEDULE_SENDED = List.of("m", "d", "x");
	private static final List<String> CALLBACK = List.of("c");
	private static final List<String> MESSAGE = List.of("m");

	public UpdateThread(TelegramBot bot, Update update) {
		this.bot = bot;
		this.update = update;
	}

	@Override
	public void run() {
		List<String> properties = updateProperties(update);

		if (properties.containsAll(SCHEDULE_SENDED)) {
			String url = bot.getFullFilePath(
				bot.execute(
					new GetFile(update.message().document().fileId())
				).file()
			);
			Bot.users.add(String.valueOf(update.message().from().id()), url);
			bot.execute(new SendMessage(
				update.message().chat().id(),
				"Новий файл графіка '" + update.message().document().fileName() + "' встановлено!"
			));
			System.out.println(Bot.users);

			return;
		}

		if (properties.containsAll(MESSAGE)) {
			CommandFactory.INSTANCE.getCommand(bot, update).process(update);
			return;
		}

		if (properties.containsAll(CALLBACK)) {
			CallbackFactory.INSTANCE.getCallback(bot, update.callbackQuery())
				.process(update.callbackQuery());
		}

	}

	private static List<String> updateProperties(Update update) {
		List<String> res = new ArrayList<>();

		if (update.message() != null) {
			res.add("m");
			if (update.message().document() != null) {
				res.add("d");
				if (update.message().document().fileName().contains(".xlsx")) {
					res.add("x");
				}
			}
		}
		if (update.callbackQuery() != null) {
			res.add("c");
		}

		return res;
	}

}
