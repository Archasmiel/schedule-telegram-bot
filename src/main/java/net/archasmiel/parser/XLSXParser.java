package net.archasmiel.parser;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Document;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import net.archasmiel.parser.sheetwork.SheetList;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class XLSXParser {

	private XLSXParser() {

	}

	public static byte[] fileBytes(String url) throws IOException {
		try (InputStream in = new URL(url).openStream()) {
			return in.readAllBytes();
		} catch (IOException e) {
			throw new IOException("Couldn't read file");
		}
	}

	public static float fileSizeMB(byte[] bytes) {
		return bytes.length/1024000f;
	}

	public static boolean isValidSize(byte[] bytes) {
		return fileSizeMB(bytes) <= 1f;
	}

	public static SheetList readTableToList(TelegramBot bot, long chatID, String url) throws IOException {
		byte[] fileBytes = fileBytes(url);
		if (isValidSize(fileBytes)) {
			return byteTableToList(fileBytes);
		}

		bot.execute(new SendMessage(chatID, "Помилка, розмір файлу не повинен перевищувати 1 МБ."));
		throw new IllegalStateException("File size is bigger than 1 MB");
	}

	public static SheetList byteTableToList(byte[] fileBytes) {
		SheetList.Builder sheetList = new SheetList.Builder();

		try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(fileBytes))) {
			workbook.sheetIterator().forEachRemaining(sheetList::addSheet);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sheetList.build().getSheets().forEach(sheet ->
			System.out.println(sheet.getData())
		);
		return sheetList.build();
	}

}
