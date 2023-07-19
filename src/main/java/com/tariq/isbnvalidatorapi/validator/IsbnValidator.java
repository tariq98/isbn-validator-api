package com.tariq.isbnvalidatorapi.validator;

import com.tariq.isbnvalidatorapi.exception.InvalidIsbnFormatException;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class IsbnValidator {
    public boolean validate(String isbn) {
        String error = validateIsbnFormat(isbn);
        if (!error.isEmpty()) {
            throw new InvalidIsbnFormatException(error);
        }
        return (isbn.length() == 10) ? validateIsbn10(isbn) : validateIsbn13(isbn);
    }

    private String validateIsbnFormat(String isbn) {
        if(isbn.length() != 10 && isbn.length() != 13) {
            return "ISBN has to be 10 or 13 characters";
        }
        if(isbn.length() == 10 && !isbn.matches("^[0-9]{9}[0-9X]$")) {
            return "ISBN10 can only contain digits 0-9 or 'X' at the end";
        }
        if(isbn.length() == 13 && !isbn.matches("^[0-9]+$")) {
            return "ISBN13 can only contain digits 0-9";
        }
        return "";
    }

    private boolean validateIsbn10(String isbn) {
        int sum = IntStream.range(0, 10)
                .map(i -> {
                    if(i == 9 && isbn.charAt(i) == 'X') {
                        return 10;
                    }
                    return (10 - i) * Character.getNumericValue(isbn.charAt(i));
                })
                .sum();
        return sum % 11 == 0;
    }

    private boolean validateIsbn13(String isbn) {
        int sum = IntStream.range(0, 13)
                .map(i -> {
                    if ((i + 1) % 2 == 0) {
                        return 3 * Character.getNumericValue(isbn.charAt(i));
                    }
                    return Character.getNumericValue(isbn.charAt(i));
                })
                .sum();
        return sum % 10 == 0;
    }
}
