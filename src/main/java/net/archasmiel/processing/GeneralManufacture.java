package net.archasmiel.processing;

import com.pengrad.telegrambot.model.Update;
import net.archasmiel.processing.factory.CallbackFactory;
import net.archasmiel.processing.factory.CommandFactory;
import net.archasmiel.processing.factory.GetFileFactory;
import net.archasmiel.processing.factory.IFactory;
import net.archasmiel.processing.signal.basic.Signal;

import java.util.ArrayList;
import java.util.List;

public class GeneralManufacture {

	public static final GeneralManufacture INSTANCE = new GeneralManufacture();

	private static final IFactory<Signal> CALLBACKS = CallbackFactory.getFactory();
	private static final IFactory<Signal> COMMANDS = CommandFactory.getFactory();
	private static final IFactory<Signal> FILES = GetFileFactory.getFactory();

	public IFactory<Signal> manufacture(Update update) {
		List<String> spectre = getSpectre(update);

		if (checkSpectre(spectre, CALLBACKS)) {
			return CALLBACKS;
		}
		if (checkSpectre(spectre, FILES)) {
			return FILES;
		}
		if (checkSpectre(spectre, COMMANDS)) {
			return COMMANDS;
		}

		throw new IllegalStateException("Not found factory spectre");
	}

	private boolean checkSpectre(List<String> spectre, IFactory<Signal> factory) {
		return spectre.containsAll(factory.getSpectre());
	}

	private static List<String> getSpectre(Update update) {
		List<String> res = new ArrayList<>();

		if (update.callbackQuery() != null) {
			res.add("c");
		} else
		if (update.message() != null) {
			res.add("m");
			if (update.message().document() != null) {
				res.add("d");
			}
		}

		return res;
	}

}
