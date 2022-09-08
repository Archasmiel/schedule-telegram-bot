package net.archasmiel.processing.callback;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import net.archasmiel.processing.callback.advanced.FixedDayCallback;
import net.archasmiel.processing.callback.advanced.NearDayCallback;
import net.archasmiel.processing.callback.basic.Callback;
import net.archasmiel.processing.callback.error.UnknownCallback;

public class CallbackFactory {

	public static final CallbackFactory INSTANCE = new CallbackFactory();

	public Callback getCallback(TelegramBot bot, CallbackQuery query) {
		String data = query.data();

		if (NearDayCallback.compare(data)) {
			return new NearDayCallback(bot);
		}
		if (FixedDayCallback.compare(data)) {
			return new FixedDayCallback(bot);
		}

		return new UnknownCallback(bot);
	}

	public static CallbackFactory getInstance() {
		return INSTANCE;
	}

}
