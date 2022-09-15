package net.archasmiel.processing.signal.files;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Document;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.bot.Bot;
import net.archasmiel.processing.signal.basic.Signal;

public class GetScheduleFile extends Signal {

	public GetScheduleFile(TelegramBot bot) {
		super(bot);
	}

	@Override
	public synchronized void process(Update update) {
		Document document = update.message().document();
		Message message = update.message();

		if (document.fileSize() / 1_000_024f <= 0.65f) {
			String url = bot.getFullFilePath(
				bot.execute(
					new GetFile(document.fileId())
				).file()
			);

			Bot.users.add(message.from().id(), url);
			Bot.writeUsers();

			bot.execute(new SendMessage(
				message.chat().id(),
				"Новий файл графіка '" + document.fileName() + "' встановлено!"
			));

			System.out.println(Bot.users);
			return;
		}

		bot.execute(new SendMessage(
			message.chat().id(),
			"Розмір файлу перевищує 650 КБ. Файл графіка не встановлено."
		));
	}
}
