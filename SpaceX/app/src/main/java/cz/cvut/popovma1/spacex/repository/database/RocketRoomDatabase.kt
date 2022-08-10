package cz.cvut.popovma1.spacex.repository.database

import android.content.Context
import androidx.room.Room
import quanti.com.kotlinlog.Log

// tmp object
class RocketRoomDatabase(applicationContext: Context) {
    init {
        if(db == null) {
            Log.d("Initialising database...")
            db = Room.databaseBuilder(
                applicationContext,
                RocketDatabase::class.java,
                "rocketDatabase"
            ).build()
            Log.d("Database initialised")
        } else {
            Log.d("Database already initialised")
        }
    }
    companion object {
        var db: RocketDatabase? = null
    }
}