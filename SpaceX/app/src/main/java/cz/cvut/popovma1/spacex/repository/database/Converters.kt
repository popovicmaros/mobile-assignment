package cz.cvut.popovma1.spacex.repository.database

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import cz.cvut.popovma1.spacex.repository.model.Stage

class Converters {
    private val moshi: Moshi = Moshi.Builder().build()
    private val stageAdapter: JsonAdapter<Stage> = moshi.adapter(Stage::class.java)

    @TypeConverter
    fun stringToStage(string: String): Stage = stageAdapter.fromJson(string) ?: Stage.NULL_STAGE

    @TypeConverter
    fun stageToString(stage: Stage): String = stageAdapter.toJson(stage)
}