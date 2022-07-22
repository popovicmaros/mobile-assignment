package cz.cvut.popovma1.spacex

object RocketsSampleData {
    fun getRocketsList(): List<Rocket> = (1..4).map {
        Rocket(
            rocketName = "Falcon $it",
            firstFlight = "$it.1.2022",
            description = "Falcon $it is a two-stage rocket designed and manufactured by SpaceX for " +
                    "the reliable and safe transport of satellites and the Dragon spacecraft into " +
                    "orbit.",
            heightInMeters = it * 10,
            diameterInMeters = it,
            massInKilograms = it * 1000,
            stages = getRocketStages()
        )
    }

    fun getRocket(): Rocket = getRocketsList()[0]

    fun getRocketStages(): List<Rocket.Stage> =
        (1..3).map {
            Rocket.Stage(
                isReusable = (it % 2 == 0),
                enginesCnt = it-1,
                tonsOfFuel = it * 1000,
                burnTimeInSec = it * 100
            )
        }

    fun getRocketPhotos(): List<Int> = listOf(
        R.drawable.rocket_photo1,
        R.drawable.rocket_photo2,
        R.drawable.rocket_photo3,
    )
}