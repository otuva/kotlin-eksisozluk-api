package com.github.otuva.eksisozluk.annotations

/**
 * Variable annotation for routes that requires formatting.
 * (i.e. includes %s and useless without formatting)
 * */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.PROPERTY)
public annotation class RequiresFormatting
