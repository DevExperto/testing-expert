package com.devexperto.testingexpert.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Database(entities = [MoveEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val gameDao: GameDao
}

@Dao
interface GameDao {

    @Query("SELECT * FROM MoveEntity")
    fun getGame(): Flow<List<MoveEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMove(move: MoveEntity)

    @Query("DELETE FROM MoveEntity")
    suspend fun reset()

}