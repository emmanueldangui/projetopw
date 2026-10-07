package br.edu.utfpr.pb.pw44s.server.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "Esse nome de usuário já está em uso";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}