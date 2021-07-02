package com.example.demo.validator.validator;

import com.example.demo.validator.annotation.NotBlankValidAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 拷贝至 {@link org.hibernate.validator.internal.constraintvalidators.bv.NotBlankValidator}
 *
 * @author 10338
 */
public class NotBlankValidator implements ConstraintValidator<NotBlankValidAnnotation, CharSequence> {

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null) {
            return false;
        }

        return charSequence.toString().trim().length() > 0;
    }
}
