package ua.lpnu.kzp;

/**
 * Клас-модель для зберігання чотирьох обчислених показників.
 * <p>Використовується для передачі результатів обчислень від {@link Calculator}
 * до {@link Reporter}.</p>
 */
public class Statistics {

    /** Кількість коректних записів. */
    private int validCount;

    /** Сумарний еквівалент у гривнях. */
    private double totalEquivalent;

    /** Середній курс. */
    private double averageRate;

    /** Кількість операцій купівлі. */
    private int buyCount;

    /**
     * Створює новий об'єкт з обчисленими показниками.
     *
     * @param validCount      кількість коректних записів
     * @param totalEquivalent сумарний еквівалент у гривнях
     * @param averageRate     середній курс
     * @param buyCount        кількість операцій купівлі
     */
    public Statistics(int validCount, double totalEquivalent, double averageRate, int buyCount) {
        this.validCount = validCount;
        this.totalEquivalent = totalEquivalent;
        this.averageRate = averageRate;
        this.buyCount = buyCount;
    }

    /**
     * Повертає кількість коректних записів.
     *
     * @return кількість коректних записів
     */
    public int getValidCount() {
        return validCount;
    }

    /**
     * Встановлює кількість коректних записів.
     *
     * @param validCount нова кількість коректних записів
     */
    public void setValidCount(int validCount) {
        this.validCount = validCount;
    }

    /**
     * Повертає сумарний еквівалент у гривні.
     *
     * @return сумарний еквівалент
     */
    public double getTotalEquivalent() {
        return totalEquivalent;
    }

    /**
     * Встановлює сумарний еквівалент у гривні.
     *
     * @param totalEquivalent новий сумарний еквівалент
     */
    public void setTotalEquivalent(double totalEquivalent) {
        this.totalEquivalent = totalEquivalent;
    }

    /**
     * Повертає середній курс.
     *
     * @return середній курс
     */
    public double getAverageRate() {
        return averageRate;
    }

    /**
     * Встановлює середній курс.
     *
     * @param averageRate новий середній курс
     */
    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    /**
     * Повертає кількість операцій купівлі.
     *
     * @return кількість операцій купівлі
     */
    public int getBuyCount() {
        return buyCount;
    }

    /**
     * Встановлює кількість операцій купівлі.
     *
     * @param buyCount нова кількість операцій купівлі
     */
    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }
}