package com.nolwendroid.musiccompare.utils

import java.util.*

class IncrementHashMap : HashMap<String, Int>() {
    val a = ""
    fun put(key: String) {
        put(key, -1)
    }

    override fun put(key: String, value: Int): Int? {
        return if (this.containsKey(key)) {
            val tempValue = this[key]
            if (tempValue != null) {
                super.put(key, tempValue + 1)
            } else {
                super.put(key, 1)
            }
        } else {
            super.put(key, 1)
        }
    }

    fun unmodifyWithSort(): Map<String, Int> {
        if (this.isNotEmpty()) {
            val associate = this.entries.sortedByDescending { it.value }.associate { it.toPair() }
            return Collections.unmodifiableMap(associate)
        } else {
            return this
        }
    }

    fun putWithIncrement(key: String, valueForBand: Int) {
        if (this.containsKey(key)) {
            var currentValue = this[key]
            if (currentValue != null) {
                currentValue += valueForBand
                put(key, currentValue)
            }
        } else {
            put(key, valueForBand)
        }
    }

}