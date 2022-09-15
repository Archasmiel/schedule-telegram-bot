package net.archasmiel.processing.factory;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import net.archasmiel.processing.signal.basic.Signal;
import net.archasmiel.processing.signal.callback.FixedDayCallback;
import net.archasmiel.processing.signal.callback.NearDayCallback;
import net.archasmiel.processing.signal.callback.UnknownCallback;

import java.util.List;

public class CallbackFactory implements IFactory<Signal> {

	private static final IFactory<Signal> INSTANCE = new CallbackFactory();
	private static final List<String> SPECTRE = List.of("c");

	public static IFactory<Signal> getFactory() {
		return INSTANCE;
	}

	@Override
	public List<String> getSpectre() {
		return SPECTRE;
	}

	public Signal fabricate(TelegramBot bot, Update update) {
		String data = update.callbackQuery().data();

		if (NearDayCallback.compare(data)) {
			return new NearDayCallback(bot);
		}
		if (FixedDayCallback.compare(data)) {
			return new FixedDayCallback(bot);
		}

		return new UnknownCallback(bot);
	}

}
