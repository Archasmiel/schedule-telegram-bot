package net.archasmiel.processing.signal.basic;

import com.pengrad.telegrambot.model.Update;

public interface ISignal {
	void process(Update data);
}
