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

    companion object {
        val NULL_ROCKET = Rocket(
            id = 0,
            rocketName = "",
            firstFlight = "",
            description = "",
            heightInMeters = 0,
            diameterInMeters = 0,
            massInKilograms = 0,
            stages = listOf()
        )
    }
}

