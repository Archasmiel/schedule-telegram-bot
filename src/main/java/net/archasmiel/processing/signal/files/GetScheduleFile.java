package net.archasmiel.processing.signal.files;

import com.pengrad.telegrambot.TelegramBot;
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
	public void process(Update update) {
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
	}
}
