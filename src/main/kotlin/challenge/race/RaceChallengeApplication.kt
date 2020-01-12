package challenge.race

import challenge.race.component.LogComponent
import challenge.race.component.RaceComponent
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@SpringBootApplication
class RaceChallengeApplication

fun main(args: Array<String>) {
	runApplication<RaceChallengeApplication>(*args)
	val fileComponent = LogComponent()
	val raceComponent = RaceComponent()
	val lapInfo = fileComponent.getLapInfoOfLog("src/main/resources/race.log")
	val raceResult = raceComponent.getRaceResult(lapInfo)
	println("Race Result!")
	raceResult.result.sortedBy { it.arrivalPosition }.forEach{ println(it) }
	exitProcess(0)
}
