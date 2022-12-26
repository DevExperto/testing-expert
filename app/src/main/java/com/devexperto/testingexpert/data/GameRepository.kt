package com.devexperto.testingexpert.data

import androidx.room.*
import com.devexperto.testingexpert.domain.TicTacToe
import com.devexperto.testingexpert.domain.move
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GameDataSource {
    val game: Flow<TicTacToe>
    suspend fun saveMove(row: Int, column: Int)
    suspend fun reset()
}

class RoomGameDataSource(
    private val gameDao: GameDao
) : GameDataSource {

    override val game: Flow<TicTacToe>
        get() = gameDao.getGame().map { it.toTicTacToe() }

    override suspend fun saveMove(row: Int, column: Int) {
        gameDao.saveMove(MoveEntity(0, row, column))
    }

    override suspend fun reset() {
        gameDao.reset()
    }
}

private fun List<MoveEntity>.toTicTacToe(): TicTacToe {
    return fold(TicTacToe()) { acc, move ->
        acc.move(move.row, move.column)
    }
}

class GameRepository(private val localDataSource: GameDataSource) {
    val game: Flow<TicTacToe> = localDataSource.game

    suspend fun move(row: Int, column: Int) {
        localDataSource.saveMove(row, column)
    }

    suspend fun reset() {
        localDataSource.reset()
    }
}

@Entity
data class MoveEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val row: Int,
    val column: Int
)

@Dao
interface GameDao {

    @Query("SELECT * FROM MoveEntity")
    fun getGame(): Flow<List<MoveEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMove(move: MoveEntity)

    @Query("DELETE FROM MoveEntity")
    suspend fun reset()

}

@Database(entities = [MoveEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val gameDao: GameDao
}