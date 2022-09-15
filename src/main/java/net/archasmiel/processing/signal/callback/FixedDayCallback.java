package net.archasmiel.processing.signal.callback;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.processing.signal.basic.Signal;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class FixedDayCallback extends Signal {

	private static final List<String> CALLBACKS = List.of(
		"day1_1", "day1_2",
		"day2_1", "day2_2",
		"day3_1", "day3_2",
		"day4_1", "day4_2",
		"day5_1", "day5_2",
		"day6_1", "day6_2"
	);
	private static final Map<String, String> DAYS = Map.ofEntries(
		new AbstractMap.SimpleEntry<>("day1", "понеділок"),
		new AbstractMap.SimpleEntry<>("day2", "вівторок"),
		new AbstractMap.SimpleEntry<>("day3", "середу"),
		new AbstractMap.SimpleEntry<>("day4", "четвер"),
		new AbstractMap.SimpleEntry<>("day5", "п'ятницю"),
		new AbstractMap.SimpleEntry<>("day6", "суботу")
	);
	private static final Map<String, String> NUMS = Map.ofEntries(
		new AbstractMap.SimpleEntry<>("1", "перший"),
		new AbstractMap.SimpleEntry<>("2", "другий")
	);

	public FixedDayCallback(TelegramBot bot) {
		super(bot);
	}

	public static boolean compare(String callback) {
		return CALLBACKS.contains(callback);
	}

	@Override
	public void process(Update update) {
		CallbackQuery callback = update.callbackQuery();
		String[] split = callback.data().split("_");

		if (split.length != 2 || !DAYS.containsKey(split[0]) || !NUMS.containsKey(split[1])) {
			throw new IllegalStateException("Wrong data for FixedDayCallback");
		}

		String baseMessage = String.format("Розклад на %s (%s тиждень):", DAYS.get(split[0]), NUMS.get(split[1]));
		bot.execute(new SendMessage(callback.message().chat().id(), baseMessage));
	}

}
