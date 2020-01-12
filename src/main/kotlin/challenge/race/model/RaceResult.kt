package challenge.race.model

data class RaceResult(
    val result: List<RacerResult>
)

data class RacerResult(
    val arrivalPosition: String,
    val racerCode: String,
    val racerName: String,
    val numberOfCompletedLaps: String,
    val totalTimeOfRace: String
)