package com.github.otuva.eksisozluk.models.search

public enum class TermType(public val value: String) {
    Text(""),
    Entry("%23"),
    Author("@"),
}