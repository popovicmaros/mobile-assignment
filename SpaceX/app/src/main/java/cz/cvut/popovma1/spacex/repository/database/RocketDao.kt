package cz.cvut.popovma1.spacex.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.cvut.popovma1.spacex.repository.model.Rocket

@Dao
interface RocketDao {

/*
    @Query("SELECT * FROM rocket")
    fun getAllStream(): Flow<List<Rocket>>
*/

    @Query("SELECT * FROM rocket")
    suspend fun getAll(): List<Rocket>?

    @Query("SELECT * FROM rocket where id=:id")
    suspend fun getRocket(id: Int): Rocket?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(rockets: List<Rocket>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(rocket: Rocket)

    @Query("DELETE FROM rocket")
    suspend fun deleteAll()
}
