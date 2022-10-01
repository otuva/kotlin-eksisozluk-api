package com.github.otuva.eksisozluk.annotations

/**
 * Annotation for methods that modifies internal state of the class.
 * */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
public annotation class ModifiesInternal
