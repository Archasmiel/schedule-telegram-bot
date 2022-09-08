package net.archasmiel.processing.callback;

import com.pengrad.telegrambot.TelegramBot;
import net.archasmiel.processing.callback.advanced.FixedDayCallback;
import net.archasmiel.processing.callback.advanced.NearDayCallback;
import net.archasmiel.processing.callback.basic.Callback;
import net.archasmiel.processing.callback.error.UnknownCallback;

public class CallbackFactory {

	private static final CallbackFactory INSTANCE = new CallbackFactory();

	public Callback getCallback(TelegramBot bot, String callback) {

		if (NearDayCallback.compare(callback)) {
			return new NearDayCallback(bot);
		}
		if (FixedDayCallback.compare(callback)) {
			return new FixedDayCallback(bot);
		}

		return new UnknownCallback(bot);
	}

	public static CallbackFactory getInstance() {
		return INSTANCE;
	}

}
