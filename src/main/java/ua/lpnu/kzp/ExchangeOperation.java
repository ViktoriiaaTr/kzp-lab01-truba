package ua.lpnu.kzp;

import java.util.Locale;
import java.util.Objects;

public final class ExchangeOperation {
    private final String currency;
    private final double amount;
    private final double rate;
    private final boolean buy;

    public ExchangeOperation(String currency, double amount, double rate, boolean buy) {
        this.currency = Objects.requireNonNull(currency, "Валюта не може бути null");
        if (currency.isBlank()) {
            throw new IllegalArgumentException("Валюта не може бути порожньою");
        }
        if (amount < 0 || !Double.isFinite(amount)) {
            throw new IllegalArgumentException("Сума має бути скінченною і не від'ємною");
        }
        if (rate < 0 || !Double.isFinite(rate)) {
            throw new IllegalArgumentException("Курс має бути скінченним і не від'ємним");
        }
        this.amount = amount;
        this.rate = rate;
        this.buy = buy;
    }

    public static ExchangeOperation fromCsv(String line) {
        Objects.requireNonNull(line, "Рядок не може бути null");
        String[] fields = line.split(";", -1);
        if (fields.length != 4) {
            throw new IllegalArgumentException("Очікується 4 поля");
        }
        try {
            String currency = fields[0].trim();
            double amount = Double.parseDouble(fields[1].trim());
            double rate = Double.parseDouble(fields[2].trim());
            String buyText = fields[3].trim();
            
            boolean buy;
            if ("true".equalsIgnoreCase(buyText)) {
                buy = true;
            } else if ("false".equalsIgnoreCase(buyText)) {
                buy = false;
            } else {
                throw new IllegalArgumentException("Поле 'buy' має бути 'true' або 'false'");
            }
            return new ExchangeOperation(currency, amount, rate, buy);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Числове поле має помилковий формат", exception);
        }
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public double getRate() {
        return rate;
    }

    public boolean isBuy() {
        return buy;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%s: %.2f за курсом %.2f, купівля: %b", 
            currency, amount, rate, buy);
    }
}