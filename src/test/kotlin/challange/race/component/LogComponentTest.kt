package challange.race.component

import challenge.race.component.LogComponent
import challenge.race.exception.LogFormatException
import challenge.race.model.LapInfo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.FileNotFoundException

class LogComponentTest {

    @Test
    fun `given a valid path log file with race info, should return Laps info list`() {
        val expected = listOf(
            LapInfo("038", "F.MASSA", 1, "1:02.852", 44.275),
            LapInfo("033", "R.BARRICHELLO", 1, "1:04.352", 43.243),
            LapInfo("038", "F.MASSA", 2, "1:30.352", 43.243),
            LapInfo("002", "K.RAIKKONEN", 1, "3:40.352", 30.243),
            LapInfo("033", "R.BARRICHELLO", 2, "1:47.352", 43.243)
        )
        val lapInfoList = LogComponent().getLapInfoOfLog("src/test/resources/raceTest.log")

        assertEquals(expected, lapInfoList)
    }

    @Test
    fun `given a valid path log file with invalid format, should throw LogFormatException`() {
        assertThrows<LogFormatException> {
            LogComponent().getLapInfoOfLog("src/test/resources/invalid.log")
        }
    }

    @Test
    fun `given a invalid path log file , should throw LogFormatException`() {
        assertThrows<FileNotFoundException> {
            LogComponent().getLapInfoOfLog("invalidpath")
        }
    }
}