package cz.cvut.popovma1.spacex.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Stage (
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
    val id: Int = 0,  // TODO remove?

    @Json(name = "isReusable")
    val isReusable: Boolean,

    @Json(name = "enginesCnt")
    val enginesCnt: Int,

    @Json(name = "tonsOfFuel")
    val tonsOfFuel: Int,

    @Json(name = "burnTimeInSec")
    val burnTimeInSec: Int
) {

    companion object {
        val NULL_STAGE = Stage(
            isReusable = false,
            enginesCnt = 0,
            tonsOfFuel = 0,
            burnTimeInSec = 0
        )
    }

}
