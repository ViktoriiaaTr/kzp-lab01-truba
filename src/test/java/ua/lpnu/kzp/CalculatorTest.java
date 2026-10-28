package ua.lpnu.kzp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void returnsZerosForEmptyList() {
        ExchangeSummary stats = Calculator.calculate(List.of());
        assertAll(
            () -> assertEquals(0, stats.validCount()),
            () -> assertEquals(0.0, stats.totalEquivalent(), 0.0001),
            () -> assertEquals(0.0, stats.averageRate(), 0.0001),
            () -> assertEquals(0, stats.buyCount())
        );
    }

    @Test
    void calculatesSingleRecord() {
        ExchangeOperation record = new ExchangeOperation("USD", 100.00, 41.50, true);
        ExchangeSummary stats = Calculator.calculate(List.of(record));
        assertAll(
            () -> assertEquals(1, stats.validCount()),
            () -> assertEquals(4150.00, stats.totalEquivalent(), 0.0001),
            () -> assertEquals(41.50, stats.averageRate(), 0.0001),
            () -> assertEquals(1, stats.buyCount())
        );
    }

    @Test
    void calculatesMultipleRecords() {
        ExchangeOperation r1 = new ExchangeOperation("USD", 100.00, 41.50, true);
        ExchangeOperation r2 = new ExchangeOperation("EUR", 50.00, 45.00, false);
        ExchangeOperation r3 = new ExchangeOperation("Гривня", 200.00, 1.00, true);
        ExchangeSummary stats = Calculator.calculate(List.of(r1, r2, r3));
        assertAll(
            () -> assertEquals(3, stats.validCount()),
            () -> assertEquals(6600.00, stats.totalEquivalent(), 0.0001),
            () -> assertEquals(29.1666, stats.averageRate(), 0.001),
            () -> assertEquals(2, stats.buyCount())
        );
    }

    @Test
    void averageRateUsesDoubleDivision() {
        ExchangeOperation r1 = new ExchangeOperation("USD", 100.00, 41.50, true);
        ExchangeOperation r2 = new ExchangeOperation("EUR", 50.00, 45.00, false);
        ExchangeSummary stats = Calculator.calculate(List.of(r1, r2));
        assertEquals(43.25, stats.averageRate(), 0.0001);
    }

    @Test
    void buyCountIgnoresFalse() {
        ExchangeOperation r1 = new ExchangeOperation("USD", 100.00, 41.50, false);
        ExchangeOperation r2 = new ExchangeOperation("EUR", 50.00, 45.00, false);
        ExchangeSummary stats = Calculator.calculate(List.of(r1, r2));
        assertEquals(0, stats.buyCount());
    }
}