package challenge.race.exception

import java.lang.RuntimeException

class InvalidLapTimeException(override val message: String?) : RuntimeException(message)