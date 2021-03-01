package com.apphud.sdk

internal const val JSON_NAME_KEY = "key"
internal const val JSON_NAME_VALUE = "value"
internal const val JSON_NAME_SET_ONCE = "set_once"
internal const val JSON_NAME_KIND = "kind"
internal const val JSON_NAME_INCREMENT = "increment"

internal data class ApphudUserProperty(
    val key: String,
    val value: Any?,
    val increment: Boolean = false,
    val setOnce: Boolean = false,
    val type: String = ""
) {

    fun toJSON(): MutableMap<String, Any?>? {
        if (increment && value == null) {
            return null
        }

        val jsonParamsString: MutableMap<String, Any?> = mutableMapOf(
            JSON_NAME_KEY to key,
            JSON_NAME_VALUE to if (value !is Float || value !is Double) value else value as Double,
            JSON_NAME_SET_ONCE to setOnce
        )
        if (value != null) {
            jsonParamsString[JSON_NAME_KIND] = type
        }
        if (increment) {
            jsonParamsString[JSON_NAME_INCREMENT] = increment
        }
        return jsonParamsString
    }

}
