package ua.lpnu.kzp;

public class CurrencyRecord {
    private String currency;
    private double amount;
    private double rate;
    private boolean buy;

    public CurrencyRecord(String currency, double amount, double rate, boolean buy) {
        this.currency = currency;
        this.amount = amount;
        this.rate = rate;
        this.buy = buy;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isBuy() {
        return buy;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
    }
}