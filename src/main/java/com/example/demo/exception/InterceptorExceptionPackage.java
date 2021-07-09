package com.example.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class InterceptorExceptionPackage extends BaseExceptionPackage {
    @Nullable
    InterceptorExceptionPackage incompleteURLException;

    public InterceptorExceptionPackage() {
    }

    public InterceptorExceptionPackage(String message) {
        super(message);
    }


    @NonNull
    public boolean hasException() {
        return incompleteURLException != null;
    }

    @NonNull
    public InterceptorExceptionPackage getExistException() {
        return Objects.requireNonNullElseGet(
                incompleteURLException,
                () -> new InterceptorExceptionPackage(unknownMessage()));
    }
}
