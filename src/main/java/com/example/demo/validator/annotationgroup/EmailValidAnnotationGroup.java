package com.example.demo.validator.annotationgroup;

import com.example.demo.validator.annotation.EmailValidAnnotation;
import com.example.demo.validator.annotation.NotBlankValidAnnotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author 10338
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@EmailValidAnnotation // 邮箱验证-注解
@NotBlankValidAnnotation // 不能为空-注解
public @interface EmailValidAnnotationGroup {
    String a = null;

    final Class<EmailValidAnnotation> EMAIL_VALID_ANNOTATION = EmailValidAnnotation.class;
    final Class<NotBlankValidAnnotation> NOT_BLANK_VALID_ANNOTATION = NotBlankValidAnnotation.class;

//    final String EMAIL_GROUP_MESSAGE = EmailValidAnnotation.EMAIL_MESSAGE + "<br/>" + NotBlankValidAnnotation.NOT_BLANK_MESSAGE;

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
