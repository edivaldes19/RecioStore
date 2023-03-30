package com.edival.reciostore.domain.util

fun <T> isListEqual(first: List<T>, second: List<T>): Boolean {
    if (first.size != second.size) return false
    return first.zip(second).all { (x, y) -> x == y }
}