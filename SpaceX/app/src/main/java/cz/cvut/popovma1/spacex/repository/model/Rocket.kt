package cz.cvut.popovma1.spacex.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass
import cz.cvut.popovma1.spacex.repository.database.Converters
import org.jetbrains.annotations.NotNull

@Entity
@JsonClass(generateAdapter = true)
data class Rocket (
    @PrimaryKey val id: Int,

    @NotNull val rocketId: String,
    val rocketName: String,
    val firstFlight: String,
    val description: String,

    val heightInMeters: Int,
    val diameterInMeters: Int,
    val massInKilograms: Int,

    @TypeConverters(Converters::class)
    val stages: List<Stage>,

    @TypeConverters(Converters::class)
    val images: List<String>
) {

    companion object {
        val NULL_ROCKET = Rocket(
            id = 0,
            rocketId = "",
            rocketName = "",
            firstFlight = "",
            description = "",
            heightInMeters = 0,
            diameterInMeters = 0,
            massInKilograms = 0,
            stages = listOf(),
            images = listOf()
        )

    }
}

