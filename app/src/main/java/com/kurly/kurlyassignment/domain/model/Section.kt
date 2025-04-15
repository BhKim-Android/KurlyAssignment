package com.kurly.kurlyassignment.domain.model

data class Section(
    val title: String,
    val id: Int,
    val type: SectionType,
    val url: String
)

enum class SectionType {
    HORIZONTAL, GRID, VERTICAL;

    companion object {
        fun from(value: String): SectionType = when(value) {
            "horizontal" -> HORIZONTAL
            "grid" -> GRID
            else -> VERTICAL
        }
    }
}