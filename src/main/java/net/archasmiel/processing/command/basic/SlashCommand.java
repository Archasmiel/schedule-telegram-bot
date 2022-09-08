package net.archasmiel.processing.command.basic;

import com.pengrad.telegrambot.TelegramBot;

public abstract class SlashCommand extends Command {

	protected SlashCommand(TelegramBot bot) {
		super(bot);
	}

}
