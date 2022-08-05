package cz.cvut.popovma1.spacex.repository.database

import cz.cvut.popovma1.spacex.repository.model.Stage
import org.junit.Test

import org.junit.Assert.*

internal class ConvertersTest {

    @Test
    fun stringToStage() {
        val c = Converters()
        val stage = Stage.NULL_STAGE
        assertEquals(stage, c.stringToStage(c.stageToString(stage)))
    }

    @Test
    fun stageToString() {
    }
}