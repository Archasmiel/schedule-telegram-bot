package net.archasmiel.bot.thread;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.response.GetFileResponse;
import net.archasmiel.parser.XLSXParser;
import net.archasmiel.parser.sheetwork.SheetList;
import net.archasmiel.processing.callback.CallbackFactory;
import net.archasmiel.processing.command.CommandFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class UpdateThread extends Thread {

	private final TelegramBot bot;
	private final Update update;

	private static final List<String> SCHEDULE_SENDED = List.of("m", "d", "x");
	private static final List<String> CALLBACK = List.of("c");
	private static final List<String> MESSAGE = List.of("m");

	public UpdateThread(TelegramBot bot, Update update) {
		this.bot = bot;
		this.update = update;
	}

	@Override
	public void run() {
		List<String> properties = updateProperties(update);

		if (properties.containsAll(SCHEDULE_SENDED)) {
			try {
				XLSXParser.readTableToList(bot, update.message().document());
			} catch (IOException e) {
				e.printStackTrace();
			}

			return;
		}

		if (properties.containsAll(MESSAGE)) {
			CommandFactory.getInstance().getCommand(bot, update.message().text()).process(update);
			return;
		}

		if (properties.containsAll(CALLBACK)) {
			CallbackQuery query = update.callbackQuery();
			CallbackFactory.getInstance().getCallback(bot, query.data()).process(query);
		}

	}

	private static List<String> updateProperties(Update update) {
		List<String> res = new ArrayList<>();

		if (update.message() != null) {
			res.add("m");
		}
		if (update.message().document() != null) {
			res.add("d");
		}
		if (update.message().document().fileName().contains(".xlsx")) {
			res.add("x");
		}
		if (update.callbackQuery() != null) {
			res.add("c");
		}

		return res;
	}

}
