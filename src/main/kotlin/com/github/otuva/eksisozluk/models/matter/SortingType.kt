package com.github.otuva.eksisozluk.models.matter

/**
 * Represents the sorting type of the matter.
 * */
public enum class SortingType(public val value: String) {
    Best("nice"),
    Hot("popular"),
    All(""),
    Recent("recent"),
}