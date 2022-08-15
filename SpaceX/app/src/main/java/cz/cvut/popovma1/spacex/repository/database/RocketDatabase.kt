package cz.cvut.popovma1.spacex.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cz.cvut.popovma1.spacex.repository.model.Rocket

@Database(entities = [Rocket::class], version = 1)
@TypeConverters(Converters::class)
abstract class RocketDatabase : RoomDatabase() {

    abstract fun rocketDao(): RocketDao
}
