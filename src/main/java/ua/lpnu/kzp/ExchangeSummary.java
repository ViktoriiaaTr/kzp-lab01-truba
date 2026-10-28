package ua.lpnu.kzp;

public record ExchangeSummary(
        int validCount,
        double totalEquivalent,
        double averageRate,
        int buyCount) {

    public ExchangeSummary {
        if (validCount < 0 || buyCount < 0 || totalEquivalent < 0 || averageRate < 0
                || !Double.isFinite(totalEquivalent) || !Double.isFinite(averageRate)) {
            throw new IllegalArgumentException("Показники не можуть бути від'ємними або нескінченними");
        }
    }
}