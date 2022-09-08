package net.archasmiel.parser;

import java.util.Arrays;
import java.util.List;

public class ScheduleMessage {

	public static class Builder {
		private final ScheduleMessage schedule;

		public Builder() {
			schedule = new ScheduleMessage();
			schedule.text = new StringBuilder();
		}

		public Builder addLine(String line) {
			schedule.text.append(line);
			return this;
		}

		public Builder addLine(String line, char lastChar) {
			schedule.text.append(line).append(lastChar);
			return this;
		}

		public Builder addLines(String[] lines) {
			Arrays.stream(lines).forEach(this::addLine);
			return this;
		}

		public Builder addLines(String[] lines, char lastChar) {
			Arrays.stream(lines).forEach(line -> addLine(line, lastChar));
			return this;
		}

		public Builder addLines(List<String> lines) {
			lines.forEach(this::addLine);
			return this;
		}

		public Builder addLines(List<String> lines, char lastChar) {
			lines.forEach(line -> addLine(line, lastChar));
			return this;
		}

		public ScheduleMessage build() {
			if (schedule.text.length() == 0)
				throw new IllegalStateException("Schedule zero length!");
			return schedule;
		}
	}

	private StringBuilder text;

	public StringBuilder getText() {
		return text;
	}

}
