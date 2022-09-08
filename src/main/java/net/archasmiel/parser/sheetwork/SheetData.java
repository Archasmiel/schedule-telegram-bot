package net.archasmiel.parser.sheetwork;

import java.util.ArrayList;
import java.util.List;

public class SheetData {

	public static class Builder {
		private final SheetData sheetData;

		public Builder() {
			sheetData = new SheetData();
		}

		public Builder name(String name) {
			sheetData.name = name;
			return this;
		}

		public Builder addRow(List<String> row)  {
			sheetData.data.add(row);
			return this;
		}

		public Builder addRow(String[] row)  {
			return this.addRow(List.of(row));
		}

		public SheetData build() {
			return sheetData;
		}
	}

	private String name;
	private final List<List<String>> data;

	public SheetData() {
		data = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<List<String>> getData() {
		return data;
	}
}
