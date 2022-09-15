package net.archasmiel.processing.factory;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import net.archasmiel.processing.signal.basic.Signal;

import java.util.List;

public interface IFactory<O extends Signal> {
	O fabricate(TelegramBot bot, Update data);
	List<String> getSpectre();
}
