package com.devmike.gamepedia.Utils

fun String.getQueryKey(): Int? {
    return try { this.substringAfterLast("page=").substringBefore("&page_size").toInt() } catch (e: Exception) { null }
}

fun getRatingColor(score: Int): Long {
    return if (score > 80) {
        GREENCOLORHEX
    } else if (score > 40) {
        ORANGECOLORHEX
    } else if (score > 0) {
        REDCOLORHEX
    } else GRAYCOLORHEX
}

const val REDCOLORHEX = 0xFF000000
const val ORANGECOLORHEX = 0xFFFFA500
const val GREENCOLORHEX = 0xFF00FF00
const val GRAYCOLORHEX = 0xFF888888
