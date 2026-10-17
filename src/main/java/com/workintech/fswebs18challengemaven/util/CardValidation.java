package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import lombok.Data;
import org.springframework.http.HttpStatus;



@Data
public class CardValidation {
    public static <T>  void isNull(T value) {
        if (value == null) {
            throw new CardException("Id cannot be null",HttpStatus.BAD_REQUEST);
        }
    }

    public static void isIdValid(long id) {
        if (id <= 0) {
            throw new CardException("Id cannot be negative",HttpStatus.BAD_REQUEST);
        }
    }





}
