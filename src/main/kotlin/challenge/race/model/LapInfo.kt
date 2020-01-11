package challenge.race.model

data class LapInfo(
    val racerCode: String,
    val racerName: String,
    val lapNumber: Int,
    val lapTime: String,
    val averageSpeed: Double
)