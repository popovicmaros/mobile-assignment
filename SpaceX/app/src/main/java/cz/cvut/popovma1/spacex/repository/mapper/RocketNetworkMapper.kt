package cz.cvut.popovma1.spacex.repository.mapper

import cz.cvut.popovma1.spacex.repository.entity.RocketNetwork
import cz.cvut.popovma1.spacex.repository.model.Rocket

class RocketNetworkMapper {

    fun mapToRocket(rocketNetwork: RocketNetwork): Rocket {
        val emptyRocket = Rocket.NULL_ROCKET
        val emptyStage = Rocket.NULL_STAGE

        with(rocketNetwork) {
            val rocket = emptyRocket.copy(
                id = id,
                rocketId = rocketId,
                rocketName = name,
                firstFlight = DateConverter().convertDateFormat(firstFlight),
                description = description,
                heightInMeters = height.meters.toInt(), // TODO make double
                diameterInMeters = diameter.meters.toInt(),// TODO make double
                massInKilograms = mass.kilograms.toInt(),// TODO make double
                stages = listOf(stage1, stage2).map { stage ->
                    emptyStage.copy(
                        isReusable = stage.isReusable,
                        enginesCnt = stage.enginesCnt,
                        tonsOfFuel = stage.tonsOfFuel.toInt(),
                        burnTimeInSec = stage.burnTimeInSec ?: 0,
                    )
                },
                images = images
            )
            return rocket
        }
    }
}