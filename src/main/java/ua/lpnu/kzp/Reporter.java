package ua.lpnu.kzp;

import java.util.List;
import java.util.Locale;

public final class Reporter {

    private Reporter() {
    }

    public static String formatReport(Statistics stats, List<String> errors) {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format(Locale.ROOT, "Коректних записів: %d%n", stats.getValidCount()));
        builder.append(String.format(Locale.ROOT, "Сумарний еквівалент: %.2f грн%n", stats.getTotalEquivalent()));
        builder.append(String.format(Locale.ROOT, "Середній курс: %.2f%n", stats.getAverageRate()));
        builder.append(String.format(Locale.ROOT, "Операцій купівлі: %d%n", stats.getBuyCount()));
        builder.append(String.format(Locale.ROOT, "Помилок: %d%n", errors.size()));

        for (String error : errors) {
            builder.append(error).append(System.lineSeparator());
        }

        return builder.toString();
    }
}