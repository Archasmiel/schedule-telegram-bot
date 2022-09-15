package net.archasmiel.processing.factory;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import net.archasmiel.processing.signal.basic.Signal;
import net.archasmiel.processing.signal.files.GetScheduleFile;
import net.archasmiel.processing.signal.files.UnknownFile;

import java.util.List;

public class GetFileFactory implements IFactory<Signal> {

	private static final IFactory<Signal> INSTANCE = new GetFileFactory();
	private static final List<String> SPECTRE = List.of("d");

	public static IFactory<Signal> getFactory() {
		return INSTANCE;
	}

	@Override
	public Signal fabricate(TelegramBot bot, Update update) {
		if (update.message().document().fileName().contains(".xlsx")) {
			return new GetScheduleFile(bot);
		}

		return new UnknownFile(bot);
	}

	@Override
	public List<String> getSpectre() {
		return SPECTRE;
	}
}
