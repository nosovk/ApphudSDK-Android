package ru.rosbank.mbdg.myapplication

/**
 * This class will contain some utils, more will be added in the future.
 */
internal object ApphudUtils {

    var logging: Boolean = false
        private set

    /**
     * Enable console logging.
     */
    fun enableDebugLogs() {
        logging = true
    }
}