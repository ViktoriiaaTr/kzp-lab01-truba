package ua.lpnu.kzp;

public class Statistics {
    private int validCount;             /* кількість коректних записів */
    private double totalEquivalent;     /* сумарний еквівалент */
    private double averageRate;         /* середній курс */
    private int buyCount;               /* к-сть операцій купівлі */

    public Statistics(int validCount, double totalEquivalent, double averageRate, int buyCount) {
        this.validCount = validCount;
        this.totalEquivalent = totalEquivalent;
        this.averageRate = averageRate;
        this.buyCount = buyCount;
    }

    public int getValidCount() {
        return validCount;
    }

    public void setValidCount(int validCount) {
        this.validCount = validCount;
    }

    public double getTotalEquivalent() {
        return totalEquivalent;
    }

    public void setTotalEquivalent(double totalEquivalent) {
        this.totalEquivalent = totalEquivalent;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }
}