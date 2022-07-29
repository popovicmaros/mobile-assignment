package cz.cvut.popovma1.spacex.repository.model

data class Rocket (
    val id: Int,
    val rocketName: String,
    val firstFlight: String, // TODO Date/Calendar ?
    val description: String,

    val heightInMeters: Int,
    val diameterInMeters: Int,
    val massInKilograms: Int,

    val stages: List<Stage>
) {
    data class Stage (
        val isReusable: Boolean,
        val enginesCnt: Int,
        val tonsOfFuel: Int,
        val burnTimeInSec: Int
    )
}

