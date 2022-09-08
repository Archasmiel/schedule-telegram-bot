package net.archasmiel.processing.command.advanced;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.processing.command.basic.Command;

public class StartCommand extends Command {

	public static final String COMMAND = "/start";

	private static final InlineKeyboardButton[][] MARKUP = {
		{
			new InlineKeyboardButton("Сьогодні").callbackData("today"),
			new InlineKeyboardButton("Завтра").callbackData("tomorrow")
		},
		{
			new InlineKeyboardButton("Понеділок (тижд. 1)").callbackData("day1_1"),
			new InlineKeyboardButton("Понеділок (тижд. 2)").callbackData("day1_2")
		},
		{
			new InlineKeyboardButton("Вівторок (тижд. 1)").callbackData("day2_1"),
			new InlineKeyboardButton("Вівторок (тижд. 2)").callbackData("day2_2")
		},
		{
			new InlineKeyboardButton("Середа (тижд. 1)").callbackData("day3_1"),
			new InlineKeyboardButton("Середа (тижд. 2)").callbackData("day3_2")
		},
		{
			new InlineKeyboardButton("Четвер (тижд. 1)").callbackData("day4_1"),
			new InlineKeyboardButton("Четвер (тижд. 2)").callbackData("day4_2")
		},
		{
			new InlineKeyboardButton("П'ятниця (тижд. 1)").callbackData("day5_1"),
			new InlineKeyboardButton("П'ятниця (тижд. 2)").callbackData("day5_2")
		},
		{
			new InlineKeyboardButton("Субота (тижд. 1)").callbackData("day6_1"),
			new InlineKeyboardButton("Субота (тижд. 2)").callbackData("day6_2")
		},
	};

	public StartCommand(TelegramBot bot) {
		super(bot);
	}

	public static boolean equalsTo(String command) {
		return command.equals(COMMAND);
	}

	@Override
	public void process(Update update) {
		bot.execute(
			new SendMessage(
				update.message().chat().id(),
				"Оберіть потрібний варіант:"
			).replyMarkup(new InlineKeyboardMarkup(MARKUP))
		);
	}

}
