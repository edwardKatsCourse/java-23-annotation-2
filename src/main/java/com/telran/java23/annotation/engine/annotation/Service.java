package com.telran.java23.annotation.engine.annotation;

import com.telran.java23.annotation.user.UserService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {
        ElementType.TYPE
//        ,
//        ElementType.FIELD,
//        ElementType.CONSTRUCTOR,
//        ElementType.METHOD,
//        ElementType.PARAMETER,
//        ElementType.LOCAL_VARIABLE,
//        ElementType.ANNOTATION_TYPE
})
public @interface Service {

    String value() default "value";
}
