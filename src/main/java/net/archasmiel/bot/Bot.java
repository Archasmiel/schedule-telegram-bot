package net.archasmiel.bot;

import com.pengrad.telegrambot.TelegramBot;
import net.archasmiel.bot.listener.BotListener;
import net.archasmiel.bot.model.UserData;
import net.archasmiel.bot.model.UserList;
import net.archasmiel.parser.json.BeautifulJson;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Bot {

	public static final UserList users = new UserList();

	public static void main(String[] args) {
		readUsers();
		TelegramBot bot = new TelegramBot(getToken());
		bot.setUpdatesListener(new BotListener(bot));
	}

	private static String getToken() {
		InputStream is = Bot.class.getResourceAsStream("/settings.json");
		if (is == null) throw new IllegalStateException("Token read exception!");
		return new JSONObject(new JSONTokener(is)).getString("token");
	}

	public static synchronized void readUsers() {
		try {
			File file = new File("users.json");
			if (file.createNewFile()){
				try (FileWriter writer = new FileWriter(file)) {
					writer.append("{\n").append("}");
				}
			}

			try (FileInputStream is = new FileInputStream(file)) {
				JSONObject jsonObject = new JSONObject(new JSONTokener(is));
				jsonObject.keys().forEachRemaining(e ->
					users.add(
						e,
						jsonObject.getJSONObject(e).getString("url"),
						jsonObject.getJSONObject(e).getJSONArray("restricted").toList()
							.stream().map(String.class::cast).collect(Collectors.toCollection(ArrayList::new))
					)
				);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized void writeUsers() {
		try {
			File file = new File("users.json");
			if (file.createNewFile()){
				try (FileWriter writer = new FileWriter(file)) {
					writer.append("{\n").append("}");
				}
			}


			try (FileOutputStream os = new FileOutputStream(file)) {
				StringWriter writer = new StringWriter();
				JSONWriter jsonWriter = new JSONWriter(writer);
				jsonWriter.object();

				Map<String, UserData> data = users.getUsers();
				for (Map.Entry<String, UserData> entry: data.entrySet()) {
					jsonWriter
						.key(entry.getKey())
						.object()
						.key("url").value(entry.getValue().getURL())
						.key("restricted").value(entry.getValue().getRestricted())
						.endObject();
				}
				jsonWriter.endObject();
				os.write(BeautifulJson.beautiful(writer.toString()).getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
