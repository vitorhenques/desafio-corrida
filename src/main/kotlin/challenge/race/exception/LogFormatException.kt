package challenge.race.exception

import java.lang.RuntimeException

class LogFormatException(override val message: String?) : RuntimeException(message)