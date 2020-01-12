package challenge.race.component

import challenge.race.exception.LogFormatException
import challenge.race.model.LapInfo
import org.springframework.stereotype.Component
import java.io.File

@Component
class LogComponent {

    fun getLapInfoOfLog(logPath: String): List<LapInfo> {
        return formatLog(readFile(logPath))
    }

    private fun readFile(filePath: String): List<String> {
        val bufferedReader = File(filePath).bufferedReader()
        return bufferedReader.use{ it.readLines() }
    }

    private fun formatLog(logLines: List<String>): List<LapInfo>  {
        val lapList = mutableListOf<LapInfo>()
        try {
            logLines.forEachIndexed { index, it ->
                if (index == 0) return@forEachIndexed
                val splitString = it.replace(Regex("\\s{2,}"), "|").split("|")
                val (code, name) = splitString[1].split(" – ")
                lapList.add(
                    LapInfo(
                        code,
                        name,
                        splitString[2].toInt(),
                        splitString[3],
                        splitString[4].replace(",", ".").toDouble()
                    )
                )
            }
        } catch (e: Exception){
          throw LogFormatException("Log cannot be formatted, because it content doesn't have correct lap information")
        }

        return lapList
    }
}