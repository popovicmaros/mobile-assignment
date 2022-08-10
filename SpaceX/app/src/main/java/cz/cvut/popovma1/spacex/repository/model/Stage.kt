package cz.cvut.popovma1.spacex.repository.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stage(

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
