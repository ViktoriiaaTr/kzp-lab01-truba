package ua.lpnu.kzp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void returnsZerosForEmptyList() {
        Statistics stats = Calculator.calculate(List.of());
        assertAll(
            () -> assertEquals(0, stats.getValidCount()),
            () -> assertEquals(0.0, stats.getTotalEquivalent(), 0.0001),
            () -> assertEquals(0.0, stats.getAverageRate(), 0.0001),
            () -> assertEquals(0, stats.getBuyCount())
        );
    }

    @Test
    void calculatesSingleRecord() {
        CurrencyRecord record = new CurrencyRecord("USD", 100.00, 41.50, true);
        Statistics stats = Calculator.calculate(List.of(record));
        assertAll(
            () -> assertEquals(1, stats.getValidCount()),
            () -> assertEquals(4150.00, stats.getTotalEquivalent(), 0.0001),
            () -> assertEquals(41.50, stats.getAverageRate(), 0.0001),
            () -> assertEquals(1, stats.getBuyCount())
        );
    }

    @Test
    void calculatesMultipleRecords() {
        CurrencyRecord r1 = new CurrencyRecord("USD", 100.00, 41.50, true);
        CurrencyRecord r2 = new CurrencyRecord("EUR", 50.00, 45.00, false);
        CurrencyRecord r3 = new CurrencyRecord("Гривня", 200.00, 1.00, true);
        Statistics stats = Calculator.calculate(List.of(r1, r2, r3));
        assertAll(
            () -> assertEquals(3, stats.getValidCount()),
            () -> assertEquals(6600.00, stats.getTotalEquivalent(), 0.0001),
            () -> assertEquals(29.1666, stats.getAverageRate(), 0.001),
            () -> assertEquals(2, stats.getBuyCount())
        );
    }

    @Test
    void averageRateUsesDoubleDivision() {
        CurrencyRecord r1 = new CurrencyRecord("USD", 100.00, 41.50, true);
        CurrencyRecord r2 = new CurrencyRecord("EUR", 50.00, 45.00, false);
        Statistics stats = Calculator.calculate(List.of(r1, r2));
        assertEquals(43.25, stats.getAverageRate(), 0.0001);
    }

    @Test
    void buyCountIgnoresFalse() {
        CurrencyRecord r1 = new CurrencyRecord("USD", 100.00, 41.50, false);
        CurrencyRecord r2 = new CurrencyRecord("EUR", 50.00, 45.00, false);
        Statistics stats = Calculator.calculate(List.of(r1, r2));
        assertEquals(0, stats.getBuyCount());
    }
}