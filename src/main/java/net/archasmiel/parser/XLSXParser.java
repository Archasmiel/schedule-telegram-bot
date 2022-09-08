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

	public static SheetList readTableToList(TelegramBot bot, Document document) throws IOException {
		String url = bot.getFullFilePath(
			bot.execute(
				new GetFile(document.fileId())
			).file()
		);

		try (InputStream in = new URL(url).openStream()) {
			byte[] fileBytes = in.readAllBytes();
			float fileSizeMB = fileBytes.length / 1_048_576f;

			if (fileSizeMB < 1) {
				SheetList list = XLSXParser.byteTableToList(fileBytes);
				System.out.printf("Received table %s -> %f MB%n", document.fileName(), fileSizeMB);
				System.out.printf("%n%n%n");
				return list;
			}

			throw new IOException("File size is bigger than 1 MB");
		} catch (IOException e) {
			e.printStackTrace();
		}

		throw new IOException("Couldn't read file");
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
