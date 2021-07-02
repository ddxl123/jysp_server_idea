package com.example.demo.validator.annotation;

import com.example.demo.validator.validator.NotBlankValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 拷贝至 {@link javax.validation.constraints.NotBlank}
 *
 * @author 10338
 */
@Documented
@Constraint(validatedBy = {NotBlankValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Inherited
public @interface NotBlankValidAnnotation {
    final String CODE = "5001";
    final String MESSAGE = "5001,邮箱不能为空！";

    String message() default CODE + MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
