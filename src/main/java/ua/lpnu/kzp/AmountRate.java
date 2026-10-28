package ua.lpnu.kzp;

public record AmountRate(double amount, double rate) {
    public AmountRate {
        if (amount < 0 || rate < 0) {
            throw new IllegalArgumentException("Сума та курс не можуть бути від'ємними");
        }
    }
}