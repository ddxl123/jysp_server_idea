package com.example.demo.validator.validator;

import com.example.demo.validator.annotation.EmailValidAnnotation;
import org.hibernate.validator.internal.constraintvalidators.AbstractEmailValidator;
import org.hibernate.validator.internal.util.logging.Log;
import org.hibernate.validator.internal.util.logging.LoggerFactory;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Pattern;
import java.lang.invoke.MethodHandles;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

/**
 * 拷贝至 {@link org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator}
 * @author 10338
 */
public class EmailValidator extends AbstractEmailValidator<EmailValidAnnotation> {

    private static final Log LOG = LoggerFactory.make(MethodHandles.lookup());

    private java.util.regex.Pattern pattern;

    @Override
    public void initialize(EmailValidAnnotation emailAnnotation) {
        super.initialize(emailAnnotation);

        Pattern.Flag[] flags = emailAnnotation.flags();
        int intFlag = 0;
        for (Pattern.Flag flag : flags) {
            intFlag = intFlag | flag.getValue();
        }

        // we only apply the regexp if there is one to apply
        if (!".*".equals(emailAnnotation.regexp()) || emailAnnotation.flags().length > 0) {
            try {
                pattern = java.util.regex.Pattern.compile(emailAnnotation.regexp(), intFlag);
            } catch (PatternSyntaxException e) {
                throw LOG.getInvalidRegularExpressionException(e);
            }
        }
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isValid = super.isValid(value, context);
        if (pattern == null || !isValid) {
            return isValid;
        }

        Matcher m = pattern.matcher(value);
        return m.matches();
    }
}
