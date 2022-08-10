package cz.cvut.popovma1.spacex.repository.database

import android.content.Context
import androidx.room.Room
import quanti.com.kotlinlog.Log

// tmp object
class RocketRoomDatabase(applicationContext: Context) {
    init {
        db?.let {
            Log.d("Database already initialised")
        } ?: run {
            Log.d("Initialising database...")
            db = Room.databaseBuilder(
                applicationContext,
                RocketDatabase::class.java,
                "rocketDatabase"
            ).build()
            Log.d("Database initialised")
        }
    }
    companion object {
        var db: RocketDatabase? = null
    }
}