package com.github.otuva.eksisozluk.annotations

/**
 * Annotation for methods that provides limited functionality without login.
 *
 * Things you can and cannot do are listed in the methods' documentation.
 * */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
public annotation class LimitedWithoutLogin
