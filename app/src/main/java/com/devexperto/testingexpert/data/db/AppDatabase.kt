package com.devexperto.testingexpert.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Database(entities = [MoveEntity::class, ScoreEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val gameDao: GameDao
    abstract val scoreDao: ScoreDao
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

@Dao
interface ScoreDao {

    @Query("SELECT * FROM ScoreEntity")
    fun getAll(): Flow<List<ScoreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(score: ScoreEntity)

}