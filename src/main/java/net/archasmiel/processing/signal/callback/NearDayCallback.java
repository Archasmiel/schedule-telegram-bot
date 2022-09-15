package net.archasmiel.processing.signal.callback;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.processing.signal.basic.Signal;

import java.util.List;

public class NearDayCallback extends Signal {

	private static final List<String> CALLBACKS = List.of("today", "tomorrow");

	public NearDayCallback(TelegramBot bot) {
		super(bot);
	}

	public static boolean compare(String callback) {
		return CALLBACKS.contains(callback);
	}

	@Override
	public void process(Update update) {
		CallbackQuery callback = update.callbackQuery();
		String baseMessage;

		if (callback.data().equals("today")) {
			baseMessage = "Розклад на сьогодні:";
			bot.execute(new SendMessage(callback.message().chat().id(), baseMessage));
			return;
		}
		if (callback.data().equals("tomorrow")) {
			baseMessage = "Розклад на завтра:";
			bot.execute(new SendMessage(callback.message().chat().id(), baseMessage));
			return;
		}

		throw new IllegalStateException("Wrong data for NearDayCallback");
	}
}
