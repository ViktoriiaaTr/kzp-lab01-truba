package ua.lpnu.kzp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ExchangeOperationTest {

    @Test
    void fromCsvCreatesValidEntity() {
        ExchangeOperation op = ExchangeOperation.fromCsv("USD;100.00;41.50;true");
        assertAll(
            () -> assertEquals("USD", op.getCurrency()),
            () -> assertEquals(100.00, op.getAmount(), 0.0001),
            () -> assertEquals(41.50, op.getRate(), 0.0001),
            () -> assertTrue(op.isBuy())
        );
    }

    @Test
    void constructorRejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
            () -> new ExchangeOperation("USD", -50.0, 41.5, true));
    }

    @Test
    void constructorRejectsNullCurrency() {
        assertThrows(NullPointerException.class,
            () -> new ExchangeOperation(null, 100.0, 41.5, true));
    }

    @Test
    void constructorRejectsBlankCurrency() {
        assertThrows(IllegalArgumentException.class,
            () -> new ExchangeOperation("   ", 100.0, 41.5, true));
    }

    @Test
    void fromCsvRejectsInvalidNumber() {
        assertThrows(IllegalArgumentException.class,
            () -> ExchangeOperation.fromCsv("USD;abc;41.50;true"));
    }

    @Test
    void recordUsesValueEquality() {
        AmountRate first = new AmountRate(100.0, 41.5);
        AmountRate second = new AmountRate(100.0, 41.5);
        assertEquals(first, second);
    }
}