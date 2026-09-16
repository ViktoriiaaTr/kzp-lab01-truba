package ua.lpnu.kzp;

/**
 * Модель одного запису про валютну операцію.
 * <p> Містить код валюти, суму, курс та ознаку операції купівлі.</p>
 */
public class CurrencyRecord {

    private String currency;
    private double amount;
    private double rate;
    private boolean buy;

    /**
     * Створює новий запис.
     *
     * @param currency код валюти (не порожній)
     * @param amount   сума у валюті (додатна)
     * @param rate     курс обміну (додатний)
     * @param buy      {@code true} для купівлі, {@code false} для продажу
     */
    public CurrencyRecord(String currency, double amount, double rate, boolean buy) {
        this.currency = currency;
        this.amount = amount;
        this.rate = rate;
        this.buy = buy;
    }

    /**
     * Повертає код валюти.
     *
     * @return код валюти
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Встановлює код валюти.
     *
     * @param currency новий код валюти
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Повертає суму.
     *
     * @return сума
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Встановлює суму.
     *
     * @param amount нова сума
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Повертає курс.
     *
     * @return курс
     */
    public double getRate() {
        return rate;
    }

    /**
     * Встановлює курс.
     *
     * @param rate новий курс
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * Повертає ознаку операції купівлі.
     *
     * @return {@code true}, якщо це купівля
     */
    public boolean isBuy() {
        return buy;
    }

    /**
     * Встановлює ознаку операції купівлі.
     *
     * @param buy {@code true} для купівлі
     */
    public void setBuy(boolean buy) {
        this.buy = buy;
    }
}