package utils;

import java.util.List;

public class TableFormatter {
  public static String newTable(List<String> headers, List<List<String>> rows) {
    StringBuilder sb = new StringBuilder();

    // szerokosc koluimn
    int[] columnWidths = new int[headers.size()];
    for (int i = 0; i < headers.size(); i++) {
      columnWidths[i] = headers.get(i).length();
    }

    for (List<String> row : rows) {
      for (int i = 0; i < row.size(); i++) {
        columnWidths[i] = Math.max(columnWidths[i], row.get(i).length());
      }
    }

    // naglowek
    sb.append("  | ");
    for (int i = 0; i < headers.size(); i++) {
      sb.append(padRight(headers.get(i), columnWidths[i])).append(" | ");
    }
    sb.append("\n");

    // krecha
    sb.append("-".repeat(sb.length())).append("\n");

    // dane
    int index = 1;
    for (List<String> row : rows) {
      sb.append(index++).append(" | ");
      for (int i = 0; i < row.size(); i++) {
        sb.append(padRight(row.get(i), columnWidths[i])).append(" | ");
      }
      sb.append("\n");
    }

    return sb.toString();
  }

  private static String padRight(String text, int width) {
    return String.format("%-" + width + "s", text);
  }
}
