package cz.cvut.popovma1.spacex.repository.sampledata

import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.Stage

object RocketsSampleData {
    fun getRocketsList(): List<Rocket> = (1..20).map {
        Rocket(
            id = it,
            rocketId = "falcon$it",
            rocketName = "Falcon $it",
            firstFlight = "$it.1.2022",
            description = "Falcon $it is a two-stage rocket designed and manufactured by SpaceX for " +
                    "the reliable and safe transport of satellites and the Dragon spacecraft into " +
                    "orbit.",
            heightInMeters = it * 10,
            diameterInMeters = it,
            massInKilograms = it * 1000,
            stages = getRocketStages(),
            images = getRocketImages()
        )
    }

    fun getRocketImages(): List<String> = listOf(
        "https://imgur.com/DaCfMsj.jpg",
        "https://imgur.com/azYafd8.jpg"
    )

    fun getRocket(id: Int = 1): Rocket {
        val idxOffset = 1
        return getRocketsList()[id - idxOffset]
    }

    fun getRocketStages(): List<Stage> =
        (1..3).map {
            Stage(
                isReusable = (it % 2 == 0),
                enginesCnt = it-1,
                tonsOfFuel = it * 1000,
                burnTimeInSec = it * 100
            )
        }

}