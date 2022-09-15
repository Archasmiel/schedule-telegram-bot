package net.archasmiel.bot;

import com.pengrad.telegrambot.TelegramBot;
import net.archasmiel.bot.listener.BotListener;
import net.archasmiel.bot.model.UserList;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class Bot {

	public static final UserList users = new UserList();
	static {
		try {
			File file = new File("users.json");
			if (file.createNewFile()){
				FileWriter writer = new FileWriter(file);
				writer.append("{\n").append("}");
				writer.close();
			}

			InputStream is = new FileInputStream("users.json");
			JSONObject jsonObject = new JSONObject(new JSONTokener(is));
			jsonObject.keys().forEachRemaining(e -> users.add(e, jsonObject.getString(e)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
