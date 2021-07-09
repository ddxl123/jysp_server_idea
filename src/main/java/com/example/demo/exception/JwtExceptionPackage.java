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
public class JwtExceptionPackage extends BaseExceptionPackage {

    @Nullable
    JwtExceptionPackage expiredException;

    @Nullable
    JwtExceptionPackage decodeException;

    @Nullable
    JwtExceptionPackage otherException;

    @Nullable
    JwtExceptionPackage generateTokenException;

    public JwtExceptionPackage() {
    }

    public JwtExceptionPackage(String message) {
        super(message);
    }

    public JwtExceptionPackage(Throwable throwable) {
        super(throwable);
    }

    @NonNull
    public boolean hasVerifyException() {
        return expiredException != null || decodeException != null || otherException != null;
    }

    @NonNull
    public JwtExceptionPackage getExistVerifyException() {
        return Objects.requireNonNullElseGet(
                expiredException,
                () -> Objects.requireNonNullElseGet(
                        decodeException,
                        () -> Objects.requireNonNullElseGet(
                                otherException,
                                () -> new JwtExceptionPackage(unknownMessage()))));
    }

    @NonNull
    public boolean hasGenerateException() {
        return generateTokenException != null;
    }

    @NonNull
    public JwtExceptionPackage getExistGenerateException() {
        return Objects.requireNonNullElseGet(
                generateTokenException,
                () -> new JwtExceptionPackage(unknownMessage()));
    }
}
