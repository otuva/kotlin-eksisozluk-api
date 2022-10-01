package com.github.otuva.eksisozluk.annotations

/**
 * Annotation for methods that provides limited functionality without login.
 * */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
public annotation class LimitedWithoutLogin
