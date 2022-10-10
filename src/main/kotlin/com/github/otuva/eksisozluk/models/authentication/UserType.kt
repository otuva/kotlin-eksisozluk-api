package com.github.otuva.eksisozluk.models.authentication

/**
 * Represents user types.
 *
 * They're used to determine the user's privileges. (While calling api functions)
 * */
public enum class UserType {
    Anonymous,
    Regular
}