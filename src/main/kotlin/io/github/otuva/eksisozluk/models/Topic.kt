package io.github.otuva.eksisozluk.models

data class Topic(
    val id: Int,
    val title: String,
    val entries: List<Entry>,
    val pageCount: Int,
    val pageSize: Int,
    val pageIndex: Int,
    val pinnedEntry: Entry?,
    val entryCounts: EntryCounts,
    val draftEntry:

)