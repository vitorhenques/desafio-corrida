package challenge.race

import challenge.race.component.LogComponent
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RaceChallengeApplication

fun main(args: Array<String>) {
	runApplication<RaceChallengeApplication>(*args)
	val fileComponent = LogComponent()
	val lapInfo = fileComponent.getLapInfoOfLog("src/main/resources/race.log")
}
