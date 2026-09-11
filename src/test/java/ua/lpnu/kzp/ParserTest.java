package ua.lpnu.kzp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void parsesValidLine() {
        CurrencyRecord record = Parser.parseLine("USD;100.00;41.50;true");
        assertAll(
            () -> assertEquals("USD", record.getCurrency()),
            () -> assertEquals(100.00, record.getAmount(), 0.0001),
            () -> assertEquals(41.50, record.getRate(), 0.0001),
            () -> assertTrue(record.isBuy())
        );
    }

    @Test
    void parsesValidLineWithBuyFalse() {
        CurrencyRecord record = Parser.parseLine("EUR;50.00;45.00;false");
        assertFalse(record.isBuy());
    }

    @Test
    void parsesUkrainianCharacters() {
        CurrencyRecord record = Parser.parseLine("Гривня;200.00;1.00;true");
        assertEquals("Гривня", record.getCurrency());
    }

    @Test
    void rejectsEmptyLine() {
        assertThrows(IllegalArgumentException.class,
            () -> Parser.parseLine(""));
    }

    @Test
    void rejectsBlankLine() {
        assertThrows(IllegalArgumentException.class,
            () -> Parser.parseLine("   "));
    }

    @Test
    void rejectsWrongNumberOfFieldsTooFew() {
        assertThrows(IllegalArgumentException.class,
            () -> Parser.parseLine("USD;100.00;41.50"));
    }

    @Test
    void rejectsWrongNumberOfFieldsTooMany() {
        assertThrows(IllegalArgumentException.class,
            () -> Parser.parseLine("USD;100.00;41.50;true;extra"));
    }

    @Test
    void rejectsEmptyCurrency() {
        assertThrows(IllegalArgumentException.class,
            () -> Parser.parseLine(";100.00;41.50;true"));
    }

    @Test
    void rejectsNonNumericAmount() {
        assertThrows(IllegalArgumentException.class,
            () -> Parser.parseLine("USD;abc;41.50;true"));
    }

    @Test
    void rejectsNonNumericRate() {
        assertThrows(IllegalArgumentException.class,
            () -> Parser.parseLine("USD;100.00;xyz;true"));
    }

    @Test
    void rejectsNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
            () -> Parser.parseLine("USD;-50.00;41.50;true"));
    }

    @Test
    void rejectsNegativeRate() {
        assertThrows(IllegalArgumentException.class,
            () -> Parser.parseLine("USD;100.00;-41.50;true"));
    }

    @Test
    void rejectsZeroAmount() {
        assertThrows(IllegalArgumentException.class,
            () -> Parser.parseLine("USD;0.00;41.50;true"));
    }

    @Test
    void rejectsZeroRate() {
        assertThrows(IllegalArgumentException.class,
            () -> Parser.parseLine("USD;100.00;0.00;true"));
    }

    @Test
    void rejectsInvalidBuyValue() {
        assertThrows(IllegalArgumentException.class,
            () -> Parser.parseLine("USD;100.00;41.50;yes"));
    }
}