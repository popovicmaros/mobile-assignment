package cz.cvut.popovma1.spacex.repository.database

import androidx.room.*
import cz.cvut.popovma1.spacex.repository.model.Rocket
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {

    @Query("SELECT * FROM rocket")
    fun getAll(): Flow<List<Rocket>>

    @Query("SELECT * FROM rocket")
    suspend fun getAllRockets(): List<Rocket>

    @Query("SELECT * FROM rocket where id=:id")
    suspend fun getRocket(id: Int): Rocket

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(rockets: List<Rocket>)

    @Query("DELETE FROM rocket")
    suspend fun deleteAll()

}