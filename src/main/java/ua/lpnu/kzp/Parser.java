package ua.lpnu.kzp;

/**
 * Клас для розбору та валідації одного рядка CSV.
 */
public final class Parser {

    private Parser() {
    }

    /**
     * Розбирає один рядок CSV та повертає запис.
     *
     * @param line рядок у форматі currency;amount;rate;buy
     * @return об'єкт {@link CurrencyRecord}
     * @throws IllegalArgumentException якщо рядок некоректний
     */
    public static CurrencyRecord parseLine(String line) {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("порожній рядок");
        }

        String[] fields = line.split(";", -1);

        if (fields.length != 4) {
            throw new IllegalArgumentException(
                "очікується 4 поля, знайдено " + fields.length);
        }

        String currency = fields[0].trim();
        if (currency.isEmpty()) {
            throw new IllegalArgumentException("поле 'currency' порожнє");
        }

        double amount;
        try {
            amount = Double.parseDouble(fields[1].trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("поле 'amount' не є числом");
        }

        double rate;
        try {
            rate = Double.parseDouble(fields[2].trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("поле 'rate' не є числом");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("поле 'amount' має бути додатним");
        }

        if (rate <= 0) {
            throw new IllegalArgumentException("поле 'rate' має бути додатним");
        }

        String buyText = fields[3].trim();
        boolean buy;
        if ("true".equalsIgnoreCase(buyText)) {
            buy = true;
        } else if ("false".equalsIgnoreCase(buyText)) {
            buy = false;
        } else {
            throw new IllegalArgumentException("поле 'buy' має бути 'true' або 'false'");
        }

        return new CurrencyRecord(currency, amount, rate, buy);
    }
}