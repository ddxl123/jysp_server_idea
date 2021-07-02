package com.example.demo.validator.validator;

import com.example.demo.validator.annotation.NotNullValidAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 拷贝至 {@link org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator}
 *
 * @author 10338
 */
public class NotNullValidator implements ConstraintValidator<NotNullValidAnnotation, Object> {

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        return object != null;
    }
}
