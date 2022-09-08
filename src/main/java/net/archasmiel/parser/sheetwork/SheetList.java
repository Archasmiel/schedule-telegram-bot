package net.archasmiel.parser.sheetwork;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class SheetList {

	public static class Builder {
		private final SheetList list;

		public Builder() {
			list = new SheetList();
		}

		public Builder addSheet(Sheet sheet) {
			SheetData.Builder data = new SheetData.Builder();
			sheet.forEach(row -> {
					List<String> rowData = new ArrayList<>();
					row.forEach(cell -> {
						switch (cell.getCellType()) {
							case BLANK -> rowData.add("");
							case STRING, BOOLEAN, NUMERIC, FORMULA -> rowData.add(cell.toString());
							default -> throw new IllegalStateException("Unexpected value: " + cell.getCellType());
						}
					});
					data.addRow(rowData);
				}
			);
			return this.addSheet(data.name(sheet.getSheetName()).build());
		}

		public Builder addSheet(SheetData data) {
			list.sheets.add(data);
			return this;
		}

		public SheetList build() {
			return list;
		}

	}

	private List<SheetData> sheets;

	public SheetList() {
		sheets = new ArrayList<>();
	}

	public List<SheetData> getSheets() {
		return sheets;
	}
}
