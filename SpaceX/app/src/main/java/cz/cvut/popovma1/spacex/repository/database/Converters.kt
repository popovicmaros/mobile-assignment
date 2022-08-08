package cz.cvut.popovma1.spacex.repository.database

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import cz.cvut.popovma1.spacex.repository.model.Stage

class Converters {
    private val moshi: Moshi = Moshi.Builder().build()

    private val stageAdapter: JsonAdapter<Stage> = moshi.adapter<Stage>(Stage::class.java)

    private val stageListType = Types.newParameterizedType(List::class.java, Stage::class.java)
    private val stageListAdapter: JsonAdapter<List<Stage>> = moshi.adapter(stageListType)

    private val imagesListType = Types.newParameterizedType(List::class.java, String::class.java)
    private val imagesAdapter = moshi.adapter<List<String>>(imagesListType)

    @TypeConverter
    fun stageListToString(stages: List<Stage>): String = stageListAdapter.toJson(stages)
    @TypeConverter
    fun stringToStageList(string: String): List<Stage> = stageListAdapter.fromJson(string) ?: listOf()


    @TypeConverter
    fun stageToString(stage: Stage): String = stageAdapter.toJson(stage)
    @TypeConverter
    fun stringToStage(string: String): Stage = stageAdapter.fromJson(string) ?: Stage.NULL_STAGE

    @TypeConverter
    fun imagesToString(images: List<String>): String = imagesAdapter.toJson(images)
    @TypeConverter
    fun stringToImages(string: String): List<String> = imagesAdapter.fromJson(string) ?: listOf()

}