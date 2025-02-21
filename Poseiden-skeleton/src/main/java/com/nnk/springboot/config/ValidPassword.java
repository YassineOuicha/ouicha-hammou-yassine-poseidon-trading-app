package com.nnk.springboot.config;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "The password must contain at least 8 characters, a capital letter, a number and a symbol";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
