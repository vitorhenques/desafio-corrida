package challenge.race.component

import challenge.race.exception.InvalidLapTimeException
import challenge.race.model.LapInfo
import challenge.race.model.RaceResult
import challenge.race.model.RacerResult
import org.springframework.stereotype.Component
import java.time.*
import java.time.format.DateTimeFormatter

@Component
class RaceComponent {

    fun getRaceResult(lapInfo: List<LapInfo>): RaceResult {
        val racerCodes = lapInfo.groupBy { it.racerCode }
        val racerResult = mutableListOf<RacerResult>()
        racerCodes.forEach { (racerCode, racerLapInfo) ->
            racerResult.add(
                RacerResult(
                    getArrivalPosition(lapInfo, racerCode),
                    racerCode,
                    racerLapInfo.first().racerName,
                    racerLapInfo.size.toString(),
                    getTotalTimeOfRace(racerLapInfo)
                )
            )
        }

        return RaceResult(racerResult)
    }

    private fun getArrivalPosition(lapInfo: List<LapInfo>, racerCode: String): String {
        val racerLapInfo = lapInfo.groupBy { it.racerCode }
        if(getLastLapNumber(lapInfo) > racerLapInfo[racerCode]?.size ?: 0) return "N/A"
        val lapTimes = mutableMapOf<String, String>()
        var arrivalPosition = "N/A"
        racerLapInfo.forEach {
            lapTimes[it.key] = getTotalTimeOfRace(it.value)
        }
        lapTimes.toList().sortedBy { it.second }.forEachIndexed { index, value ->
            if (value.first == racerCode) {
                arrivalPosition = (index + 1).toString()
                return@forEachIndexed
            }
        }
        return arrivalPosition
    }

    private fun getLastLapNumber(lapInfo: List<LapInfo>): Int {
        return lapInfo.maxBy { it.lapNumber }?.lapNumber ?: 0
    }

    private fun getTotalTimeOfRace(racerLapInfo: List<LapInfo>): String {
        var milliseconds = 0L
        var seconds = 0L
        var minutes = 0L
        try {
            racerLapInfo.forEach{
                minutes += it.lapTime.split(Regex("[:|.]"))[0].toLong()
                seconds += it.lapTime.split(Regex("[:|.]"))[1].toLong()
                milliseconds += it.lapTime.split(Regex("[:|.]"))[2].toLong()
            }
            minutes += (seconds / 60)
            seconds = (seconds % 60) + (milliseconds / 1000)
            milliseconds %= 1000
        } catch (e: Exception) {
            throw InvalidLapTimeException("It wasn't possible calculate the total time, because the lap time is invalid")
        }

        return "$minutes:${seconds.toString().padStart(2, '0')}." +
                milliseconds.toString().padStart(3, '0')
    }
}