package com.devexperto.testingexpert.data.local

import androidx.room.TypeConverter
import com.devexperto.testingexpert.domain.O
import com.devexperto.testingexpert.domain.Winner
import com.devexperto.testingexpert.domain.X
import java.util.*

class Converters {
    @TypeConverter
    fun fromWinner(winner: Winner): String {
        return winner.toString()
    }

    @TypeConverter
    fun toWinner(winner: String): Winner {
        return if (winner == "X") X else O
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(date: Long): Date {
        return Date(date)
    }
}