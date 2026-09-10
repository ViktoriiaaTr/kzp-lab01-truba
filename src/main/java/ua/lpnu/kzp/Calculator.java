package ua.lpnu.kzp;

import java.util.List;

public final class Calculator {

    private Calculator() {
    }

    public static Statistics calculate(List<CurrencyRecord> records) {
        int validCount = records.size();

        if (validCount == 0) {
            return new Statistics(0, 0.0, 0.0, 0);
        }

        double totalEquivalent = 0.0;
        double sumRate = 0.0;
        int buyCount = 0;

        for (CurrencyRecord record : records) {
            totalEquivalent += record.getAmount() * record.getRate();
            sumRate += record.getRate();
            if (record.isBuy()) {
                buyCount++;
            }
        }

        double averageRate = sumRate / validCount;

        return new Statistics(validCount, totalEquivalent, averageRate, buyCount);
    }
}