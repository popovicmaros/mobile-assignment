package cz.cvut.popovma1.spacex.repository.mapper

import cz.cvut.popovma1.spacex.repository.entity.RocketNetwork
import cz.cvut.popovma1.spacex.repository.model.Rocket
import cz.cvut.popovma1.spacex.repository.model.Stage

class RocketNetworkMapperImpl : RocketNetworkMapper {

    override fun mapToRocket(rocketNetwork: RocketNetwork): Rocket {

        with(rocketNetwork) {
            val rocket = Rocket(
                id = id,
                rocketId = rocketId,
                rocketName = name,
                firstFlight = DateConverter().convertDateFormat(firstFlight),
                description = description,
                heightInMeters = height.meters.toInt(),
                diameterInMeters = diameter.meters.toInt(),
                massInKilograms = mass.kilograms.toInt() / 1000,
                stages = listOf(stage1, stage2).map { stage ->
                    Stage(
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
