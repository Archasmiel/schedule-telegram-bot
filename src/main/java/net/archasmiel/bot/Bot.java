package net.archasmiel.bot;

import com.pengrad.telegrambot.TelegramBot;
import net.archasmiel.bot.listener.BotListener;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class Bot {

	public static void main(String[] args) {
		TelegramBot bot = new TelegramBot(getToken());
		bot.setUpdatesListener(new BotListener(bot));
	}

	private static String getToken() {
		InputStream is = Bot.class.getResourceAsStream("/settings.json");
		if (is == null) throw new IllegalStateException("Token read exception!");

		return new JSONObject(new JSONTokener(is)).getString("token");
	}

}
