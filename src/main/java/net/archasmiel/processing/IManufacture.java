package net.archasmiel.processing;

import com.pengrad.telegrambot.model.Update;
import net.archasmiel.processing.factory.IFactory;
import net.archasmiel.processing.signal.basic.Signal;

public interface IManufacture {
	IFactory<Signal> manufacture(Update update);
}
