package com.github.otuva.eksisozluk.annotations

/**
 * Annotation for methods that requires login.
 * */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
public annotation class RequiresLogin