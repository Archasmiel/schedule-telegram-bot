package net.archasmiel.parser;

import java.util.Arrays;
import java.util.List;

public class Schedule {

	public static class Builder {

		private Schedule schedule;

		public Builder() {
			schedule = new Schedule();
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

		public Schedule build() {
			if (schedule == null)
				throw new IllegalStateException("No schedule for build!");
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
