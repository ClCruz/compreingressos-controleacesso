package com.compreingressos.controleacesso.interfaces;

import com.compreingressos.controleacesso.validator.CpfValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author edicarlos.barbosa
 */
@Constraint(validatedBy = CpfValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cpf {

    String message() default "Informe um CPF válido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
