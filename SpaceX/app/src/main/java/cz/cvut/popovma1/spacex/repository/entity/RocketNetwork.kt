package cz.cvut.popovma1.spacex.repository.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RocketNetwork(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "rocket_name") val name: String = "",
    @Json(name = "first_flight") val firstFlight: String = "",

    @Json(name = "height") val height: Height = Height(),
    @Json(name = "diameter") val diameter: Diameter = Diameter(),
    @Json(name = "mass") val mass: Mass = Mass(),

    @Json(name = "description") val description: String = "",

    @Json(name = "first_stage") val stage1: StageNetwork = StageNetwork(),
    @Json(name = "second_stage") val stage2: StageNetwork  = StageNetwork()
) {

    @JsonClass(generateAdapter = true)
    data class Height(
        @Json(name = "meters") val meters: Double = 0.0,
    )

    @JsonClass(generateAdapter = true)
    data class Diameter(
        @Json(name = "meters") val meters: Double = 0.0
    )

    @JsonClass(generateAdapter = true)
    data class Mass(
        @Json(name = "kg") val kilograms: Double = 0.0
    )

    @JsonClass(generateAdapter = true)
    data class StageNetwork (
        @Json(name = "reusable") val isReusable: Boolean = false,
        @Json(name = "engines") val enginesCnt: Int = 0,
        @Json(name = "fuel_amount_tons") val tonsOfFuel: Double = 0.0,
        @Json(name = "burn_time_sec") val burnTimeInSec: Int? = 0
    )

}

