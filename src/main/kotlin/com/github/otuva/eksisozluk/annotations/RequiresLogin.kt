package com.github.otuva.eksisozluk.annotations

/**
 * Annotation for methods that requires login.
 * Methods should check if the user is logged in or not.
 *
 * @throws [com.github.otuva.eksisozluk.NotAuthorizedException] if the user is not logged in.
 * */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.CLASS)
public annotation class RequiresLogin