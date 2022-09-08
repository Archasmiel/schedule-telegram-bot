package net.archasmiel.processing.callback.advanced;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.processing.callback.basic.Callback;

import java.util.List;

public class NearDayCallback extends Callback {

	private static final List<String> CALLBACKS = List.of("today", "tomorrow");

	public NearDayCallback(TelegramBot bot) {
		super(bot);
	}

	public static boolean compare(String callback) {
		return CALLBACKS.contains(callback);
	}

	@Override
	public void process(CallbackQuery callback) {
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
