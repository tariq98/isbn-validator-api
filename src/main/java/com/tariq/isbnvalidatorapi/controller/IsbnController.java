package com.tariq.isbnvalidatorapi.controller;

import com.tariq.isbnvalidatorapi.dto.ValidateIsbnResponse;
import com.tariq.isbnvalidatorapi.validator.IsbnValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/isbn")
public class IsbnController {
    private IsbnValidator isbnValidator;

    public IsbnController(IsbnValidator isbnValidator) {
        this.isbnValidator = isbnValidator;
    }

    @GetMapping("/validate/{isbn}")
    public ResponseEntity validate(@PathVariable String isbn) {
        boolean valid = isbnValidator.validate(isbn);
        return new ResponseEntity(new ValidateIsbnResponse(valid), HttpStatus.OK);
    }
}
