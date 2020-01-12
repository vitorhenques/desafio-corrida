package challange.race.component

import challenge.race.component.RaceComponent
import challenge.race.exception.InvalidLapTimeException
import challenge.race.model.LapInfo
import challenge.race.model.RaceResult
import challenge.race.model.RacerResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RaceComponentTest {

    @Test
    fun `given a valid list of lap info, should return Result of Race`() {
        val lapInfoList = listOf(
            LapInfo("038", "F.MASSA", 1, "1:02.852", 44.275),
            LapInfo("033", "R.BARRICHELLO", 1, "1:04.352", 43.243),
            LapInfo("038", "F.MASSA", 2, "1:30.352", 43.243),
            LapInfo("002", "K.RAIKKONEN", 1, "3:40.352", 30.243),
            LapInfo("033", "R.BARRICHELLO", 2, "1:47.352", 43.243)
        )
        val expectedResult = RaceResult(
            listOf(
                RacerResult("1", "038", "F.MASSA", "2", "2:33.204"),
                RacerResult("2", "033", "R.BARRICHELLO", "2", "2:51.704"),
                RacerResult("N/A", "002", "K.RAIKKONEN", "1", "3:40.352")
            )
        )
        val raceResult = RaceComponent().getRaceResult(lapInfoList)
        assertEquals(expectedResult, raceResult)
    }

    @Test
    fun `given a list of lap info with lap time invalid, should throw InvalidLapTimeException`() {
        val lapInfoList = listOf(
            LapInfo("038", "F.MASSA", 1, "1:02.852", 44.275),
            LapInfo("033", "R.BARRICHELLO", 1, "DSA2", 43.243),
            LapInfo("038", "F.MASSA", 2, "1:30A.352", 43.243),
            LapInfo("002", "K.RAIKKONEN", 1, "3:40.352", 30.243),
            LapInfo("033", "R.BARRICHELLO", 2, "1:47.352", 43.243)
        )

        assertThrows<InvalidLapTimeException> {
            RaceComponent().getRaceResult(lapInfoList)
        }
    }
}