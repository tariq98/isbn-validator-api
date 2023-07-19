package com.tariq.isbnvalidatorapi.validator;

import com.tariq.isbnvalidatorapi.exception.InvalidIsbnFormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IsbnValidatorTest {
    private IsbnValidator isbnValidator;

    public IsbnValidatorTest() {
        this.isbnValidator = new IsbnValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "9185057819",
            "8544976158",
            "7260904836",
            "043942089X"
    })
    public void validateReturnsTrueWhenIsbn10IsValid(String isbn) {
        boolean result = isbnValidator.validate(isbn);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "9182222811",
            "8544274158",
            "6260304836",
            "0439420892"
    })
    public void validateReturnsFalseWhenIsbn10IsInValid(String isbn) {
        boolean result = isbnValidator.validate(isbn);
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "9783161484100",
            "9796862825875",
            "9790111381006",
            "9792366196114"
    })
    public void validateReturnsTrueWhenIsbn13IsValid(String isbn) {
        boolean result = isbnValidator.validate(isbn);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "5712343334100",
            "9796811111115",
            "8742611281006",
            "9772643561114"
    })
    public void validateReturnsFalseWhenIsbn13IsFalse(String isbn) {
        boolean result = isbnValidator.validate(isbn);
        assertFalse(result);
    }

    @Test
    public void validateThrowsExceptionIfIsbnContainsInvalidCharacter() {
        String isbn = "918+057819";
        assertThrows(InvalidIsbnFormatException.class, () ->
                isbnValidator.validate(isbn));
    }

    @Test
    public void validateThrowsExceptionIfInvalidIsbnFormat() {
        String isbn = "571234333410021212";
        assertThrows(InvalidIsbnFormatException.class, () ->
                isbnValidator.validate(isbn));
    }
}
