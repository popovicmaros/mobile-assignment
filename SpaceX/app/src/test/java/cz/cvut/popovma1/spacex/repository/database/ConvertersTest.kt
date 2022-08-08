package cz.cvut.popovma1.spacex.repository.database

import cz.cvut.popovma1.spacex.repository.model.Stage
import cz.cvut.popovma1.spacex.repository.sampledata.RocketsSampleData
import org.junit.Test

import org.junit.Assert.*
import quanti.com.kotlinlog.Log

internal class ConvertersTest {

    @Test
    fun stringToStage() {
        val c = Converters()
        val stage = Stage.NULL_STAGE
        assertEquals(stage, c.stringToStage(c.stageToString(stage)))
    }

    @Test
    fun stringToStageList() {
        val c = Converters()
        val stages = RocketsSampleData.getRocketStages()
        println(c.stageListToString(stages))

        assertEquals(stages, c.stringToStageList(c.stageListToString(stages)))
    }

    @Test
    fun stringToImages() {
        val c = Converters()
        val images = RocketsSampleData.getRocketImages()
        assertEquals(images, c.stringToImages(c.imagesToString(images)))
    }

}