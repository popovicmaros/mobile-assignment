package cz.cvut.popovma1.spacex.repository.mapper

import cz.cvut.popovma1.spacex.repository.entity.RocketNetwork
import cz.cvut.popovma1.spacex.repository.model.Rocket

interface RocketNetworkMapper {
    fun mapToRocket(rocketNetwork: RocketNetwork): Rocket
}
