package ua.lpnu.kzp;

import java.util.List;

public final class Calculator {

    private Calculator() {
    }

    public static ExchangeSummary calculate(List<ExchangeOperation> records) {
        int validCount = records.size();

        if (validCount == 0) {
            return new ExchangeSummary(0, 0.0, 0.0, 0);
        }

        double totalEquivalent = 0.0;
        double sumRate = 0.0;
        int buyCount = 0;

        for (ExchangeOperation record : records) {
            totalEquivalent += record.getAmount() * record.getRate();
            sumRate += record.getRate();
            if (record.isBuy()) {
                buyCount++;
            }
        }

        double averageRate = sumRate / validCount;
        return new ExchangeSummary(validCount, totalEquivalent, averageRate, buyCount);
    }
}